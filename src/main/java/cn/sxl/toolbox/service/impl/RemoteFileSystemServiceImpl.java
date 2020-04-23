package cn.sxl.toolbox.service.impl;

import cn.sxl.toolbox.entity.SftpConfig;
import cn.sxl.toolbox.service.RemoteFileSystemService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jcraft.jsch.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * @author sxl
 * @since 2020/4/21 下午9:31
 */
@Service
@Log4j2
public class RemoteFileSystemServiceImpl implements RemoteFileSystemService {
    private final SftpConfig config;

    /**
     * 设置第一次登陆的时候提示，可选值：(ask | yes | no)
     */
    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";

    public RemoteFileSystemServiceImpl(SftpConfig config) {
        this.config = config;
    }

    /**
     * 创建 SFTP 连接
     *
     * @return sftp channel
     * @throws Exception connect error
     */
    private ChannelSftp createSftp() throws Exception {
        JSch jsch = new JSch();
        log.info("Try to connect sftp[" + config.getUsername() + "@" + config.getHost() + "]");

        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort());
        session.setPassword(config.getPassword());
        session.connect(config.getSessionConnectTimeout());

        log.info("Session connected to {}.", config.getHost());

        Channel channel = session.openChannel(config.getProtocol());
        channel.connect(config.getChannelConnectedTimeout());

        log.info("Channel created to {}.", config.getHost());

        return (ChannelSftp) channel;
    }

    /**
     * 加密秘钥方式登陆
     *
     * @return sftp channel
     */
    private ChannelSftp connectByKey() throws Exception {
        JSch jsch = new JSch();

        // 设置密钥和密码 ,支持密钥的方式登陆
        if (Strings.isNullOrEmpty(config.getPrivateKey())) {
            if (Strings.isNullOrEmpty(config.getPassphrase())) {
                // 设置带口令的密钥
                jsch.addIdentity(config.getPrivateKey(), config.getPassphrase());
            } else {
                // 设置不带口令的密钥
                jsch.addIdentity(config.getPrivateKey());
            }
        }
        log.info("Try to connect sftp[" + config.getUsername() + "@" + config.getHost() + "], use private key[" + config.getPrivateKey()
                + "]");

        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort());
        // 设置登陆超时时间
        session.connect(config.getSessionConnectTimeout());
        log.info("Session connected to " + config.getHost() + ".");

        // 创建sftp通信通道
        Channel channel = session.openChannel(config.getProtocol());
        channel.connect(config.getChannelConnectedTimeout());
        log.info("Channel created to " + config.getHost() + ".");
        return (ChannelSftp) channel;
    }

    /**
     * 上传文件
     *
     * @param targetPath  目标路径
     * @param inputStream 输入流
     * @param isKey       是否使用 Key
     * @return 上传文件
     * @throws Exception 上传异常
     */
    @Override
    public boolean uploadFile(String targetPath, InputStream inputStream, Boolean isKey) throws Exception {
        ChannelSftp sftp;

        if (isKey) {
            sftp = this.connectByKey();
        } else {
            sftp = this.createSftp();
        }

        try {
            sftp.cd(config.getRoot());
            log.info("Change path to {}", config.getRoot());

            int index = targetPath.lastIndexOf("/");
            String fileDir = targetPath.substring(0, index);
            String fileName = targetPath.substring(index + 1);
            boolean dirs = this.createDirs(fileDir, sftp);
            if (!dirs) {
                log.error("Remote path error. path:{}", targetPath);
                throw new Exception("Upload File failure");
            }
            sftp.put(inputStream, fileName);
            return true;
        } catch (Exception e) {
            log.error("Upload file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Upload File failure");
        } finally {
            this.disconnect(sftp);
        }
    }

    /**
     * 上传文件
     *
     * @param targetPath 目标路径
     * @param file       文件
     * @param isKey      是否使用 Key
     * @return 上传文件
     * @throws Exception 上传异常
     */
    @Override
    public boolean uploadFile(String targetPath, File file, Boolean isKey) throws Exception {
        return this.uploadFile(targetPath, new FileInputStream(file), isKey);
    }

    /**
     * 下载文件
     *
     * @param targetPath 目标路径
     * @param isKey      是否使用 Key
     * @return 下载文件
     * @throws Exception 下载异常
     */
    @Override
    public File downloadFile(String targetPath, Boolean isKey) throws Exception {
        ChannelSftp sftp;
        OutputStream outputStream = null;

        if (isKey) {
            sftp = this.connectByKey();
        } else {
            sftp = this.createSftp();
        }

        try {
            sftp.cd(config.getRoot());
            log.info("Change path to {}", config.getRoot());

            File file = new File(targetPath.substring(targetPath.lastIndexOf("/") + 1));

            outputStream = new FileOutputStream(file);
            sftp.get(targetPath, outputStream);
            log.info("Download file success. TargetPath: {}", targetPath);
            return file;
        } catch (Exception e) {
            log.error("Download file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Download File failure");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            this.disconnect(sftp);
        }
    }

    /**
     * 删除文件
     *
     * @param targetPath 目标路径
     * @param isKey      是否使用 Key
     * @return 删除结果
     * @throws Exception 删除异常
     */
    @Override
    public boolean deleteFile(String targetPath, Boolean isKey) throws Exception {
        ChannelSftp sftp = null;
        try {
            if (isKey) {
                sftp = this.connectByKey();
            } else {
                sftp = this.createSftp();
            }

            sftp.cd(config.getRoot());
            sftp.rm(targetPath);
            return true;
        } catch (Exception e) {
            log.error("Delete file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Delete File failure");
        } finally {
            this.disconnect(sftp);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<String> listFiles(String targetPath, Boolean isKey) throws Exception {
        ChannelSftp sftp;
        List<String> fileNameList = Lists.newArrayList();

        if (isKey) {
            sftp = this.connectByKey();
        } else {
            sftp = this.createSftp();
        }

        try {
            sftp.cd(config.getRoot());
            Vector vector = sftp.ls(targetPath);

            for (Object o : vector) {
                if (o instanceof ChannelSftp.LsEntry) {
                    ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) o;
                    String fileName = lsEntry.getFilename();
                    if (".".equals(fileName) || "..".equals(fileName)) {
                        continue;
                    }
                    fileNameList.add(fileName);
                }
            }
        } catch (SftpException e) {
            log.error("list failure. TargetPath: {}", targetPath, e);
        } finally {
            this.disconnect(sftp);
        }

        return fileNameList;
    }

    /**
     * 创建一级或者多级目录
     *
     * @param dirPath 路径
     * @param sftp    安全链接
     * @return 创建结果
     */
    private boolean createDirs(String dirPath, ChannelSftp sftp) {
        if (dirPath != null && !dirPath.isEmpty()
                && sftp != null) {
            String[] dirs = Arrays.stream(dirPath.split("/"))
                    .filter(Strings::isNullOrEmpty)
                    .toArray(String[]::new);

            for (String dir : dirs) {
                try {
                    sftp.cd(dir);
                    log.info("Change directory {}", dir);
                } catch (Exception e) {
                    try {
                        sftp.mkdir(dir);
                        log.info("Create directory {}", dir);
                    } catch (SftpException e1) {
                        log.error("Create directory failure, directory:{}", dir, e1);
                        e1.printStackTrace();
                    }
                    try {
                        sftp.cd(dir);
                        log.info("Change directory {}", dir);
                    } catch (SftpException e1) {
                        log.error("Change directory failure, directory:{}", dir, e1);
                        e1.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 创建session
     *
     * @param jsch     jsch
     * @param host     主机地址
     * @param username 用户名
     * @param port     端口
     * @return 新会话
     * @throws Exception 创建异常
     */
    private Session createSession(JSch jsch, String host, String username, Integer port) throws Exception {
        Session session;

        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }

        if (session == null) {
            throw new Exception(host + " session is null");
        }

        session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, config.getSessionStrictHostKeyChecking());
        return session;
    }

    /**
     * 关闭连接
     *
     * @param sftp 安全链接
     */
    private void disconnect(ChannelSftp sftp) {
        try {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                } else if (sftp.isClosed()) {
                    log.info("sftp is closed already");
                }
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}

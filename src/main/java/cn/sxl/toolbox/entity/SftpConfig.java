package cn.sxl.toolbox.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sxl
 * @since 2020/4/21 下午9:26
 */
@Data
@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sftp.client")
public class SftpConfig {
    private String host;

    private Integer port;

    private String protocol;

    private String username;

    private String password;

    private String root;

    private String privateKey;

    private String passphrase;

    private String sessionStrictHostKeyChecking;

    private Integer sessionConnectTimeout;

    private Integer channelConnectedTimeout;
}

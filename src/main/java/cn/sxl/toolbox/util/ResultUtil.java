package cn.sxl.toolbox.util;

import cn.sxl.toolbox.entity.Host;
import cn.sxl.toolbox.vo.ProgramVO;

import java.util.List;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 05:16
 */

public class ResultUtil {

    public static String prettyResult(List<Host> hostList) {
        StringBuilder prettyResult = new StringBuilder();

        for (Host host : hostList) {
            appendLine(prettyResult, host);
        }

        return prettyResult.toString();
    }

    public static String prettyResult(ProgramVO programVO) {
        StringBuilder prettyResult = new StringBuilder("# ").append(programVO.getProgram().getName());

        for (Host host : programVO.getHostList()) {
            appendLine(prettyResult, host);
        }

        return prettyResult.toString();

    }

    private static void appendLine(StringBuilder sb, Host host) {
        sb.append("\n").append(host.getIp()).append(" ").append(host.getName());
    }
}

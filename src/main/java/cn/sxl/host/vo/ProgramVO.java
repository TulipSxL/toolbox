package cn.sxl.host.vo;

import cn.sxl.host.entity.Host;
import cn.sxl.host.entity.Program;
import lombok.Data;

import java.util.List;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 07:27
 */

@Data
public class ProgramVO {
    private Program program;
    private List<Host> hostList;
}

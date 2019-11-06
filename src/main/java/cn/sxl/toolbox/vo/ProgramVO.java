package cn.sxl.toolbox.vo;

import cn.sxl.toolbox.entity.Host;
import cn.sxl.toolbox.entity.Program;
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

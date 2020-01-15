package cn.sxl.toolbox.vo;

import cn.sxl.toolbox.entity.HsRecord;
import lombok.Data;

import java.util.List;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 12:46
 */

@Data
public class HsRecordVO {

    List<HsRecord> records;
    private String date;
    private Integer changedScore;
}

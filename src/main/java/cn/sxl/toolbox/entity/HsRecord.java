package cn.sxl.toolbox.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 00:23
 */
@Data
@Entity
@Table(name = "hs_record")
public class HsRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer rank;
    private Integer score;
    private Integer increment;
    private Time time;
    private Date date;
}

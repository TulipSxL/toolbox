package cn.sxl.toolbox.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SxL
 * @since 1.6.0
 * 2019-10-22 15:28
 */

@Data
@Entity
@Table(name = "amount")
public class Amount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String year;
    private String month;
    private Double total;
    private Double cost;
    private Double consumption;
    private Double average;
}

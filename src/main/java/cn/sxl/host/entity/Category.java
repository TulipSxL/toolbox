package cn.sxl.host.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SxL
 * @since 1.5.0
 * 2019-10-15 00:09
 */

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String variant;
}

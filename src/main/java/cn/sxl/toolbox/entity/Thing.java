package cn.sxl.toolbox.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SxL
 * @since 1.5.0
 * 2019-10-15 00:02
 */

@Data
@Entity
@Table(name = "thing")
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer categoryId;
}

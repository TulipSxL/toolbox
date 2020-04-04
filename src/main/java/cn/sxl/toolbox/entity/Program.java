package cn.sxl.toolbox.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:29
 */

@Data
@Table(name = "program")
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}

package cn.sxl.toolbox.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 15:06
 */

@Data
@Entity
@Table(name = "hs_hero")
public class HsHero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Boolean state;
}

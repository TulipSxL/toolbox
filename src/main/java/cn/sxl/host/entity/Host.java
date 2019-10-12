package cn.sxl.host.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:18
 */

@Data
@Table(name = "host")
@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ip;
    private String name;
    private Boolean status;
}

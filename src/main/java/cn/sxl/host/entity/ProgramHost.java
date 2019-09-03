package cn.sxl.host.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SxL
 * @since 1.2.0
 * 2019-09-03 15:51
 */

@Data
@Entity
@Table(name = "program_host")
public class ProgramHost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer programId;
    private Integer hostId;

    public ProgramHost(Integer programId, Integer hostId) {
        this.programId = programId;
        this.hostId = hostId;
    }

    public ProgramHost() {
    }
}

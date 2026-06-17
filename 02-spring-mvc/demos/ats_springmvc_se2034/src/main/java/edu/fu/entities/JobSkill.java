package edu.fu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Job_Skill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSkill {

    @EmbeddedId
    private JobSkillId id;

    @ManyToOne (fetch = FetchType.LAZY)
    @MapsId("jobId")
    @JoinColumn(name = "Job_Id")
    private Jobs job;

    @ManyToOne (fetch = FetchType.LAZY)
    @MapsId("skillId")
    @JoinColumn(name = "Skill_Id")
    private Skills skill;
}

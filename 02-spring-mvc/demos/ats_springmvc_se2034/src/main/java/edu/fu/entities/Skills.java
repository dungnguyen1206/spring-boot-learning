package edu.fu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Skills")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skills extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Skill_Name" , nullable = false, columnDefinition = ("NVARCHAR(50)"))
    private String skillName;

    @Column(name = "Category" , nullable = false, columnDefinition = ("NVARCHAR(50)"))
    private String category;

    @OneToMany(mappedBy = "skill")
    private Set<JobSkill> jobSkills;

}

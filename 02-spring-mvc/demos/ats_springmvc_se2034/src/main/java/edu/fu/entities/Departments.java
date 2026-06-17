package edu.fu.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Departments")
@Builder
@Getter
@Setter
@AllArgsConstructor
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findDepartmentByName", query = "Select d from Departments  d left join fetch d.jobsList j where d.departmentName = :departmentName"),
        @org.hibernate.annotations.NamedQuery(name = "findAll", query = "select d from Departments d")})
public class Departments extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Department_Name",unique = true, columnDefinition = "NVARCHAR(50)")
    private String departmentName;

    private String description;

    private static String university;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Users> usersList = new ArrayList<>();
    public void addUser(Users user){
        usersList.add(user);
        user.setDepartment(this);
    }

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Jobs> jobsList = new HashSet<>();
    public void addJob(Jobs job){
        jobsList.add(job);
        job.setDepartment(this);
    }

    public Departments() {
    }

    public Departments( String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Departments{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", description='" + description + '\'' +
                '}' + super.toString();
    }
}

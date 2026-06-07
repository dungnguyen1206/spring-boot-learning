package edu.fu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs", uniqueConstraints = {@UniqueConstraint(name = "UXI_TITLE", columnNames = {"title"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Jobs extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @Column(name = "Title" , nullable = false, columnDefinition = "VARCHAR(500)")
    private String title;

    @Column(name = "Location", nullable = false, columnDefinition = "VARCHAR(500)")
    private String location;

    @Column(name = "Salary_Min", nullable = false, columnDefinition = "NUMERIC(15,2)")
    private double salary_min;

    @Column(name = "Salary_Max", nullable = false, columnDefinition = "NUMERIC(15,2)")
    private double salary_max;

    @Column(name ="Status", nullable = false, columnDefinition = "VARCHAR(50)")
    private String status;

    @Column(name ="Utm_Source", nullable = false, columnDefinition = "VARCHAR(150)")
    private String utm_source;

    @Column(name ="Utm_Medium", nullable = false, columnDefinition = "VARCHAR(150)")
    private String utm_medium;

    @Column(name = "Deadline", nullable = false)
    private Instant deadline;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "Department_Id", referencedColumnName ="Id" )
    private Departments department;

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }



}

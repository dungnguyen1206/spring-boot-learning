package edu.fu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
public class Users extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "User_Name", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(name = "Email", unique = true ,nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name = "Password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "Phone", nullable = false, columnDefinition = "VARCHAR(30)")
    private String phone;

    @Column(name = "Role", nullable = false, columnDefinition = "VARCHAR(50)")
    private String role;

    @Column(name = "Status", nullable = false, columnDefinition = "VARCHAR(50)")
    private String status;

    @Column(name="Sso_Provider_Id")
    private String ssoProviderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Department_Id", referencedColumnName = "Id")
    private Departments department;

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Users() {
    }

    public Users(String name, String email, String password, String phone, String role, String status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\''+'}' + super.toString();
    }

}

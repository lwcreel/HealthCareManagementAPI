package com.example.healthcaremanagement.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table", uniqueConstraints = { @UniqueConstraint(columnNames = {"username", "email"}) })
public class User {

    @Getter @Setter @GeneratedValue(strategy = GenerationType.IDENTITY) @Id private long id;
    @Getter @Setter private long phoneNumber;
    @Getter @Setter private long funds = 1000;
    @Getter @Setter private boolean isAdmin;
    @Getter @Setter private String dob;

    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter @NotBlank @Size(max = 20) private String username;
    @Getter @Setter @Email @Size(max=50) private String email;
    @Getter @Setter @Size(max=120) private String password;

    @ManyToMany(targetEntity = Medicine.class)
    @JoinTable(name = "user_medicines", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    @Getter @Setter private List<Medicine> medCart;


    @OneToMany(targetEntity = Report.class)
    @JoinTable(name = "user_reports", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "report_id", referencedColumnName = "id"))
    @Getter @Setter private List<Report> reportList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter @Setter private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password, String firstName, String lastName, Long phoneNumber)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(String username, String email, String password, String firstName, String lastName, Long phoneNumber, Set<Role> roles)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }


}

package com.example.healthcaremanagement.User;

import com.example.healthcaremanagement.Medicine.Medicine;
import com.example.healthcaremanagement.Report.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User implements UserDetails {

    @Getter @Setter @GeneratedValue @Id private long id;
    @Getter @Setter private long phoneNumber;
    @Getter @Setter private long funds = 1000;
    @Getter @Setter private boolean isAdmin;
    @Getter @Setter private String dob;

    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String username;
    @Getter @Setter private String email;
    @Getter @Setter private String password;

    @ManyToMany(targetEntity = Medicine.class)
    @JoinTable(name = "user_medicines", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    @Getter @Setter private List<Medicine> medCart;


    @OneToMany(targetEntity = Report.class)
    @JoinTable(name = "user_reports", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "report_id", referencedColumnName = "id"))
    @Getter @Setter private List<Report> reportList;

    @Override
    public Set<GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        if (isAdmin) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

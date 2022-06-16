package com.example.healthcaremanagement.Medicine;

import com.example.healthcaremanagement.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {

    @Getter @Setter @GeneratedValue @Id private long id;
    @Getter @Setter private long quantity;
    @Getter @Setter private String medName;
    @Getter @Setter private String companyName;
    @Getter @Setter private String uses;
    @Getter @Setter private String expDate;
    @Getter @Setter private int costFactor;

    @ManyToMany(targetEntity = User.class)
    @Getter @Setter private List<User> userList;
}

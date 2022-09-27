package com.example.healthcaremanagement.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @JsonProperty("id") @Getter @Setter @GeneratedValue @Id long id;
    @JsonProperty("info") @Getter @Setter String info;
    @JsonProperty("customerId") @Getter @Setter long customerId;
}



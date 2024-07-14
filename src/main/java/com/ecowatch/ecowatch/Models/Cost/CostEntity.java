package com.ecowatch.ecowatch.Models.Cost;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "costs", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private String currency;
    private double amount;
    private LocalDate dateAdded;
}

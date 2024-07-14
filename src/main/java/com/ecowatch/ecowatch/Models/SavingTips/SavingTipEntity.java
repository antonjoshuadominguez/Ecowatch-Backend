package com.ecowatch.ecowatch.Models.SavingTips;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "saving_tips", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavingTipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String tip;
}

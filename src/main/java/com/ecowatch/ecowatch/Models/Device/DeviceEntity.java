package com.ecowatch.ecowatch.Models.Device;

import java.util.Date;

import com.ecowatch.ecowatch.Models.Enums.DeviceType;
import com.ecowatch.ecowatch.Models.User.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "devices", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long device_id;
    private String device_name;
    private DeviceType type;
    private Date installation_date;
    private double voltage;
    private double watts;

    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false)
    private UserEntity added_by;
}

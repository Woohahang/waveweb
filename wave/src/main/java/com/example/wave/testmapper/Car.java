package com.example.wave.testmapper;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Car {
	@Id
    private Long id;
    private String model;
    private String brand;
}

package com.mahdi.website.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "c_active")
    private Boolean active;
    @Column(name = "c_manual_id")
    private String manualId;
    @Version
    @Column(name = "c_version")
    private Integer version;
}

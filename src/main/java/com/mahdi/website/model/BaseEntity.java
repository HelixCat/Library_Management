package com.mahdi.website.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    @Column(name = "c_version")
    private Integer version;
}

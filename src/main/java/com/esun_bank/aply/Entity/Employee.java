package com.esun_bank.aply.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Employee")
public class Employee{
    @Id
    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "FLOOR_SEAT_SEQ")
    private SeatingChart seatingChart; 
}
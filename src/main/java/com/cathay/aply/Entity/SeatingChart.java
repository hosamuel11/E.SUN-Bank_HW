package com.cathay.aply.Entity;
import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name = "SeatingChart")
public class SeatingChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLOOR_SEAT_SEQ")
    private Long floorSeatSeq;

    @Column(name = "FLOOR_NO")
    private int floorNo;

    @Column(name = "SEAT_NO")
    private int seatNo;

}

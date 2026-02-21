package com.ict.workshop.fitness.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity // บอกว่าคลาสนี้คือตารางใน Database
@Table(name = "bmr_records")   // กำหนดชื่อตาราง หากไม่ระบุตารางจะใช้ชื่อเดียวกับชื่อคลาส
@Data
public class BmrRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ให้ DB Gen ID อัตโนมัติ (1, 2,...)
    private int id;  // Auto running
    private double weight;
    private double height;
    private int age;
    private String gender;  // male, female
    private double bmr; // ค่า BMR ที่คำนวณได้
    private LocalDateTime createdAt; // เก็บเวลาที่บันทึก
}
package com.ict.workshop.fitness.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BmrRequest {
    @Schema(description = "น้ำหนักตัว (กก.)", example = "70.5")
    private double weight; // กิโลกรัม
    @Schema(description = "ส่วนสูง (ซม.)", example = "175")
    private double height; // เซนติเมตร
    @Schema(description = "อายุ (ปี)", example = "25")
    private int age;
    @Schema(description = "เพศ (male/female)", example = "male")
    private String gender; // "male" หรือ "female"
}

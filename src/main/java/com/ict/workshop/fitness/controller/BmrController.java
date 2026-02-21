package com.ict.workshop.fitness.controller;

import com.ict.workshop.fitness.dto.BmrRequest;
import com.ict.workshop.fitness.dto.BmrResponse;
import com.ict.workshop.fitness.model.BmrRecord;
import com.ict.workshop.fitness.service.BmrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/fitness")
@Tag(name = "BMR Calculator", description = "จัดการคำนวณค่า BMR และบันทึกประวัติค่า BMR")
public class BmrController {

    private final BmrService bmrService;

    // ใช้ Constructor Injection (IoC/DI Concept)
    public BmrController(BmrService bmrService) {
        this.bmrService = bmrService;
    }

    @GetMapping("/info")
    public Map<String, Object> getBmrInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("title", "Basal Metabolic Rate (BMR)");
        info.put("description", "อัตราการความต้องการเผาผลาญของร่างกายในชีวิตประจำวันพื้นฐาน");
        info.put("formula_used", "Mifflin-St Jeor Equation");
        info.put("note", "ค่าที่ได้เป็นเพียงการประมาณการเบื้องต้น");
        return info;
    }

    @Operation(summary = "คำนวณค่า BMR และบันทึกข้อมูล", description = "รับค่า น้ำหนัก ส่วนสูง อายุ เพศ เพื่อคำนวณและเก็บลงฐานข้อมูล")
    @PostMapping("/calculate-bmr")
    public BmrResponse getBmr(@RequestBody BmrRequest request) {
        double result = bmrService.calculateBmr(request);

        BmrResponse response = new BmrResponse();
        response.setBmr(result);
        response.setMessage("คำนวณค่า BMR สำเร็จ");

        return response;
    }
    @Operation(summary = "ดึงประวัติการคำนวณค่า BMR ทั้งหมด")
    @GetMapping("/history")
    public List<BmrRecord> getAllHistory() {
        return bmrService.getAllHistory(); // เพิ่ม Endpoint สำหรับดูประวัติการคำนวณ
    }

    // Get History by ID
    // ตัวอย่าง URL: http://localhost:8080/api/v1/fitness/history/1
    @GetMapping("/history/{id}")
    public BmrRecord getHistoryById(@PathVariable Integer id) {
        return bmrService.getHistoryById(id);
    }

    // Delete History by ID
    // ตัวอย่าง URL: http://localhost:8080/api/v1/fitness/delete/1
    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteHistory(@PathVariable Integer id) {
        bmrService.deleteHistory(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "ลบข้อมูลไอดีที่ " + id + " สำเร็จแล้ว");
        return response;
    }
}


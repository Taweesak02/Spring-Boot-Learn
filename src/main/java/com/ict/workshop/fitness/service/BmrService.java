package com.ict.workshop.fitness.service;

import com.ict.workshop.fitness.dto.BmrRequest;
import com.ict.workshop.fitness.model.BmrRecord;
import com.ict.workshop.fitness.repository.BmrRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BmrService {
    private final BmrRepository bmrRepository;

    public BmrService(BmrRepository bmrRepository) {
        this.bmrRepository = bmrRepository;
    }

    public double calculateBmr(BmrRequest request) {
        double bmr;
        if ("male".equalsIgnoreCase(request.getGender())) {
            bmr = (10 * request.getWeight()) + (6.25 * request.getHeight()) - (5 * request.getAge()) + 5;
        } else {
            bmr = (10 * request.getWeight()) + (6.25 * request.getHeight()) - (5 * request.getAge()) - 161;
        }
        // 2. สร้าง Entity เพื่อบันทึก
        BmrRecord record = new BmrRecord();
        record.setWeight(request.getWeight());
        record.setHeight(request.getHeight());
        record.setAge(request.getAge());
        record.setGender(request.getGender());
        record.setBmr(bmr);
        record.setCreatedAt(LocalDateTime.now());
        // 3. สั่งบันทึกลง Database
        bmrRepository.save(record);

        return bmr;
    }

    public List<BmrRecord> getAllHistory() {
        return bmrRepository.findAll(); // ดึงประวัติทั้งหมดออกมา
    }

    public List<BmrRecord> getAllByGender(String gender) {
        return bmrRepository.findByGender(gender); // ดึงประวัติทั้งหมดออกมา
    }

    public BmrRecord getHistoryById(Integer id) {
        return bmrRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ไม่พบข้อมูลประวัติไอดีที่: " + id));
    }

    public void deleteHistory(Integer id){
        if(!bmrRepository.existsById(id)){
            throw new RuntimeException("ไม่สามารถลบได้ เนื่องจากไม่พบไอดี: " + id);
        }
        bmrRepository.deleteById(id);
    }
}

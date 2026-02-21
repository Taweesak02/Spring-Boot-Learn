package com.ict.workshop.fitness.exception;

import com.ict.workshop.fitness.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice // บอก Spring ว่าคลาสนี้ใช้ดักจับ Exception ทั่วทั้งโปรเจกต์
public class GlobalExceptionHandler {

    // ดักจับเฉพาะ RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Requesting Error",
                ex.getMessage() // ข้อความที่เรา throw ออกมาจาก Service
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // ดักจับ Exception อื่นๆ ที่เราไม่ได้ระบุไว้ (กรณีโปรแกรมพังไม่คาดคิด)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "เกิดข้อผิดพลาดภายในระบบ กรุณาติดต่อผู้ดูแล"
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

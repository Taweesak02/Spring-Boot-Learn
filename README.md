# BMR Calculator API

## 📋 คำอธิบายโครงการ

แอปพลิเคชัน Spring Boot ที่ใช้สำหรับคำนวณ **BMR (Basal Metabolic Rate)** หรือ "ปริมาณแคลอรี่พื้นฐาน" ซึ่งเป็นจำนวนแคลอรี่ที่ร่างกายต้องการเพื่อรักษาการทำงานขั้นพื้นฐาน

## 🔧 เทคโนโลยีที่ใช้

- **Java** - ภาษาโปรแกรมมิ่ง
- **Spring Boot** - Framework หลัก
- **Spring Data JPA** - ORM และ Data Access
- **Maven** - Build Tool

## 📦 ส่วนประกอบหลัก

### Controller (BmrController)
- จัดการ HTTP Requests และ Responses
- Endpoints สำหรับเรียกใช้บริการ BMR

### Service (BmrService)
- ตรรมานะของ Business Logic
- คำนวณค่า BMR ตามสูตรทางการแพทย์

### Repository (BmrRepository)
- ติดต่อกับฐานข้อมูล
- ดำเนินการ CRUD Operations

### Model (BmrRecord)
- Entity Class สำหรับเก็บข้อมูล BMR

### DTO (Data Transfer Objects)
- **BmrRequest**: รับข้อมูลจากผู้ใช้
- **BmrResponse**: ส่งผลลัพธ์ให้ผู้ใช้
- **ErrorResponse**: ส่งข้อความข้อผิดพลาด

### Exception Handler (GlobalExceptionHandler)
- จัดการข้อผิดพลาดในระบบ
- ส่งคืน HTTP Response ที่เหมาะสม

## 🚀 วิธีการรัน

### ข้อกำหนดเบื้องต้น
- JDK 11 ขึ้นไป
- Maven 3.6 ขึ้นไป

### ขั้นตอนการติดตั้งและรัน

```bash
# Clone หรือ Download โครงการ
cd d:/testingGround/Spring-Boot-Learn

# Compile โครงการ
./mvnw clean compile

# Build และ Package
./mvnw clean package

# รัน Application
./mvnw spring-boot:run

# หรือรัน JAR โดยตรง
java -jar target/workshop-*.jar
```

## � ฐานข้อมูล

โครงการใช้ **H2 Database** (In-Memory Database) สำหรับการพัฒนา

**Configuration:**
```properties
spring.datasource.url=jdbc:h2:mem:bmrdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**H2 Console Access:**
- URL: `http://localhost:8080/h2-console`
- Username: `sa`
- Password: (เว้นไว้)

## 📚 Swagger API Documentation

View and test API endpoints using Swagger UI:

**Swagger UI:**
- URL: `http://localhost:8080/api-doc.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 🧪 การทดสอบ

```bash
# รัน Unit Tests
./mvnw test

# รัน Test ที่ระบุ
./mvnw test -Dtest=WorkshopApplicationTests
```

## 📚 API Endpoints

### ตัวอย่าง Endpoints (คำนวณ BMR)

```
POST /api/bmr/calculate
Content-Type: application/json

{
  "weight": 70,        // น้ำหนัก (kg)
  "height": 175,       // ส่วนสูง (cm)
  "age": 30,           // อายุ (ปี)
  "gender": "MALE"     // เพศ (MALE/FEMALE)
}
```

**Response:**
```json
{
  "bmr": 1700,
  "message": "คำนวณ BMR สำเร็จ"
}
```

## 🏗️ โครงสร้างโครงการ

```
src/main/java/com/ict/workshop/
├── WorkshopApplication.java          # Main Application Class
└── fitness/
    ├── controller/
    │   └── BmrController.java        # REST API Controller
    ├── service/
    │   └── BmrService.java           # Business Logic Layer
    ├── repository/
    │   └── BmrRepository.java        # Data Access Layer
    ├── model/
    │   └── BmrRecord.java            # Entity Model
    ├── dto/
    │   ├── BmrRequest.java           # Request DTO
    │   ├── BmrResponse.java          # Response DTO
    │   └── ErrorResponse.java        # Error Response DTO
    └── exception/
        └── GlobalExceptionHandler.java # Exception Handling
```

---

**สร้างเมื่อ**: 2026-08-29  
**เวอร์ชัน**: 1.0

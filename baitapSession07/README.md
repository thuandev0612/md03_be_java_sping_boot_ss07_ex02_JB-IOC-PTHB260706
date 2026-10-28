# Session07 - Bài 1 và 2: Đăng ký ứng viên và chuẩn hóa lỗi

Java 21, Spring Boot, PostgreSQL. Chia các package model, dto, repository, service, controller, response và exception.

## Chạy ứng dụng

1. Mở project bằng IntelliJ, chọn JDK 21.
2. Chạy `BaitapSession07Application`.
3. Mở Postman, chọn POST → Body → raw → JSON.

Database trên máy hiện tại đã được chuẩn bị: `recruit_pro_session07`. Mật khẩu nằm ngoài project tại `../.local-config/baitapSession07.properties`.

Máy khác cần tạo database bằng `CREATE DATABASE recruit_pro_session07;`, rồi đặt biến môi trường `DB_USERNAME`, `DB_PASSWORD`. Có thể đổi địa chỉ kết nối bằng `DB_URL`. Hibernate tự tạo bảng `candidates` nhờ `ddl-auto=update`.

## Đăng ký ứng viên

```text
POST http://localhost:8080/api/candidates
Content-Type: application/json
```

```json
{
  "fullName": "Nguyen Van An",
  "email": "an@example.com",
  "age": 18,
  "yearsOfExperience": 0
}
```

Thành công trả **201 Created** và JSON ứng viên gồm id tự tăng, fullName, email, age, yearsOfExperience. Đường dẫn `/api/v1/candidates` trong ảnh đề bài cũng sử dụng được.

`CandidateCreateDTO` sử dụng `@NotBlank`, `@Size(min=5, max=50)` cho tên; `@NotBlank`, `@Email` cho email; `@NotNull`, `@Min(18)` cho tuổi; `@NotNull`, `@Min(0)` cho kinh nghiệm. Tuổi và kinh nghiệm là số nguyên, bắt buộc truyền.

Controller nhận `@RequestBody` và kích hoạt kiểm tra bằng `@Valid`. Dữ liệu đúng mới chuyển xuống Service → JpaRepository để lưu vào PostgreSQL. Dữ liệu sai trả **400 Bad Request**, JSON `ApiResponse` chứa lỗi theo từng trường, không thêm bản ghi.

Ví dụ dữ liệu sai:

```json
{
  "fullName": "An",
  "email": "sai-email",
  "age": 17,
  "yearsOfExperience": -1
}
```

## Phản hồi lỗi chuẩn (Bài 2)

`ApiResponse<T>` có ba trường: status (String), message (String), data (T).

`GlobalExceptionHandler` dùng `@RestControllerAdvice` và `@ExceptionHandler` để bắt `MethodArgumentNotValidException`. Handler lấy `field` và `defaultMessage` từ `BindingResult`, đưa vào `Map<String, String>` trong data. Nếu một trường có nhiều lỗi, giữ thông báo đầu tiên cho trường đó.

Với JSON sai ở trên, API trả HTTP 400:

```json
{
  "status": "400 BAD_REQUEST",
  "message": "Dữ liệu không hợp lệ!",
  "data": {
    "fullName": "Tên ứng viên phải có từ 5 đến 50 ký tự",
    "email": "Email không đúng định dạng",
    "age": "Ứng viên phải từ 18 tuổi",
    "yearsOfExperience": "Số năm kinh nghiệm không được âm"
  }
}
```

JSON bị lỗi cú pháp, thiếu body hoặc sai kiểu số nguyên trả HTTP 400 với cùng wrapper, message `Dữ liệu JSON không hợp lệ!`, data là null. Các lỗi HTTP khác như sai URL hoặc sai phương thức cũng dùng wrapper và giữ mã HTTP tương ứng.

Đăng ký hợp lệ vẫn trả HTTP 201 và thông tin ứng viên như Bài 1.

Kiểm tra dữ liệu đã lưu bằng pgAdmin:

```sql
SELECT id, full_name, email, age, years_of_experience
FROM candidates ORDER BY id;
```

File build/cache được tạo ngoài thư mục project khi kiểm tra để giữ số file nộp GitHub thấp.

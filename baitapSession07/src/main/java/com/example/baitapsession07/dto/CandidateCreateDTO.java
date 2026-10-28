package com.example.baitapsession07.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CandidateCreateDTO {
    @NotBlank(message = "Tên ứng viên không được để trống")
    @Size(min = 5, max = 50, message = "Tên ứng viên phải có từ 5 đến 50 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 18, message = "Ứng viên phải từ 18 tuổi")
    private Integer age;

    @NotNull(message = "Số năm kinh nghiệm không được để trống")
    @Min(value = 0, message = "Số năm kinh nghiệm không được âm")
    private Integer yearsOfExperience;
}

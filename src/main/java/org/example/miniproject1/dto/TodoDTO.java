package org.example.miniproject1.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDTO {
    @NotBlank(message = "Không được để trống nội dung")
    private String content;
    @FutureOrPresent(message = "Ngày phải >= hiện tại")
    private LocalDate dueDate;
    private String status;
    private String priority;
}

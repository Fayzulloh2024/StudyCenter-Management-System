package org.example.study.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.study.entity.abs.BaseEntity;
import org.example.study.entity.enums.LessonStatus;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Lesson extends BaseEntity {
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private LessonStatus status;


}

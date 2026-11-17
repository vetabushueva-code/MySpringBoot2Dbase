package ru.bushuevavioletta.MySpringBoot2Dbase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Учебная дисциплина
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "DISCIPLINES")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Название дисциплины
    @Column(name = "name")
    private String name;

    // Количество часов
    @Column(name = "hours")
    private Integer hours;

    // Семестр
    @Column(name = "semester")
    private Integer semester;
}

package ru.bushuevavioletta.MySpringBoot2Dbase.service;

import ru.bushuevavioletta.MySpringBoot2Dbase.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(int id);

    boolean deleteStudent(int id);
}

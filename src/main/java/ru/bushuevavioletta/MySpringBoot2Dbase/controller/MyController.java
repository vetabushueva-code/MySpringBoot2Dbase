package ru.bushuevavioletta.MySpringBoot2Dbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bushuevavioletta.MySpringBoot2Dbase.dto.OperationResponse;
import ru.bushuevavioletta.MySpringBoot2Dbase.entity.Student;
import ru.bushuevavioletta.MySpringBoot2Dbase.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    private final StudentService studentService;

    public MyController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<OperationResponse<List<Student>>> allStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok(OperationResponse.ok(
                "Найдено студентов: " + allStudents.size(), allStudents));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<OperationResponse<Student>> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return notFound(id);
        }
        return ResponseEntity.ok(OperationResponse.ok("Студент найден", student));
    }

    @PostMapping("/students")
    public ResponseEntity<OperationResponse<Student>> saveStudent(@RequestBody Student student) {
        student.setId(null); // id назначает база данных
        Student saved = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OperationResponse.ok("Студент добавлен", saved));
    }

    @PutMapping("/students")
    public ResponseEntity<OperationResponse<Student>> updateStudent(@RequestBody Student student) {
        if (student.getId() == null) {
            return ResponseEntity.badRequest()
                    .body(OperationResponse.fail("Для изменения нужно указать id студента"));
        }
        if (studentService.getStudent(student.getId()) == null) {
            return notFound(student.getId());
        }
        Student updated = studentService.saveStudent(student);
        return ResponseEntity.ok(OperationResponse.ok("Студент изменён", updated));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<OperationResponse<Student>> deleteStudent(@PathVariable("id") int id) {
        if (!studentService.deleteStudent(id)) {
            return notFound(id);
        }
        return ResponseEntity.ok(OperationResponse.ok("Студент с id = " + id + " удалён", null));
    }

    private ResponseEntity<OperationResponse<Student>> notFound(int id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(OperationResponse.fail("Студент с id = " + id + " не найден"));
    }
}

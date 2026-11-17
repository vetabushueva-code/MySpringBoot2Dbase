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
import ru.bushuevavioletta.MySpringBoot2Dbase.entity.Discipline;
import ru.bushuevavioletta.MySpringBoot2Dbase.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping("/disciplines")
    public ResponseEntity<OperationResponse<List<Discipline>>> allDisciplines() {
        List<Discipline> all = disciplineService.getAllDisciplines();
        return ResponseEntity.ok(OperationResponse.ok("Найдено дисциплин: " + all.size(), all));
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<OperationResponse<Discipline>> getDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return notFound(id);
        }
        return ResponseEntity.ok(OperationResponse.ok("Дисциплина найдена", discipline));
    }

    @PostMapping("/disciplines")
    public ResponseEntity<OperationResponse<Discipline>> saveDiscipline(@RequestBody Discipline discipline) {
        discipline.setId(null);
        Discipline saved = disciplineService.saveDiscipline(discipline);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OperationResponse.ok("Дисциплина добавлена", saved));
    }

    @PutMapping("/disciplines")
    public ResponseEntity<OperationResponse<Discipline>> updateDiscipline(@RequestBody Discipline discipline) {
        if (discipline.getId() == null) {
            return ResponseEntity.badRequest()
                    .body(OperationResponse.fail("Для изменения нужно указать id дисциплины"));
        }
        if (disciplineService.getDiscipline(discipline.getId()) == null) {
            return notFound(discipline.getId());
        }
        Discipline updated = disciplineService.saveDiscipline(discipline);
        return ResponseEntity.ok(OperationResponse.ok("Дисциплина изменена", updated));
    }

    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<OperationResponse<Discipline>> deleteDiscipline(@PathVariable("id") int id) {
        if (!disciplineService.deleteDiscipline(id)) {
            return notFound(id);
        }
        return ResponseEntity.ok(OperationResponse.ok("Дисциплина с id = " + id + " удалена", null));
    }

    private ResponseEntity<OperationResponse<Discipline>> notFound(int id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(OperationResponse.fail("Дисциплина с id = " + id + " не найдена"));
    }
}

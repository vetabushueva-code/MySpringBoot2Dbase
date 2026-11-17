package ru.bushuevavioletta.MySpringBoot2Dbase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bushuevavioletta.MySpringBoot2Dbase.dao.DisciplineDAO;
import ru.bushuevavioletta.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineDAO disciplineDAO;

    public DisciplineServiceImpl(DisciplineDAO disciplineDAO) {
        this.disciplineDAO = disciplineDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Discipline> getAllDisciplines() {
        return disciplineDAO.getAllDisciplines();
    }

    @Override
    @Transactional
    public Discipline saveDiscipline(Discipline discipline) {
        return disciplineDAO.saveDiscipline(discipline);
    }

    @Override
    @Transactional(readOnly = true)
    public Discipline getDiscipline(int id) {
        return disciplineDAO.getDiscipline(id);
    }

    @Override
    @Transactional
    public boolean deleteDiscipline(int id) {
        return disciplineDAO.deleteDiscipline(id);
    }
}

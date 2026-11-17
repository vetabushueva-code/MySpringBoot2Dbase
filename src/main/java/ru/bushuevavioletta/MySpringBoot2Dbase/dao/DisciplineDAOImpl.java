package ru.bushuevavioletta.MySpringBoot2Dbase.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.bushuevavioletta.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

@Slf4j
@Repository
public class DisciplineDAOImpl implements DisciplineDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Discipline> getAllDisciplines() {
        List<Discipline> allDisciplines = entityManager
                .createQuery("from Discipline", Discipline.class)
                .getResultList();
        log.info("getAllDisciplines: {}", allDisciplines);
        return allDisciplines;
    }

    @Override
    public Discipline saveDiscipline(Discipline discipline) {
        return entityManager.merge(discipline);
    }

    @Override
    public Discipline getDiscipline(int id) {
        return entityManager.find(Discipline.class, id);
    }

    @Override
    public boolean deleteDiscipline(int id) {
        int deleted = entityManager
                .createQuery("delete from Discipline where id = :disciplineId")
                .setParameter("disciplineId", id)
                .executeUpdate();
        return deleted > 0;
    }
}

package edu.fu.dao;

import edu.fu.entities.Departments;
import edu.fu.utils.DbContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableTransactionManagement
@Transactional
@AllArgsConstructor
public class DepartmentRepository implements DepartmentInterface{

    private final SessionFactory sessionFactory;

    @Override
    public Departments findById(long id) {
        Session session = sessionFactory.openSession();
        return session.get(Departments.class, id);
    }

    @Override
    @Transactional
    public Departments create(Departments department) {
        Session session = sessionFactory.openSession();
        session.persist(department);
        return department;
    }

    @Override
    @Transactional
    public Departments update(Departments department) {
        Session session = sessionFactory.openSession();
        session.merge(department);
        return department;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        session.remove(session.find(Departments.class, id));
    }

    @Override
    public List<Departments> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT d FROM Departments d", Departments.class).getResultList();
    }

    @Override
    @Transactional
    public Departments findDepartmentByName(String name) {
        Session session = sessionFactory.openSession();
            TypedQuery<Departments> departments = session.createNamedQuery("findDepartmentByName", Departments.class).setParameter("departmentName", name);
            return departments.getSingleResult();
    }
}

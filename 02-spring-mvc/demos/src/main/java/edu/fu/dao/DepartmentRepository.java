package edu.fu.dao;

import edu.fu.entities.Departments;
import edu.fu.utils.DbContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class DepartmentRepository implements DepartmentInterface{
    private EntityManager entityManager;

    public DepartmentRepository() {
        this.entityManager = DbContext.getEntityManager();
    }
    @Override
    public Departments findById(long id) {
      Session session = null;
      try{
          session = entityManager.unwrap(Session.class);
          return (Departments) session.get(Departments.class, id);
      }
      catch(Exception e){
          throw new RuntimeException(e);
      }
      finally {
         if(session != null){
             session.close();
         }
      }
    }

    @Override
    public Departments create(Departments department) {
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
        return department;
    }

    @Override
    public Departments update(Departments department) {
        entityManager.getTransaction().begin();
        entityManager.merge(department);
        entityManager.getTransaction().commit();
        return department;
    }

    @Override
    public void delete(long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Departments.class, id));
        entityManager.getTransaction().commit();

    }

    @Override
    public List<Departments> findAll() {
        return entityManager.createQuery("SELECT d FROM Departments d", Departments.class).getResultList();
    }

    @Override
    public Departments findDepartmentByName(String name) {
        Session session = null;
        try{
            session = entityManager.unwrap(Session.class);
            TypedQuery<Departments> departments = session.createNamedQuery("findDepartmentByName", Departments.class).setParameter("departmentName", name);
            return departments.getSingleResult();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        finally {
            if(session != null){
                session.close();

            }
        }
    }
}

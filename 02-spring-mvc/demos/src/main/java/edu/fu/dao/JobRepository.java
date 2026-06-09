package edu.fu.dao;

import edu.fu.entities.Jobs;
import edu.fu.utils.DbContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JobRepository implements JobInterface{

    private EntityManager em;
    public JobRepository() {
     em = DbContext.getEntityManager();
    }
    public Jobs findById(Long id){
        try(Session session = em.unwrap(Session.class);) {
            TypedQuery<Jobs> query = session.createQuery("select j from Jobs j where j.id = :id", Jobs.class);
            return query.setParameter("id", id).getSingleResult();
        }
        catch (Exception e){throw new RuntimeException(e);}
        };

   public List<Jobs> findAll(){
       return em.createQuery("select j from Jobs j").getResultList();
    };

    public Jobs save(Jobs job){
         em = DbContext.getEntityManager();
        EntityTransaction transaction = null;
       try {
           transaction = em.getTransaction();
           transaction.begin();
           em.persist(job);
           transaction.commit();


       }
        catch (Exception e){
           if (transaction != null)
               transaction.rollback();
           throw new RuntimeException(e);
        }
        return job;
    };
    public void delete(Jobs job){
        em.getTransaction().begin();
        em.remove(job);
        em.getTransaction().commit();

    };
    public Jobs update(Jobs job){
        em.getTransaction().begin();
        em.merge(job);
        em.getTransaction().commit();
        return job;
    };

    public boolean isExist(String title){
        try(Session session = em.unwrap(Session.class);) {
            Long result = session.createQuery("select count(j) from Jobs j where j.title = :title", Long.class).setParameter("title", title).getSingleResult();
            return result >0;
        }catch (Exception e){throw new RuntimeException(e);}

    }
}

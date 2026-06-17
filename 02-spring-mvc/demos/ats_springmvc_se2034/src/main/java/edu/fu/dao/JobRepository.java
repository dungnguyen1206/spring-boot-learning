package edu.fu.dao;

import edu.fu.entities.Jobs;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class JobRepository implements JobInterface{

    private final SessionFactory sessionFactory;

    public Jobs findById(Long id){
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Jobs> query = session.createQuery("select j from Jobs j where j.id = :id", Jobs.class);
        return query.setParameter("id", id).getSingleResult();
        };

   public List<Jobs> findAll(){
       return sessionFactory.getCurrentSession()
               .createQuery("select j from Jobs j", Jobs.class)
               .getResultList();
    };

    public Jobs save(Jobs job){
        sessionFactory.getCurrentSession().persist(job);
        return job;
    };
    public void delete(Jobs job){
        sessionFactory.getCurrentSession().remove(job);

    };
    public Jobs update(Jobs job){
        sessionFactory.getCurrentSession().merge(job);
        return job;
    };

    public boolean isExist(String title){
        Session session = sessionFactory.getCurrentSession();
        Long result = session.createQuery("select count(j) from Jobs j where j.title = :title", Long.class)
                .setParameter("title", title)
                .getSingleResult();
        return result >0;

    }
}

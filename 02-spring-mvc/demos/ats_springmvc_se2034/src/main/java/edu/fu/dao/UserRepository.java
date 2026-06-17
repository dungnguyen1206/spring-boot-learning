package edu.fu.dao;

import edu.fu.entities.Users;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserRepository implements UserInterface {
    private EntityManager em;
    public UserRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public List<Users> findAll() {
        return em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
    }
    @Override
    public Users findById(Long id){
        return em.find(Users.class, id);
    };

    @Override
    public Users save(Users user){
       em.getTransaction().begin();
       em.persist(user);
       em.getTransaction().commit();
       return user;
    };
    @Override
    public Users update(Users user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        return user;
    };

    @Override
    public void delete(Long id){
        em.getTransaction().begin();
        em.remove(em.find(Users.class, id));
        em.getTransaction().commit();
    };

}

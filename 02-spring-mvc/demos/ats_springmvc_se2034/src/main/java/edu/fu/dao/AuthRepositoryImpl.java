package edu.fu.dao;

import edu.fu.entities.Users;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository{
    private final SessionFactory sessionFactory;


    @Override
    public Users login(String email, String password) {
        Session session = sessionFactory.openSession();
        TypedQuery<Users> query = session.createQuery("from Users u where u.email=:email and u.password=:password", Users.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    @Override
    public Users register(Users user) {
        return null;
    }
}

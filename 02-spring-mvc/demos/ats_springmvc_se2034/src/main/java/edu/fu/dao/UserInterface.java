package edu.fu.dao;

import edu.fu.entities.Users;

import java.util.List;

public interface UserInterface {
    Users findById(Long id);
    List<Users> findAll();
    Users save(Users user);
    Users update(Users user);
    void delete(Long id);

}

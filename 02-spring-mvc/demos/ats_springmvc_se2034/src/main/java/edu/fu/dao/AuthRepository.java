package edu.fu.dao;

import edu.fu.entities.Users;

public interface AuthRepository {
    Users login(String email, String password);

    Users register(Users user);

}

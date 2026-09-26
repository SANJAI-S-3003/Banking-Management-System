package com.bank.dao;

import com.bank.model.User;

public interface UserDAO {

    boolean register(User user);

    User login(String email, String password);
}
package com.furniturestore.service;

import com.furniturestore.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
}

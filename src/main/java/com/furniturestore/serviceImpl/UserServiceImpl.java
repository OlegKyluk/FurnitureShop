package com.furniturestore.serviceImpl;

import com.furniturestore.entity.Role;
import com.furniturestore.entity.ShoppingCart;
import com.furniturestore.entity.User;
import com.furniturestore.repository.RoleRepository;
import com.furniturestore.repository.ShoppingCartRepository;
import com.furniturestore.repository.UserRepository;
import com.furniturestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ShoppingCartRepository shoppingCartRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setTotalPrice(new BigDecimal(0));
        shoppingCartRepository.save(shoppingCart);
        user.setShoppingCart(shoppingCart);
        userRepository.save(user);
    }

}
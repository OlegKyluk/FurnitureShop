package com.furniturestore.serviceImpl;

import com.furniturestore.entity.Furniture;
import com.furniturestore.entity.FurnitureInCard;
import com.furniturestore.entity.ShoppingCart;
import com.furniturestore.entity.User;
import com.furniturestore.repository.ShoppingCartRepository;
import com.furniturestore.service.FurnitureInCardService;
import com.furniturestore.service.FurnitureService;
import com.furniturestore.service.ShoppingCartService;
import com.furniturestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final FurnitureService furnitureService;
    private final FurnitureInCardService furnitureInCardService;


    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserService userService, FurnitureService furnitureService, FurnitureInCardService furnitureInCardService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.furnitureService = furnitureService;
        this.furnitureInCardService = furnitureInCardService;
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findOne(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void delete(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public Page<ShoppingCart> findAll(Pageable pageable) {
        return shoppingCartRepository.findAll(pageable);
    }

    @Override
    public void addItem(Long id, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Optional<Furniture> furniture = furnitureService.findOne(id);
        boolean alreadyInBasket = false;

        for (FurnitureInCard f : user.getShoppingCart().getFurnitureInCards()) {
            if (furniture.isPresent() && f.getFurniture() == furniture.get()) {
                alreadyInBasket = true;
                f.setQuantity(f.getQuantity() + 1);
                f.setTotalPrice(f.getFurniture().getPrice().multiply(new BigDecimal(f.getQuantity())));
                f.getShoppingCart().setTotalPrice(f.getShoppingCart().getTotalPrice().add(f.getFurniture().getPrice()));
                furnitureInCardService.save(f);
            }
        }

        if (alreadyInBasket) {
        } else {
            FurnitureInCard furnitureInCard = new FurnitureInCard();
            if (furniture.isPresent()) {
                furnitureInCard.setFurniture(furniture.get());
                furnitureInCard.setQuantity(1);
                furnitureInCard.setShoppingCart(user.getShoppingCart());
                furnitureInCard.setTotalPrice(furnitureInCard.getFurniture().getPrice());
                furnitureInCard.getShoppingCart().setTotalPrice(furnitureInCard.getShoppingCart().getTotalPrice().add(furnitureInCard.getFurniture().getPrice()));
                furnitureInCardService.save(furnitureInCard);
            }
        }
    }

    @Override
    public void incrementItem(Long id, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        for (FurnitureInCard f : user.getShoppingCart().getFurnitureInCards()) {
            if (f.getId().equals(id)) {
                int quantity = f.getQuantity();
                f.setQuantity(++quantity);
                f.setTotalPrice(f.getFurniture().getPrice().multiply(new BigDecimal(f.getQuantity())));
                f.getShoppingCart().setTotalPrice(f.getShoppingCart().getTotalPrice().add(f.getFurniture().getPrice()));
                furnitureInCardService.save(f);
                return;
            }
        }
    }

    @Override
    public void decrementItem(Long id, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        for (FurnitureInCard f : user.getShoppingCart().getFurnitureInCards()) {
            if (f.getId().equals(id)) {
                if (f.getQuantity() == 1) {
                    f.getShoppingCart().setTotalPrice(f.getShoppingCart().getTotalPrice().subtract(f.getFurniture().getPrice()));
                    furnitureInCardService.delete(f.getId());
                    return;
                } else {
                    f.setQuantity(f.getQuantity() - 1);
                    f.setTotalPrice(f.getTotalPrice().subtract(f.getFurniture().getPrice()));
                    f.getShoppingCart().setTotalPrice(f.getShoppingCart().getTotalPrice().subtract(f.getFurniture().getPrice()));
                    furnitureInCardService.save(f);
                    return;
                }
            }
        }
    }

    @Override
    public void removeItem(Long id, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        for (FurnitureInCard f : user.getShoppingCart().getFurnitureInCards()) {
            if (f.getId().equals(id)) {
                f.getShoppingCart().setTotalPrice(f.getShoppingCart().getTotalPrice().subtract(f.getTotalPrice()));
                furnitureInCardService.delete(f.getId());
            }
        }
    }

    @Override
    public void removeAllItems(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        user.getShoppingCart().setTotalPrice(new BigDecimal(0));
        furnitureInCardService.deleteAll(user.getShoppingCart().getFurnitureInCards());
    }
}

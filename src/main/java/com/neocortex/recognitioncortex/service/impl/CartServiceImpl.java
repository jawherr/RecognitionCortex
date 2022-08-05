package com.neocortex.recognitioncortex.service.impl;

import com.neocortex.recognitioncortex.entities.Cart;
import com.neocortex.recognitioncortex.entities.Order;
import com.neocortex.recognitioncortex.entities.ProductInOrder;
import com.neocortex.recognitioncortex.entities.Utilisateur;
import com.neocortex.recognitioncortex.enums.ResultEnum;
import com.neocortex.recognitioncortex.exception.MyException;
import com.neocortex.recognitioncortex.repository.CartRepository;
import com.neocortex.recognitioncortex.repository.OrderRepository;
import com.neocortex.recognitioncortex.repository.ProductInOrderRepository;
import com.neocortex.recognitioncortex.repository.UtilisateurRepository;
import com.neocortex.recognitioncortex.service.CartService;
import com.neocortex.recognitioncortex.service.ProductService;
import com.neocortex.recognitioncortex.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    ProductInOrderRepository productInOrderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UtilisateurService utilisateurService;

    @Override
    public Cart getCart(Utilisateur utilisateur) {
        return utilisateur.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<ProductInOrder> productInOrders, Utilisateur utilisateur) {
        Cart finalCart = utilisateur.getCart();
        productInOrders.forEach(productInOrder -> {
            Set<ProductInOrder> set = finalCart.getProducts();
            Optional<ProductInOrder> old = set.stream().filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
            ProductInOrder prod;
            if (old.isPresent()) {
                prod = old.get();
                prod.setCount(productInOrder.getCount() + prod.getCount());
            } else {
                prod = productInOrder;
                prod.setCart(finalCart);
                finalCart.getProducts().add(prod);
            }
            productInOrderRepository.save(prod);
        });
        cartRepository.save(finalCart);

    }

    @Override
    @Transactional
    public void delete(String itemId, Utilisateur utilisateur) {
        if(itemId.equals("") || utilisateur == null) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        var op = utilisateur.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCart(null);
            productInOrderRepository.deleteById(productInOrder.getId());
        });
    }

    @Override
    @Transactional
    public void checkout(Utilisateur utilisateur) {
        // Creat an order
        Order order = new Order(utilisateur);
        orderRepository.save(order);

        // clear cart's foreign key & set order's foreign key& decrease stock
        utilisateur.getCart().getProducts().forEach(productInOrder -> {
            productInOrder.setCart(null);
            productInOrder.setOrder(order);
            productService.decreaseStock(productInOrder.getProductId(), productInOrder.getCount());
            productInOrderRepository.save(productInOrder);
        });

    }
}

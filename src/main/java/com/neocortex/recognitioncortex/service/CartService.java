package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Cart;
import com.neocortex.recognitioncortex.entities.ProductInOrder;
import com.neocortex.recognitioncortex.entities.Utilisateur;

import java.util.Collection;

public interface CartService {
    Cart getCart(Utilisateur utilisateur);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, Utilisateur utilisateur);

    void delete(String itemId, Utilisateur utilisateur);

    void checkout(Utilisateur utilisateur);
}
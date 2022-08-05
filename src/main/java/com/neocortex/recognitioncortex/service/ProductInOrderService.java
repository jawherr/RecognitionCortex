package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.ProductInOrder;
import com.neocortex.recognitioncortex.entities.Utilisateur;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, Utilisateur utilisateur);
    ProductInOrder findOne(String itemId, Utilisateur utilisateur);
}

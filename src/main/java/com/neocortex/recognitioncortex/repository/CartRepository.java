package com.neocortex.recognitioncortex.repository;

import com.neocortex.recognitioncortex.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}

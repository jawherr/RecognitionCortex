package com.neocortex.recognitioncortex.controller.user;

import com.neocortex.recognitioncortex.entities.Cart;
import com.neocortex.recognitioncortex.entities.ProductInOrder;
import com.neocortex.recognitioncortex.entities.Utilisateur;
import com.neocortex.recognitioncortex.form.ItemForm;
import com.neocortex.recognitioncortex.repository.ProductInOrderRepository;
import com.neocortex.recognitioncortex.service.CartService;
import com.neocortex.recognitioncortex.service.ProductInOrderService;
import com.neocortex.recognitioncortex.service.ProductService;
import com.neocortex.recognitioncortex.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderService productInOrderService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @PostMapping("")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ProductInOrder> productInOrders, Principal principal) {
        Utilisateur utilisateur = utilisateurService.findOne(principal.getName());
        try {
            cartService.mergeLocalCart(productInOrders, utilisateur);
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Merge Cart Failed");
        }
        return ResponseEntity.ok(cartService.getCart(utilisateur));
    }

    @GetMapping("")
    public Cart getCart(Principal principal) {
        Utilisateur utilisateur = utilisateurService.findOne(principal.getName());
        return cartService.getCart(utilisateur);
    }


    @PostMapping("/add")
    public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
        var productInfo = productService.findOne(form.getProductId());
        try {
            mergeCart(Collections.singleton(new ProductInOrder(productInfo, form.getQuantity())), principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/{itemId}")
    public ProductInOrder modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity, Principal principal) {
        Utilisateur utilisateur = utilisateurService.findOne(principal.getName());
        productInOrderService.update(itemId, quantity, utilisateur);
        return productInOrderService.findOne(itemId, utilisateur);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        Utilisateur utilisateur = utilisateurService.findOne(principal.getName());
        cartService.delete(itemId, utilisateur);
        // flush memory into DB
    }


    @PostMapping("/checkout")
    public ResponseEntity checkout(Principal principal) {
        Utilisateur utilisateur = utilisateurService.findOne(principal.getName());// Email as username
        cartService.checkout(utilisateur);
        return ResponseEntity.ok(null);
    }


}

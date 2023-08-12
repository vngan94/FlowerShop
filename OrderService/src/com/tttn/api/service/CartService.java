package com.tttn.api.service;

import com.tttn.api.dbresult.RGetCart;
import com.tttn.api.entities.request.*;
import com.tttn.api.repository.CartRepository;

import java.util.List;

public class CartService {

  private static CartService cartService = null;

  public static CartService ins() {
    if (cartService == null) {
      cartService = new CartService();
    }
    return cartService;
  }

  public static List<RGetCart> getCartsByUserId(GetCartsByUserIdPayload request) throws Exception {
    return CartRepository.getCartsByUserId(request);
  }

  public static RGetCart getCartByUserFlo(GetCartByUserFloPayload request) throws Exception {
    return CartRepository.getCartByUserFlo(request);
  }

  public static RGetCart getCartById(GetCartByIdPayload request) throws Exception {
    return CartRepository.getCartById(request);
  }

  public static int create(PostCartPayLoad request) throws Exception {
    return CartRepository.create(request);
  }

  public static void update(UpdateCartPayLoad request) throws Exception {
    CartRepository.update(request);
  }
}

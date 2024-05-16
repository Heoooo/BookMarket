package com.springmvc.repository;

import com.springmvc.domain.Order;

public interface OrderRepository {
	Long saveOrder(Order order);
}

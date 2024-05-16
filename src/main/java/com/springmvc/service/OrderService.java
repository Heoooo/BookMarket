package com.springmvc.service;

import com.springmvc.domain.Order;

public interface OrderService {
	void confirmOrder(String bookId, long quantity);
	Long saveOrder(Order order);
}

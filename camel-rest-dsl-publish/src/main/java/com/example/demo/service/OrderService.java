package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Order;

@Service
public class OrderService {


		private List<Order> list = new ArrayList<>();

		@PostConstruct
		public void initData() {
			list.add(new Order(67, "Mobile", 5000));
			list.add(new Order(89, "Book", 400));
			list.add(new Order(45, "AC", 15000));
			list.add(new Order(46, "Shoes", 4000));
		}

		public Order addOrder(Order order) {
			list.add(order);
			return order;
		}

		public List<Order> getOrders() {
			return list;
		}
		
		public Order findOrderById (Integer id) {

			Order order = null;
			
			Iterator<Order> orderList = list.iterator();
			
			while (orderList.hasNext() ) {

				order = orderList.next();
				if (order.getId() == id) {
					break;
				}else {
					order = null;
				}
			}

			return order;
			
		}

	}


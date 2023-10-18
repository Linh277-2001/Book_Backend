package com.example.bookstore.service;

import com.example.bookstore.model.OrderDetail;
import com.example.bookstore.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

//    public List<OrderDetail> getOrderDetailsByOrderId(long orderId) {
//        return orderDetailRepository.findByOrderId(orderId);
//    }
}

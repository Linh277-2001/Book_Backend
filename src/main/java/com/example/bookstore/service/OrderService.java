package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderDetail;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    public Order getOrderDetail(Long orderId) {
        Optional<Order> order = this.orderRepository.findById(orderId);
        return order.isPresent() ? order.get() : null;
    }

    public float getCartAmount(List<OrderDetail> orderDetailList) {

        float totalCartAmount = 0f; // Tổng toàn bộ giỏ hàng
        float singleCartAmount = 0f;    //Tổng tiền của mỗi danh mục trong giỏ
        int availableQuantity = 0;  // Số lượng có sẵn trong kho

        for (OrderDetail cart : orderDetailList) {    //Duyệt qua từng danh mục trong giỏ

            int bookId = cart.getBookId();    // Lấy id của sản phẩm trong giỏ
            Optional<Book> book = bookRepository.findById(bookId);//
            if (book.isPresent()) {  //Nếu sản phẩm tồn tại
                Book book1 = book.get();   // Gán tên sản phẩm tồn tại là book1
                if (book1.getAvailableQuantity() < cart.getQuantity()) {
                    singleCartAmount = book1.getPrice() * book1.getAvailableQuantity();
                    cart.setQuantity(book1.getAvailableQuantity());
                } else {
                    singleCartAmount = cart.getQuantity() * book1.getPrice();
                    availableQuantity = book1.getAvailableQuantity() - cart.getQuantity();//Tính số lượng còn lại
                }
                totalCartAmount = totalCartAmount + singleCartAmount;// Tính tổng cả giỏ
                book1.setAvailableQuantity(availableQuantity);// Gán số lượng còn lại cho book1
                availableQuantity=0;    //Đặt lại =0 dùng cho lần tiếp theo
                cart.setBookName(book1.getName());// Thêm tên cho sản phẩm trong giỏ
                cart.setAmount(singleCartAmount);   //Thêm số lượng sản phẩm cho từng loại
                bookRepository.save(book1);
            }
        }
        return totalCartAmount;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}

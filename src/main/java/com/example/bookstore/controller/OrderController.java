package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseOrderDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.User;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.service.UserSerVice;
import com.example.bookstore.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderService orderService;
    private BookService bookService;
    private UserSerVice userSerVice;


    public OrderController(OrderService orderService, BookService bookService, UserSerVice userSerVice) {
        this.orderService = orderService;
        this.bookService = bookService;
        this.userSerVice = userSerVice;
    }

    private Logger logger = LoggerFactory.getLogger(OrderController.class); //Để tạo log


    //Đã tạo ra trong book api
    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Book>> getAllBooks() {

        List<Book> bookList = bookService.getAllBooks();

        return ResponseEntity.ok(bookList);
    }
    //
    @GetMapping(value = "/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) {

        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }


    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Request Payload " + orderDTO.toString());
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
        float amount = orderService.getCartAmount(orderDTO.getCartItems()); // Tổng giá tiền của cả giỏ hàng

        User user = new User(orderDTO.getUserName(), orderDTO.getUserEmail());  // Tạo 1 khách hàng với name, email mà người dùng đẩy vào giỏ hàng
        Integer userIdFromDb = userSerVice.isUserPresent(user);
        if (userIdFromDb != null) { // Nếu id người dùng đó tồn tại
            user.setId(userIdFromDb);  // Lấy id tìm được lưu vào thành Id của người mới tìm được
            logger.info("User already present in db with id : " + userIdFromDb);
        }else{              // Nếu người dùng không tìm kiếm được trong DB
            user = userSerVice.saveUser(user);  // Lưu người dùng không tồn tại, thành người dùng mới
            logger.info("User saved.. with id : " + user.getId());
        }
        orderDTO.setCreated(DateUtil.getCurrentDateTime());
        //Order order = new Order(orderDTO.getOrderDescription(), user, orderDTO.getCartItems()); // Tạo 1 order với tham số mô tả, người dùng, danh sách chi tiết giỏ
        Order order = new Order(orderDTO.getOrderDescription(), userIdFromDb, orderDTO.getCartItems(),orderDTO.getAddress(),orderDTO.getPhone(), orderDTO.getCreated()); // Chỉnh sửa
        order = orderService.saveOrder(order);  // LƯU ORDER VÀO DB ==> CÓ lỗi ở đây
        logger.info("Order processed successfully..");

        responseOrderDTO.setAmount(amount);
        responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
        responseOrderDTO.setOrderId(order.getId());
        responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());

        logger.info("Push okee nhe");

        return ResponseEntity.ok(responseOrderDTO); // Trả về đối tượng response
    }

}

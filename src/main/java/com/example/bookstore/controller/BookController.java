package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book")

public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/all")
    public List<Book> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return bookRepository.findAll(sort);
    }

    //    @PostMapping
//    public Book insertBook(@RequestBody Book book){
//        if(bookRepository.existsById(book.getId())) return null;
//        return bookRepository.save(book);
//    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book insertBook(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
//            @RequestParam("price") Long price,
            @RequestParam("created") Date created,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        //Path imagePath = Paths.get("D://image"); //D:\BookStore Frontend\bookstore\public
        Path imagePath = Paths.get("D://BookStore Frontend//bookstore//public");
        if (!Files.exists(imagePath)) {
            Files.createDirectories(imagePath);
        }
        Path file = imagePath.resolve(image.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }

        Category category = new Category();
        category.setId(categoryId);

        Book book = new Book();
        book.setName(name);
        book.setDescription(description);
//        book.setPrice(price);
        book.setCreated(created);
        book.setCategory(category);
        book.setPhoto(image.getOriginalFilename());
        return bookRepository.save(book);
    }

    //Sửa Book
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable int id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
//            @RequestParam("price") Long price,
            @RequestParam("created") Date created,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Book existingBook = optionalBook.get();

        // Xử lý ảnh nếu image không null
        if (image != null) {
            Path imagePath = Paths.get("D://BookStore Frontend//bookstore//public");
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }
            Path file = imagePath.resolve(image.getOriginalFilename());

            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(image.getBytes());
            }
            existingBook.setPhoto(image.getOriginalFilename());
        }

        existingBook.setName(name);
        existingBook.setDescription(description);
//        existingBook.setPrice(price);
        existingBook.setCreated(created);

        Category category = new Category();
        category.setId(categoryId);
        existingBook.setCategory(category);

        Book updatedBook = bookRepository.save(existingBook);
        return ResponseEntity.ok(updatedBook);
    }



//    @PutMapping("/update/{id}")
//    public Book updateBook(@PathVariable int id,@RequestBody Book book){
//        Book updateBook=bookRepository.findById(id).get();
//        updateBook.setName(book.getName());
//        updateBook.setDescription(book.getDescription());
//        updateBook.setCategory(book.getCategory());
//        return bookRepository.save(updateBook);
//
//    }

    // get by
    @GetMapping("/category/{categoryId}")
    public List<Book> getCategoryById(@PathVariable int categoryId){

        return bookRepository.findByCategoryId(categoryId);
    }
    @GetMapping("/count")
    public long countAllBook(){
        return bookRepository.count();
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        try{
            bookRepository.deleteById(id);
            return ResponseEntity.ok("OK");
        }catch ( Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
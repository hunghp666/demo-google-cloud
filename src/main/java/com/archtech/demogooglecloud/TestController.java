package com.archtech.demogooglecloud;

import com.archtech.demogooglecloud.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping ("/hello")
    public String hello(){
        return "Hello from Google Cloud";
    }

    @PostMapping("/books")
    public String newBook(@RequestParam("title") String title,
                          @RequestParam("author") String author,
                          @RequestParam("year") int year
                          ){
        Book book = new Book(title,author, year);
        return bookRepository.save(book).toString();
    }

    @PostMapping("/books/find")
    public ResponseEntity findBook(
                          @RequestParam("author") String author
    ){
        return ResponseEntity.ok(bookRepository.findByAuthor(author));
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestPart("file") MultipartFile file
                                     ) throws Exception {
        System.out.println(file.getOriginalFilename());
        bookService.uploadFile(file.getOriginalFilename(), file);
        return ResponseEntity.ok().build();
    }
}

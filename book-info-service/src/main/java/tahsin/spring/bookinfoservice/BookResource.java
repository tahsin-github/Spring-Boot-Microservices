package tahsin.spring.bookinfoservice;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookResource {
    @RequestMapping("/{bookId}")
    public Book getBookInfo(@PathVariable("bookId") String bookId){
        return new Book(bookId, "all quiet on the western front");
    }
}

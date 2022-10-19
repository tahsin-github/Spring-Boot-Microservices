package tahsin.spring.bookratingsservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingData")
public class RatingResource {
    @RequestMapping("/{bookId}")
    public Rating getRating(@PathVariable("bookId") String bookId){
        return new Rating(bookId, 4);
    }
}

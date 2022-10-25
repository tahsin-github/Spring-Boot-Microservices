package tahsin.spring.bookcatalogservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class BookCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){


        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("2345", 5)
        );

        return ratings.stream().map(rating -> {
//            Book book = restTemplate.getForObject("http://localhost:8082/books/" + rating.getBookId(), Book.class);

            Book book =  webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/books/" + rating.getBookId(), Book.class)
                    .retrieve()
                    .bodyToMono(
                            Book.class
                    )
                    .block();

            return new CatalogItem(book.getName(), "It is a good book", rating.getRating());
        }).collect(Collectors.toList());

//        return Collections.singletonList(new CatalogItem("all quiet on the western front", "Book on First World War", 5));
    }
}

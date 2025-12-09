package tests.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBookRequest {
    private String userId;
    private List<Book> collectionOfIsbns;

    public AddBookRequest(String userId, String isbn) {
        this.userId = userId;
        this.collectionOfIsbns = Collections.singletonList(new Book(isbn));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Book {
        private String isbn;
    }
}
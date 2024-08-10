package vn.edu.likelion.app.bookstore.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequestDTO {
    @Size(min = 4, max = 100)
    String name;
    String author;
    int quantity;
    int price;
    String description;
}

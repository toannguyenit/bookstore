package vn.edu.likelion.app.bookstore.dto.response;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponseDTO {
    Long id;
    String name;
    String author;
    String description;
    int quantity;
    int stockin;
    int stockout;
    int price;
    int isDeleted;
    Date createTime;
    Date updateTime;
}

package vn.edu.likelion.app.bookstore.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockResponseDTO {
    Long id;
    int quantity_before;
    int quantity_stock;
    int quantity_after;
    String name;
    int price;
}

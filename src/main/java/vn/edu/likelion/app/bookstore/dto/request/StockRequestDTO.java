package vn.edu.likelion.app.bookstore.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockRequestDTO {
    Long id;
    int quantity_stock;
}

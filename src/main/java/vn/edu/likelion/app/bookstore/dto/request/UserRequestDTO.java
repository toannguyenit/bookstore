package vn.edu.likelion.app.bookstore.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {

    @Size(min = 4, max = 20, message = "username phai >4 va <20")
    String username;

    @Size(min = 6, max = 20, message = "password phai >4 va <20")
    String password;
    String name;

}

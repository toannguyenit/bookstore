package vn.edu.likelion.app.bookstore.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_users")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity  extends BaseEntity {

    @Column(unique = true, nullable = false)
     String username;

    @Column(nullable = false)
     String password;

    @Column(nullable = false)
     String name;



    @OneToMany(mappedBy = "userEntity",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     List<StockEntity> stockEntities;

}

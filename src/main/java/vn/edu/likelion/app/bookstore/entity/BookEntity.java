package vn.edu.likelion.app.bookstore.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbl_books")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
     String name;

    @Column
     String author;

    @Column(nullable = false)
     int quantity;

    @Column(nullable = false)
     int price;

    @Column
     String description;

    @OneToMany(mappedBy = "bookEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     List<StockEntity> stockInEntities;

}

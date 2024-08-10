package vn.edu.likelion.app.bookstore.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbl_stocks")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "book_id")
    BookEntity bookEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity userEntity;

    @Column(nullable = false)
    int quantity;

    @Column(nullable = false)
    int type;

}

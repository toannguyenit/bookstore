package vn.edu.likelion.app.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.app.bookstore.dto.response.BookResponseDTO;
import vn.edu.likelion.app.bookstore.entity.BookEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository  extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByName(String name);


    @Query(value = "SELECT b.id, b.name, b.author, b.description, " +
                   "SUM(CASE WHEN s.type = 0 THEN s.quantity ELSE 0 END) AS stockin, " +
                   "SUM(CASE WHEN s.type = 1 THEN s.quantity ELSE 0 END) AS stockout, " +
                   "SUM(CASE WHEN s.type = 0 THEN s.quantity ELSE 0 END) - SUM(CASE WHEN s.type = 1 THEN s.quantity ELSE 0 END) AS quantity, " +
                   "b.price, b.is_deleted, b.create_time, b.update_time " +
                   "FROM tbl_books b " +
                   "JOIN tbl_stocks s ON b.id = s.book_id " +
                   "GROUP BY b.id, b.name, b.author, b.description, b.price, b.is_deleted, b.create_time, b.update_time "
                 , nativeQuery = true)
    List<Object[]> findAllJoinStock();

    @Query(value = "SELECT b.id, b.name, b.author, b.description, " +
                   "SUM(CASE WHEN s.type = 0 THEN s.quantity ELSE 0 END) AS stockin, " +
                   "SUM(CASE WHEN s.type = 1 THEN s.quantity ELSE 0 END) AS stockout, " +
                   "SUM(CASE WHEN s.type = 0 THEN s.quantity ELSE 0 END) - SUM(CASE WHEN s.type = 1 THEN s.quantity ELSE 0 END) AS quantity, " +
                   "b.price, b.is_deleted, b.create_time, b.update_time " +
                   "FROM tbl_books b " +
                   "JOIN tbl_stocks s ON b.id = s.book_id " +
                   "GROUP BY b.id, b.name, b.author, b.description, b.price, b.is_deleted, b.create_time, b.update_time " +
                   "ORDER BY " +
                   "CASE WHEN :type = 'price' THEN b.price END ASC, " +
                   "CASE WHEN :type = 'stockout' THEN stockout END ASC, " +
                   "CASE WHEN :type = 'quantity' THEN quantity END ASC, " +
                   "b.id", nativeQuery = true)
    List<Object[]> findAllJoinStockSort(@Param("type") String type);


    @Query(value = "SELECT b.id, b.name, b.author, b.description, " +
                   "SUM(CASE WHEN s.type = 0 THEN s.quantity ELSE 0 END) AS stockin, " +
                   "SUM(CASE WHEN s.type = 1 THEN s.quantity ELSE 0 END) AS stockout, " +
                   "SUM(CASE WHEN s.type = 0 THEN s.quantity ELSE 0 END) - SUM(CASE WHEN s.type = 1 THEN s.quantity ELSE 0 END) AS quantity, " +
                   "b.price, b.is_deleted, b.create_time, b.update_time " +
                   "FROM tbl_books b " +
                   "JOIN tbl_stocks s ON b.id = s.book_id " +
                   "WHERE b.id = :id " +
                   "GROUP BY b.id, b.name, b.author, b.description, b.price, b.is_deleted, b.create_time, b.update_time",
            nativeQuery = true)
    List<Object[]> findByIdJoinStock(@Param("id") Long id);

    @Query(value = "SELECT b.id, b.name, b.author, b.description, " +
                   "SUM(CASE WHEN s.type = 0 THEN s.quantity ELSE 0 END) AS stockin, " +
                   "SUM(CASE WHEN s.type = 1 THEN s.quantity ELSE 0 END) AS stockout, " +
                   "SUM(CASE WHEN s.type = 0 THEN s.quantity ELSE 0 END) - SUM(CASE WHEN s.type = 1 THEN s.quantity ELSE 0 END) AS quantity, " +
                   "b.price, b.is_deleted, b.create_time, b.update_time " +
                   "FROM tbl_books b " +
                   "JOIN tbl_stocks s ON b.id = s.book_id " +
                   "WHERE b.name = :name " +
                   "GROUP BY b.id, b.name, b.author, b.description, b.price, b.is_deleted, b.create_time, b.update_time",
            nativeQuery = true)
    List<Object[]> findByNameJoinStock(@Param("name") String name);





}

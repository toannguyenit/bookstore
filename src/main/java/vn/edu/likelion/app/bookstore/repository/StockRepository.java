package vn.edu.likelion.app.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.app.bookstore.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
}

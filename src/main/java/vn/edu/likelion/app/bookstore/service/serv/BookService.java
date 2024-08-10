package vn.edu.likelion.app.bookstore.service.serv;

import vn.edu.likelion.app.bookstore.dto.response.BookResponseDTO;
import vn.edu.likelion.app.bookstore.entity.BookEntity;
import vn.edu.likelion.app.bookstore.repository.BookRepository;

import java.util.List;

public interface BookService extends BaseCRUD<BookEntity> {


    BookResponseDTO findByIdJoinStock(Long id);
    BookResponseDTO findByNameJoinStock(String name);
    List<BookResponseDTO> findAllJoinStock();
    List<BookResponseDTO> findAllJoinStockSort(String type);
}

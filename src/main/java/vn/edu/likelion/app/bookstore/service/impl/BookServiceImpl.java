package vn.edu.likelion.app.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.app.bookstore.dto.response.BookResponseDTO;
import vn.edu.likelion.app.bookstore.entity.BookEntity;
import vn.edu.likelion.app.bookstore.entity.StockEntity;
import vn.edu.likelion.app.bookstore.entity.UserEntity;
import vn.edu.likelion.app.bookstore.repository.BookRepository;
import vn.edu.likelion.app.bookstore.repository.StockRepository;
import vn.edu.likelion.app.bookstore.service.serv.BookService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public BookEntity create(BookEntity entity) {
        BookEntity bookEntity = bookRepository.save(entity);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);


        StockEntity stockEntity = new StockEntity();
        stockEntity.setBookEntity(bookEntity);
        stockEntity.setType(0);
        stockEntity.setQuantity(bookEntity.getQuantity());
        stockEntity.setUserEntity(userEntity);
        stockRepository.save(stockEntity);

        return bookEntity;
    }

    @Override
    public BookEntity update(Long id, BookEntity entity) {
        BookEntity oldEntity = bookRepository.findById(id).orElse(null);

        // Cau lenh nay dam bao Entity khong phai la null thi se thuc hien tiep, neu khong se dung
        assert oldEntity != null;

        oldEntity.setAuthor(entity.getAuthor());
        oldEntity.setQuantity(entity.getQuantity());
        oldEntity.setPrice(entity.getPrice());
        oldEntity.setDescription(entity.getDescription());
        oldEntity.setUpdateTime(new Date());

        return bookRepository.save(oldEntity);
    }


    @Override
    public void delete(Long id) {
        BookEntity oldEntity = bookRepository.findById(id).orElse(null);
        // Cau lenh nay dam bao Entity khong phai la null thi se thuc hien tiep, neu khong se dung
        assert oldEntity != null;

        oldEntity.setIsDeleted(1);
        oldEntity.setUpdateTime(new Date());
        bookRepository.save(oldEntity);
    }

    @Override
    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Iterable<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> findByName(String name) {
        return bookRepository.findByName(name);
    }


    @Override
    public BookResponseDTO findByIdJoinStock(Long id) {
        return convertToBookResponseDTO(bookRepository.findByIdJoinStock(id).get(0));
    }

    @Override
    public BookResponseDTO findByNameJoinStock(String name) {
        return convertToBookResponseDTO(bookRepository.findByNameJoinStock(name).get(0));
    }


    @Override
    public List<BookResponseDTO> findAllJoinStock() {
        List<Object[]> results = bookRepository.findAllJoinStock();
        return results.stream().map(this::convertToBookResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> findAllJoinStockSort(String type) {
        List<Object[]> results = bookRepository.findAllJoinStockSort(type);
        return results.stream().map(this::convertToBookResponseDTO).collect(Collectors.toList());
    }

    private BookResponseDTO convertToBookResponseDTO(Object[] result) {
        return BookResponseDTO.builder()
                .id(((Number) result[0]).longValue())
                .name((String) result[1])
                .author((String) result[2])
                .description((String) result[3])
                .stockin(((Number) result[4]).intValue())
                .stockout(((Number) result[5]).intValue())
                .quantity(((Number) result[6]).intValue())
                .price(((Number) result[7]).intValue())
                .isDeleted(((Number) result[8]).intValue())
                .createTime((Date) result[9])
                .updateTime((Date) result[10])
                .build();
    }

//    private BookResponseDTO convertToBookResponseDTO(Object[] result) {
//        try {
//            return BookResponseDTO.builder()
//                    .id(((Number) result[0]).longValue())
//                    .name((String) result[1])
//                    .author((String) result[2])
//                    .description((String) result[3])
//                    .stockin(((Number) result[4]).intValue())
//                    .stockout(((Number) result[5]).intValue())
//                    .quantity(((Number) result[6]).intValue())
//                    .price(((Number) result[7]).intValue())
//                    .isDeleted(((Number) result[8]).intValue())
//                    .createTime((Date) result[9])
//                    .updateTime((Date) result[10])
//                    .build();
//        } catch (ClassCastException e) {
//            // Handle the exception if the type casting fails
//            e.printStackTrace();
//            return null; // or throw an appropriate exception
//        }
//    }
}

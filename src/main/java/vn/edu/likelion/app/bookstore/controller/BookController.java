package vn.edu.likelion.app.bookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.app.bookstore.dto.ApiResponse;
import vn.edu.likelion.app.bookstore.dto.request.BookRequestDTO;
import vn.edu.likelion.app.bookstore.dto.request.StockRequestDTO;
import vn.edu.likelion.app.bookstore.dto.response.BookResponseDTO;
import vn.edu.likelion.app.bookstore.dto.response.StockResponseDTO;
import vn.edu.likelion.app.bookstore.entity.BookEntity;
import vn.edu.likelion.app.bookstore.entity.StockEntity;
import vn.edu.likelion.app.bookstore.entity.UserEntity;
import vn.edu.likelion.app.bookstore.service.serv.BookService;
import vn.edu.likelion.app.bookstore.service.serv.StockService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
//@CheckLogin
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private StockService stockService;

    // Chức năng xem danh sách Book, thống kê sách và số lượng bán ra
    @GetMapping("/finds")
    public List<BookResponseDTO> findAll() {
        return (bookService.findAllJoinStock());
    }

    // Chức năng xem chi tiết Book
    @GetMapping("/{id}")
    public ApiResponse<BookResponseDTO> findById(@PathVariable Long id) throws Exception {
        return ApiResponse.<BookResponseDTO>builder()
                .result(bookService.findByIdJoinStock(id))
                .build();
    }

    // Chức năng xem chi tiết Book
    @GetMapping("/findbyname")
    public ApiResponse<BookResponseDTO> findById(@RequestParam String name) throws Exception {
        return ApiResponse.<BookResponseDTO>builder()
                .result(bookService.findByNameJoinStock(name))
                .build();
    }

    // Chức năng xem số lượng Best seller
    @GetMapping("/best_seller/{num}")
    public List<BookResponseDTO> findBestSeller(@PathVariable int num) throws Exception {
        List<BookResponseDTO> allBooks = bookService.findAllJoinStock();

        return allBooks.stream()
                .sorted((b1, b2) -> Integer.compare(b2.getStockout(), b1.getStockout())) // Sắp xếp giảm dần theo stock_out
                .limit(num) // Giới hạn số lượng phần tử theo giá trị num
                .collect(Collectors.toList());
    }

    // Chức năng thêm Book
    @PostMapping()
    public ApiResponse<BookResponseDTO> create(@RequestBody BookRequestDTO bookRequestDTO) {
        return ApiResponse.<BookResponseDTO>builder()
                .result(toDTO(bookService.create(toEntity(bookRequestDTO))))
                .build();
    }

    // Chức năng sửa Book
    @PutMapping("/{id}")
    public ApiResponse<BookResponseDTO> update(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        return ApiResponse.<BookResponseDTO>builder()
                .result(toDTO(bookService.update(id, toEntity(bookRequestDTO))))
                .build();
    }

    // Chức năng xoá sách
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    // Chức năng sắp xếp theo giá bán, số lượng sách đã bán ra
    @GetMapping("/sort/{type}")
    public List<BookResponseDTO> sortedBy(@PathVariable String type) {
        return bookService.findAllJoinStockSort(type);
    }

    // Chức năng nhập thêm sách
    @PostMapping("/stockin")
    public ApiResponse<StockResponseDTO> stockIn( @RequestBody StockRequestDTO stockRequestDTO) {
        BookResponseDTO bookResponseDTO = bookService.findByIdJoinStock(stockRequestDTO.getId());

        int quantity_before = bookResponseDTO.getQuantity();
        int quantity_stock= stockRequestDTO.getQuantity_stock();

        int quantity_after = quantity_before + quantity_stock;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        BookEntity bookEntity = bookService.findById(stockRequestDTO.getId()).orElseThrow(() -> new RuntimeException("Book not found"));

        StockEntity stockEntity = new StockEntity();
        stockEntity.setBookEntity(bookEntity);
        stockEntity.setUserEntity(userEntity);
        stockEntity.setType(0);
        stockEntity.setQuantity(stockRequestDTO.getQuantity_stock());

        StockEntity stockEntitySave = stockService.create(stockEntity);


        StockResponseDTO stockResponseDTO = new StockResponseDTO();
        if (stockEntitySave != null) {
            bookEntity.setQuantity(quantity_after);
            bookService.update(bookEntity.getId(), bookEntity);

            stockResponseDTO.setQuantity_before(quantity_before);
            stockResponseDTO.setQuantity_stock(quantity_stock);
            stockResponseDTO.setQuantity_after(quantity_after);
             stockResponseDTO.setId(bookEntity.getId());
             stockResponseDTO.setPrice(bookEntity.getPrice());
            stockResponseDTO.setName(bookEntity.getName());
        }else {

            System.out.println("Chua luu duoc");
        }

        return ApiResponse.<StockResponseDTO>builder()
                .result(stockResponseDTO)
                .build();
    }

    // Chức năng bán sách
    @PostMapping("/stockout")
    public ApiResponse<StockResponseDTO> stockOut(@RequestBody StockRequestDTO stockRequestDTO) {
        BookResponseDTO bookResponseDTO = bookService.findByIdJoinStock(stockRequestDTO.getId());

        int quantity_before = bookResponseDTO.getQuantity();
        int quantity_stock= stockRequestDTO.getQuantity_stock();
        if (quantity_stock > quantity_before) {
            throw new ArithmeticException("So luong stock out qua lon");
        }
        int quantity_after = quantity_before - quantity_stock;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        BookEntity bookEntity = bookService.findById(stockRequestDTO.getId()).orElseThrow(() -> new RuntimeException("Book not found"));

        StockEntity stockEntity = new StockEntity();
        stockEntity.setBookEntity(bookEntity);
        stockEntity.setUserEntity(userEntity);
        stockEntity.setType(1);
        stockEntity.setQuantity(stockRequestDTO.getQuantity_stock());

        StockEntity stockEntitySave = stockService.create(stockEntity);


        StockResponseDTO stockResponseDTO = new StockResponseDTO();
        if (stockEntitySave != null) {
            bookEntity.setQuantity(quantity_after);
            bookService.update(bookEntity.getId(), bookEntity);

            stockResponseDTO.setQuantity_before(quantity_before);
            stockResponseDTO.setQuantity_stock(quantity_stock);
            stockResponseDTO.setQuantity_after(quantity_after);
            stockResponseDTO.setId(bookEntity.getId());
            stockResponseDTO.setPrice(bookEntity.getPrice());
            stockResponseDTO.setName(bookEntity.getName());
        }

        return ApiResponse.<StockResponseDTO>builder()
                .result(stockResponseDTO)
                .build();
    }

    private BookEntity toEntity(BookRequestDTO bookRequestDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookRequestDTO.getName());
        bookEntity.setAuthor(bookRequestDTO.getAuthor());
        bookEntity.setPrice(bookRequestDTO.getPrice());
        bookEntity.setDescription(bookRequestDTO.getDescription());
        bookEntity.setQuantity(bookRequestDTO.getQuantity());
        return bookEntity;
    }

    private BookResponseDTO toDTO(BookEntity bookEntity) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(bookEntity.getId());
        bookResponseDTO.setName(bookEntity.getName());
        bookResponseDTO.setAuthor(bookEntity.getAuthor());
        bookResponseDTO.setPrice(bookEntity.getPrice());
        bookResponseDTO.setQuantity(bookEntity.getQuantity());
        bookResponseDTO.setDescription(bookEntity.getDescription());
        bookResponseDTO.setIsDeleted(bookEntity.getIsDeleted());
        bookResponseDTO.setCreateTime(bookEntity.getCreateTime());
        bookResponseDTO.setUpdateTime(bookEntity.getUpdateTime());
        return bookResponseDTO;
    }

    private List<BookResponseDTO> toDTOs(Iterable<BookEntity> bookEntities) {
        List<BookResponseDTO> bookResponseDTOS = new ArrayList<>();
        for (BookEntity bookEntity : bookEntities) {
            BookResponseDTO bookResponseDTO = toDTO(bookEntity);
            bookResponseDTOS.add(bookResponseDTO);
        }
        return bookResponseDTOS;
    }
}

package vn.edu.likelion.app.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.app.bookstore.entity.StockEntity;
import vn.edu.likelion.app.bookstore.repository.StockRepository;
import vn.edu.likelion.app.bookstore.service.serv.StockService;

import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;


    @Override
    public StockEntity create(StockEntity entity) {
        return stockRepository.save(entity);
    }

    // Chuc nang nay khong ton tai
    @Override
    public StockEntity update(Long id, StockEntity entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<StockEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<StockEntity> findAll() {
        return null;
    }

    @Override
    public Optional<StockEntity> findByName(String name) {
        return Optional.empty();
    }
}

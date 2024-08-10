package vn.edu.likelion.app.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.app.bookstore.entity.UserEntity;
import vn.edu.likelion.app.bookstore.repository.BookRepository;
import vn.edu.likelion.app.bookstore.repository.UserRepository;
import vn.edu.likelion.app.bookstore.service.serv.AppService;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public UserEntity login(String username, String password) {
        return null;
    }
}

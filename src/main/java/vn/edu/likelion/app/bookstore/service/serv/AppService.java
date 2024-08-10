package vn.edu.likelion.app.bookstore.service.serv;

import vn.edu.likelion.app.bookstore.entity.UserEntity;

public interface AppService {

    UserEntity login(String username, String password);
}

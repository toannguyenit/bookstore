package vn.edu.likelion.app.bookstore.component;

import org.springframework.stereotype.Component;
import vn.edu.likelion.app.bookstore.entity.UserEntity;

@Component
public class SessionUser {
    
    private UserEntity userEntity = null;

    private String token = null;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}

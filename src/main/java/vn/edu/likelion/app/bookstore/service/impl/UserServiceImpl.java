package vn.edu.likelion.app.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.app.bookstore.entity.UserEntity;
import vn.edu.likelion.app.bookstore.repository.UserRepository;
import vn.edu.likelion.app.bookstore.service.serv.UserService;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserEntity create(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public UserEntity update(Long id, UserEntity entity) {
        UserEntity oldEntity = userRepository.findById(id).orElse(null);

        if (oldEntity != null) {
            entity.setId(oldEntity.getId());
            entity.setUsername(oldEntity.getUsername());
            entity.setPassword(oldEntity.getPassword());
            entity.setUpdateTime(new Date());
            return userRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        UserEntity entity = userRepository.findById(id).orElse(null);

        assert entity != null;

        entity.setIsDeleted(1);
        entity.setUpdateTime(new Date());

        userRepository.save(entity);

    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findByName(String name) {
        return userRepository.findByName(name);
    }
}

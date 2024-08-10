package vn.edu.likelion.app.bookstore.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.app.bookstore.dto.ApiResponse;
import vn.edu.likelion.app.bookstore.dto.request.UserRequestDTO;
import vn.edu.likelion.app.bookstore.dto.response.BookResponseDTO;
import vn.edu.likelion.app.bookstore.dto.response.UserResponseDTO;
import vn.edu.likelion.app.bookstore.entity.BookEntity;
import vn.edu.likelion.app.bookstore.entity.UserEntity;
import vn.edu.likelion.app.bookstore.service.serv.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/finds")
    public List<UserResponseDTO> findAll() {
        return toDTOs(userService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponseDTO> findById(@PathVariable Long id) {
            UserEntity userEntity = userService.findById(id).orElse(null);

        assert userEntity != null;

        return ApiResponse.<UserResponseDTO>builder()
                .result(toDTO(userEntity))
                .build();
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserEntity userEntitySave = userService.create(toEntity(userRequestDTO));
        return ResponseEntity.status(200).body(toDTO(userEntitySave));
    }

    @PutMapping

    private UserResponseDTO toDTO(UserEntity userEntity) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setUsername(userEntity.getUsername());
        userResponseDTO.setName(userEntity.getName());
        userResponseDTO.setIsDeleted(userEntity.getIsDeleted());
        userResponseDTO.setCreateTime(userEntity.getCreateTime());
        userResponseDTO.setUpdateTime(userEntity.getUpdateTime());

        return userResponseDTO;
    }

    private UserEntity toEntity(UserRequestDTO userRequestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDTO.getUsername());
        userEntity.setPassword(userRequestDTO.getPassword());
        userEntity.setName(userRequestDTO.getName());
        return userEntity;
    }

    private List<UserResponseDTO> toDTOs(Iterable<UserEntity> userEntities) {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserResponseDTO userResponseDTO = toDTO(userEntity);
            userResponseDTOS.add(userResponseDTO);
        }
        return userResponseDTOS;
    }

}

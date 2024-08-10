package vn.edu.likelion.app.bookstore.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.likelion.app.bookstore.dto.request.UserRequestDTO;
import vn.edu.likelion.app.bookstore.dto.response.UserResponseDTO;
import vn.edu.likelion.app.bookstore.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUser(UserRequestDTO request);

    UserResponseDTO toUserResponse(UserEntity user);

}
package vn.edu.likelion.app.bookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.app.bookstore.dto.request.LoginRequestDTO;
import vn.edu.likelion.app.bookstore.dto.response.UserResponseDTO;
import vn.edu.likelion.app.bookstore.service.serv.AppService;

@RestController
@RequestMapping("/api/app/")
@CrossOrigin
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {


        return null;
    }
}

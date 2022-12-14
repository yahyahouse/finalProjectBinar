package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.UploadResponse;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.model.users.UsersResponse;
import com.binar.dummyproject.repository.role.RoleRepository;
import com.binar.dummyproject.repository.users.UsersRepository;
import com.binar.dummyproject.service.users.UsersService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import static com.binar.dummyproject.model.InfoConst.*;

@Tag(name = "Users", description = "API for processing various operations with Users entity")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    /*
    setup untuk upload gambar
     */
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dummyprojectbinar",
            "api_key", "221166829538913",
            "api_secret", "5KfEb789PD2SosIE12zXehlidwM"));

    @PostMapping("/public/update-users-password/{userId}")
    public ResponseEntity<ResponseEntity> updateUsersPassword(
            @PathVariable("userId") Integer userId,
            @RequestParam String oldPassword,
            @RequestParam String password,
            @RequestParam String retypePassword,
            Authentication authentication) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setUserId(userId);
        Users users = usersRepository.findByUserId(userId);
        if (password.equals(retypePassword)) {
            if (passwordEncoder.matches(oldPassword, users.getPassword())) {
                usersService.updateUsersPassword(password, userId);
                return new ResponseEntity(PASSWORD_TERGANTI, HttpStatus.OK);
            } else
                return new ResponseEntity(SALAH_PASSWORD, HttpStatus.BAD_REQUEST);

        } else
            return new ResponseEntity(PASSWORD_SAMA, HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Update users profile")
    @PostMapping(value = "/public/update-users-profile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadResponse> updateUsersProfile(
            @RequestParam("userId") Integer userId,
            @RequestParam("full_name_user") String fullNameUser,
            @RequestParam("users_image") MultipartFile usersImage,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("phone") String phone,
            Authentication authentication
    ) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setUserId(userId);
        UploadResponse response = new UploadResponse();
        File file = new File(usersImage.getOriginalFilename());

        try (FileOutputStream os = new FileOutputStream(file)) {

            os.write(usersImage.getBytes());
            Map result = cloudinary.uploader().upload(file,
                    ObjectUtils.asMap("users_image_id", "users_image"));
            response.setMessage("Upload successful");
            String[] url = new String[1];
            url[0] = result.get("url").toString();
            response.setUrl(url[0]);
            Users users = usersService.findByUserId(userId);
            users.getPassword();
            users.getEmail();
            users.getUsername();
            users.setFullNameUser(fullNameUser);
            users.setAddress(address);
            users.setCity(city);
            users.setPhone(phone);
            users.setUrlUser(url[0]);
            usersRepository.save(users);
            return new ResponseEntity(new UsersResponse(users.getUserId(), fullNameUser, url, address, city, phone), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get detail user")
    @GetMapping(value = "/seller/get-user-detail/{userId}")
    public ResponseEntity<List<Users>> getProductByUserId(@PathVariable("userId") Integer userId) {
        usersService.getUsersByUserId(userId);
        return ResponseEntity.accepted().body(usersService.getUsersByUserId(userId));
    }
}

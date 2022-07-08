package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.UploadResponse;
import com.binar.dummyproject.model.Users;
import com.binar.dummyproject.model.UsersResponse;
import com.binar.dummyproject.repository.users.UsersRepository;
import com.binar.dummyproject.service.users.UsersService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Users", description = "API for processing various operations with Users entity")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepository usersRepository;


    /*
    setup untuk upload gambar
     */
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dummyprojectbinar",
            "api_key", "221166829538913",
            "api_secret", "5KfEb789PD2SosIE12zXehlidwM"));

//    @Operation(summary = "Update users profile")
//    @PutMapping("/public/update-users-profile")
//    public ResponseEntity<Map<String, Object>> updateUsersProfile(
//            @Schema(example = "{" +
//                    "\"userId\":\"1\"," +
//                    "\"username\":\"userTest\"," +
//                    "\"address\":\"Jl. Mermaidman\"," +
//                    "\"usersImageId\":\"28\"," +
//                    "\"city\":\"Ambon\"," +
//                    "\"phone\":\"0877777773\"," +
//                    "\"role\":[\"SELLER\", \"BUYER\"]" +
//                    "}")
//            @RequestBody Map<String, Object> usersProfile){
//        usersService.updateUsersProfile(Integer.valueOf(usersProfile.get("userId").toString()), usersProfile.get("username").toString(),
//                usersProfile.get("address").toString(), usersProfile.get("phone").toString(), usersProfile.get("city").toString()
//        );
//
//
//        Map<String,Object> response = new HashMap<>();
//        response.put("userId", usersProfile.get("userId"));
//        response.put("username", usersProfile.get("username"));
//        response.put("address", usersProfile.get("address"));
//        response.put("phone", usersProfile.get("phone"));
//        response.put("city", usersProfile.get("city"));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
    @Operation(summary = "users change password")
    @PutMapping("/public/update-users-password")
    public ResponseEntity <Map<String, Object>> updateUsersPassword(
            @RequestBody Map<String, Object> usersPassword){
        usersService.updateUsersPassword(usersPassword.get("password").toString(), Integer.valueOf(usersPassword.get("userId").toString()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update users profile")
    @PostMapping(value = "/public/update-users-profile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadResponse> updateUsersProfile(
            @RequestParam("userId")Integer userId,
            @RequestParam("username")String username,
            @RequestParam("users_image") MultipartFile usersImage,
            @RequestParam("address")String address,
            @RequestParam("city")String city,
            @RequestParam("phone")String phone
    ) throws IOException {
        UploadResponse response = new UploadResponse();
        File file = new File(usersImage.getOriginalFilename());
        FileOutputStream os = new FileOutputStream(file);
        os.write(usersImage.getBytes());
        os.close();
        Map result = cloudinary.uploader().upload(file,
                ObjectUtils.asMap("users_image_id", "users_image"));
        response.setMessage("Upload successful");
        String[] url = new String[1];
        url[0] = result.get("url").toString();
        response.setUrl(url[0]);

        Users users = usersService.findByUserId(userId);
        users.getPassword();
        users.getEmail();
        users.setUsername(username);
        users.setAddress(address);
        users.setCity(city);
        users.setPhone(phone);
        users.setUrl(url[0]);
        usersRepository.save(users);

        return new ResponseEntity(new UsersResponse(userId,username,url,address,city,phone), HttpStatus.OK);
    }

    @Operation(summary = "Get detail user")
    @GetMapping(value = "/seller/get-product-seller/{userId}")
    public ResponseEntity<List<Users>> getProductByUserId(@PathVariable("userId") Integer userId){
        usersService.getUsersByUserId(userId);
        return ResponseEntity.accepted().body(usersService.getUsersByUserId(userId));
    }
}

package com.example.demo.controller;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRead;
import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PagedResourcesAssembler<UserRead> userReadPagedResourcesAssembler;

    private final UserMapper userMapper;


    @Operation(summary = "Get all users")
    @GetMapping
    public List<UserDTO> getUsers() {
        List<User> userList = userService.getAllUsers();
        return userList.stream().map(userMapper::userToUserDTO).toList();
    }

    @Operation(
            summary = "Create a new user",
            description = "Adds a user to the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping(path = "")
    public UserDTO createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return userMapper.userToUserDTO(newUser);
    }

    @Operation(
            summary = "this sends a signature requests to anyone with an email in our user table db",
            description = "sends a e signature request along with options. ",
            responses = {@ApiResponse(responseCode = "200", description = "signature sent",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class)))
            }
    )
    @GetMapping(path = "/searchUsersByEmail")
    public ResponseEntity<PagedModel<EntityModel<UserRead>>> getUsersByEmail(@RequestParam String email, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "email") String sortParam) {
        Page<User> searchResults = userService.searchUsersWithEmail(email, page, size, sortParam);
        Page<UserRead> mappedPage = searchResults.map(user -> UserRead.builder().username(user.getUsername()).email(user.getEmail()).name(user.getName()).build());
        PagedModel<EntityModel<UserRead>> pagedModel = userReadPagedResourcesAssembler.toModel(mappedPage);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }


}

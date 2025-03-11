//package com.example.demo.controller;
//
//import com.dropbox.sign.ApiException;
//import com.dropbox.sign.model.ErrorResponse;
//import com.example.demo.utils.Error;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.NoSuchElementException;
//
//@Slf4j
//@RestControllerAdvice
//public class GlobalControllerHandler {
//
//     @Operation(
//        summary = "Handles resource not found exceptions",
//        description = "Returns a 404 error response when a requested resource is not found.",
//        responses = {
//            @ApiResponse(
//                responseCode = "400",
//                description = "Resource not found",
//                content = @Content(schema = @Schema(implementation = Error.class))
//            )
//        }
//    )
//    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
//    public ResponseEntity<Error> EmptyResultDataAccessException(EmptyResultDataAccessException noSuchElementException) {
//        Error error = new Error(noSuchElementException, "Request body input in request does not match any records in database");
//        log.error("validation exception thrown: {}", error);
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    @Operation(
//        summary = "global exception capture for thrown exception from dropbox",
//        description = "for any exception related from dropbox's side",
//        responses = {
//            @ApiResponse(
//                responseCode = "400",
//                description = "Call to DropBox Service failed.",
//                content = @Content(schema = @Schema(implementation = Error.class))
//            )
//        }
//    )
//    @ExceptionHandler(ApiException.class)
//    public ResponseEntity<Error> apiException(ApiException apiException) {
//        Error error = new Error(apiException, "dropbox request failed. Please review stack trace");
//        log.error("api exception threw: {}", error);
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Operation(
//        summary = "an unchecked exception was thrown",
//        description = "Returns a 500 error response when an unchecked exception is thrown.",
//        responses = {
//            @ApiResponse(
//                responseCode = "500",
//                content = @Content(schema = @Schema(implementation = Error.class))
//            )
//        }
//    )
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Error> genericException(Exception exception) {
//        Error error = new Error(exception, "An unexpected error occurred. Please report to dev team");
//        log.error("unchecked exception thrown: {}", error);
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}

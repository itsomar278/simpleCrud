package com.NetflixClone.Task.NetflixClone.Controller.ExcepionsHandling;

import com.NetflixClone.Task.NetflixClone.Model.CustomExceptions.EmptyUserResult;
import com.NetflixClone.Task.NetflixClone.Model.CustomExceptions.UserAlreadyExists;
import com.NetflixClone.Task.NetflixClone.Model.CustomExceptions.UserNotFound;
import com.NetflixClone.Task.NetflixClone.Model.CustomExceptions.WrongInputData;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyUserResult.class)
    public ResponseEntity<Object> handleEmptyUserResult(EmptyUserResult ex , WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setMessage("There is no users in the database");
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NO_CONTENT);
        return responseEntity;
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExists ex , WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setMessage("There is a user with such email in the database");
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
        return responseEntity;
    }

    @ExceptionHandler(WrongInputData.class)
    public ResponseEntity<Object> handleWrongInputData(WrongInputData ex , WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setMessage("Please check your input data");
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFound ex , WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setMessage("There is no user with such email or Id in the database");
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

}

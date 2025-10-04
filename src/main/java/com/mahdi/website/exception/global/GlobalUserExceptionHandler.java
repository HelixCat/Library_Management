package com.mahdi.website.exception.global;

import com.mahdi.website.dto.ResponseDTO;
import com.mahdi.website.exception.user.DuplicateUserNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalUserExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalUserExceptionHandler.class);

    // TODO change UserException Handler

    @ExceptionHandler(DuplicateUserNameException.class)
    public ResponseEntity<ResponseDTO> handleDuplicateUserNameException(DuplicateUserNameException exception) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("field");
        responseDTO.setMessage("this user name is invalid");
        responseDTO.setStatusCode(HttpStatus.NOT_ACCEPTABLE.toString());
        logger.error(responseDTO.getMessage());
        logger.error(exception);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
    }

}

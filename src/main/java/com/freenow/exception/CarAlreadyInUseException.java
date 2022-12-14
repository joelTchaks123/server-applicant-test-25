package com.freenow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The car selecting is used by other driver ...")
public class CarAlreadyInUseException extends Exception{

        static final long serialVersionUID = -3387516993444229948L;


        public CarAlreadyInUseException(String message)
        {
            super(message);
        }

    }


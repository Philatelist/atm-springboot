package com.slavyanin.atm.springboot.utils;

//import com.sun.istack.internal.logging.Logger;

//import org.hibernate.bytecode.buildtime.spi.Logger;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(RestException.class)
    public @ResponseBody
    String handleException(RestException e) {
        LOG.error("Ошибка: " + e.getMessage(), e);
        return "Ошибка: " + e.getMessage();
    }
}
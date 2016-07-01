package com.slavyanin.atm.springboot.controller;

import com.slavyanin.atm.springboot.service.UserService;
import com.slavyanin.atm.springboot.utils.Ajax;
import com.slavyanin.atm.springboot.utils.ExceptionHandlerController;
import com.slavyanin.atm.springboot.utils.RestException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Set;

@Controller
public class UserController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> insert(@RequestParam("data") String userParam) throws RestException {
        try {
            if (userParam == null || userParam.equals("")) {
                return Ajax.emptyResponse();
            }
            userService.insert(userParam);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> delete(@RequestParam("data") String emailUser) throws RestException {
        try {
            if (emailUser == null || emailUser.equals("")) {
                return Ajax.emptyResponse();
            }
            userService.delete(emailUser);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getRandomData() throws RestException {
        try {
            Set<String> result = userService.findAll();
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

}
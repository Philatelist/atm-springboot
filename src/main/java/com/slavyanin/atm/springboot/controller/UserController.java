package com.slavyanin.atm.springboot.controller;

import com.slavyanin.atm.springboot.entity.Atm;
import com.slavyanin.atm.springboot.entity.User;
import com.slavyanin.atm.springboot.service.AtmService;
import com.slavyanin.atm.springboot.service.UserService;
import com.slavyanin.atm.springboot.utils.Ajax;
import com.slavyanin.atm.springboot.utils.ExceptionHandlerController;
import com.slavyanin.atm.springboot.utils.RestException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController extends ExceptionHandlerController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("atmService")
    private AtmService atmService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> insert(@RequestParam("data") String userParam) throws RestException {

        log.info(userParam);

        JSONParser parser = new JSONParser();
        try {
            if (userParam == null || userParam.equals("")) {
                return Ajax.emptyResponse();
            }

            Object obj = parser.parse(userParam);
            JSONObject jsonObject = (JSONObject) obj;
            List<String> arr = new ArrayList<>();
            arr.add((String) jsonObject.get("name"));
            arr.add((String) jsonObject.get("email"));
            arr.add((String) jsonObject.get("password"));

            userService.insert(arr);

            log.info((String) jsonObject.get("email"));

            return Ajax.emptyResponse();
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
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
    public
    @ResponseBody
    Map<String, Object> getRandomData() throws RestException {
        try {
            Set<User> result = userService.findAll();
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/checkBanknotes", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> checkBanknotes() throws RestException {
        try {
            Set<Atm> result = atmService.checkBanknotes();
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

}
package com.slavyanin.atm.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slavyanin.atm.springboot.model.Atm;
import com.slavyanin.atm.springboot.model.User;
import com.slavyanin.atm.springboot.service.AtmService;
import com.slavyanin.atm.springboot.service.UserService;
import com.slavyanin.atm.springboot.utils.Ajax;
import com.slavyanin.atm.springboot.utils.ExceptionHandlerController;
import com.slavyanin.atm.springboot.utils.RestException;
import org.json.simple.JSONArray;
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
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
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


//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    Principal user(Principal user) throws RestException {
//        try {
//            return user;
//        } catch (Exception e) {
//            throw new RestException(e);
//        }
//    }

    @RequestMapping(value = "/mock/user", method = RequestMethod.GET)
    public
    @ResponseBody
    Principal mockUser(Principal mockUser) throws RestException {
        try {

            // Convert Principal user to JSON.
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(mockUser);
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonString);
            JSONObject jsonObject = (JSONObject) object;

            System.out.println("details = " + jsonObject.get("details"));

            JSONObject detailsJsonObject = (JSONObject) jsonObject.get("details");
            System.out.println("detailsSessionId = " + detailsJsonObject.get("sessionId"));
            System.out.println("detailsRemoteAddress = " + detailsJsonObject.get("tokeremoteAddressnValue"));
            System.out.println("detailsDecodedDetails = " + detailsJsonObject.get("decodedDetails"));
            System.out.println("detailsTokenType = " + detailsJsonObject.get("tokenType"));
            System.out.println("detailsTokenValue = " + detailsJsonObject.get("tokenValue"));

            System.out.println("authorities = " + jsonObject.get("authorities"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("authorities");
            System.out.println("JSON ARRAY SIZE = " + jsonArray.size());
            System.out.println("ARRAY INDEX 0 = " + jsonArray.get(0));

            System.out.println("authenticated = " + jsonObject.get("authenticated"));

            System.out.println("userAuthentication = " + jsonObject.get("userAuthentication"));
            JSONObject userAuthenticationJsonObject = (JSONObject) jsonObject.get("userAuthentication");
            System.out.println("userAuthenticationPrincipal = " + userAuthenticationJsonObject.get("principal"));
            System.out.println("userAuthenticationAuthenticated = " + userAuthenticationJsonObject.get("authenticated"));
            System.out.println("userAuthenticationCredentials = " + userAuthenticationJsonObject.get("credentials"));
            System.out.println("userAuthenticationName = " + userAuthenticationJsonObject.get("name"));
            System.out.println("userAuthenticationDetails = " + userAuthenticationJsonObject.get("details"));


            System.out.println("principal = " + jsonObject.get("principal"));
            System.out.println("clientOnly = " + jsonObject.get("clientOnly"));
            System.out.println("credentials = " + jsonObject.get("credentials"));
            System.out.println("oauth2Request = " + jsonObject.get("oauth2Request"));

            System.out.println(mockUser.toString());
            return mockUser;
        } catch (Exception e) {
            throw new RestException(e);
        }
    }


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

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Custom Login Form");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");

        return model;

    }

    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

}
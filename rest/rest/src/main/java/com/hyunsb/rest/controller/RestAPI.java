package com.hyunsb.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestAPI {

    @RequestMapping(method = RequestMethod.GET, path = "/book")
    public void getAll(){

    }

    @RequestMapping(method = RequestMethod.POST, path = "/book")
    public void Add(){

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/book/{id}")
    public void Update(){

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/book/{id}")
    public void Delete(){

    }

}

package com.hyunsb.rest.controller;

import com.hyunsb.rest.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestAPI {

    /* 책 전체 조회 */
    @RequestMapping(method = RequestMethod.GET, path = "/book")
    public String getAll(){
        return "list";
    }

    /* 책 정보 등록 */
    @RequestMapping(method = RequestMethod.POST, path = "/book")
    public String Add(@RequestBody Book book){
        return "add";
    }

    /* 책 정보 수정 */
    @RequestMapping(method = RequestMethod.PUT, path = "/book/{id}")
    public String Update(@RequestBody Book book, @PathVariable int id){
        return "update";
    }

    /* 책 정보 삭제 */
    @RequestMapping(method = RequestMethod.DELETE, path = "/book/{id}")
    public String Delete(@PathVariable int id){
        return "delete";
    }

}

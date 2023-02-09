package com.hyunsb.rest.controller;

import com.hyunsb.rest.dto.BookDTO;
import com.hyunsb.rest.entity.BookEntity;
import com.hyunsb.rest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestController {

    private final BookService bookService;

    /* 책 전체 조회 */
    @RequestMapping(method = RequestMethod.GET, path = "/book")
    public String getAll(Model model){
        model.addAttribute("bookList", bookService.findAll());
        return "list";
    }

    /* 책 정보 등록 */
    @RequestMapping(method = RequestMethod.POST, path = "/book")
    public String Add(@RequestBody BookEntity book){
        return "add";
    }

    /* 책 정보 수정 */
    @RequestMapping(method = RequestMethod.PUT, path = "/book/{id}")
    public String Update(@RequestBody BookEntity book, @PathVariable int id){
        return "update";
    }

    /* 책 정보 삭제 */
    @RequestMapping(method = RequestMethod.DELETE, path = "/book/{id}")
    public String Delete(@PathVariable int id){
        return "delete";
    }

}

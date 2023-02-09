package com.hyunsb.rest.service;

import com.hyunsb.rest.entity.BookEntity;
import com.hyunsb.rest.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // 생성자 자동 생성 (lombok)
public class BookService {

    private final BookRepository bookRepository;

    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    }

}

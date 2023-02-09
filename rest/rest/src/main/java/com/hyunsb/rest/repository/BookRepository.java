package com.hyunsb.rest.repository;

import com.hyunsb.rest.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

}

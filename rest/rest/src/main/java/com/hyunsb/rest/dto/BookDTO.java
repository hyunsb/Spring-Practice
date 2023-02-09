package com.hyunsb.rest.dto;

import com.hyunsb.rest.entity.BookEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookDTO {

    private int id;
    private String name;
    private String author;
    private int price;

    public static BookDTO toBookDTO(BookEntity bookEntity){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setName(bookEntity.getName());
        bookDTO.setAuthor(bookEntity.getAuthor());
        bookDTO.setPrice(bookEntity.getPrice());
        return bookDTO;
    }
}

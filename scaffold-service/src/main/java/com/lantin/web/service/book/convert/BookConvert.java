package com.lantin.web.service.book.convert;

import com.lantin.web.domain.book.Book;
import com.lantin.web.domain.book.dto.BookDto;
import com.lantin.web.domain.book.vo.BookVo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 21:08 周六
 */
@Mapper(componentModel = "spring")
public interface BookConvert {

	BookDto vo2Dto(BookVo accountVo);

	BookVo dto2Vo(BookDto accountDto);

	Book dto2Entity(BookDto accountDto);

	BookDto entity2Dto(Book account);


	List<BookDto> batchConvertEntity(List<Book> list);

	List<BookVo> batchConvertDto(List<BookDto> list);
}

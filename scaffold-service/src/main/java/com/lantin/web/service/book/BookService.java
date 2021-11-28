package com.lantin.web.service.book;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantin.web.domain.book.Book;
import com.lantin.web.domain.book.dto.BookDto;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Gan Luanqing
 * @since 2021-11-27
 */
public interface BookService extends IService<Book> {



	BookDto getBook(Integer id);

	List<BookDto> getAllBooks();

	boolean saveBook(BookDto bookDto);

	boolean modifyBook(BookDto bookDto);

	boolean deleteBook(Integer id);
}

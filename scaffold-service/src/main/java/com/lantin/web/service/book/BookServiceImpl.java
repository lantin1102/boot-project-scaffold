package com.lantin.web.service.book;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantin.web.domain.book.Book;
import com.lantin.web.domain.book.dto.BookDto;
import com.lantin.web.mapper.BookMapper;
import com.lantin.web.service.book.convert.BookConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gan Luanqing
 */
@Slf4j
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

	@Autowired
	BookConvert bookConvert;

	@Override
	public BookDto getBook(Integer id) {
		LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<Book>()
				.eq(Book::getId, id);
		return bookConvert.entity2Dto(this.getOne(queryWrapper));
	}

	@Override
	public List<BookDto> getAllBooks() {

		List<Book> list = this.list();
		return bookConvert.batchConvertEntity(list);
	}

	@Override
	public boolean saveBook(BookDto bookDto) {
		return this.save(bookConvert.dto2Entity(bookDto));
	}

	@Override
	public boolean modifyBook(BookDto bookDto) {

		return this.updateById(bookConvert.dto2Entity(bookDto));
	}

	@Override
	public boolean deleteBook(Integer id) {
		return this.removeById(id);
	}
}

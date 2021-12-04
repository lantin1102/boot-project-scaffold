package com.lantin.web.controller;

import com.lantin.common.domain.response.CommonResponse;
import com.lantin.web.domain.book.dto.BookDto;
import com.lantin.web.domain.book.vo.BookVo;
import com.lantin.web.service.book.BookService;
import com.lantin.web.service.book.convert.BookConvert;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBatch;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gan Luanqing
 */
@Slf4j
@RestController
@RequestMapping("/books")
@Validated
// @Api(tags = "书籍Api接口")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	BookConvert bookConvert;

	@ApiOperation("查询单个书籍信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "书籍id", required = true, dataType = "Integer", paramType = "path")
	})
	@GetMapping("{id}")
	public CommonResponse<BookVo> getBook(@PathVariable("id") @NotNull Integer id) {

		BookVo bookVo = bookConvert.dto2Vo(bookService.getBook(id));
		return CommonResponse.success(bookVo);
	}

	@Autowired
	private RedissonClient redissonClient;

	@GetMapping
	public CommonResponse<List<BookVo>> getAllBooks() {
		List<BookDto> allBooks = bookService.getAllBooks();
		return CommonResponse.success(bookConvert.batchConvertDto(allBooks));
	}

	/**
	 * 表单提交
	 *
	 * @param bookVo bookVo
	 * @return
	 */
	@PostMapping
	public CommonResponse<Boolean> saveBook(@RequestBody @Validated BookVo bookVo) {
		BookDto bookDto = bookConvert.vo2Dto(bookVo);
		return CommonResponse.success(bookService.saveBook(bookDto));
	}

	@PutMapping
	public CommonResponse<Boolean> modifyBook(@RequestBody @Validated BookVo bookVo) {
		BookDto bookDto = bookConvert.vo2Dto(bookVo);
		return CommonResponse.success(bookService.modifyBook(bookDto));
	}

	@DeleteMapping("{id}")
	public CommonResponse<Boolean> deleteBook(@PathVariable("id") @NotNull Integer id) {
		return CommonResponse.success(bookService.deleteBook(id));
	}
}

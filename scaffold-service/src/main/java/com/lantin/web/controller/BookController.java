package com.lantin.web.controller;

import com.lantin.common.domain.response.CommonResponse;
import com.lantin.web.domain.book.dto.BookDto;
import com.lantin.web.domain.book.vo.BookVo;
import com.lantin.web.service.book.BookService;
import com.lantin.web.service.book.convert.BookConvert;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBatch;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

	// @GetMapping("{id}")
	// @ApiOperation("查询单个书籍信息")
	// @ApiImplicitParams({
	// 		@ApiImplicitParam(name = "id", value = "书籍id", required = true, dataType = "Integer", paramType = "path")
	// })
	// public CommonResponse<BookVo> getBook(@PathVariable("id") Integer id) {
	//
	// 	BookVo bookVo = bookConvert.dto2Vo(bookService.getBook(id));
	// 	return CommonResponse.success(bookVo);
	// }
	@Autowired
	private RedissonClient redissonClient;

	@GetMapping("one")
	public CommonResponse<BookVo> getBook(@RequestParam("id") @Min(value = 1, message = "id不能小于1") Integer id,
	                                      @RequestParam("name") @NotBlank(message = "名称不能为空") String name) {

		BookVo bookVo = bookConvert.dto2Vo(bookService.getBook(id));
		return CommonResponse.success(bookVo);
	}

	// @ApiOperation("查询所有书籍信息")
	// @ApiImplicitParams({
	//
	// })
	@GetMapping
	public CommonResponse<List<BookVo>> getAllBooks() {
		List<BookDto> allBooks = bookService.getAllBooks();
		RBatch batch = redissonClient.createBatch();
		
		return CommonResponse.success(bookConvert.batchConvertDto(allBooks));
	}

	// @ApiOperation("保存书籍")
	// @ApiImplicitParams({
	// 		@ApiImplicitParam(name = "bookVo", value = "书籍信息", required = true, dataType = "书籍信息Vo", paramType = "body")
	// })
	// @PostMapping
	// public CommonResponse<Boolean> saveBook(@RequestBody @Validated BookVo bookVo) {
	// 	BookDto bookDto = bookConvert.vo2Dto(bookVo);
	//
	// 	return CommonResponse.success(bookService.saveBook(bookDto));
	// }

	/**
	 * 表单提交
	 *
	 * @param bookVo
	 * @return
	 */
	@PostMapping
	public CommonResponse<Boolean> saveBook(@Validated BookVo bookVo) {
		BookDto bookDto = bookConvert.vo2Dto(bookVo);

		return CommonResponse.success(bookService.saveBook(bookDto));
	}

	// @ApiOperation("编辑书籍")
	// @ApiImplicitParams({
	// 		@ApiImplicitParam(name = "bookVo", value = "书籍信息", required = true, dataType = "书籍信息Vo", paramType = "body")
	// })
	@PutMapping
	public CommonResponse<Boolean> modifyBook(@RequestBody BookVo bookVo) {
		BookDto bookDto = bookConvert.vo2Dto(bookVo);
		return CommonResponse.success(bookService.modifyBook(bookDto));
	}

	// @ApiOperation("删除单个书籍")
	// @ApiImplicitParams({
	// 		@ApiImplicitParam(name = "id", value = "书籍id", required = true, dataType = "Integer", paramType = "path")
	// })
	@DeleteMapping("{id}")
	public CommonResponse<Boolean> deleteBook(@PathVariable("id") Integer id) {
		return CommonResponse.success(bookService.deleteBook(id));
	}
}

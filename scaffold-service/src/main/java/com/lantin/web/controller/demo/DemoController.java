package com.lantin.web.controller.demo;

import com.lantin.common.domain.response.CommonResponse;
import com.lantin.web.domain.DemoA;
import com.lantin.web.domain.ListData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

/**
 * @author Gan Luanqing
 * @date 2021/12/04 19:55 周六
 */

@RestController
@RequestMapping("demo")
@Slf4j
public class DemoController {

	@GetMapping("get_time")
	public CommonResponse testTime(){
		DemoA demoA = new DemoA();
		demoA.setDate(LocalDate.now());
		demoA.setDateTime(LocalDateTime.now());
		demoA.setLocalTime(LocalTime.now());
		return CommonResponse.success(demoA);
	}

	@PostMapping("/bind/time")
	public CommonResponse testTimeBind(DemoA demoA) {

		return CommonResponse.success(demoA);
	}

	@PostMapping("/bind/list")
	public CommonResponse bindListData(ListData data) {

		return CommonResponse.success(data);
	}

	@PostMapping("/bind/list/2")
	public CommonResponse bindListData(@RequestParam("numbers") List<Integer> numbers,
	                                   @RequestParam("files") MultipartFile file) {


		return CommonResponse.success(numbers);
	}

	@GetMapping("/bind/list")
	public CommonResponse getListData(@RequestParam("numbers") List<Integer> numbers) {

		return CommonResponse.success(numbers);
	}

	@PostMapping("/bind/time/rest")
	public CommonResponse testTimeBindJson(@RequestBody DemoA demoA) {

		return CommonResponse.success(demoA);
	}


	@PostMapping("/bind/list/data")
	public CommonResponse testListData(@RequestParam("numbers") List<Integer> numbers) {


		log.info("接收的参数为:{}", numbers);

		return CommonResponse.success(numbers);
	}


	@PostMapping("/bind/list/data/2")
	public CommonResponse testListDataObject(ListData numbers) {


		log.info("接收的参数为:{}", numbers);

		return CommonResponse.success(numbers);
	}


	@GetMapping("/file/path")
	public CommonResponse testFilePath(HttpServletRequest request) {
		String absolutePath = new File("").getAbsolutePath();
		log.info("project abs path is:{}", absolutePath);

		ServletContext servletContext = request.getSession().getServletContext();
		String realPath = servletContext.getRealPath("/");
		log.info("tomcat real path is:{}", realPath);

		String realPath1 = servletContext.getRealPath("/download");
		log.info("tomcat real path1 is:{}", realPath1);

		String exportPath = "/tmp/export";
		String dirPath = absolutePath + exportPath;
		File file = new File(dirPath);
		if (!file.exists()) {
			boolean mkdirs = file.mkdirs();
			log.info("在{}创建目录成功？:{}", dirPath, mkdirs);
		}
		System.out.println("hehehahah");
		for (int i = 0; i < 10; i++) {
			System.out.println("Absfds");
		}
		return CommonResponse.success(absolutePath);
	}

	public static void main(String[] args) {
		// Integer x = null;
		// System.out.println(x);
		// Long integer = Optional.ofNullable(x).map(Integer::longValue).orElse(null);
		// System.out.println(integer);
		Function<Object, Integer> function = new Function<>() {

			@Override
			public Integer apply(Object o) {
				String s = String.valueOf(o);
				return Integer.parseInt(s) + 5;
			}
		};
		Integer apply = function.apply("3");
		System.out.println(apply);
		Student student = new Student();
		student.setAge(22);
		student.setName("glq");

		Integer fn = fn(Student::getAge, student);

		System.out.println(fn);
	}

	private static <T> Integer fn(Function<T, Integer> getter, T num) {
		return getter.apply(num);
	}

	@Data
	static class Student {
		private Integer age;
		private String name;
	}


}

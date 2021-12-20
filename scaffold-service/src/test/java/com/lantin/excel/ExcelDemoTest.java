package com.lantin.excel;

import com.alibaba.excel.EasyExcel;
import com.lantin.excel.dto.DemoData;
import com.lantin.util.TestFileUtil;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Gan Luanqing
 * @date 2021/12/17 16:17 周五
 */

public class ExcelDemoTest {

	@Test
	public void test() {


		URL resource1 = ExcelDemoTest.class.getResource("/");

		String rootPath = this.getClass().getResource("/a.txt").getFile();
		System.out.println(rootPath);


		String rootPath1 = this.getClass().getResource("/a.txt").toString();
		System.out.println(rootPath1);


		String rootPath2 = this.getClass().getResource("/").toString();
		System.out.println(rootPath2);

		String rootPath3 = this.getClass().getResource("").toString();
		System.out.println(rootPath3);
	}

	@Test
	public void test1() {

// 写法1 JDK8+
		// since: 3.0.0-beta1
		String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		// 如果这里想使用03 则 传入excelType参数即可
		EasyExcel.write(fileName, DemoData.class)
				.sheet("模板")
				.doWrite(() -> {
					// 分页查询数据
					return data();
				});

		// 写法2
		// fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
		// // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		// // 如果这里想使用03 则 传入excelType参数即可
		// EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

		// 写法3
		// fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
		// // 这里 需要指定写用哪个class去写
		// ExcelWriter excelWriter = null;
		// try {
		// 	excelWriter = EasyExcel.write(fileName, DemoData.class).build();
		// 	WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
		// 	excelWriter.write(data(), writeSheet);
		// } finally {
		// 	// 千万别忘记finish 会帮忙关闭流
		// 	if (excelWriter != null) {
		// 		excelWriter.finish();
		// 	}
		// }
	}

	private List<DemoData> data() {
		List<DemoData> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			DemoData data = new DemoData();
			data.setString("字符串" + i);
			data.setDate(new Date());
			data.setDoubleData(0.56);
			list.add(data);
		}
		return list;
	}
}

package com.lantin.web.controller.demo;

import com.lantin.common.domain.response.CommonResponse;
import com.lantin.web.domain.DemoA;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gan Luanqing
 * @date 2021/12/04 19:55 周六
 */

@RestController
@RequestMapping("demo")
public class DemoController {


	@PostMapping("/bind/time")
	public CommonResponse testTimeBind(DemoA demoA) {

		return CommonResponse.success(demoA);
	}

	@PostMapping("/bind/time/rest")
	public CommonResponse testTimeBindJson(@RequestBody DemoA demoA) {

		return CommonResponse.success(demoA);
	}
}

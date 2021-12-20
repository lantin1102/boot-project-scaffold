package com.lantin.web.controller.mall;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantin.common.domain.response.CommonResponse;
import com.lantin.web.domain.mall.MallUser;
import com.lantin.web.service.mall.MallUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gan Luanqing
 */
@Slf4j
@RestController
@RequestMapping("mallUser")
public class MallUserController {

	@Autowired
	private MallUserService mallUserService;
	@GetMapping("/{id}")
	public CommonResponse<MallUser> findUser(@PathVariable("id") Integer id) {
		LambdaQueryWrapper<MallUser> eq = new LambdaQueryWrapper<MallUser>()
				.eq(MallUser::getId, id);
		MallUser one = mallUserService.getOne(eq);
		return CommonResponse.success(one);
	}


}

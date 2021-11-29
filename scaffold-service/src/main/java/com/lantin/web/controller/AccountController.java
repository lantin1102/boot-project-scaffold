package com.lantin.web.controller;


import com.lantin.common.domain.response.CommonResponse;
import com.lantin.web.domain.account.dto.AccountDto;
import com.lantin.web.domain.account.vo.AccountVo;
import com.lantin.web.service.account.AccountService;
import com.lantin.web.service.account.convert.AccountConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gan Luanqing
 */
@Slf4j
@RestController
@RequestMapping("account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountConvert accountConvert;

	@GetMapping("{id}")
	public CommonResponse<AccountVo> getUserAccount(@PathVariable("id") Integer id) {
		AccountDto account = accountService.findAccount(id);
		account.setCtime(LocalDateTime.now());
		return CommonResponse.success(accountConvert.dto2Vo(account));
	}

	@PostMapping
	public CommonResponse<Void> saveAccount(AccountVo accountVo) {
		return CommonResponse.success();
	}
}

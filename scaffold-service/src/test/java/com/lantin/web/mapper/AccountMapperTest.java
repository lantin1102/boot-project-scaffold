package com.lantin.web.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantin.test.base.BaseSpringBootTest;
import com.lantin.web.domain.account.Account;
import com.lantin.web.mapper.db1.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 21:47 周六
 */

class AccountMapperTest extends BaseSpringBootTest {
	@Autowired
	AccountMapper accountMapper;

	@BeforeEach
	void setUp() {
	}

	@Test
	public void test(){
		LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Account::getId,1);
		Account account = accountMapper.selectOne(queryWrapper);

		System.out.println(account);
	}
}
package com.lantin.web.service;

import com.lantin.test.base.BaseSpringBootTest;
import com.lantin.web.domain.account.Account;
import com.lantin.web.domain.book.Book;
import com.lantin.web.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 18:07 周六
 */

class AccountServiceTest extends BaseSpringBootTest {

	@Autowired
	AccountService accountService;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
	}

	@org.junit.jupiter.api.Test
	void findAccount() {
		System.out.println(accountService.findAccount(3));
	}

	@org.junit.jupiter.api.Test
	void saveAccount() {
		Account account = new Account();
		account.setUsername("打工人");
		account.setMoney(400d);
		Account account1 = accountService.saveAccount(account);

		System.out.println(account1);
	}

	@Test
	public void pageList(){
		Account account = new Account();
		account.setUsername("ang");
		System.out.println(accountService.pageListAccount(account));
	}

	@Test
	public void modify(){

	}
}
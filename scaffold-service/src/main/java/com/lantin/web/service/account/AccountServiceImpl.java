package com.lantin.web.service.account;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantin.web.domain.account.dto.AccountDto;
import com.lantin.web.domain.account.Account;
import com.lantin.web.mapper.AccountMapper;
import com.lantin.web.service.account.convert.AccountConvert;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

	@Autowired
	private AccountConvert accountConvert;

	@Override
	public AccountDto findAccount(Integer id) {
		LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<Account>()
				.eq(Account::getId, id);

		return accountConvert.entity2Dto(this.getOne(queryWrapper));
	}

	@Override
	public Account saveAccount(Account account) {
		this.save(account);
		return account;
	}

	@Override
	public List<Account> listAccount() {
		return this.list();
	}

	@Override
	public List<Account> pageListAccount(Account account) {
		Page<Account> page = new Page<>(1, 5);
		LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<Account>()
				.like(Account::getUsername, account.getUsername());
		// .like(StringUtils.hasLength(account.getUsername()),Account::getUsername,account.getUsername());
		// .like(StringUtils.hasLength(account.getUsername()),Account::getUsername,account.getUsername());

		page = this.page(page, queryWrapper);
		return page.getRecords();
	}


}

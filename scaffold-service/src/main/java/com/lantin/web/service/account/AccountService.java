package com.lantin.web.service.account;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lantin.web.domain.account.Account;
import com.lantin.web.domain.account.dto.AccountDto;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Gan Luanqing
 * @since 2021-11-27
 */
public interface AccountService extends IService<Account> {

	AccountDto findAccount(Integer id);

	Account saveAccount(Account account);

	List<Account> listAccount();

	List<Account> pageListAccount(Account account);

}

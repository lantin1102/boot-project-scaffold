package com.lantin.web.service.account.convert;

import com.lantin.web.domain.account.Account;
import com.lantin.web.domain.account.dto.AccountDto;
import com.lantin.web.domain.account.vo.AccountVo;
import org.mapstruct.Mapper;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 21:08 周六
 */
@Mapper(componentModel = "spring")
public interface AccountConvert {

	AccountDto vo2Dto(AccountVo accountVo);

	AccountVo dto2Vo(AccountDto accountDto);

	Account dto2Entity(AccountDto accountDto);

	AccountDto entity2Dto(Account account);
}

package com.lantin.web.service.activity;

import com.lantin.web.domain.activity.UserReceiveRecord;
import com.lantin.web.domain.activity.UserReceiveRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Gan Luanqing
 * @date 2021/12/19 14:07 周日
 */
@Mapper
public interface UserReceiveRecordConvert {

	UserReceiveRecordConvert INSTANCE  = Mappers.getMapper(UserReceiveRecordConvert.class);

	UserReceiveRecordDto entity2Dto(UserReceiveRecord receiveRecord);

}

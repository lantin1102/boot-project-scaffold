package com.lantin.web.service.activity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantin.web.domain.activity.ActivityPrize;
import com.lantin.web.domain.activity.UserReceiveRecordDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gan Luanqing
 * @since 2021-12-19
 */
public interface ActivityPrizeService extends IService<ActivityPrize> {


	UserReceiveRecordDto drawPrize(String prizeId, Long uid);



}

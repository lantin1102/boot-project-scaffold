package com.lantin.web.service.activity.impl;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantin.web.domain.activity.ActivityPrize;
import com.lantin.web.domain.activity.UserReceiveRecord;
import com.lantin.web.domain.activity.UserReceiveRecordDto;
import com.lantin.web.mapper.db1.activity.ActivityPrizeMapper;
import com.lantin.web.service.activity.ActivityPrizeService;
import com.lantin.web.service.activity.UserReceiveRecordConvert;
import com.lantin.web.service.activity.UserReceiveRecordService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gan Luanqing
 */
@Slf4j
@Service
public class ActivityPrizeServiceImpl extends ServiceImpl<ActivityPrizeMapper, ActivityPrize> implements ActivityPrizeService {
	@Autowired
	private UserReceiveRecordService userReceiveRecordService;
	@Autowired
	private RedissonClient redissonClient;

	private static final String thankPrizeId = "1472441211351871494";


	@Override
	public UserReceiveRecordDto drawPrize(String prizeId, Long uid) {
		// 更新中奖记录库存
		ActivityPrize activityPrize = choosePrize(prizeId, uid);
		// 保存用户获奖记录
		UserReceiveRecord userReceiveRecord = saveUserRecord(activityPrize.getPrizeId(), uid);
		return UserReceiveRecordConvert.INSTANCE.entity2Dto(userReceiveRecord);
	}

	private ActivityPrize choosePrize(String prizeId, Long uid) {
		ActivityPrize one = lambdaQuery().eq(ActivityPrize::getPrizeId, prizeId)
				.one();
		if (one.getStock() <= 0) {
			one = findThankPrize();
		} else {
			updatePrizeStock(one, uid);
		}
		return one;
	}

	private void updatePrizeStock(ActivityPrize prize, Long uid) {
		int updated = baseMapper.updatePrizeStock(prize);
		if (updated == 0) {
			log.info("库存更新失败,prizeId:{},uid:{}", prize.getPrizeId(), uid);
		}
	}

	private ActivityPrize findThankPrize() {
		LambdaQueryChainWrapper<ActivityPrize> eq = lambdaQuery().eq(ActivityPrize::getPrizeId, thankPrizeId);
		return getOne(eq);
	}

	private UserReceiveRecord saveUserRecord(String prizeId, Long uid) {
		UserReceiveRecord userReceiveRecord = UserReceiveRecord.builder()
				.recordId(IdWorker.getIdStr())
				.prizeId(prizeId)
				.uid(uid)
				.receiveTime(LocalDateTime.now())
				.build();
		userReceiveRecordService.save(userReceiveRecord);
		return userReceiveRecord;
	}
}

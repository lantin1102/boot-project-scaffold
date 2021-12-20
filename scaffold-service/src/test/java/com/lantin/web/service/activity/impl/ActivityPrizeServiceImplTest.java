package com.lantin.web.service.activity.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantin.test.base.BaseSpringBootTest;
import com.lantin.web.domain.activity.ActivityPrize;
import com.lantin.web.service.activity.ActivityPrizeService;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Gan Luanqing
 * @date 2021/12/19 14:32 周日
 */
class ActivityPrizeServiceImplTest extends BaseSpringBootTest {
	@Autowired
	ActivityPrizeService activityPrizeService;
	String prizeId = "1472441211351871493";
	@Autowired
	ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Autowired
	RedissonClient redissonClient;

	@Test
	void drawPrize() throws InterruptedException {
		Random random = new Random();
		Integer randomUid = getRandomUid(random);
		RLock lock = redissonClient.getLock("lottery.draw.prize.lock&" + prizeId);
		if (lock.tryLock(3, TimeUnit.SECONDS)) {
			activityPrizeService.drawPrize(prizeId, randomUid.longValue());
		} else {
			System.out.println("用户抽奖获取锁失败:" + randomUid);
		}
	}

	private Integer getRandomUid(Random random) {
		return random.nextInt(Integer.MAX_VALUE);
	}

	@Test
	public void test() {
		LambdaQueryWrapper<ActivityPrize> eq = new LambdaQueryWrapper<ActivityPrize>()
				.eq(ActivityPrize::getPrizeId, prizeId);
		ActivityPrize one = activityPrizeService.getOne(eq);
		System.out.println(one);

		ActivityPrize one1 = activityPrizeService.lambdaQuery()
				.eq(ActivityPrize::getPrizeId, prizeId)
				.one();

		System.out.println(one1
		);
	}

	@Test
	public void testDraw() throws InterruptedException {
		Random random = new Random();
		for (int i = 0; i < 200; i++) {
			threadPoolTaskExecutor.execute(() ->
			{
				long uid = getRandomUid(random).longValue();
				RLock lock = redissonClient.getLock("lottery.draw.prize.lock&" + prizeId);
				try {
					if (lock.tryLock(1, TimeUnit.MINUTES)) {
						activityPrizeService.drawPrize(prizeId, uid);
					} else {
						System.out.println("用户抽奖获取锁失败:" + uid);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}


		Thread.sleep(1000 * 60 * 60);

	}

	@Test
	public void testThread() throws InterruptedException {

		for (int i = 0; i < 250; i++) {
			int finalI = i;
			threadPoolTaskExecutor.execute(() -> {
				System.out.println(Thread.currentThread().getName() + "开始任务:" + finalI);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "执行了任务" + finalI + "完毕");
			});
		}


		Thread.sleep(1000 * 60 * 60);
	}

}
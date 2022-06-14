package com.lantin.web.service.test;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantin.web.domain.test.TestCount1;
import com.lantin.web.listener.TestEvent;
import com.lantin.web.mapper.test.TestCount1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lantin
 * @description 针对表【test_count1】的数据库操作Service实现
 * @createDate 2022-06-14 15:38:08
 */
@Service
public class TestCount1ServiceImpl extends ServiceImpl<TestCount1Mapper, TestCount1>
		implements TestCount1Service {

	@Autowired
	private TestCount1Mapper testCount1Mapper;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;

	@Override
	@Transactional
	public void incrCount(Integer id) {
		LambdaUpdateWrapper<TestCount1> wrapper = new LambdaUpdateWrapper<>();
		wrapper.eq(TestCount1::getId, id);
		wrapper.setSql("count=count+1");
		TestCount1 testCount1 = new TestCount1();
		testCount1.setId(id);
		testCount1Mapper.update(null, wrapper);
		TestEvent testEvent = new TestEvent(this, "更新count for" + id);
		testEvent.setId(id);
		// 异步发布事件不会导致当前线程的回滚
		taskExecutor.execute(() -> eventPublisher.publishEvent(testEvent));
		// eventPublisher.publishEvent(testEvent);
	}
}





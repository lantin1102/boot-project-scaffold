package com.lantin.web.listener;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lantin.web.domain.test.TestCount1;
import com.lantin.web.mapper.test.TestCount1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class TestEventListener implements ApplicationListener<TestEvent> {

	@Autowired
	private TestCount1Mapper testCount1Mapper;

	// 只回滚这一任务
	@Override
	@Transactional
	public void onApplicationEvent(TestEvent event) {
		Integer id = event.getId();
		// incr db
		LambdaUpdateWrapper<TestCount1> wrapper = new LambdaUpdateWrapper<>();
		wrapper.eq(TestCount1::getId, id);
		wrapper.setSql("count=count+2");
		TestCount1 testCount1 = new TestCount1();
		testCount1.setId(id);
		testCount1Mapper.update(null, wrapper);
		int i = 1 / 0;
	}
}

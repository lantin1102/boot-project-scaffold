package com.lantin.framework.config.mybatisplus;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lantin.test.base.BaseSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gan Luanqing
 * @date 2021/12/19 13:33 周日
 */
class MybatisPlusConfigTest extends BaseSpringBootTest {

	@Autowired
	IdentifierGenerator identifierGenerator;

	@Test
	public void test() {
		long id = IdWorker.getId();
		System.out.println(id);
		String idStr = IdWorker.getIdStr();
		System.out.println(idStr);
		String timeId = IdWorker.getTimeId();
		System.out.println(timeId);

		for (int i = 0; i < 20; i++) {

			System.out.println(identifierGenerator.nextId(new Object()).toString());
		}
		String uuid = IdWorker.get32UUID();
		System.out.println(uuid);
	}

}
package com.lantin.config;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lantin.test.base.BaseSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gan Luanqing
 * @date 2021/11/28 20:21 周日
 */
public class IdWorkerTest extends BaseSpringBootTest {

	@Test
	public void test(){

		long id = IdWorker.getId();

		System.out.println(id);
	}

}

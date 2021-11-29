package com.lantin.config;

import com.lantin.test.base.BaseSpringBootTest;
import com.lantin.web.domain.book.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

/**
 * @author Gan Luanqing
 * @date 2021/11/28 23:16 周日
 */
public class RedissonConfigTest extends BaseSpringBootTest {

	@Autowired
	RedissonClient redissonClient;

	@Test
	public void test() throws ExecutionException, InterruptedException {

		System.out.println(redissonClient);
		String keyPre = "domain:boot:";
		String key1 = keyPre + 3;
		RBucket<BookDto> bucket = redissonClient.getBucket(key1);
		BookDto bookDto = new BookDto();
		bookDto.setId(3);
		bookDto.setType("33");
		bookDto.setDescription("hehe");
		bookDto.setName("haha");
		bucket.set(bookDto);
		RFuture<Object> async = redissonClient.getBucket(key1).getAsync();
		Object o = async.get();
		System.out.println(o);
	}
}

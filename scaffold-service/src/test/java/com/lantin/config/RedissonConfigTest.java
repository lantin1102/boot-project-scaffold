package com.lantin.config;

import com.lantin.test.base.BaseSpringBootTest;
import com.lantin.web.domain.book.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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
	@Test
	public void test11(){
		String key = "fad";
		Integer obj  = (Integer) redissonClient.getBucket(key).get();
		Assert.notNull(obj);

		boolean exists = redissonClient.getBucket(key).isExists();
		Assert.isTrue(exists);
		// long fad = redissonClient.getAtomicLong(key).getAndSet(1);


		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		list.parallelStream()
				.forEach((num)->{
					long l = redissonClient.getAtomicLong(key).incrementAndGet();
					if (l==1){
						redissonClient.getAtomicLong(key).expire(360, TimeUnit.SECONDS);
					}
				});
	}
}

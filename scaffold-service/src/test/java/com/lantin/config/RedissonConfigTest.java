package com.lantin.config;

import com.lantin.test.base.BaseSpringBootTest;
import com.lantin.web.domain.JsonData;
import com.lantin.web.domain.book.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RFuture;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
	public void test1(){
		System.out.println(Integer.MAX_VALUE);
	}

	@Test
	public void testJavaTime(){
		JsonData jsonData = new JsonData();

		jsonData.setLocalTime(LocalTime.now());
		jsonData.setDateTime(LocalDateTime.now());

		redissonClient.getBucket("test.time").set(jsonData);
	}

	@Test
	public void testAtomicLong(){
		String key = "test.atomic.long&1";
		long l1 = redissonClient.getAtomicLong(key).incrementAndGet();
		int l =(int) l1;
		System.out.println(l);
		System.out.println(l1);

		List<String> keyList = new ArrayList<>();
		keyList.add(key);
		Map<String, Object> stringObjectMap = redissonClient.getBuckets().get(keyList.toArray(new String[0]));
		String s = String.valueOf(stringObjectMap.get(key));

		System.out.println(s);



	}

	@Test
	public void testAtomicLong2(){
		String list = "43605253,43609393,43606531,43607342,527771530,3509221,39816453,286280139,43610599,39464379";

		String[] split = list.split(",");

		List<String> strings = Arrays.asList(split);

		System.out.println(strings);
	}

	@Test
	public void testMget(){
		List<String> keyList = new ArrayList<>();
		String key1 = "activity.user.behavior.count:5985330293082368:2334&LIKE";
		keyList.add(key1);
		Map<String, Object> stringObjectMap = redissonClient.getBuckets().get(keyList.toArray(new String[0]));

		// String s = stringObjectMap.get(key1);
	}

	@Test
	public void testAtomicLong3() throws ExecutionException, InterruptedException {

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
	public void testRList(){
		RList<Object> bList = redissonClient.getList("bList");

		// for (int i = 0; i < 5; i++) {
		// 	bList.add(new Object());
		// }
		// for (Object o : bList) {
		// 	System.out.println(o);
		// }

		RList<Object> cList = redissonClient.getList("cList");

		if (!cList.isExists()){

			List<Object> dbData = null;
			cList.addAll(dbData);
			cList.expire(10,TimeUnit.HOURS);
		}

		RList<Object> cList1 = redissonClient.getList("cList");

		cList1.add(new Object());
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

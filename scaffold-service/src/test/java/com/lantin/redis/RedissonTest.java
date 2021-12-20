package com.lantin.redis;

import com.lantin.common.utils.KryoUtils;
import com.lantin.test.base.BaseSpringBootTest;
import com.lantin.web.domain.account.Account;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBinaryStream;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Gan Luanqing
 * @date 2021/12/12 0:52 周日
 */
public class RedissonTest extends BaseSpringBootTest {
	@Autowired
	RedissonClient redissonClient;

	@Test
	public void test() {

		// byte[] b1 = (byte[]) redissonClient.getBucket("demo:test:bytearr").get();
		// byte[] b2 = (byte[]) redissonClient.getBucket("demo:test:marshell").get();
		// Account deserialize1 = KryoUtils.deserialize(b2, Account.class);
		// System.out.println(deserialize1);
		// byte[] b3 = (byte[]) redissonClient.getBucket("demo:test:kryo5").get();
		// Account account = (Account) redissonClient.getBucket("demo:test:kryo5").get();
		// // Account deserialize2 = KryoUtils.deserialize(b3, Account.class);
		// System.out.println(account);

		// Account deserialize = KryoUtils.deserialize(b1, Account.class);

		// System.out.println(deserialize);
		RBinaryStream binaryStream = redissonClient.getBinaryStream("demo:test:bistream:kryo5");
		byte[] bytes = binaryStream.get();

		List<Account> accounts = KryoUtils.deserializeList(bytes, Account.class);

		System.out.println(accounts);
	}
}

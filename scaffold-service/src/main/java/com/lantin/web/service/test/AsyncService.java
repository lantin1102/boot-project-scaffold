package com.lantin.web.service.test;


import com.lantin.web.exception.MallReleaseAssetRetryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncService {




	@Async
	@Retryable( value = MallReleaseAssetRetryException.class,
			backoff = @Backoff(delay = 2000L, multiplier = 2))
	public void serviceA(int x) {


		try {
			log.info("thread:{} 执行serviceA任务", Thread.currentThread().getName());
			Thread.sleep(2000);
			if (x % 2 == 1) {
				throw new MallReleaseAssetRetryException("远程调用失败");
			}
			log.info("thread:{} serviceA执行完毕", Thread.currentThread().getName());
		} catch (InterruptedException ignore) {
		}

		// return CommonResponse.success("success");
	}

	@Recover
	public void fallback(Exception e, int x) {
		log.error("execute serviceA  fail go to fallback,request param:{}", x, e);
	}


}

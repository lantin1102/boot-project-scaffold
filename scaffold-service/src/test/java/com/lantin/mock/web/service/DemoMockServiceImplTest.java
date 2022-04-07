// package com.lantin.mock.web.service;
//
// import com.lantin.common.exception.BusinessException;
// import com.lantin.web.service.DemoMockServiceImpl;
// import com.lantin.web.service.StaticService;
// import org.junit.Test;
// import org.mockito.InjectMocks;
// import org.powermock.api.mockito.PowerMockito;
// import org.springframework.util.Assert;
//
//
//
//
// // @RunWith(value = PowerMockRunner.class)
// // @PrepareForTest({StaticService.class})
// class DemoMockServiceImplTest {
//
// 	@InjectMocks
// 	DemoMockServiceImpl demoMockService;
//
// 	@Test
// 	void saveChance() {
// 		Integer chance = 5;
// 		Integer id = 11;
// 		PowerMockito.mockStatic(StaticService.class);
// 		PowerMockito.when(StaticService.method1(any())).thenReturn(null);
//
// 		try {
// 			Integer integer = demoMockService.saveChance(chance, id);
// 		} catch (Exception e) {
// 			Assert.isTrue(e instanceof BusinessException);
// 		}
//
// 		// DemoMockServiceImpl demoMockService = new DemoMockServiceImpl();
// 		// DemoMockServiceImpl spy = PowerMockito.spy(demoMockService);
// 		// PowerMockito.when(spy.getChance(anyInt())).thenReturn(null);
// 		// Integer integer = spy.saveChance(chance, id);
// 		// Assert.isNull(integer);
//
//
// 	}
// }
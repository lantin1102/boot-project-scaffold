package com.lantin.web.service;

import com.lantin.common.exception.BusinessException;

public class DemoMockServiceImpl implements DemoMockService {
	@Override
	public Integer saveChance(int chance, Integer id) {
		Integer chance1 = this.getChance(id);
		if (chance1 == null) {
			return null;
		}
		Object o = StaticService.method1(id);
		if (o == null) {
			throw new BusinessException();
		}
		System.out.println("保存 save chance");
		return chance;
	}

	@Override
	public Integer getChance(Integer id) {
		if (id < 0) {
			return null;

		}
		return id + 100;
	}
}

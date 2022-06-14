package com.lantin.web.service.test;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantin.web.domain.test.TestCount1;

/**
* @author lantin
* @description 针对表【test_count1】的数据库操作Service
* @createDate 2022-06-14 15:38:08
*/
public interface TestCount1Service extends IService<TestCount1> {



	void incrCount(Integer id);

}

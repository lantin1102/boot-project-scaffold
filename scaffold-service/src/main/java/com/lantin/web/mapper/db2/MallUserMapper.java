package com.lantin.web.mapper.db2;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lantin.web.domain.mall.MallUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Gan Luanqing
 */
@DS("db2")
public interface MallUserMapper extends BaseMapper<MallUser> {

}

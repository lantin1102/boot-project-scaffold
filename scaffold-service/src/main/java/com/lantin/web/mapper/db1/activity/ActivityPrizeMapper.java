package com.lantin.web.mapper.db1.activity;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lantin.web.domain.activity.ActivityPrize;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Gan Luanqing
 */
public interface ActivityPrizeMapper extends BaseMapper<ActivityPrize> {


	int updatePrizeStock(@Param("record") ActivityPrize record);

}

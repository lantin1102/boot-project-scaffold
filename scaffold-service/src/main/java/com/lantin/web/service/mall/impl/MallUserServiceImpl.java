package com.lantin.web.service.mall.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantin.web.domain.mall.MallUser;
import com.lantin.web.mapper.db2.MallUserMapper;
import com.lantin.web.service.mall.MallUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gan Luanqing
 */
@Slf4j
@Service
public class MallUserServiceImpl extends ServiceImpl<MallUserMapper, MallUser> implements MallUserService {

}

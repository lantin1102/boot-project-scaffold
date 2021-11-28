package com.lantin.framework.config.mybatisplus;

import com.lantin.common.constant.BaseConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Gan Luanqing
 * @date 2021/11/28 20:11 周日
 */
@Data
@ConfigurationProperties(prefix = BaseConstants.COM_LANTIN + ".id-worker")
public class IdWorkerProperties {

	private Long workId = 1L;

	private Long dataCenterId = 1L;

}

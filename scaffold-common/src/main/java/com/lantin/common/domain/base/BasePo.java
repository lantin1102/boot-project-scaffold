package com.lantin.common.domain.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据库实体类的基类
 *
 * @author Gan Luanqing
 * @date 2021/11/27 20:51 周六
 */
@Data
public class BasePo implements Serializable {

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	// @TableField(fill = FieldFill.INSERT)
	// protected LocalDateTime ctime;
	// @TableField(fill = FieldFill.INSERT_UPDATE)
	// protected LocalDateTime mtime;
}

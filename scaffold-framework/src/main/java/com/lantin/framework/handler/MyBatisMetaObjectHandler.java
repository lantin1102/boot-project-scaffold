package com.lantin.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @Description：自动填充
 */
@Slf4j
public class MyBatisMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("开始插入填充.....");
		if (metaObject.hasSetter("createdTime")) {
			this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, LocalDateTime.now());
		}
		if (metaObject.hasSetter("updatedTime")) {
			this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now());
		}
		if (metaObject.hasSetter("ctime")) {
			this.strictInsertFill(metaObject, "ctime", LocalDateTime.class, LocalDateTime.now());
		}
		if (metaObject.hasSetter("mtime")) {
			this.strictInsertFill(metaObject, "mtime", LocalDateTime.class, LocalDateTime.now());
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("开始更新填充.....");
		if (metaObject.hasSetter("updatedTime")) {
			this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now());
		}
		if (metaObject.hasSetter("mtime")) {
			this.strictUpdateFill(metaObject, "mtime", LocalDateTime.class, LocalDateTime.now());
		}
	}
}

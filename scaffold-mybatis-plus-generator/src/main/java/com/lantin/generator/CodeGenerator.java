package com.lantin.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;

/**
 * @author Gan Luanqing
 * @date 2021/11/27 17:37 周六
 */
public class CodeGenerator {
	// TODO 修改服务名以及数据表名
	private static final String SERVICE_NAME = "mall";
	private static final String DATA_SOURCE_USER_NAME = "lantin";
	private static final String DATA_SOURCE_PASSWORD = "sxdBETsi0nPhgXgTTGlb9cwo84jcW6UXXhBLt2vujWo";
	private static final String JDBC_URL = "jdbc:mysql://home.ganlq.cn:5307";
	private static final String URL_CONFIG_STR = "useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8";
	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String PACKAGE_PREFIX = "com.lantin.";

	private static final String[] TABLE_NAMES = new String[]{
			"mall_user"
	};
	// TODO 默认生成entity，需要生成DTO修改此变量
	// 一般情况下要先生成 DTO类 然后修改此参数再生成 PO 类。
	private static final Boolean IS_DTO = false;

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 选择 freemarker 引擎，默认 Velocity
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setFileOverride(true);
		gc.setOutputDir(System.getProperty("user.dir") + "/scaffold-mybatis-plus-generator/src/main/java");
		gc.setAuthor("Gan Luanqing");
		gc.setOpen(false);
		gc.setSwagger2(false);
		gc.setServiceName("%sService");
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);

		if (IS_DTO) {
			gc.setSwagger2(true);
			gc.setEntityName("%sDTO");
		}
		mpg.setGlobalConfig(gc);

		// 数据库配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setUrl(JDBC_URL + "/" + SERVICE_NAME
				+ "?" + URL_CONFIG_STR);
		dsc.setDriverName(DRIVER_CLASS_NAME);
		dsc.setUsername(DATA_SOURCE_USER_NAME);
		dsc.setPassword(DATA_SOURCE_PASSWORD);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(PACKAGE_PREFIX + SERVICE_NAME);

		pc.setServiceImpl("service.impl");
		pc.setXml("mapper");
		mpg.setPackageInfo(pc);


		// 设置模板
		TemplateConfig tc = new TemplateConfig();
		mpg.setTemplate(tc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		strategy.setInclude(TABLE_NAMES);
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		// Boolean类型字段是否移除is前缀处理
		strategy.setEntityBooleanColumnRemoveIsPrefix(true);
		strategy.setRestControllerStyle(true);
		// 自动填充字段配置
		strategy.setTableFillList(Arrays.asList(
				new TableFill("create_date", FieldFill.INSERT),
				new TableFill("change_date", FieldFill.INSERT_UPDATE),
				new TableFill("modify_date", FieldFill.UPDATE),
				new TableFill("ctime", FieldFill.INSERT),
				new TableFill("mtime", FieldFill.INSERT_UPDATE)
		));
		mpg.setStrategy(strategy);

		mpg.execute();
	}

}

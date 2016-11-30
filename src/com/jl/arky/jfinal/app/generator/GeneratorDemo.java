package com.jl.arky.jfinal.app.generator;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * GeneratorDemo
 */
public class GeneratorDemo {
	public static final String DATABASE_USERNAME = "root";// 用户名
	public static final String DATABASE_PASSWORD = "root";// 密码
	public static final String DATABASE_NAME = "mxdata";// 数据库名
	public static final String DATABASE_HOST = "localhost";// 主机地址
	public static final String DATABASE_PORT = "3306";// 端口
	public static final String MYSQL_HOME = "";
	public static DataSource getDataSource() {
//		Prop p = PropKit.use("appconfig.txt");
//		C3p0Plugin c3p0Plugin = new C3p0Plugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
		C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:mysql://" + DATABASE_HOST + ":" + DATABASE_PORT + "/" + DATABASE_NAME
				+ "?useUnicode=true&characterEncoding=UTF-8", DATABASE_USERNAME, DATABASE_PASSWORD);
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}

	public static void main(String[] args) {
		String baseModelPackageName = "com.jl.arky.jfinal.app.model.base";
		String baseModelOutputDir = PathKit.getWebRootPath() + "/../src/com/jl/arky/jfinal/app/model/base";

		String modelPackageName = "com.jl.arky.jfinal.app.model";
		String modelOutputDir = baseModelOutputDir + "/..";

		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		gernerator.setDialect(new MysqlDialect());
		gernerator.addExcludedTable("adv");
		gernerator.setGenerateDaoInModel(true);
		gernerator.setGenerateDataDictionary(false);
		gernerator.setRemovedTableNamePrefixes("wu_");
		gernerator.generate();
	}
}





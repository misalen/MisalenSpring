package org.misalen.util;

/**
 * Hello world!
 *
 */
public class MainGenerator {

	static String user = "root";
	static String password = "MY2015sql..";
	static String dbUrl = "jdbc:mysql://118.190.113.219:3306/misalentest?useUnicode=true&characterEncoding=utf-8&useSSL=true&autoReconnect=true";
	static String driverClassName = "com.mysql.jdbc.Driver";

	public static void main(String[] args) throws Exception {

		// Class<?> clazz = SysAdmin.class;
		// Named name = new Named(clazz.getSimpleName());
		//
		// ModelGenerator modelGenerator = new ModelGenerator(user,
		// password,dbUrl, driverClassName);
		// modelGenerator.bulid("TEST").save("model.ftl","../misalen-spring-db-jpa/src/main/java/org/misalen/db/jpa/pro/domain/");
		// log("生成Model成功");
		//
		// RepositoryGenerator repositoryGenerator = new RepositoryGenerator();
		// repositoryGenerator.bulid(name).save("repository.ftl",
		// "../misalen-spring-generator/src/main/java/org/misalen/generator/repository/");
		// log("生成repository成功");
		//
		// ServiceGenerator serviceGenerator = new ServiceGenerator();
		// serviceGenerator.bulid(name).save("service.ftl",
		// "../misalen-spring-generator/src/main/java/org/misalen/generator/service/");
		// log("生成service成功");

		// AdminControllerGenerator adminControllerGenerator = new
		// AdminControllerGenerator();
		// adminControllerGenerator.bulid(name).save("admin-controller.ftl",
		// "../misalen-spring-generator/src/main/java/org/misalen/generator/controllers/");
		// log("生成Controller成功");
		//
		// PageListGenerator pageListGenerator = new PageListGenerator();
		// pageListGenerator.bulid(clazz).save("page-list.ftl",
		// "page-list.th.ftl",
		// "../misalen-spring-generator/src/main/resources/templates/");
		// log("生成list页面成功");
		//
		// PageAddGenerator pageAddGenerator = new PageAddGenerator();
		// pageAddGenerator.bulid(clazz).save("page-add.ftl", "page-add.th.ftl",
		// "../misalen-spring-generator/src/main/resources/templates/");
		// log("生成添加页面成功");
		//
		// PageUpdateGenerator pageUpdateGenerator = new PageUpdateGenerator();
		// pageUpdateGenerator.bulid(clazz).save("page-update.ftl",
		// "page-update.th.ftl",
		// "../misalen-spring-generator/src/main/resources/templates/");
		// log("生成更新页面成功");
	}

	public static void log(String str) {
		System.err.println("============================================");
		System.err.println("==========" + str);
		System.err.println("============================================");
	}
}

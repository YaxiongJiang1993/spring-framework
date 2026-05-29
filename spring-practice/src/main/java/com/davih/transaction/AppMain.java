/*
 * Copyright 2002-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.davih.transaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main application class.
 *
 * test
 *
 * Spring事务管理器，创建数据库连接conn (Spring transaction manager, creates database connection conn)
 * conn.autocommit = false;
 * conn.隔离级别 (conn isolation level)
 * conn放入ThreadLocal<Map> DataSource, conn连接 (Put conn into ThreadLocal<Map> DataSource, conn connection)
 * target.test() sql1, sql2,
 *
 *     a()
 *     挂起 --> 挂起对象.conn连接 --> (Suspend --> Suspended object.conn connection -->)
 *     Spring事务管理器，创建数据库连接conn1 (Spring transaction manager, creates database connection conn1)
 *     conn1.autocommit = false;
 *     conn1.隔离级别 (conn1 isolation level)
 *     conn1放入ThreadLocal<Map> DataSource, conn1连接 (Put conn1 into ThreadLocal<Map> DataSource, conn1 connection)
 *     sql
 *     conn1.提交 (conn1 submit/commit)
 *     恢复 --> 挂起对象.conn连接 --> ThreadLocal<Map> (Resume --> Suspended object.conn connection --> ThreadLocal<Map>)
 *
 * sql3
 *
 * 提交、回滚 (Commit, Rollback)
 *
 * @author Yaxio
 */
public class AppMain {

	private AppMain() {
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfiguration.class);
		context.refresh();

		AccountService accountService = context.getBean("accountService", AccountService.class);
		context.getBean(AccountService.class);
		System.out.println(accountService);

		TeacherService teacherService = context.getBean("teacherService", TeacherService.class);
		teacherService.test();
	}
}

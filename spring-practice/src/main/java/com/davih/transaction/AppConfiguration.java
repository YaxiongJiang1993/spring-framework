package com.davih.transaction;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Yaxio
 */
@Configuration
@ComponentScan("com.davih.transaction")
//@MapperScan("com.com.davih.cycle")
@EnableTransactionManagement
public class AppConfiguration {
}

/**
 * 
 */
package com.ddau;

import java.sql.Driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.ddau.backend.ThisWillActuallyRun;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author frere
 *
 */

@EnableAutoConfiguration
@SpringBootApplication
@EnableSwagger2 
@ComponentScan(basePackageClasses = {
		ThisWillActuallyRun.class
})
@Import({SwaggerConfig.class,DataBaseConfig.class,JdbcConnectPrivider.class})
public class DdauApplication {

	private static final Logger LOG = LoggerFactory.getLogger(DdauApplication.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ConfigurableApplicationContext ctx = SpringApplication.run(DdauApplication.class, args);
		String name = ctx.getApplicationName();
		LOG.info(name);
		JdbcConnectPrivider privider = ctx.getBean(JdbcConnectPrivider.class);
		LOG.info(privider.sqliteConnect().toString()+" ,"+privider.toString()); 
	}
	


}

/**
 * 
 */
package com.ddau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@Import({SwaggerConfig.class})
public class DdauApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DdauApplication.class, args);
	}

}

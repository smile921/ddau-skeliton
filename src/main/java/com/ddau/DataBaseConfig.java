/**
 * 
 */
package com.ddau;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author frere
 *
 */
@Component
@ConfigurationProperties(prefix = "application.database")
@PropertySource(value="application.yml")
public class DataBaseConfig {

	@Value("${url}")
	private String url;

	@Value("${driverClass}")
	private String driverClass;

	@Value("${dbName}")
	private String dbName;

	@Value("${type}")
	private String type;

	@Value("${userName}")
	private String userName;

	@Value("${password}")
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

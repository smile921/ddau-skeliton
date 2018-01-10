/**
 * 
 */
package com.ddau;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author frere
 *
 */
@Component
public class JdbcConnectPrivider {

	public static final Logger LOG = LoggerFactory.getLogger(JdbcConnectPrivider.class);
	@Autowired
	private DataBaseConfig dbConf;

	@Bean
	public Connection sqliteConnect() {
		Connection conn = null;
		try {
			LOG.info("[数据库配置：" + dbConf.getUrl() + " ]");
			conn = DriverManager.getConnection(dbConf.getUrl());
		} catch (SQLException e) {
			LOG.info("[获取数据库连接失败] ", e);
		}
		return conn;
	}

	public String toString() {
		return "[ " + dbConf.getUrl() + ", " + dbConf.getType() + " ]";
	}
}

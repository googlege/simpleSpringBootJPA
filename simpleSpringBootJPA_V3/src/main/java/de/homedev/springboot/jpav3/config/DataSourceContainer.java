
package de.homedev.springboot.jpav3.config;

import javax.sql.DataSource;

public class DataSourceContainer {
	private final DataSource dataSource;

	public DataSourceContainer(final DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

}

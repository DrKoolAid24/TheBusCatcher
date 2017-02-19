/**
 * 
 */
package com.verycherrycreek.buscatcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @author skilker
 *
 */
public class Configuration { 
	public static final String RTD_USER_NAME = "rtdusername";
	public static final String RTD_PASSWORD = "rtdpassword";
	public static final String TRANSIT_AUTHORITY_NAME = "transitauthorityname";
	public static final String TRANSIT_AUTHORITY_RESOURCE_NAME = "transitauthorityresourcename";
	public static final String DATASTORE_TECHNOLOGY_NAME = "datastoretechnologyname";
	public static final String DATASTORE_TECHNOLOGY_RESOURCE_NAME = "datastoretechnologyresourcename";
	
	private String resourceName;
	private Properties props;

	public Configuration(String pResourceName) {
		resourceName = pResourceName;
		props = new Properties();
	}

	public boolean loadProperties() {
		boolean retVal = false;

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		props = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			props.load(resourceStream);
			retVal = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}

}
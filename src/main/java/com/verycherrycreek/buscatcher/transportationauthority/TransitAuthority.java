/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.transportationauthority;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties.TRANSIT_AUTHORITY;

/**
 * @author skilker
 *
 */
public abstract class TransitAuthority {
	
	// Key that identifies the type of TransitAuthority
	public TRANSIT_AUTHORITY identifier;
	protected String resourceName;
	protected Properties props;
	
	/**
	 * @param identifier Identifies the Transit Authority represented
	 * @param configFileName
	 */
	public TransitAuthority(TRANSIT_AUTHORITY identifier, String resourceName) {
		super();
		this.identifier = identifier;
		this.resourceName = resourceName;
	}

	protected boolean getConfiguration() {
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


}

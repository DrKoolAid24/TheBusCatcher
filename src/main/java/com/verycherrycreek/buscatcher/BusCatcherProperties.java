/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher;

import java.util.Properties;


/**
 * @author skilker
 * 
 * Class contains properties used for current execution of The Bus Cathcher main application
 * 
 * Contains information about the source of the Transit Authority data, how to transform the
 * data and where to populate the data
 *
 */
public class BusCatcherProperties {
	
	// Transit Authority properties
	private Properties transitAuthorityProps;
	private Properties datastoreProps;
	/**
	 * @return the transitAuthorityProps
	 */
	public Properties getTransitAuthorityProps() {
		return transitAuthorityProps;
	}
	/**
	 * @param transitAuthorityProps the transitAuthorityProps to set
	 */
	public void setTransitAuthorityProps(Properties transitAuthorityProps) {
		this.transitAuthorityProps = transitAuthorityProps;
	}
	/**
	 * @return the datastoreProps
	 */
	public Properties getDatastoreProps() {
		return datastoreProps;
	}
	/**
	 * @param datastoreProps the datastoreProps to set
	 */
	public void setDatastoreProps(Properties datastoreProps) {
		this.datastoreProps = datastoreProps;
	}


}

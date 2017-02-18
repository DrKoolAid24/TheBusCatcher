/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher;

import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties.TRANSIT_AUTHORITY;

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
	protected TRANSIT_AUTHORITY currentTransitAuthority;
	
	// Database properties
	protected String currentDatabaseName;
	protected String currentVehiclePositionTableName;
	protected String currentTripUpdateTableName;
	
	

	
}

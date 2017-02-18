/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher;

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


	// Possible Transit Authority properties values
	enum TRANSIT_AUTHORITY {
	    RTD
	}
	
	// Transit Authority properties
	protected String currentTransitAuthority;
	
	// Database properties
	protected String currentDatabaseName;
	protected String currentVehiclePositionTableName;
	protected String currentTripUpdateTableName;
	
}

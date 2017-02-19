/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.datastore;

import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties.TRANSIT_AUTHORITY;

/**
 * @author skilker
 *
 */
public class DatastoreProperties {
	
	public static final String DB_USER_NAME = "dbusername";
	public static final String DB_PASSWORD = "dbpassword";
	public static final String DB_NAME = "dbname";
	public static final String VEHICLE_POSITION_COLLECTION_NAME = "vehiclepositioncollectionname";
	public static final String TRIP_UPDATE_COLLECTION_NAME = "tripupdatecollectionname";
	
	// Possible Datastore Technology properties values
	public enum DATASTORE_TECHNOLOGY {
		MONGODB,
		INVALID
	}
	
	static public DATASTORE_TECHNOLOGY createDatastoreTechnologyEnum(String pDatastoreTechnology) {
		DATASTORE_TECHNOLOGY datastoreTechnology = DATASTORE_TECHNOLOGY.INVALID;
		if (pDatastoreTechnology.equals("MONGODB")) {
			datastoreTechnology = DATASTORE_TECHNOLOGY.MONGODB;
		}
		return datastoreTechnology;
	}	

}

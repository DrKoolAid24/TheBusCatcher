/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.datastore;

import java.util.ArrayList;

/**
 * @author skilker
 *
 */
public interface DatastoreI {

	public boolean updateVehiclePositions(ArrayList<VehiclePosition> pVehiclePositions); 
	
	public boolean updateTripUpdates(ArrayList<TripUpdate> pTripUpdates);
	
	public boolean closeDatastoreConnection();
	
	public boolean openDatastoreConnection(); 
	
}

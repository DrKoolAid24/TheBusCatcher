/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.datastore;

import java.util.ArrayList;

import com.verycherrycreek.buscatcher.datastore.DatastoreProperties.DATASTORE_TECHNOLOGY;

/**
 * @author skilker
 *
 */
public interface DatastoreI {

	public boolean updateVehiclePositions(ArrayList<VehiclePosition> pVehiclePositions); 
	
	public boolean updateTripUpdates(ArrayList<TripUpdate> pTripUpdates);
	
	public boolean closeDatastoreConnection();
	
	public boolean openDatastoreConnection();

	public void dropTripUpdates();

	public void dropVehiclePositions(); 
	
	public DATASTORE_TECHNOLOGY getDatastoreTechnology();
	
}

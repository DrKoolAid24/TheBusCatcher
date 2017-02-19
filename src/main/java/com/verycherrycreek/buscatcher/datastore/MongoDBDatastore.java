/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.datastore;

import java.util.ArrayList;

import org.mongodb.morphia.Morphia;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties;

/**
 * @author skilker
 *
 */
public class MongoDBDatastore extends Datastore implements DatastoreI {

	private Morphia morphia;
	private org.mongodb.morphia.Datastore mongoDatastore;
	private String dbUsername;
	private String dbPassword;
	private String dbName;
	private String vehiclePositionCollectionName;
	private String tripUpdateCollectionName;
	
	
	public MongoDBDatastore(DatastoreProperties.DATASTORE_TECHNOLOGY pDatastoreTechnology, String pResourceName) {
		super(pDatastoreTechnology, pResourceName);
		if (super.getConfiguration()) {
			dbUsername = props.getProperty(DatastoreProperties.DB_USER_NAME);
			dbPassword = props.getProperty(DatastoreProperties.DB_PASSWORD);
			dbName = props.getProperty(DatastoreProperties.DB_NAME);
			vehiclePositionCollectionName = props.getProperty(DatastoreProperties.VEHICLE_POSITION_COLLECTION_NAME);
			tripUpdateCollectionName = props.getProperty(DatastoreProperties.TRIP_UPDATE_COLLECTION_NAME);
		}
		
		morphia = new Morphia();
		morphia.mapPackage("com.verycherrycreek.buscatcher.datastore");
		mongoDatastore = morphia.createDatastore(new MongoClient(), dbName);
		mongoDatastore.getDB().dropDatabase();
		mongoDatastore.ensureIndexes();
	}


	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.datastore.DatastoreI#updateVehiclePositions(com.verycherrycreek.buscatcher.datastore.VehiclePosition[])
	 */
	@Override
	public boolean updateVehiclePositions(ArrayList<VehiclePosition> pVehiclePositions) {
		boolean retResult = false;
		mongoDatastore.getDB().getCollection(vehiclePositionCollectionName).drop();
		for (VehiclePosition vehiclePosition : pVehiclePositions) {
			mongoDatastore.save(vehiclePosition);
			retResult = true;
		}
		return retResult;
	}


	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.datastore.DatastoreI#updateTripUpdates(com.verycherrycreek.buscatcher.datastore.TripUpdate[])
	 */
	@Override
	public boolean updateTripUpdates(ArrayList<TripUpdate> pTripUpdates) {
		// TODO Auto-generated method stub
		return false;
	}

}

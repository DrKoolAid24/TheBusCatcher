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
		
	}


	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.datastore.DatastoreI#updateVehiclePositions(com.verycherrycreek.buscatcher.datastore.VehiclePosition[])
	 */
	@Override
	public boolean updateVehiclePositions(ArrayList<VehiclePosition> pVehiclePositions) {
		boolean retResult = false;
		
		//TODO check that collection exists before dropping it
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
		boolean retResult = false;
		
		//TODO check that collection exists before dropping it
		mongoDatastore.getDB().getCollection(tripUpdateCollectionName).drop();
		for (TripUpdate tripUpdate : pTripUpdates) {
			mongoDatastore.save(tripUpdate);
			retResult = true;
		}
		return retResult;
	}


	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.datastore.DatastoreI#closeDatastoreConnection()
	 */
	@Override
	public boolean closeDatastoreConnection() {
		// TODO Auto-generated method stub		
		mongoDatastore.getMongo().close();
		// Returning true for this instance.  Other instances may have it set with logic
		return true;
	}


	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.datastore.DatastoreI#openDatastoreConnection()
	 */
	@Override
	public boolean openDatastoreConnection() {
		boolean retValue = false;
		morphia = new Morphia();
		morphia.mapPackage("com.verycherrycreek.buscatcher.datastore");
		mongoDatastore = morphia.createDatastore(new MongoClient(), dbName);
		//TODO Check that DB connection is working and ready.
		//mongoDatastore.getDB().dropDatabase();
		mongoDatastore.ensureIndexes();
		
		//TODO Check that DB connection is working and ready.
		return retValue;
	}

	
}

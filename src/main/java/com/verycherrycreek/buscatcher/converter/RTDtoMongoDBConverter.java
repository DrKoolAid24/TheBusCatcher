/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.converter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.verycherrycreek.buscatcher.datastore.Datastore;
import com.verycherrycreek.buscatcher.datastore.DatastoreI;
import com.verycherrycreek.buscatcher.datastore.TripUpdate;
import com.verycherrycreek.buscatcher.datastore.VehiclePosition;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthority;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityI;

/**
 * @author skilker
 *
 */
public class RTDtoMongoDBConverter extends Converter implements ConverterI {

	/**
	 * @param pTransitAuthority
	 * @param pDatastore
	 */
	public RTDtoMongoDBConverter(TransitAuthorityI pTransitAuthority, DatastoreI pDatastore) {
		super(pTransitAuthority, pDatastore);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.converter.ConverterI#executeConversion()
	 */
	@Override
	public void executeConversion() {
		
		datastore.openDatastoreConnection();
		
		while(true) {
			this.updateVehiclePositions();
			this.updateTripUpdates();
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				datastore.closeDatastoreConnection();
			}
		}
		
	}
//TODO try this to avoid drift	
//	final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//	executorService.scheduleAtFixedRate(updateRealtimeData(), 0, 60, TimeUnit.SECONDS);
//	private  Runnable updateRealtimeData() {
//		updateVehiclePositions();				
//		updateTripUpdates();
//		return;	
//	}


	/**
	 * 
	 */
	private void updateTripUpdates() {
		// Drop TripUpdates
		datastore.dropTripUpdates();

		// Get FeedMessage with VehiclePositions, convert to array, and update the DB
		FeedMessage tripUpdateFeedMessage = null;
		try {
			tripUpdateFeedMessage = transitAuthority.getTripUpdates();

			ArrayList<TripUpdate> tripUpdates = new ArrayList<TripUpdate>();
			for (FeedEntity entity : tripUpdateFeedMessage.getEntityList()) {
				TripUpdate tu = new TripUpdate(entity);
				tripUpdates.add(tu);
			}
			datastore.updateTripUpdates(tripUpdates);
		
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
	}

	/**
	 * 
	 */
	private void updateVehiclePositions() {
		// Drop VehiclePositions
		datastore.dropVehiclePositions();
		
		// Get FeedMessage with VehiclePositions, convert to array, and update the DB
		FeedMessage vehiclePositionsFeedMessage = null;
		try {
			vehiclePositionsFeedMessage = transitAuthority.getVehiclePositions();

			ArrayList<VehiclePosition> vehiclePositions = new ArrayList<VehiclePosition>();
			for (FeedEntity entity : vehiclePositionsFeedMessage.getEntityList()) {
				VehiclePosition vp = new VehiclePosition(entity);
				vehiclePositions.add(vp);
			}
			datastore.updateVehiclePositions(vehiclePositions);
		
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
	}

}

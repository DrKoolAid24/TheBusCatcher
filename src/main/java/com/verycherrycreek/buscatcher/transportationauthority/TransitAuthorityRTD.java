/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.transportationauthority;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;

/**
 * @author skilker
 *
 */
public class TransitAuthorityRTD implements TransitAuthorityI {

	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityI#getVehiclePositions()
	 */
	@Override
	public FeedMessage getVehiclePositions() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityI#getTripUpdates()
	 */
	@Override
	public FeedMessage getTripUpdates() {
		// TODO Auto-generated method stub
		return null;
	}

}

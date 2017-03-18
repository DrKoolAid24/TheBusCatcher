/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.datastore;

/**
 * @author skilker
 *
 */
public class StopTimeUpdate {
	private String stopId;
	private int stopSequence;
	private String scheduleRelationship;
	private long arrival;
	private long departure;
	
	public StopTimeUpdate() {
	}
	
	public StopTimeUpdate(com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate pStopTimeUpdate) {
		this.stopId = pStopTimeUpdate.getStopId();
		this.stopSequence = pStopTimeUpdate.getStopSequence();
		this.scheduleRelationship = pStopTimeUpdate.getScheduleRelationship().toString();
		this.arrival = pStopTimeUpdate.getArrival().getTime();
		this.departure = pStopTimeUpdate.getDeparture().getTime();
	}
	
}
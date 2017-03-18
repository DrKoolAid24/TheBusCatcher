package com.verycherrycreek.buscatcher.datastore;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;

@Entity("tripupdates")
public class TripUpdate {
    @Id
    private ObjectId id;
    
    private String agency;
    private String tripId;
    private String scheduleRelationship;
    private String routeId;
    private int directionId;
    private String vehicleId;
    private String label;
    private List<com.verycherrycreek.buscatcher.datastore.StopTimeUpdate> stopTimeUpdates;


    public TripUpdate() {
    }

    public TripUpdate(FeedEntity pFeedEntity) {
    	
    	//TODO refactor to be more efficient and use one getTrip to get the TripUpdate and resuse it
    	this.agency = "RTD";
    	this.tripId = pFeedEntity.getTripUpdate().getTrip().getTripId();
    	this.scheduleRelationship = pFeedEntity.getTripUpdate().getTrip().getScheduleRelationship().toString();
    	this.routeId = pFeedEntity.getTripUpdate().getTrip().getRouteId();
    	this.directionId = pFeedEntity.getTripUpdate().getTrip().getDirectionId();
    	this.vehicleId = pFeedEntity.getTripUpdate().getVehicle().getId();
    	this.label = pFeedEntity.getTripUpdate().getVehicle().getLabel();
    	this.stopTimeUpdates = new ArrayList<com.verycherrycreek.buscatcher.datastore.StopTimeUpdate>();
    	
    	for(com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate stopTimeUpdate : pFeedEntity.getTripUpdate().getStopTimeUpdateList()) {
    		com.verycherrycreek.buscatcher.datastore.StopTimeUpdate newStopTimeUpdate = new com.verycherrycreek.buscatcher.datastore.StopTimeUpdate(stopTimeUpdate);
    		this.stopTimeUpdates.add(newStopTimeUpdate);
    	}
    	
 //   	this.stopTimeUpdates = pFeedEntity.getTripUpdate().getStopTimeUpdateList();  
    }
    
    public ObjectId getId() {
        return id;
    }

}	

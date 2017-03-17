package com.verycherrycreek.buscatcher.datastore;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;

@Entity("tripupdates")
public class TripUpdate {
    @Id
    private ObjectId id;

    private String tripId;
    private String scheduleRelationship;
    private String routeId;
    private int directionId;

    public TripUpdate() {
    }

    public TripUpdate(FeedEntity pFeedEntity) {
    	
    	//TODO refactor to be more efficient and use one getTrip to get the TripUpdate and resuse it
    	this.tripId = pFeedEntity.getTripUpdate().getTrip().getTripId();
    	this.scheduleRelationship = pFeedEntity.getTripUpdate().getTrip().getScheduleRelationship().toString();
    	this.routeId = pFeedEntity.getTripUpdate().getTrip().getRouteId();
        this.directionId = pFeedEntity.getVehicle().getTrip().getDirectionId();
    }
    public ObjectId getId() {
        return id;
    }
}	

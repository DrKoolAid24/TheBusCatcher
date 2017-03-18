package com.verycherrycreek.buscatcher.datastore;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;

@Entity("vehiclepositions")
public class VehiclePosition {
    @Id
    private ObjectId id;
    private String agency;
    private String vehicleId;
    private String tripId;
    private String scheduleRelationship;
    private String routeId;
    private int directionId;
    private double latitude;
    private double longitude;
    private float bearing;
    private String currentStatus;
    private long timestamp;
    private String stopId;

    public VehiclePosition() {
    }

    public VehiclePosition(FeedEntity pFeedEntity) {
        this.vehicleId = pFeedEntity.getVehicle().getVehicle().getId();
        this.agency = "RTD";
        // Trip Updates
        this.tripId = pFeedEntity.getVehicle().getTrip().getTripId();
        this.scheduleRelationship = pFeedEntity.getVehicle().getTrip().getScheduleRelationship().toString();
        this.routeId = pFeedEntity.getVehicle().getTrip().getRouteId();
        this.directionId = pFeedEntity.getVehicle().getTrip().getDirectionId();
        this.latitude = pFeedEntity.getVehicle().getPosition().getLatitude();
        this.longitude = pFeedEntity.getVehicle().getPosition().getLongitude();
        this.bearing = pFeedEntity.getVehicle().getPosition().getBearing();
        this.currentStatus = pFeedEntity.getVehicle().getCurrentStatus().toString();
        this.timestamp = pFeedEntity.getVehicle().getTimestamp();
        this.stopId = pFeedEntity.getVehicle().getStopId();   
        
    }
    public ObjectId getId() {
        return id;
    }
}	

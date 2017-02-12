package com.verycherrycreek.buscatcher;

/**
 * @author skilker
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.security.Timestamp;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.mongodb.MongoClient;

public class Main {
	
	private static String CONFIG_PROPERTIES_FILE = "config.properties";
	private static String ERROR_RETRIEVING_CONFIGURATION_FILE = "Error occured retrieving configuration file";
		
	public static void main(String[] args) {
		
		// Read properties file to get main configuration settings
		Configuration configuration = new Configuration(CONFIG_PROPERTIES_FILE);
		if (!configuration.getConfiguration()) {			
			System.out.print(ERROR_RETRIEVING_CONFIGURATION_FILE);
			System.exit(1);
		}
		
		String rtdUsername = configuration.props.getProperty(configuration.RTD_USER_NAME);
		String rtdPassword = configuration.props.getProperty(configuration.RTD_PASSWORD);

		// Try doing something with MongoDB
		final Morphia morphia = new Morphia();

		// tell Morphia where to find your classes
		// can be called multiple times with different packages or classes
		morphia.mapPackage("org.mongodb.morphia.example");

		// create the Datastore connecting to the default port on the local host
		final Datastore datastore = morphia.createDatastore(new MongoClient(), "rtd_example");
        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();

		try {
			
			// Sets the authenticator that will be used by the networking code
		    // when a proxy or an HTTP server asks for authentication.
			Authenticator.setDefault(new CustomAuthenticator(rtdUsername, rtdPassword));
			
			URL url = new URL("http://www.rtd-denver.com/google_sync/VehiclePosition.pb");
			FeedMessage feed = FeedMessage.parseFrom(url.openStream());		

			DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");
			Date dateobj = new Date();
			System.out.println(df.format(dateobj));	
			
			int totalTime = 0;
			int totalReadings = 0;
			
			System.out.println("Start of vehicle position");
			for (FeedEntity entity : feed.getEntityList()) {
//				if (entity.getVehicle().getTrip().getRouteId().equals("24")) {
					System.out.println(entity);	
					
					long theTimeStamp = entity.getVehicle().getTimestamp();
//					System.out.println("Timestamp in long is :" + theTimeStamp); 
					java.util.Date time=new java.util.Date((long)theTimeStamp*1000);					
					
					System.out.println("\n\nTime is: " + time);
					//String dateFormatted = formatter.format(date);
					System.out.println("Time of URL Request: " + df.format(dateobj));			
					System.out.println("Differnce: " + (dateobj.getTime() - time.getTime())/1000);
					
					totalTime += (Math.abs((dateobj.getTime() - time.getTime())));
					totalReadings++;
					
					VehiclePosition vp = new VehiclePosition(entity);
			        datastore.save(vp);
					
//				}
				
				
			}
			
			System.out.println("Total Time: " + totalTime/1000);
			System.out.println("Total Readings: " + totalReadings);
			System.out.println("Average seconds off: " + (totalTime/totalReadings)/1000);

/*
			for (FeedEntity entity : feed.getEntityList()) {
				if (entity.hasTripUpdate()) {
					System.out.println(entity.getTripUpdate());
				}				
			}
*/

			url = new URL("http://www.rtd-denver.com/google_sync/TripUpdate.pb");
			feed = FeedMessage.parseFrom(url.openStream());		
			System.out.println("Start of Trip Update");
			for (FeedEntity entity : feed.getEntityList()) {
				if (entity.getTripUpdate().getTrip().getRouteId().equals("24")) {
					System.out.println(entity);					
				}
				
			}
			
/*			
			// read text returned by server
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    
		    String line;
		    while ((line = in.readLine()) != null) {
		    	System.out.println(line);
		    }
		    in.close();
*/		    
		}
		catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		}
		catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}	
		
	}
	
	
	public static class CustomAuthenticator extends Authenticator {
		private String username;
		private String password;
		
		public CustomAuthenticator(String pUsername, String pPassword) {
			username = pUsername;
			password = pPassword;
		}
		// Called when password authorization is needed
		protected PasswordAuthentication getPasswordAuthentication() {
			
			// Get information about the request
			String prompt = getRequestingPrompt();
			String hostname = getRequestingHost();
			InetAddress ipaddr = getRequestingSite();
			int port = getRequestingPort();

			// Return the information (a data holder that is used by Authenticator)
			return new PasswordAuthentication(username, password.toCharArray());
			
		}
		
	}
	
}
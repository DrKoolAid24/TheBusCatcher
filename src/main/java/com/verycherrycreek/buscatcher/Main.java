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
import java.util.Properties;

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
import com.verycherrycreek.buscatcher.converter.ConverterI;
import com.verycherrycreek.buscatcher.converter.RTDtoMongoDBConverter;
import com.verycherrycreek.buscatcher.datastore.DatastoreFactory;
import com.verycherrycreek.buscatcher.datastore.DatastoreI;
import com.verycherrycreek.buscatcher.datastore.DatastoreProperties;
import com.verycherrycreek.buscatcher.datastore.DatastoreProperties.DATASTORE_TECHNOLOGY;
import com.verycherrycreek.buscatcher.datastore.VehiclePosition;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityFactory;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityI;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties;

public class Main {

	private static String CONFIG_PROPERTIES_FILE = "config.properties";
	private static String ERROR_RETRIEVING_CONFIGURATION_FILE = "Error occured retrieving configuration file";
	private static String INVALID_TRANSIT_AUTHORITY = "Invalid Transit Authority found";
	private static String INVALID_DATASTORE_TECHNOLOGY = "Invalid Datastore Technology found";

	public static void main(String[] args) {

		// Create BusCatcherProperties to manage execution properties
		BusCatcherProperties busCatcherProperties = new BusCatcherProperties();

		// Read properties file to get main configuration settings
		Configuration configuration = new Configuration(CONFIG_PROPERTIES_FILE);
		if (!configuration.loadProperties()) {
			System.out.print(ERROR_RETRIEVING_CONFIGURATION_FILE);
			System.exit(1);
		}
		Properties configurationProps = configuration.getProps();

		// Check if using a valid Transit Authority
		String transitAuthorityName = configurationProps
				.getProperty(Configuration.TRANSIT_AUTHORITY_NAME);
		TransitAuthorityProperties.TRANSIT_AUTHORITY currentTransitAuthority = TransitAuthorityProperties
				.createTransitAuthorityEnum(transitAuthorityName);
		if (currentTransitAuthority == TransitAuthorityProperties.TRANSIT_AUTHORITY.INVALID) {
			System.out.print(INVALID_TRANSIT_AUTHORITY);
			System.exit(1);
		}

		// TODO populate properties for execution

		// Get Transit Authority using identifier from properties
		String transitAuthorityResourceName = configurationProps
				.getProperty(Configuration.TRANSIT_AUTHORITY_RESOURCE_NAME);
		TransitAuthorityI transitAuthority = TransitAuthorityFactory
				.createTransitAuthority(currentTransitAuthority,
						transitAuthorityResourceName);

		// Currently seting password from main config file to keep it from
		// Github for now
		String rtdUsername = configurationProps
				.getProperty(configuration.RTD_USER_NAME);
		String rtdPassword = configurationProps
				.getProperty(configuration.RTD_PASSWORD);

		transitAuthority.setPassword(rtdPassword);
		transitAuthority.setUsername(rtdUsername);

		// Configure the Datastore to be used for storing the data retrieved
		// from the Transit Authority
		// Check if using a valid Transit Authority
		String datastoreName = configurationProps
				.getProperty(Configuration.DATASTORE_TECHNOLOGY_NAME);
		DATASTORE_TECHNOLOGY currentDatastoreTechnology = DatastoreProperties
				.createDatastoreTechnologyEnum(datastoreName);
		if (currentDatastoreTechnology == DatastoreProperties.DATASTORE_TECHNOLOGY.INVALID) {
			System.out.print(INVALID_DATASTORE_TECHNOLOGY);
			System.exit(1);
		}

		// Get Datastore resource name from properties
		String datastoreResourceName = configurationProps
				.getProperty(Configuration.DATASTORE_TECHNOLOGY_RESOURCE_NAME);
		DatastoreI datastore = DatastoreFactory.createDatastore(
				currentDatastoreTechnology, datastoreResourceName);

		
		datastore.openDatastoreConnection();
		
		//TODO add ConvertFactory object to create special conversion logic - just using RTDtoMongo for now
		ConverterI rtdToMongoDBConverter = new RTDtoMongoDBConverter(transitAuthority, datastore);
		rtdToMongoDBConverter.executeConversion();
		
		
		datastore.closeDatastoreConnection();

	}

}

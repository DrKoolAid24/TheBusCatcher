/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.transportationauthority;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;

public class CustomAuthenticator extends Authenticator {
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

/**
 * @author cabinw
 * @email cabinw@gmail.com
 * 
 * get the mac of the computer
 * using a new feature of JDK1.6
 */

package com.macc.shell;

import java.net.NetworkInterface; 
import java.util.Enumeration; 

public class MacGetter {
	/**
	 * 
	 * Get the hex value of byte value
	 * Return a string value
	 * 
	 * @param b
	 * @return
	 */
	  static String hexByte(byte b) { 
		  String s = "000000" + Integer.toHexString(b); 
		  return s.substring(s.length() - 2); 
	  } 
	  
	  /**
	   * Get the String value of the MAC address
	   * @return
	   */
	  public static String getMac() { 
	    try { 
	      Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces(); 
	      while (el.hasMoreElements()) { 
	    	  byte[] mac = el.nextElement().getHardwareAddress(); 
	    	  if (mac == null) 
	    		  continue; 

	    	  StringBuilder builder = new StringBuilder(); 
	    	  for (byte b : mac) { 
	    		  builder.append(hexByte(b)); 
	    		  builder.append("-"); 
	    	  } 
	    	  builder.deleteCharAt(builder.length() -1); 
	    	  return builder.toString();
	      	} 
	    } 
	    catch (Exception exception) 
	    { 
	      exception.printStackTrace(); 
	    }
		return null; 
	  }
}

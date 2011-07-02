/**
 * @author cabinw
 * @email cabinw@gmail.com
 * 
 * manage the data storage
 */
package com.macc.gui;

import java.util.ArrayList;
import java.io.*;

public class Data {
	
	/**
	 * add an address to store.txt
	 * @param s
	 * @throws IOException
	 */
	public  static void add(String s) throws IOException{
		ArrayList<String> v=new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader("resources//store.txt"));
		String line;
		while((line = in.readLine()) != null){
			v.add(line);
		}
		for(int i=0; i< v.size(); ++i){
			if(s.equals(v.get(i)))
				return;
		}
		v.add(s);
		PrintWriter out = new PrintWriter("resources//store.txt");
		for(int i=0; i< v.size(); ++i){
			out.println(v.get(i));
		}
		out.close();//if not close(),will not get the right answer
	}
	
	public static String[] get() throws IOException{
		ArrayList<String> v=new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader("resources//store.txt"));
		String line;
		while((line = in.readLine()) != null){
			v.add(line);
		}
		int length = v.size();
		String[] s = new String[length];
		for(int i=0; i< length; ++i){
			s[i] = v.get(i);
		}
		
		return s;
	}
}

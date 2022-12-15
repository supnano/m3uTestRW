package com.najib.m3uTestRW;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.tgermain.m3uparser.core.Entry;
import be.tgermain.m3uparser.core.Parser;
import be.tgermain.m3uparser.core.Writer;

public class App {
	
  

  public static void main(String[] args) throws FileNotFoundException {
    String path = getMandatoryArgument("file");
    String fileWebURL = null;
    String outputfilePath = getMandatoryArgument("OutputfilePath");
    try {
    	fileWebURL = getMandatoryArgument("fileWebURL");
		try (BufferedInputStream in = new BufferedInputStream(new URL(fileWebURL).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(path + "Downlowded")) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// handle exception
		}
    } catch (IllegalArgumentException e) {
    	
    }
    File fichierSortie = new File(outputfilePath);

    FileOutputStream fileOutputStream = new FileOutputStream(fichierSortie);
    ArrayList<String> acceptedGroups = getAcceptedGroups();
    InputStream is = null;
    try {
		is = new FileInputStream(path);
		Parser parser = new Parser();
	    List<Entry> entryList = parser.parse(is);
	    System.out.println("The number of entries is : " +entryList.size());
	    Set<String> groups = new HashSet<String>();
	    Set<String> excludedGroups = new HashSet<String>();
	    Set<String> channels = new HashSet<String>();
	    List<Entry> filtredEntryList = new ArrayList<Entry>();
	    for(Entry entry : entryList) {
	    	if( acceptedGroups.contains(entry.getGroupTitle()) ) {
	    		filtredEntryList.add(entry);
		    	groups.add(entry.getGroupTitle());
		    	channels.add(entry.getChannelName());
	    	} else {
	    		excludedGroups.add(entry.getGroupTitle());
	    	}

	    }

	    Writer writer = new Writer();
	    writer.write(filtredEntryList, fileOutputStream);

	    System.out.println("The number of filtred channels is " +channels.size());
	    System.out.println("Excluded groups are :");
	    
	    for(Object s : excludedGroups.toArray() ) {
	    	System.out.println(s);
	    }
	    
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }
  private static String getMandatoryArgument(String key) throws IllegalArgumentException{
      String value = System.getProperty(key);
      if(value == null){
          throw new IllegalArgumentException(key + " argument is mandatory");
      }
      return value;
  }
  private static ArrayList<String> getAcceptedGroups(){
	  ArrayList<String> acceptedGroups = new ArrayList<String>();
	  acceptedGroups.add("AM | USA | DOCUMENTARY");
	  acceptedGroups.add("AM | USA | MOTOSPORTS");
	  //acceptedGroups.add("AM | USA | NBA");
	  acceptedGroups.add("AR | 24/7 ARABIC");
	  acceptedGroups.add("AR | ABU DHABI SPORTS");
	  acceptedGroups.add("AR | ALGERIA");
	  acceptedGroups.add("AR | ARABIC KIDS");
	  acceptedGroups.add("AR | ARABIC NEWS & FACTUAL");
	  acceptedGroups.add("AR | ART");
	  acceptedGroups.add("AR | BEIN ENTERTAINMENT");
	  acceptedGroups.add("AR | BEIN SPORT");
	  acceptedGroups.add("AR | BEIN SPORT 4K");
	  acceptedGroups.add("AR | ISLAMIC");
	  acceptedGroups.add("AR | JORDAN");
	  acceptedGroups.add("AR | LEBANON");
	  acceptedGroups.add("AR | MBC");
	  acceptedGroups.add("AR | MOROCCO");
	  acceptedGroups.add("AR | MYHD PREMIUM");
	  acceptedGroups.add("AR | OSN");
	  acceptedGroups.add("AR | PALESTINE");
	  acceptedGroups.add("AR | QATAR");
	  //acceptedGroups.add("AR | ROTANA");
	  acceptedGroups.add("AR | SAOUDI");
	  acceptedGroups.add("AR | SHAHID");
	  //acceptedGroups.add("AR | STARZPLAY");
	  acceptedGroups.add("AR | UAE");
	  acceptedGroups.add("EU | FRANCE CINEMA");
	  //acceptedGroups.add("EU | FRANCE ENTERTAINMENT");
	  acceptedGroups.add("EU | FRANCE GENERAL");
	  acceptedGroups.add("EU | FRANCE KIDS");
	  acceptedGroups.add("EU | FRANCE SPORTS");
	  //acceptedGroups.add("EU | UK");
	  //acceptedGroups.add("Radio Morocco");
	  acceptedGroups.add("SRS | ANIME [FR]");
	  //acceptedGroups.add("SRS | ARABIC [TR]");
	  //acceptedGroups.add("SRS | ENGLISH");
	  //acceptedGroups.add("SRS | FRENCH");
	  //acceptedGroups.add("SRS | RAMADAN 2022");
	  acceptedGroups.add("VOD | ANIME [FR]");
	  acceptedGroups.add("VOD | ARABIC [ANIME]");
	  acceptedGroups.add("VOD | ARABIC [KIDS]");
	  acceptedGroups.add("VOD | DC [FR]");
	  acceptedGroups.add("VOD | DISNEY+ [FR]");
	  acceptedGroups.add("VOD | EN LATEST MOVIES");
	  acceptedGroups.add("VOD | FR LATEST MOVIES");
	  acceptedGroups.add("VOD | FRENCH");
	  acceptedGroups.add("VOD | MARVEL [FR]");
	  acceptedGroups.add("VOD | NETFLIX [FR]");
	  acceptedGroups.add("AR | BEIN SPORT MAX");
	  acceptedGroups.add("VOD | ARABIC [Documentary]");
	  //acceptedGroups.add("VOD | AMAZIGH");
	  
	  return acceptedGroups;
  }
}

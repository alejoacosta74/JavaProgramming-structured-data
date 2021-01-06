
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package week3;
import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
    	 records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
    	 records.clear();
    	 FileResource fr = new FileResource(filename);
    	 for (String s : fr.lines()) {
    		 LogEntry le = WebLogParser.parseEntry(s); 
    		 records.add(le);    		 
    	 }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     int countUniqueIPs () {
    	 ArrayList<String> uniqueIP = new ArrayList<String>();    	 
    	 for (LogEntry le : records) {
          String tempIP = le.getIpAddress();
          if (!uniqueIP.contains(tempIP)) {
        	  uniqueIP.add(tempIP);
          }
         }    	 
    	 return uniqueIP.size();
     }
     
     void printAllHigherThanNum (int num) {    	 
    	 for (LogEntry le : records) {
    		 if (le.getStatusCode()>num) {
             System.out.println(le);
    		 }
         }    	 
     }
     
     ArrayList<String> uniqueIPVisitsOnDay (String someday) {
    	 ArrayList<String> uniqueIPday = new ArrayList<String>();
    	 String currTime = "", currDay ="";
    	 for (LogEntry le : records) {
          String tempIP = le.getIpAddress();
          currTime = le.getAccessTime().toString();
          currDay = currTime.substring(4, 10);
          
          if (!uniqueIPday.contains(tempIP) && currDay.contentEquals(someday)) {
        	  uniqueIPday.add(tempIP);
          }
         }    	 
    	 return uniqueIPday;    	 
     }
     
     int countUniqueIPsInRange (int low, int high) {
    	 ArrayList<String> uniqueIPrange = new ArrayList<String>();
    	 int currStatus;
    	 for (LogEntry le : records) {
          String tempIP = le.getIpAddress();
          currStatus = le.getStatusCode();
          //System.out.println("Curr Status:" + currStatus + " low: " + low + " High: " + high);
          if (!uniqueIPrange.contains(tempIP) && currStatus >= low && currStatus <= high) {
        	  uniqueIPrange.add(tempIP);
          }
         }    	 
    	 return uniqueIPrange.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP (){
    	 HashMap<String, Integer> countMap = new HashMap<String, Integer>();
    	 for (LogEntry le : records) {
             String tempIP = le.getIpAddress();
             if (!countMap.containsKey(tempIP)) {
            	 countMap.put(tempIP, 1);
             }
             else {
            	 int count = countMap.get(tempIP);
            	 countMap.put(tempIP, count+1);
             }
          }
    	 return countMap;
     }   	 
 
     public int mostNumberVisitsByIP ( HashMap<String, Integer> countMap) {
    	int max =0, count=0;
    	String ipMax="";
    	for (String IP : countMap.keySet()) {
    		count = countMap.get(IP);
    		if (count > max) {
    			max = count;
    			ipMax = IP;
    		}
    	}
    	return max;
     }
     
     public ArrayList<String> iPsMostVisits (HashMap<String, Integer> countMap){
    	 int ipMax = mostNumberVisitsByIP(countMap);
    	 int count;
    	 ArrayList<String> ipList = new ArrayList<String>();
     	for (String IP : countMap.keySet()) {
    		count = countMap.get(IP);
    		if (count == ipMax) {
    			ipList.add(IP);
    		}
    	}
    	return ipList;    	 
     }
     
     public HashMap<String, ArrayList <String>> iPsForDays () {    	 
    	 String currTime,currDay;
    	 HashMap<String, ArrayList <String>> ipDaysMap = new HashMap<String, ArrayList <String>>();
    	 ArrayList <String> tempList;
    	 for (LogEntry le : records) {             
             currTime = le.getAccessTime().toString();
             currDay = currTime.substring(4, 10);
             
             if (!ipDaysMap.containsKey(currDay)) {
            	 tempList = new ArrayList <String>();             }
             else {
            	 tempList = ipDaysMap.get(currDay);
             }
        	 String currIp = le.getIpAddress();
        	 tempList.add(currIp);
        	 ipDaysMap.put(currDay, tempList);
        }
    	 return ipDaysMap;
     }	 
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList <String>> ipDaysMap) {
    	 int tempCount, dayCount=0;
    	 ArrayList<String> tempList;
    	 String maxDay = "";
      	for (String day : ipDaysMap.keySet()) {
     		tempList = ipDaysMap.get(day);
     		tempCount = tempList.size();
     		if (tempCount > dayCount) {
     			maxDay = day;
     			dayCount = tempCount;
     			//System.out.println("tempCount: " + tempCount + " - dayCount: " + dayCount + " -MaxDay: " + maxDay );
     		}
     	}
      	return maxDay;
     }
    
     public ArrayList<String>  iPsWithMostVisitsOnDay (HashMap<String, ArrayList <String>> ipDaysMap, String day){
    	 //Make an ArrayList with the ip addresses that occur on your day parameter in the HashMap
         ArrayList<String> visitsOnDay = ipDaysMap.get(day);
         
         //Make a NEW HashMap to hold the unique IP addresses and the number of times they occur
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         
         //Now fill this HashMap using the same algorithm we've been using for the previous few assignments
         for(String ip : visitsOnDay){
             if(!ipCounts.containsKey(ip)){
                 ipCounts.put(ip, 1);
             }
             else{
                 ipCounts.put(ip, ipCounts.get(ip) + 1);
             }
         }
         
         //Now, the answer is as simple as calling iPsMostVisits (which returns an ArrayList<String>) on the HashMap you just made!
         return iPsMostVisits(ipCounts);	 
     }
}

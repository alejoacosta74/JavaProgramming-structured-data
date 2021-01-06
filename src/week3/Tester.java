package week3;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    void printArrayList (ArrayList<String> list){
    	for (int k=0; k < list.size(); k++) {
    		System.out.println(list.get(k));	
    	}
    }
    public void testLogAnalyzer() {
        // complete method
    	LogAnalyzer test = new LogAnalyzer();
 /* 
    	test.readFile("./src/week3/short-test_log");  	
 //   	test.printAll();
    	System.out.println("Unique IPs: " + test.countUniqueIPs());
    	String date = "Sep 14";
    	System.out.println("Unique IP visits on day : " + date + " is = " + test.uniqueIPVisitsOnDay(date).size());
    	int low = 200;
    	int high = 299;
    	System.out.println("Unique IPs in range low: " + low + "  - high: " + high + " Unique ips in range: "+ test.countUniqueIPsInRange(low, high));

    	low = 300;
    	high = 399;
    	System.out.println("Unique IPs in range low: " + low + "  - high: " + high + " Unique ips in range: "+ test.countUniqueIPsInRange(low, high));
    	test.readFile("./src/week3/weblog-short_log");
    	date = "Sep 14";
    	System.out.println("Unique IP visits on day : " + date + " is = " + test.uniqueIPVisitsOnDay(date).size());
*/    
  
/*    	
    	test.readFile("./src/week3/weblog1_log");
    	//Q2
    	System.out.println("Q2: logs con status > 400:"); 
    	test.printAllHigherThanNum(400);
    	//Q3
    	System.out.println("Q3: Unique visits Mar 17:");
    	String date = "Mar 17";
    	System.out.println("Unique IP visits on day : " + date + " is = " + test.uniqueIPVisitsOnDay(date).size());
    	//Q3
    	System.out.println("Q4:");
    	int low = 200;
    	int high = 299;
    	System.out.println("Unique IPs in range low: " + low + "  - high: " + high + " Unique ips in range: "+ test.countUniqueIPsInRange(low, high));
*/    	
    	
       	test.readFile("./src/week3/weblog1_log");
       	HashMap<String, Integer> countMap = test.countVisitsPerIP();
       	int ipMax = test.mostNumberVisitsByIP(countMap);
       	System.out.println("most number visit IP: " + ipMax);
       	
       	ArrayList<String> ipMostVisitList = test.iPsMostVisits(countMap);
       	for (int k=0; k < ipMostVisitList.size(); k++) {
    		System.out.println(ipMostVisitList.get(k));	
    	}
       	
       	
       	HashMap<String, ArrayList<String>> IpDaysMap = test.iPsForDays();
 /*      	
       	for (String key : IpDaysMap.keySet()) {
       		System.out.println("Day: " + key);
       		System.out.println(IpDaysMap.get(key));
       	}
 */ 
       	String maxDay = test.dayWithMostIPVisits(IpDaysMap);
        String dayQ4 = "Mar 17"; 	
       	ArrayList<String> listQ4 = test.iPsWithMostVisitsOnDay(IpDaysMap, dayQ4);
       	for (int k=0; k < listQ4.size(); k++) {
    		System.out.println(listQ4.get(k));	
    	}
    	
    	System.out.println("*******************************************************************");
    	
    	LogAnalyzer test2 = new LogAnalyzer();
    	test2.readFile("./src/week3/weblog1_log");
    	HashMap<String, Integer> countMap2 = test2.countVisitsPerIP();
       	int ipMax2 = test2.mostNumberVisitsByIP(countMap2);
       	System.out.println("Q1: most number visit IP: " + ipMax2);
       	
       	ArrayList<String> mostVisitIp2 = test2.iPsMostVisits(countMap2);
     	System.out.println("Q2: What single Ip address is returned");
       	printArrayList(mostVisitIp2);

       	HashMap<String, ArrayList<String>> IpDaysMap2 = test2.iPsForDays();
       	String maxDay2 = test2.dayWithMostIPVisits(IpDaysMap2);
    	System.out.println("Q3: Day: " + maxDay2);
    	
    	System.out.println("*******************************************************************");
    	LogAnalyzer test3 = new LogAnalyzer();
    	test3.readFile("./src/week3/weblog2_log");
    	int uniqIP = test3.countUniqueIPs();
    	System.out.println("Q4: CountUniqueIP: " + uniqIP);
    	ArrayList<String> arrayQ5 = test3.uniqueIPVisitsOnDay("Sep 24");
    	System.out.println("Q5: Size of array: " + arrayQ5.size());
    	int q6 = test3.countUniqueIPsInRange(200, 299); 
    	System.out.println("Q6: Number returned uniq Ips in range: " + q6);
    	HashMap<String, Integer> countMap3 = test3.countVisitsPerIP();
    	int q7 = test3.mostNumberVisitsByIP(countMap3);
    	System.out.println("Q7: Most number visit by IP: " + q7);
    	ArrayList<String> arrayQ8 = test3.iPsMostVisits(countMap3);
    	System.out.println("Q8: IP most vistis: ");
    	for (int k=0; k < arrayQ8.size(); k++) {
    		System.out.println(arrayQ8.get(k));	
    	}
    	
    	HashMap<String, ArrayList<String>> ipDaysMap3 = test3.iPsForDays();
    	String maxDay3 = test3.dayWithMostIPVisits(ipDaysMap3);
    	System.out.println("Q9: day with most visits: " + maxDay3);
    	
    	ArrayList<String> arrayQ9 = test3.iPsWithMostVisitsOnDay(ipDaysMap3, "Sep 30");
    	for (int k=0; k < arrayQ9.size(); k++) {
    		System.out.println(arrayQ9.get(k));	
    	}
    }
    	
    public static void main (String[] args) {
    	    Tester w3 = new Tester();
    	    w3.testLogAnalyzer();
    	    
    }
}

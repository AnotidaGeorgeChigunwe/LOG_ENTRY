
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (01/01/2019)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String l : fr.lines()) {
             WebLogParser webLgParser = new WebLogParser();
             LogEntry logE = webLgParser.parseEntry(l);
             records.add(logE);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs () {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry logE : records) {
             String ipAdd = logE.getIpAddress();
             if (!uniqueIPs.contains(ipAdd)){
                uniqueIPs.add(ipAdd);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int number) {
         for (LogEntry logE : records) {
             if ( logE.getStatusCode() > number){
                System.out.println(logE);
             }
         }            
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay (String Mmm_Dd) {
         ArrayList<String> logOnDate = new ArrayList<String>();
         for (LogEntry logE : records) {
             String currDate = logE.getAccessTime().toString();
             //System.out.println(currDate);//test
             String ipAdd = logE.getIpAddress();
             //System.out.println(ipAdd);//test
             if(currDate.contains(Mmm_Dd) && !logOnDate.contains(ipAdd)) {
                 logOnDate.add(ipAdd);
             }
         }
         return logOnDate;
     }
     
     public int countUniqueIPsInRange( int low, int high ) {
         int count =0;
         ArrayList<String> uniqueOnes = new ArrayList<String>();
         for (LogEntry logE : records) {
             String ipAdd = logE.getIpAddress();
             if ( logE.getStatusCode() >= low && logE.getStatusCode() <= high){
                if (!uniqueOnes.contains(ipAdd)) {
                    count++;
                    uniqueOnes.add(ipAdd);
                }
             }
         } 
         return count;   
     }
     //CORRECT
     public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> unquieIPCount = new HashMap<String,Integer>();
        for (LogEntry logE : records) {
            String ip = logE.getIpAddress();
            if (!unquieIPCount.containsKey(ip)) {
                unquieIPCount.put(ip,1);
            }
            else {
                unquieIPCount.put(ip, unquieIPCount.get(ip)+1);
            }
        }
        return unquieIPCount; 
     }
     //CORRECT
     public int mostNumberVisitsByIP(HashMap<String,Integer> map) {
         int maxValue = 0;
         for (String s: map.keySet()) {
             int currValue = map.get(s);
             if (currValue>maxValue) {
                 maxValue=currValue;
             }
         }
         return maxValue;
     }
     //CORRECT
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map) {
         int max = mostNumberVisitsByIP(map);
         ArrayList<String> arrayList = new ArrayList<String>();
         for (String s: map.keySet()) {
             int currValue = map.get(s);
             if (currValue==max) {
                 arrayList.add(s);
             }
         }
         return arrayList;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays () {
         HashMap<String,ArrayList<String>> map = new HashMap<String,
                                                           ArrayList<String>>();
         for (LogEntry logE : records) {
             String time = logE.getAccessTime().toString();
             String currDate = time.substring(4,10);
             String ipAdd = logE.getIpAddress();
             ArrayList<String> arrayList = new ArrayList<String> ();
            if (!map.containsKey(currDate)){ 
                arrayList.add(ipAdd);
                map.put(currDate,arrayList);
            }
            else {
                arrayList= map.get(currDate);
                arrayList.add(ipAdd);
                map.put(currDate,arrayList);
            }
         }
         return map;
     }
     
     public String dayWithMostIPVisits (HashMap<String,ArrayList<String>> map) {
         String date = "*NO DATE*";
         int max = 0;
         for (String s : map.keySet()) {
             ArrayList<String> arrayList = map.get(s);
             if (arrayList.size()> max) {
                 max = arrayList.size();
                 date = s;
             }
         }
         return date;
     }
     
      
     private ArrayList<String> mostVisitingIPinArray(ArrayList<String> array) {
        HashMap<String,Integer> unquieIPCount = new HashMap<String,Integer>();
        for (String s : array) {
            if (!unquieIPCount.containsKey(s)) {
                unquieIPCount.put(s,1);
            }
            else {
                unquieIPCount.put(s, unquieIPCount.get(s)+1);
            }
        }
        return iPsMostVisits(unquieIPCount);        
     }
     
     public  ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map,String Mmm_Dd ) {
         ArrayList<String> arrayList = new ArrayList<String>();
         for (String s : map.keySet()) {
             if (s.contains(Mmm_Dd)) {
                 ArrayList<String> currIPs = map.get(s);                     
                 arrayList = mostVisitingIPinArray(currIPs);
             }
         }
         return arrayList;
     }
     
     
}


/**
 * Write a description of class Tester here.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (01/01/2019)
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
    
    public void testLogAnalyzer() {
        LogAnalyzer logAna = new LogAnalyzer ();
        logAna.readFile("Data\\weblog1_log.txt");
        logAna.printAll();
        System.out.println("\n\n\n");//break
        int UniqueIPs = logAna.countUniqueIPs ();
        System.out.println("The unquie IP Address are : " + UniqueIPs);
        System.out.println("\n\n\n all higher than a number below");//break
        logAna.printAllHigherThanNum(400);
        System.out.println("all higher than a number above \n\n\n");//break
        ArrayList<String> IPVisitOnDay = logAna.uniqueIPVisitsOnDay ("Mar 17");
        System.out.println("The IPVisitOnDay size is : " + IPVisitOnDay.size());
        for (String s : IPVisitOnDay) {
            System.out.println("\n"+ s);
        }
        System.out.println(logAna.countUniqueIPsInRange( 200, 299 ));
        HashMap<String,Integer> map = logAna.countVisitsPerIP();
        System.out.println("the mostNumberVisitsByIP : " + 
                            logAna.mostNumberVisitsByIP(map) + "\n");
        ArrayList<String> iPsMostVisits = logAna.iPsMostVisits(map);
        System.out.println("The iPsMostVisits below \n" );
        for (String s : iPsMostVisits) {
            System.out.println("\n"+ s);
        } 
        System.out.println("The iPsMostVisits above \n" );
        HashMap<String,ArrayList<String>> map2 =logAna.iPsForDays ();
        System.out.println( "The dayWithMostIPVisits : " + 
                           logAna.dayWithMostIPVisits (map2));
                           
        ArrayList<String> iPsWithMostVisitsOnDay = 
                            logAna.iPsWithMostVisitsOnDay(map2,"Mar 17");
        System.out.println("The iPsWithMostVisitsOnDay below \n" );
        for (String s : iPsWithMostVisitsOnDay) {
            System.out.println("\n"+ s);
        }
    }
}

import edu.duke.*; 
import java.io.File; 
import org.apache.commons.csv.*; 

/**
 * Write a description of printNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class printNames {
    public void printNames () {
        FileResource fr = new FileResource(); 
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2)); 
            if(numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + 
                                    " Gender " + rec.get(1) + 
                                    " Num Born " + rec.get(2));                         
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0; 
        int totalBoys = 0; 
        int totalGirls = 0; 
        int totalNames = 0; 
 
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2)); 
            totalBirths += numBorn; 
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;      
                totalNames++; 
            } else {
                totalGirls += numBorn;  
                totalNames++; 
            }
        }        
        System.out.println("Total births = " + totalBirths); 
        System.out.println("Total Girls = " + totalGirls); 
        System.out.println("Total Boys = " + totalBoys); 
        System.out.println("Total names = " + totalNames); 
    }
    
    
    public void testTotalBirths() {
        FileResource fr = new FileResource("/babyNames/data/example-small.csv"); 
        totalBirths(fr);                 
    }
    
    public int getRank (int year, String name, String gender) {
        int rank = 0; 
        int position = 0; 
        //"E:\babyNames\data\example-small.csv"
        FileResource fr = new FileResource("/babyNames/data/yob" + year + "short.csv"); 
        CSVParser parser = fr.getCSVParser(false); 
        for (CSVRecord rec : parser) {
             
            String nameFound = rec.get(0); 
            String genderFound = rec.get(1); 
                                             
            if (genderFound.equals(gender)) { 
                ++rank;
                if (nameFound.equals(name)) {
                rank = rank; 
                break; 
                }
            }                                    
        }
        
        if (rank == 0) {
                return -1; 
        }         
            
        return rank; 
    }
    
    public void testGetRank() {
        //FileResource fr = new FileResource(); 
        System.out.println(getRank(2014, "Ava", "F")); 
        System.out.println(getRank(2014, "William", "M")); 
        System.out.println(getRank(2014, "Emma", "F")); 
        System.out.println(getRank(2014, "Noah", "M"));
        System.out.println(getRank(2014, "Mason", "M")); 
    }
}

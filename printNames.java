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
    
    public int getRank (String name, String gender) {
        int rank = 0; 
        int position = 0; 
        //"E:\babyNames\data\example-small.csv"
        FileResource fr = new FileResource(); 
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
       //System.out.println(getRank(2014, "Ava", "F")); 
       // System.out.println(getRank(2014, "William", "M")); 
       System.out.println(getRank("Emma", "F")); 
       //System.out.println(getRank(2014, "Noah", "M"));
       System.out.println(getRank("Mason", "M")); 
    }
    
    public String getName (int year, int rank, String gender) {
        FileResource fr = new FileResource("/babyNames/data/yob" + year + "short.csv");
        //Don't forget to specify there is no header row with "false" 
        CSVParser parser = fr.getCSVParser(false); 
        String nameFound = ""; 
        int rankFound = 0; 
        
        for (CSVRecord rec : parser) {            
            String genderFound = rec.get(1); 
            
            if (genderFound.equals(gender)) {
                rankFound++;   
                 
                if (rankFound == rank) {                   
                    nameFound = rec.get(0); 
                    break; 
                }
            }            
        }
        
        if (rankFound == 0) {
            return "No Name";                 
        }                
        return nameFound; 
    }
    
    public void testGetName() {
        System.out.println(getName(2014, 3, "M")); 
        System.out.println(getName(2012, 4, "M")); 
        System.out.println(getName(2012, 1, "F")); 
    }
    
    /*
    public void whatIsNameInYear (String name, int year, int  newYear, String gender) {
             
        int initialRank = getRank(year, name, gender);
            
        String equivName = getName(newYear, initialRank, gender); 
        
        System.out.println(name + " born in " + year + ", " 
                            + "would be " + equivName + " if she was born in " + newYear); 
        
    }
    
    */
   
    public void testWhatIsNameInYear() {
        //whatIsNameInYear("Emma", 2014, 2012, "F");        
    }
    
    public void yearOfHighestRank (String name, String gender) {
        int currRank = 0; 
        int highestRank = 0; 
        File fileName = null; 
        int yearWithRank = 0; 
        
        
        DirectoryResource dr = new DirectoryResource(); 
        for (File f : dr.selectedFiles()) {
            String highestRankName = f.getName();  
            String year = highestRankName.substring(3, 7); 
            int yearInt = Integer.parseInt(year); 
            
            FileResource fr = new FileResource(f);  
            CSVParser parser = fr.getCSVParser(false); 
            for (CSVRecord currRow : parser) {
            
            String nameFound = currRow.get(0); 
            String genderFound = currRow.get(1);
            
            if (genderFound.equals(gender)) { 
                ++currRank;
                
                if (nameFound.equals(name)) {  
                    currRank = currRank;   
                    //System.out.println(currRank); 
                    break;
                    
                }
                break; 
            }        
                
            } 
            
            if (currRank == 0) {
                currRank = -1; 
            }
            
            if (highestRank == 0) {
                    highestRank = currRank;
                    
                } else {
                    if (currRank < highestRank && currRank != -1) {
                    highestRank = currRank;  
                    yearWithRank = yearInt; 
                }
            
            }
        
        
         
            System.out.println(yearInt); 
        }    
        
        //return highestRank; 
    }
         


    
    public void testYearOfHighestRank() {
       yearOfHighestRank("Emma", "F"); 
                
    }
}




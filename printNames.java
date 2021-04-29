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
            System.out.println("Name " + rec.get(0) + 
                                " Gender " + rec.get(1) + 
                                " Num Born " + rec.get(2));                         
        }
    }
}

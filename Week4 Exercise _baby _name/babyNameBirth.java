
/**
 * Write a description of jhs here.
 * 
 * @author (Indra Katuwal ) 
 * @version (a version number or a date)
 * 
 * exercise Baby name Quiz week 4
 **/
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class babyNameBirth {
    public void totalBirths(FileResource fr){
        int totalBirth = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numGirls = 0;
        int numBoys = 0;
        int numTotal = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBirths = Integer.parseInt(rec.get(2));
            totalBirth += numBirths;
            if (rec.get(1).equals("M")){
                totalBoys += numBirths;
                numBoys += 1;
            }
            else {
                totalGirls += numBirths;  
                numGirls += 1;
            }
        }
        numTotal = numGirls + numBoys;
        System.out.println("total birth = " + totalBirth);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("number of names = " + numTotal);
        System.out.println("number of girl names = " + numGirls);
        System.out.println("number of boy names = " + numBoys);
    }
    public void testTotalBirth(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public int getRank(int year, String name, String gender, FileResource fr){
        int counter = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                counter++;
                if (rec.get(0).equals(name)){
                    return counter;
                }
            }
        }
        return -1;
    }
    public void testGetRank(){
        FileResource fr = new FileResource();
        System.out.println(getRank(1971, "Frank", "M", fr));
    }
    public String getName(int year, int rank, String gender, FileResource fr){
        int counter = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                counter++;
                if (counter == rank){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    public void testGetName(){
        FileResource fr = new FileResource();
        System.out.println(getName(2012, 450, "M", fr));
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        FileResource fr = new FileResource();
        int rank = getRank(year, name, gender, fr);
        FileResource frr = new FileResource();
        System.out.println(name + " born in " + year + " would be " + getName(newYear, rank, gender, frr) + 
                           " if she born in " + newYear);
    }
    public void testwhatIsNameInYear(){
        whatIsNameInYear("Susan", 1972, 2014, "F");
    }
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource(); 
        int highestRank = 99999;
        int highestYear = 0;
        for (File f : dr.selectedFiles()){
            String ye = f.getName();
            ye = ye.substring(ye.indexOf("yob") + 3, ye.indexOf("yob") + 7);
            int year = Integer.parseInt(ye);
            FileResource fr = new FileResource(f);
            int currRank = getRank(year, name, gender, fr);
            if (currRank < highestRank && currRank != -1){
                highestRank = currRank;
                highestYear = year;
            }
        }
        if (highestRank == 99999){
            return -1;
        }
        
        return highestYear;
    }
    public void testYearOfHighestRank(){
    System.out.println(yearOfHighestRank("Emily", "F"));
    }
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource(); 
        double counter = 0;
        double rankCounter = 0;
        for (File f : dr.selectedFiles()){
            String ye = f.getName();
            ye = ye.substring(ye.indexOf("yob") + 3, ye.indexOf("yob") + 7);
            int year = Integer.parseInt(ye);
            FileResource fr = new FileResource(f);
            int currRank = getRank(year, name, gender, fr);
            if (currRank != -1){
                counter++;
                rankCounter += currRank;
            }
        }
        if(counter == 0){
                return -1.0;
        }
        return rankCounter / counter;
    }
    public void testGetAverageRank(){
        System.out.println(getAverageRank("Robert", "M"));
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String filename = "../us_babynames/us_babynames_by_year/yob" + String.valueOf(year) + ".csv";
        FileResource fr = new FileResource(filename);
        int counter = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                if (rec.get(0).equals(name)){
                    return counter;
                }
                counter += Integer.parseInt(rec.get(2));
            }
        }
        return counter;
    }
    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
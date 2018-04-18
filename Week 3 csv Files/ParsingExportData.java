
/**
 * 
 * Indra Katuwal
 * Accesssing the data in java programming
 * creating file resource 
 */



import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        // Print info about a country
        String info = countryInfo(parser, "Nauru");
        System.out.println(info);
        
        // Print countries that export certain items
        // Reset parser
        parser = fr.getCSVParser();
        String exportItem1 = "fish";
        String exportItem2 = "nuts";
        System.out.println("Countries that export " + exportItem1 + " and " + exportItem2 + ":");
        listExportersTwoProducts(parser, exportItem1, exportItem2);
        
        //Print that the name of countires
        parser = fr.getCSVParser();
        String exportItem3 = "gold";
        String exportItem4 = "diamond";
        System.out.println("Countries that export " + exportItem3 + " and " + exportItem4 + ":");
        listExportersTwoProducts(parser, exportItem3, exportItem4);
        
        // Print number of countries that export a certain item
        // Reset parser
        parser = fr.getCSVParser();
        String exportItem = "sugar";
        int num = numberOfExporters(parser, exportItem);
        System.out.println("Number of countries that export " + exportItem + ": " + num);
        
        // Print number of countries that export gold
        // Reset parser
        parser = fr.getCSVParser();
        String exportItem6 = "gold";
        int num2 = numberOfExporters(parser, exportItem6);
        System.out.println("Number of countries that export " + exportItem6 + ": " + num2);
        
        // Print countries and their Value amount for all countries whose Value (dollars) string is longer than amount string
        // Reset parser
        parser = fr.getCSVParser();
        String amount = "$999,999,999,999";
        System.out.println("Countries whose \"Value (dollars)\" string is longer than " + amount + ":");
        bigExporters(parser, amount);
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        // Parse the contents of the file record-wise
        for (CSVRecord record : parser) {
            // If there exists a record containing a "Value (dollars)" string longer than amount,
            if (record.get("Value (dollars)").length() > amount.length()) {
                // Print country and value amount
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int num = 0;
        // Parse the contents of the file record-wise
        for (CSVRecord record : parser) {
            // If there exists a record containing the export item,
            if (record.get("Exports").contains(exportItem)) {
                // Increment count by one
                num++;
            }
        }
        return num;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        // Parse the contents of the file record-wise
        for (CSVRecord record : parser) {
            // If there exists a record containing both export items,
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {
                // Print the country associated with the record
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public String countryInfo(CSVParser parser, String country) {
        // NOTE: Can't be declared inside the for loop?
        String info = "";
        // Parse the contents of the file record-wise
        for (CSVRecord record : parser) {
            // If there exists a record for the country,
            if (record.get("Country").equals(country)) {
                // Return a string of info about the country
                info = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return info;
            }
        }
        // If there is no information about the country
        return "NOT FOUND";
    }
}

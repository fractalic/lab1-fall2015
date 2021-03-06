import java.io.*;
import java.util.*;

/**
 * This is the main class for the Tour de France program. It has the main( )
 * method that reads biker information from a file and then performs some
 * computations with the data.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public class TdFMain {

    /**
     * This is the main( ) method.
     * 
     * @param args
     *            We do not use this parameter at this point.
     */
    public static void main(String[] args) {
    	// Hardcoded competition years contained in the dataset.
    	int firstCompetitionYear = 2005;
    	int lastCompetitionYear = 2012;

        // The FileInputStream to open and read from the file that
        // has the Tour de France data.
        FileInputStream tdfStream;

        // This map maintains the relationship between the
        // name of the biker and the object that holds the
        // biker's data.
        Map<String, Biker> allBikers = new TreeMap<String, Biker>();

        // Let us try to open the data file.
        // The file name is hardcoded, which is not elegant.
        // Suffices for now.
        try {
            tdfStream = new FileInputStream("tdf.txt");
        } catch (FileNotFoundException e) {
            // If, for some reason, the file was not found,
            // then throw an exception.
            // The file is however included in the git repo
            // so this should not happen.
            throw new RuntimeException(e);
        }

        // We have opened the file.
        // Let us try to read data.
        try {
            // We will use a BufferedReader to read the data from the file.
            BufferedReader tdfReader = new BufferedReader(
                    new InputStreamReader(tdfStream));

            // We will read one line at a time and then split it.
            // The format for tdf.txt is as follows:
            // - Column 1: Year
            // - Column 2: Average Speed
            // - Column 3: Biker's last name
            // - Column 4: Biker's first name
            // tdf.txt contains real data. It is also noisy like real data.
            // Some of the names have formatting issues but we have left
            // things as is.
            String line;

            // Read each line of the file until there is nothing left to read.
            while ((line = tdfReader.readLine()) != null) {

                // Split the line into columns using the split( )
                // method for Strings.
                String[] columns = line.split(",");

                // After the split, we should have the following (as Strings):
                // - columns[0] contains the year,
                // - columns[1] contains the average speed,
                // - columns[2] contains the last name,
                // - columns[3] contains the first name.

                // Is this biker already in our list?
                // If so then we do not have to create a new biker.
                // We only have to add an entry to the existing biker.
                // We will use the full name to search allBikers.
                String key = columns[3] + columns[2]; // this is the full name

                // If search is successful then add stats
                if (allBikers.containsKey(key)) {
                    allBikers.get(key).addPerformanceStats(
                            Integer.parseInt(columns[0]),
                            Double.parseDouble(columns[1]));

                    // System.out.println("Added data to biker "+allBikers.get(
                    // key ).getName( ));
                } else {
                    // Let us now create a new Biker
                    Biker newBiker = new Biker(columns[2], columns[3],
                            Integer.parseInt(columns[0]),
                            Double.parseDouble(columns[1]));

                    // Now we add this biker to allBikers.
                    allBikers.put(key, newBiker);

                    // System.out.println("Created new biker "+newBiker.getName()
                    // );
                }

            }
            tdfReader.close();
            tdfStream.close();
        } catch (Exception e) {
            // If, for any reason, we had some problems reading data...
            throw new RuntimeException(e);
        }
        
        // Save the median speed for each biker.
        ArrayList<Double> bikerMedianSpeeds = new ArrayList<Double>();
        // Save all the average speeds for every biker over every year.
        ArrayList<Double> allRecordedAverageSpeeds = new ArrayList<Double>();
        
        // for each entry in allBikers:
        // print the best gain for the biker.
        // The best gain is defined as the maximum improvement in
        // speed between successive entries at the Tour de France.
        // This does not have to be between consecutive years;
        // entries with a gap (no racing) between the years is okay.
        for (Map.Entry<String, Biker> currentEntry : allBikers.entrySet()) {
            Biker currentBiker = currentEntry.getValue();
            
            // Compute each biker's median speed over all years they competed.
            bikerMedianSpeeds.add(currentBiker.getMedianSpeed());
            
            // List all the average speeds for every biker over every year.
            Boolean competedCurrentYear = false;
        	for (int competitionYear = firstCompetitionYear;
            		competitionYear <= lastCompetitionYear;
            		competitionYear++ ) {
        		
        		Double bikerSpeedCurrentYear =
        				currentBiker.getSpeedForYear(competitionYear);
        		
        		competedCurrentYear = (bikerSpeedCurrentYear > 0) ? true : false;
        		
            	if (competedCurrentYear) {
            		allRecordedAverageSpeeds.add(bikerSpeedCurrentYear);
            	}
            }

            System.out.println(String.format("%-30s: %s",
                    currentBiker.getName(), currentBiker.getBestGain()));
        }
        
        // Compute the median over all recorded speeds.
        Collections.sort(allRecordedAverageSpeeds);
        double medianSpeed = getMedian(allRecordedAverageSpeeds);

        System.out.println("\nThe median speed at the Tour de France is "
                + medianSpeed);
        
        // Compute the median of each biker's median speed over
        // all years they competed.
        Collections.sort(bikerMedianSpeeds);
        double medianOfMedians = getMedian(bikerMedianSpeeds);

        System.out.println("\nThe median of medians at the Tour de France is "
                + medianOfMedians);
    }
    /**
     * The getMedian( ) method finds the median of a list.
     * The median is the middle element, or the average of the two
     * middle elements in a list with an even number of elements.
     * 
     * @param sortedElements
     *            A sorted ArrayList of Doubles, from which to select the median.
     *            
     */
    public static double getMedian(ArrayList<Double> sortedElements) {
    	double median = sortedElements.get(sortedElements.size() / 2);
        if ((sortedElements.size() % 2)==0) {
        	Double lowerMidpoint =
        			sortedElements.get(sortedElements.size() / 2 - 1);
        	
        	Double upperMidpoint =
        			sortedElements.get(sortedElements.size() / 2);
        	
        	median = (lowerMidpoint + upperMidpoint) / 2.0;
        }
    	return median;
    }
}
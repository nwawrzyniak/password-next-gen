package nwawsoft.pwng.model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Rating {
    public Rating() {

    }

    public boolean dictionaryCheck(String pLine) {
        BufferedReader myReader;
        String currentLine;
        try {
            myReader = new BufferedReader(new FileReader("dictionary_min_5.txt"));
            while ((currentLine = myReader.readLine()) != null) {
                if (pLine.toLowerCase().contains(currentLine.toLowerCase())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}

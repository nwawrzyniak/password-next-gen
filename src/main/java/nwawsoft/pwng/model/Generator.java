package nwawsoft.pwng.model;

import nwawsoft.pwng.exceptions.LogicErrorException;
import nwawsoft.util.MutatedVowels;

import java.util.Random;

public class Generator {
    private Rating r = new Rating();
    private char[] set;

    public Generator(CharacterSet cs) {
        CharacterSetFactory csf = new CharacterSetFactory(cs);
        set = csf.getCharacterSet();
    }

    public String create() {
        // ToDo: REWORK ENTIRELY! Use set.
        Random rand = new Random();
        String output = "";
        int wantedLength = rand.nextInt(5) + 14;
        char[] symbol = new char[1];
        int lastUse = -1;

        while (output.length() < wantedLength) {
            int use = rand.nextInt(4);
            symbol[0] = MutatedVowels.oE;
            if ((use == 0) && (lastUse != 0)) {
                int a = rand.nextInt(26) + 97;
                symbol = Character.toChars(a);
            }
            if ((use == 1) && (lastUse != 1)) {
                int a = rand.nextInt(26) + 65;
                symbol = Character.toChars(a);
            }
            if ((use == 2) && (lastUse != 2)) {
                int a = rand.nextInt(10) + 48;
                symbol = Character.toChars(a);
            }
            if ((use == 3) && (lastUse != 3)) {
                int a = rand.nextInt(5) + 91;
                symbol = Character.toChars(a);
            }
            if (use != lastUse) {
                output = output + symbol[0];
            }
            lastUse = use;
        }
        try {
            if (r.hasTypes(output, 4)) {
                return output;
            }
        } catch (LogicErrorException e) {
            e.printStackTrace();
        }
        return create();
    }
}

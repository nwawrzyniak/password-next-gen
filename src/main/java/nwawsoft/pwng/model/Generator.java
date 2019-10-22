package nwawsoft.pwng.model;

import nwawsoft.pwng.exceptions.UnhandledCharacterSetException;

import java.util.Random;

public class Generator {
    private Rating r = new Rating();
    private char[] set;
    private CharacterSet cs;

    public Generator(CharacterSet cs) {
        this.cs = cs;
        CharacterSetFactory csf = new CharacterSetFactory(cs);
        set = csf.getCharacterSet();
    }

    public String create() throws UnhandledCharacterSetException {
        Random rand = new Random();
        StringBuilder output = new StringBuilder();
        int wantedLength = rand.nextInt(5) + 14;
        while (output.length() < wantedLength) {
            char c = set[rand.nextInt(set.length)];
            output.append(c);
        }
        if (cs == CharacterSet.OLD || cs == CharacterSet.FULL) {
            if (r.level5Criteria(output.toString())) {
                return output.toString();
            } else {
                return create();
            }
        } else if (cs == CharacterSet.EASY_GERMAN || cs == CharacterSet.EASY_ENGLISH) {
            if (r.level3Criteria(output.toString())) {
                return output.toString();
            } else {
                return create();
            }
        } else {
            throw new UnhandledCharacterSetException(cs);
        }
    }
}

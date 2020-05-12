package com.nwawsoft.pwng.model;

import com.nwawsoft.pwng.exceptions.LogicErrorException;
import com.nwawsoft.pwng.exceptions.UnhandledCharacterSetException;
import com.nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import com.nwawsoft.pwng.model.characterset.CharacterSet;
import com.nwawsoft.pwng.model.characterset.CharacterSetFactory;

import java.util.Random;

/**
 * Handles the generation/creation of safe passwords. To generate a password you need to specify a CharacterSet.
 */
public class Generator {
    private final Rating r;
    private final CharacterSet cs;
    private final char[] set;

    public Generator(final CharacterSet cs) {
        r = new Rating();
        this.cs = cs;
        CharacterSetFactory csf = new CharacterSetFactory();
        set = csf.buildCharacterSet(cs);
    }

    /**
     * Generates a pseudo-random level 5 password with a length between (and including) 14 and 18 characters.
     *
     * @return a level 5 password as a String.
     * @throws UnhandledCharacterSetException if CharacterSet is unhandled.
     */
    public String create() throws UnhandledCharacterSetException, LogicErrorException, UnknownCharacterTypeException {
        Random rand = new Random();
        StringBuilder output = new StringBuilder();
        int wantedLength = rand.nextInt(5) + 14;
        while (output.length() < wantedLength) {
            char c = set[rand.nextInt(set.length)];
            output.append(c);
        }
        if (cs == CharacterSet.FULL || cs == CharacterSet.OPTIMIZED) {
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

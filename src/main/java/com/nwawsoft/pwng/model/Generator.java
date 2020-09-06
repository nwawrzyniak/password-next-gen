package com.nwawsoft.pwng.model;

import com.nwawsoft.pwng.exceptions.LogicErrorException;
import com.nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import com.nwawsoft.pwng.model.characterset.CharacterSet;

import java.util.Random;

/**
 * Handles the generation/creation of safe passwords. To generate a password you need to specify a CharacterSet.
 */
public class Generator {
    private final Rating r;
    private final char[] set;

    public Generator(final CharacterSet cs) {
        r = new Rating();
        set = cs.getChars().toCharArray();
    }

    /**
     * Generates a pseudo-random level 5 password with a length between (and including) 14 and 18 characters.
     *
     * @return a level 5 password as a String.
     */
    public String create(final int tryCount) throws LogicErrorException, UnknownCharacterTypeException {
        // ToDo: Move magic numbers to Settings
        // ToDo: Go down properly in Criteria (5 -> 4 -> 3 -> 2 -> 1)
        Random rand = new Random();
        StringBuilder output = new StringBuilder();
        int wantedLength = rand.nextInt(5) + 14;
        while (output.length() < wantedLength) {
            char c = set[rand.nextInt(set.length)];
            output.append(c);
        }
        if (tryCount < Settings.GENERATOR_MAX_TRIES_UNTIL_LOWER_CRITERIA) {
            if (r.level5Criteria(output.toString())) {
                return output.toString();
            } else {
                return create(tryCount + 1);
            }
        } else {
            if (r.level3Criteria(output.toString())) {
                return output.toString();
            } else {
                return create(tryCount + 1);
            }
        }
    }
}

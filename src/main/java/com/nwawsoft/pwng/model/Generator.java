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
     * Generates a pseudo-random password with the highest possible password level (starting from level 5) with between
     * Settings.GENERATOR_MIN_PASSWORD_LENGTH and (Settings.GENERATOR_MIN_PASSWORD_LENGTH +
     * Settings.GENERATOR_BONUS_MAX_LENGTH) characters. If Settings.GENERATOR_MAX_TRIES_UNTIL_LOWER_CRITERIA tries are
     * needed a one level weaker password is generated instead.
     *
     * @return a password as a String.
     */
    public String create() throws LogicErrorException, UnknownCharacterTypeException {
        return create(0, 5);
    }

    private String create(final int tryCount, final int currentLevel) throws LogicErrorException,
            UnknownCharacterTypeException {
        Random rand = new Random();
        StringBuilder output = new StringBuilder();
        int wantedLength = (rand.nextInt(Settings.GENERATOR_BONUS_MAX_LENGTH) + 1) +
                Settings.GENERATOR_MIN_PASSWORD_LENGTH;
        while (output.length() < wantedLength) {
            char c = set[rand.nextInt(set.length)];
            output.append(c);
        }
        if (currentLevel == 5) {
            if (tryCount < Settings.GENERATOR_MAX_TRIES_UNTIL_LOWER_CRITERIA) {
                if (r.level5Criteria(output.toString())) {
                    return output.toString();
                } else {
                    return create(tryCount + 1, currentLevel);
                }
            } else {
                return create(0, currentLevel - 1);
            }
        } else if (currentLevel == 4) {
            if (tryCount < Settings.GENERATOR_MAX_TRIES_UNTIL_LOWER_CRITERIA) {
                if (r.level4Criteria(output.toString())) {
                    return output.toString();
                } else {
                    return create(tryCount + 1, currentLevel);
                }
            } else {
                return create(0, currentLevel - 1);
            }
        } else if (currentLevel == 3) {
            if (tryCount < Settings.GENERATOR_MAX_TRIES_UNTIL_LOWER_CRITERIA) {
                if (r.level3Criteria(output.toString())) {
                    return output.toString();
                } else {
                    return create(tryCount + 1, currentLevel);
                }
            } else {
                return create(0, currentLevel - 1);
            }
        } else if (currentLevel == 2) {
            if (tryCount < Settings.GENERATOR_MAX_TRIES_UNTIL_LOWER_CRITERIA) {
                if (r.level2Criteria(output.toString())) {
                    return output.toString();
                } else {
                    return create(tryCount + 1, currentLevel);
                }
            } else {
                return create(0, currentLevel - 1);
            }
        } else { // level 1
            return output.toString();
        }
    }
}




























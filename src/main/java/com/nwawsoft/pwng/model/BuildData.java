package com.nwawsoft.pwng.model;

public class BuildData {
    private static final int mainVersion = 1;
    private static final int featureUpdate = 0;
    private static final int bugfixUpdate = 0;
    private static final int buildNumber = 18; // counting since March 11, 2020.

    /**
     * Assembles and returns the version number of the current build.
     *
     * @return a String representation of the current build.
     */
    public static String getVersion() {
        return mainVersion + "." + featureUpdate + "." + bugfixUpdate;
    }

    /**
     * Returns the build number of the current build.
     *
     * @return the build number of the current build.
     */
    public static int getBuildNumber() {
        return buildNumber;
    }

    /**
     * Assembles and returns the version number of the current build and the build number itself.
     *
     * @return a String representation of the version number and the build number of the current build.
     */
    public static String getFullVersion() {
        return getVersion() + " (Build " + buildNumber + ")";
    }
}

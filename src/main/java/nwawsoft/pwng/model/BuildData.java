package nwawsoft.pwng.model;

public class BuildData {
    public int mainVersion = 1;
    public int featureUpdate = 0;
    public int bugfixUpdate = 0;
    public int buildNumber = 2; // counting since March 11, 2020.

    /**
     * Assembles and returns the version number of the current build.
     *
     * @return a String representation of the current build.
     */
    public String getVersion() {
        return mainVersion + "." + featureUpdate + "." + bugfixUpdate;
    }

    /**
     * Returns the build number of the current build.
     *
     * @return the build number of the current build.
     */
    public int getBuildNumber() {
        return buildNumber;
    }

    /**
     * Assembles and returns the version number of the current build and the build number itself.
     *
     * @return a String representation of the version number and the build number of the current build.
     */
    public String getFullVersion() {
        return mainVersion + "." + featureUpdate + "." + bugfixUpdate + "(Build " + buildNumber + ")";
    }
}

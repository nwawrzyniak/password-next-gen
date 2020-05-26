package com.nwawsoft.pwng.model;

import java.io.File;
import java.net.URL;

/**
 * Provides functions to load information from a/the resource directory.
 */
public class ResourceLoader {
    /**
     * Returns a File[] containing the full names of all files in the specified directory.
     *
     * @param directory any directory within the resource directory without a leading or ending '/' (slash).
     * @return a File[] containing the full names of all files in the specified directory. null if the URL matching the
     *          directory was not found.
     */
    public static File[] getResourceFolderFiles (final String directory) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource(directory);
        String path = null;
        if (url != null) {
            path = url.getPath();
        }
        if (path != null) {
            return new File(path).listFiles();
        } else {
            return null;
        }
    }
}

package com.nwawsoft.pwng.model.language;

public enum Language {
    ENGLISH(0, "en_"), GERMAN(1, "de_");

    private final int index;
    private final String countryCode;

    private Language(final int index, final String countryCode) {
        this.index = index;
        this.countryCode = countryCode;
    }

    public int getIndex() {
        return index;
    }

    public String getCountryCode() {
        return countryCode;
    }
}

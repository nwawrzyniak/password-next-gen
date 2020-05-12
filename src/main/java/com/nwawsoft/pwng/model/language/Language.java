package com.nwawsoft.pwng.model.language;

public enum Language {
    DUMMY(-1), ENGLISH(0), GERMAN(1);

    private final int index;

    private Language(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

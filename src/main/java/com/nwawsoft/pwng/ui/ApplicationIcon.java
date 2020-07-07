package com.nwawsoft.pwng.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApplicationIcon {
    private static final URL ICON16 = MainFrame.class.getResource("/graphics/icon/lock_colorful_16x.png");
    private static final URL ICON20 = MainFrame.class.getResource("/graphics/icon/lock_colorful_20x.png");
    private static final URL ICON32 = MainFrame.class.getResource("/graphics/icon/lock_colorful_32x.png");
    private static final URL ICON40 = MainFrame.class.getResource("/graphics/icon/lock_colorful_40x.png");
    private static final URL ICON64 = MainFrame.class.getResource("/graphics/icon/lock_colorful_64x.png");

    private final List<Image> images = new ArrayList<>();

    public List<Image> getApplicationIcon() {
        if (images.isEmpty()) {
            try {
                images.add(ImageIO.read(ICON64));
                images.add(ImageIO.read(ICON40));
                images.add(ImageIO.read(ICON32));
                images.add(ImageIO.read(ICON20));
                images.add(ImageIO.read(ICON16));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images;
    }
}

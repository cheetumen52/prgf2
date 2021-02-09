package raster;

import transforms.Col;

public class ZbufferVisibility {
    private ImageBuffer iBuffer;
    private DepthBuffer zBuffer;

    public ZbufferVisibility(int width, int height) {
        iBuffer = new ImageBuffer(width, height);
        zBuffer = new DepthBuffer(width, height);
    }

    public void drawElementWithZtest(int x, int y, double z, Col color) {
        //TODO
    }
}

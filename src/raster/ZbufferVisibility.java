package raster;

import transforms.Col;

public class ZbufferVisibility {
    private ImageBuffer iBuffer;
    private DepthBuffer zBuffer;

    public ZbufferVisibility(int width, int height) {
        this(new ImageBuffer(width, height));
    }

    public ZbufferVisibility(ImageBuffer imageBuffer) {
        iBuffer = imageBuffer;
        zBuffer = new DepthBuffer(imageBuffer.getWidth(), imageBuffer.getHeight());
    }

    public ImageBuffer getiBuffer() {
        return iBuffer;
    }

    public void setiBuffer(ImageBuffer iBuffer) {
        this.iBuffer = iBuffer;
    }

    public DepthBuffer getzBuffer() {
        return zBuffer;
    }

    public void setzBuffer(DepthBuffer zBuffer) {
        this.zBuffer = zBuffer;
    }

    public void drawElementWithZtest(int x, int y, double z, Col color) {
        if (z < zBuffer.getElement(x, y)) {
            zBuffer.setElement(x, y, z);
            iBuffer.setElement(x, y, color);
        }
    }
}

package render;

import raster.ZbufferVisibility;


public class RasterizerEdge {
    private ZbufferVisibility zBufferVisibility;
    private int width, height;

    public RasterizerEdge(ZbufferVisibility zBufferVisibility) {
        this.zBufferVisibility = zBufferVisibility;
        width = zBufferVisibility.getiBuffer().getWidth();
        height = zBufferVisibility.getiBuffer().getHeight();
    }

    public void rasterize(Edge edge) {

    }
}
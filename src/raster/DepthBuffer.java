package raster;

import java.util.Arrays;

public class DepthBuffer implements Raster<Double> {
    private final double[][] buffer;
    private double clearValue;

    public DepthBuffer(int width, int height) {
        buffer = new double[width][height];
        setClearValue(1.);
        clear();
    }

    @Override
    public void clear() {
        for (double[] doubles : buffer) {
            Arrays.fill(doubles, clearValue);
        }
    }

    @Override
    public void setClearValue(Double value) {
        this.clearValue = value;
    }

    @Override
    public int getWidth() {
        return buffer[0].length;
    }

    @Override
    public int getHeight() {
        return buffer.length;
    }

    @Override
    public Double getElement(int x, int y) {
        if (checkBorder(x, y)) return buffer[x][y];
        return null;
    }

    @Override
    public void setElement(int x, int y, Double value) {
        if (checkBorder(x, y)) buffer[x][y] = value;
    }
}

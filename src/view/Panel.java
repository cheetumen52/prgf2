package view;

import raster.ImageBuffer;
import transforms.Col;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public static final int WIDTH = 800, HEIGHT = 600;
    private ImageBuffer raster;

    Panel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        raster = new ImageBuffer(WIDTH, HEIGHT);
    }

    public ImageBuffer getRaster() {
        return raster;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        raster.repaint(g);
    }

    public void resize() {
        if (this.getWidth() < 1 || this.getHeight() < 1)
            return;
        ImageBuffer newRaster = new ImageBuffer(this.getWidth(), this.getHeight());
        newRaster.setClearValue(new Col(Color.BLACK.getRGB()));
        raster.setClearValue(new Col(Color.BLACK.getRGB()));
        clear();
        newRaster.draw(raster);
        raster = newRaster;
    }

    public void clear() {
        raster.clear();
    }
}

package render;

import raster.ZbufferVisibility;
import transforms.Col;
import transforms.Vec3D;

public class RasterizerTriangle {
    private ZbufferVisibility zbf;
    private int width, height;

    public RasterizerTriangle(ZbufferVisibility zbf) {
        this.zbf = zbf;
        width = zbf.getiBuffer().getWidth();
        height = zbf.getiBuffer().getHeight();
    }

    public void rasterize(Triangle triangle) {
        Vec3D a = triangle.getA().ignoreW().mul(new Vec3D(1, -1, 1)).add(new Vec3D(1, 1, 0)).mul(new Vec3D((width - 1) / 2, (height - 1) / 2, 1));
        Vec3D b = triangle.getB().ignoreW().mul(new Vec3D(1, -1, 1)).add(new Vec3D(1, 1, 0)).mul(new Vec3D((width - 1) / 2, (height - 1) / 2, 1));
        Vec3D c = triangle.getC().ignoreW().mul(new Vec3D(1, -1, 1)).add(new Vec3D(1, 1, 0)).mul(new Vec3D((width - 1) / 2, (height - 1) / 2, 1));

        //sort a,b,c by Y ; a.y <= b.y <= c.y

        //kontrola zda je v okně (X a Y)
        for (int y = (int) a.getY(); y < b.getY(); y++) {
            double s1 = (y - a.getY()) / (b.getY() - a.getY());
            Vec3D ab = a.mul(1 - s1).add(b.mul(s1));
            double s2 = (y - a.getY()) / (c.getY() - a.getY());
            Vec3D ac = a.mul(1 - s2).add(c.mul(s2));
            for (int x = (int) ab.getX(); x < (int) ac.getX(); x++) {
                //interpolace Z a color
                zbf.drawElementWithZtest(x, y, 0.5, new Col(0xff0000));
            }
        }
        //todo
    }
}

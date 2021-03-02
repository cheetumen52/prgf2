package render;

import model.Vertex;
import raster.ZbufferVisibility;
import transforms.Vec3D;

import java.awt.*;


public class RasterizerTriangle {
    private ZbufferVisibility zbf;
    private int width, height;
    private Shader shader;

    public RasterizerTriangle(ZbufferVisibility zbf) {
        this.zbf = zbf;
        width = zbf.getiBuffer().getWidth();
        height = zbf.getiBuffer().getHeight();
    }

       /*
    triangle vertices a,b,c are coordinates in NDC
    x,y is in interval <-1;1>
    z is in interval <0;1>
     */


    public void rasterize(Triangle triangle) {
        //viewport transform
        Vec3D a = triangle.getA().getPosition().ignoreW().mul(new Vec3D(1, -1, 1)).add(new Vec3D(1, 1, 0)).mul(new Vec3D((width - 1) / 2, (height - 1) / 2, 1));
        Vec3D b = triangle.getB().getPosition().ignoreW().mul(new Vec3D(1, -1, 1)).add(new Vec3D(1, 1, 0)).mul(new Vec3D((width - 1) / 2, (height - 1) / 2, 1));
        Vec3D c = triangle.getC().getPosition().ignoreW().mul(new Vec3D(1, -1, 1)).add(new Vec3D(1, 1, 0)).mul(new Vec3D((width - 1) / 2, (height - 1) / 2, 1));

        Vec3D temp;
        //sort a,b,c by Y ; c.y >= b.y >= a.y
        if (a.getY() > c.getY()) {
            temp = a;
            a = c;
            c = temp;
        }
        if (a.getY() > b.getY()) {
            temp = a;
            a = b;
            b = temp;
        }
        //staci zkontrolovat ktery je nejvetsi
        if (b.getY() > c.getY()) {
            temp = b;
            b = c;
            c = temp;
        }


        Vertex vA = triangle.getA();
        Vertex vB = triangle.getB();
        Vertex vC = triangle.getC();


        for (int y = (int) a.getY(); y < b.getY(); y++) {

            if (y <= zbf.getiBuffer().getHeight()) {

                double s1 = (y - a.getY()) / (b.getY() - a.getY());
                Vec3D ab = a.mul(1 - s1).add(b.mul(s1));
                Vertex vAB = vA.mul(1 - s1).add(vB.mul(s1));
                double s2 = (y - a.getY()) / (c.getY() - a.getY());
                Vec3D ac = a.mul(1 - s2).add(c.mul(s2));
                Vertex vAC = vA.mul(1 - s2).add(vC.mul(s2));

                for (int x = (int) ab.getX(); x < (int) ac.getX(); x++) {
                    if (x <= zbf.getiBuffer().getWidth()) {
                        //interpolace Z a color
                        //double t = ...
                        //Vec3D abc = ab.mul(1-t).add(ac.mul(t));
                        //double z = abc.getZ();
                        double t = (x - ab.getX()) / (ac.getX() - ab.getX());
                        Vertex vABC = vAB.mul(1 - t).add(vAC.mul(t));

                        zbf.drawElementWithZtest(x, y, 0.5, shader.shade(vABC));
                    }
                }
            }
        }

        // Obvod trojuhelníku

        Graphics g = zbf.getiBuffer().getImg().getGraphics();
        g.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
        g.drawLine((int) a.getX(), (int) a.getY(), (int) c.getX(), (int) c.getY());
        g.drawLine((int) c.getX(), (int) c.getY(), (int) b.getX(), (int) b.getY());
        //todo
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }
}

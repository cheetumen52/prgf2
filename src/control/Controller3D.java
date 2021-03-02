package control;

import geometryObjects.ArrowX;
import model.Solid;
import model.Texture;
import model.Vertex;
import raster.ImageBuffer;
import raster.ZbufferVisibility;
import render.RasterizerTriangle;
import render.Renderer;
import render.Shader;
import render.Triangle;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;
import view.Panel;

import java.awt.*;
import java.awt.event.*;

public class Controller3D implements Controller {

    private final Panel panel;

    private int width, height;
    private boolean pressed = false;
    private int ox, oy;
    private ZbufferVisibility zbf;
    private RasterizerTriangle rt;
    private Renderer render;

    boolean modeCleared = false;

    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearValue(new Col(0x101010));
        zbf = new ZbufferVisibility(raster);
        rt = new RasterizerTriangle(zbf);
        render = new Renderer(rt);

        Shader shader = v -> Texture.getTexel(v.getTexCoord().getX(), v.getTexCoord().getY());
        //Shader shader = (Triangle t, Vertex v) -> t.getColor();
        /*Shader shader = (Vertex a,Vertex b, Vertex c,Vertex v) ->
        {
            Vec3D normal = v.getNormal().normalized().get();
            Vec3D light = new Vec3D(1,2,5).normalized().get();
            double cosA = normal.dot(light);
            return new Col(cosA,cosA,1.);
        };  //diffuzní složka osvětlení*/
        rt.setShader(shader);
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent ev) {
                if (ev.getButton() == MouseEvent.BUTTON1) {
                    pressed = true;
                    ox = ev.getX();
                    oy = ev.getY();
                    panel.getRaster().setElement(ox, oy, new Col(0xff0000));
                    redraw();
                }
            }

            public void mouseReleased(MouseEvent ev) {
                if (ev.getButton() == MouseEvent.BUTTON1) {
                    panel.getRaster().setElement(ox, oy, new Col(0xffff));
                    pressed = false;
                    redraw();
                }
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent ev) {
                if (pressed) {
                    ox = ev.getX();
                    oy = ev.getY();
                    panel.getRaster().setElement(ox, oy, new Col(0xffff00));
                    redraw();
                }
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                // out.print(key.getKeyCode());
                switch (key.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE:
                        hardClear();
                        break;
                    case KeyEvent.VK_M:
                        modeCleared = !modeCleared;
                        break;
                }
                redraw();
            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
                redraw();
            }
        });
    }

    private void redraw() {
        if (modeCleared)
            panel.clear();
        width = panel.getRaster().getWidth();
        height = panel.getRaster().getHeight();
        Graphics g = panel.getRaster().getGraphics();
        g.setColor(Color.white);
        g.drawLine(0, 0, width, height);
        panel.getRaster().getGraphics().drawLine(0, 0, ox, oy);

        zbf.drawElementWithZtest(10, 100, 0.5, new Col(0xffff00));
        zbf.drawElementWithZtest(10, 100, 0.7, new Col(0xff0000));


        Solid a = new ArrowX();
        render.render(a);
        rt.rasterize(new Triangle(new Vertex(new Point3D(1, 1, 0), new Col(1., 0., 0.), new Vec2D(0, 0)),
                new Vertex(new Point3D(-1, 0, 0), new Col(0., 1.0, 0.), new Vec2D(0, 1)),
                new Vertex(new Point3D(0, -1, 0), new Col(0., 0., 1.), new Vec2D(1, 0))));


        g.drawString("mode (cleared every redraw): " + modeCleared, 10, 10);
        g.drawString("(c) UHK FIM PGRF", width - 150, height - 10);
        panel.repaint();
    }

    private void hardClear() {
        panel.clear();
        initObjects(panel.getRaster());
    }

}

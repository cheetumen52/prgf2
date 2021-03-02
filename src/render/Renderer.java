package render;

import model.Part;
import model.Solid;
import model.Vertex;

import java.util.List;

public class Renderer {
    private RasterizerTriangle rasterizerTriangle;
    public Renderer(RasterizerTriangle rasterizerTriangle) {
        this.rasterizerTriangle = rasterizerTriangle;
    }

    public void render(Solid solid) {
        //transformace - todo
        for (Part part : solid.getPart()) {
            switch (part.getType()) {
                case TRIANGLES:
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexA = part.getStart() + i * 3;
                        int indexB = part.getStart() + i * 3 + 1;
                        int indexC = part.getStart() + i * 3 + 2;

                        Vertex a = solid.getVertex().get(solid.getIndex().get(indexA));
                        Vertex b = solid.getVertex().get(solid.getIndex().get(indexB));
                        Vertex c = solid.getVertex().get(solid.getIndex().get(indexC));

                        clipTriangle(new Triangle(a, b, c));
                    }
                    break;
                case LINES:

                    break;
                case CIRCLES:
                    break;
            }
        }
    }

    public void render(List<Solid> scene) {
        for (Solid s : scene) {
            render(s);
        }
    }

    /*
     * input: triangle, vertices - coords before dehomo
     * sort a,b,c by z
     * clip by z=0
     * dehomog
     * call rasterizerTriangle.rasterize()
     * */
    void clipTriangle(Triangle triangle) {
        /*
        Vertex a = triangle.getA();
        Vertex b = triangle.getB();
        Vertex c = triangle.getC();


        Vertex temp;
        if (a.getPosition().getZ() > c.getPosition().getZ()) {
            temp = a;
            a = c;
            c = temp;
        }
        if (a.getPosition().getZ() > b.getPosition().getZ()) {
            temp = a;
            a = b;
            b = temp;
        }
        //staci zkontrolovat ktery je nejvetsi
        if (b.getPosition().getZ() > c.getPosition().getZ()) {
            temp = b;
            b = c;
            c = temp;
        }

        //1.condition
        if (a.getPosition().getZ() <= 0) {
            return;
        }
        if (b.getPosition().getZ() <= 0) {
            //calculate intersection D and E
            //dehomog(); // A,D,E
            //rasterize(); //ADE
        }
        //condition 3-4 todo

        */
        rasterizerTriangle.rasterize(triangle);


    }
}

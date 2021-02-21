package render;

import model.Vertex;

public class Renderer {

    /*
     * input: triangle, vertices - coords before dehomo
     * sort a,b,c by z
     * clip by z=0
     * dehomog
     * call rasterizerTriangle.rasterize()
     * */
    void clipTriangle(Triangle triangle) {
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
    }
}

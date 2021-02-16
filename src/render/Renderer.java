package render;

import transforms.Point3D;

public class Renderer {

    /*
     * input: triangle, vertices - coords before dehomo
     * sort a,b,c by z
     * clip by z=0
     * dehomog
     * call rasterizerTrianhle.rasterize()
     * */
    void clipTriangle(Triangle triangle) {
        Point3D a = triangle.getA();
        Point3D b = triangle.getB();
        Point3D c = triangle.getC();

        //sort by Z -> a.z > b.z > c.z


        //1.condition
        if (a.getZ() <= 0) {
            return;
        }
        if (b.getZ() <= 0) {
            //calculate intersection D and E
            //dehomog(); // A,D,E
            //rasterize(); //ADE
        }
        //condition 3-4 todo
    }
}

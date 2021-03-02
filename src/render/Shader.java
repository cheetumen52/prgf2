package render;

import model.Vertex;
import transforms.Col;

@FunctionalInterface
public interface Shader {
    //Col shade(Vertex v);
    Col shade(Vertex v);
    //Col shade(Vertex a,Vertex b,Vertex c,Vertex v);
    /*default Col shade(Vertex v){
        return v.getColor();
    }*/
}

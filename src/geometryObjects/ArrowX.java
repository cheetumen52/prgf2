package geometryObjects;

import model.Part;
import model.Solid;
import model.TypeTopology;
import model.Vertex;
import transforms.Col;

import java.awt.*;

public class ArrowX extends Solid {
    public ArrowX() {
        super();
        getVertex().add(new Vertex(0, 0, 0, new Col(Color.white.getRGB())));
        getVertex().add(new Vertex(1, 0, 0, new Col(Color.white.getRGB())));
        getVertex().add(new Vertex(0.95, 0.1, 0, new Col(Color.white.getRGB())));
        getVertex().add(new Vertex(0.95, -0.1, 0, new Col(Color.white.getRGB())));
        getVertex().add(new Vertex(0.95, 0, 0.1, new Col(Color.white.getRGB())));

        getIndex().add(0);
        getIndex().add(1);

        getIndex().add(1);
        getIndex().add(2);
        getIndex().add(3);

        getIndex().add(1);
        getIndex().add(2);
        getIndex().add(4);

        getIndex().add(1);
        getIndex().add(4);
        getIndex().add(3);

        getIndex().add(4);
        getIndex().add(2);
        getIndex().add(3);

        getPart().add(new Part(TypeTopology.LINES, 0, 1));
        getPart().add(new Part(TypeTopology.TRIANGLES, 2, 4));
    }
}

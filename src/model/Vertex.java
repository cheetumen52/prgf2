package model;

import transforms.Col;
import transforms.Point3D;

public class Vertex {
    Point3D position;
    Col color;

    public Vertex(int x, int y, int z, Col color) {
        position = new Point3D(x, y, z);
        this.color = color;
    }

    public Vertex(Point3D mul, Col color) {
        position = mul;
        this.color = color;
    }

    public Vertex(Point3D dehomog) {
        position = dehomog;
        this.color = color;
    }

    public Vertex mul(double d) {
        return new Vertex(position.mul(d), color);
    }

    public Vertex add(Vertex v) {
        return new Vertex(position.add(v.getPosition()), color);
    }

    public Vertex dehomog() {
        return new Vertex(new Point3D(position.dehomog().get()), color);
    }

    public Point3D getPosition() {
        return position;
    }
}

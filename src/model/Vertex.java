package model;

import transforms.Col;
import transforms.Point3D;

public class Vertex implements Vectorizable<Vertex> {
    Point3D position;
    Col color;

    public Vertex(double x, double y, double z, Col color) {
        position = new Point3D(x, y, z);
        this.color = color;
    }

    public Vertex(Point3D pos, Col color) {
        position = pos;
        this.color = color;
    }

    public Vertex(double x, double y, double z) {
        position = new Point3D(x, y, z);
    }

    @Override
    public Vertex mul(double d) {
        return new Vertex(position.mul(d), color.mul(d));
    }

    @Override
    public Vertex add(Vertex v) {
        return new Vertex(position.add(v.getPosition()), color.add(v.getColor()));
    }

    public Vertex dehomog() {
        return new Vertex(new Point3D(position.dehomog().get()), color);
    }

    public Col getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Vertex { " + position + " }";
    }

    public Point3D getPosition() {
        return position;
    }
}

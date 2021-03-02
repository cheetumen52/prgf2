package model;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;
import transforms.Vec3D;

public class Vertex implements Vectorizable<Vertex> {
    private Point3D position;
    private Col color;
    private Vec2D texCoord;
    private Vec3D normal;

    public Vertex(double x, double y, double z, Col color) {
        position = new Point3D(x, y, z);
        this.color = color;
        texCoord = new Vec2D(0, 0);
    }

    public Vertex(Point3D pos, Col color) {
        position = pos;
        this.color = color;
        texCoord = new Vec2D(0, 0);
    }

    public Vertex(Point3D pos, Col color, Vec2D texCoord) {
        position = pos;
        this.color = color;
        this.texCoord = texCoord;
    }

    public Vertex(double x, double y, double z) {
        position = new Point3D(x, y, z);
    }

    @Override
    public Vertex mul(double d) {
        return new Vertex(position.mul(d), color.mul(d), texCoord.mul(d));
    }

    @Override
    public Vertex add(Vertex v) {
        return new Vertex(position.add(v.getPosition()), color.add(v.getColor()), texCoord.add(v.getTexCoord()));
    }

    public Vertex dehomog() {
        return this.mul(1 / this.position.getW());
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

    public Vec2D getTexCoord() {
        return texCoord;
    }

    public Vec3D getNormal() {
        return normal;
    }

    public void setNormal(Vec3D normal) {
        this.normal = normal;
    }
}

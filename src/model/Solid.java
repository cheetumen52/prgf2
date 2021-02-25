package model;

import java.util.ArrayList;
import java.util.List;

public class Solid {
    private List<Vertex> vertex;
    private List<Integer> index;
    private List<Part> part;

    public Solid() {
        index = new ArrayList<>();
        vertex = new ArrayList<>();
        part = new ArrayList<>();
    }

    public List<Vertex> getVertex() {
        return vertex;
    }

    public List<Integer> getIndex() {
        return index;
    }

    public List<Part> getPart() {
        return part;
    }
}

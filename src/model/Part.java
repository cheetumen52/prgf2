package model;

public class Part {
    private TypeTopology type;
    private int start;
    private int count;

    public Part(TypeTopology type, int start, int count) {
        this.type = type;
        this.start = start;
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public int getCount() {
        return count;
    }

    public TypeTopology getType() {
        return type;
    }
}

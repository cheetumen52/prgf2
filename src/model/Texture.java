package model;

import transforms.Col;

public class Texture {
    public static Col getTexel(double x, double y) { //x,y in <0,1>
        int xI = (int) (x * 20);
        int yI = (int) (y * 20);
        if ((xI + yI) % 2 == 0)
            return new Col(0., 1., 1);
        return new Col(1., 1., 0);
    }
}

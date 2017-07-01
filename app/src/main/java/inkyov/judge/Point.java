package inkyov.judge;

import java.io.Serializable;

/**
 * Created by Inkyov on 11/21/2016.
 */

public class Point implements Serializable{
    String color;
    int point;
    private static final long serialVersionUID = 78931958389436584L;

    Point(String color, int point){
        this.color = color;
        this.point = point;
    }

    public String getColor(){
        return this.color;
    }

    public int getPoint(){
        return this.point;
    }

}

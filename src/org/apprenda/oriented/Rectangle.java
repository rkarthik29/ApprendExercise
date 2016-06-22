package org.apprenda.oriented;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

import org.apprenda.aligned.Coordinate2D;
import org.apprenda.common.Utils;

public class Rectangle extends Polygon {

    
    public Rectangle(Coordinate2D a,Coordinate2D b,Coordinate2D c,Coordinate2D d){
        super();
        ArrayList<Coordinate2D> coordinates= new ArrayList<Coordinate2D>();
        coordinates.add(a);
        coordinates.add(b);
        coordinates.add(c);
        coordinates.add(d);
        this.setCoordinates(coordinates);
    }
    
    public Rectangle(ArrayList<Coordinate2D> coordinates){
        super(coordinates);
    }
    
    public static void main(String[] args){
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 1),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 1),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(3, 2),new Coordinate2D(3, 3)
                        ,new Coordinate2D(4, 2),new Coordinate2D(4,3));
        //Rectangle r2 = new Rectangle(new Coordinate2D(1, 2),new Coordinate2D(2, 3)
        //        ,new Coordinate2D(2, 0),new Coordinate2D(3,1));
        
        if(r1.contains(r2))
            System.out.print("r1 contains r2");
        else
            System.out.print("r1 does not contain r2");
    }
}

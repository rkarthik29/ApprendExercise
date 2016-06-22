package org.apprenda.aligned;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

import org.apprenda.common.Utils;

public class Rectangle {
    
    private Coordinate2D bottomLeft;
    private Coordinate2D topRight;
    private long xlength;
    private long ylength;
    public Rectangle (Coordinate2D a, long xlength,long ylength){
        this.xlength=Math.abs(xlength);
        this.ylength=Math.abs(ylength);
        long topx=a.getX();
        long topy=a.getY();
        long bottomx=a.getX()+xlength;
        long bottomy=a.getY()+ylength;
        if(xlength < 0 && ylength >0){
            topx=a.getX()+xlength;
            bottomx=a.getX();
        }else if(xlength >0 && ylength<0)   {
            topy=a.getY()+ylength;
            bottomy=a.getY();
        }else if(xlength<0 && ylength<0){
            topx=a.getX()+xlength;
            bottomx=a.getX();
            topy=a.getY()+ylength;
            bottomy=a.getY();
        }
        this.bottomLeft=new Coordinate2D(topx, topy);
        this.topRight=new Coordinate2D(bottomx,bottomy);
        
        System.out.println(this.bottomLeft);
        System.out.println(this.topRight);
    }
    
    public boolean isContaining(Rectangle rect){
        if(rect!=null){
            return (this.bottomLeft.getX() <= rect.bottomLeft.getX() && 
                this.bottomLeft.getY() <= rect.bottomLeft.getY() &&
                this.topRight.getX()>= rect.topRight.getX()&&
                this.topRight.getY()>= rect.topRight.getY());
        }else{
            return false;
        }
    }
    
    public boolean isContainedin(Rectangle rect){
        if(rect!=null){
            return rect.isContaining(this);
        }else{
            return false;
        }
    }
    
    public boolean isAdjoining(Rectangle rect){
        if(rect==null){
            return false;
        }else{
            return (this.bottomLeft.getX()==rect.topRight.getX() ||
                    this.bottomLeft.getY()==rect.topRight.getY() ||
                    this.topRight.getX()==rect.bottomLeft.getX() ||
                    this.topRight.getY()==rect.bottomLeft.getY());
        }
    }
    
    public boolean isInterSecting(Rectangle rect){
       return false;
    }
    
    public boolean coversCoordinate(Coordinate2D p){
        if(p==null){
            return false;
        }else{
            return (this.bottomLeft.getX()<p.getX() && this.topRight.getX()>p.getX()
                    && this.bottomLeft.getY()<p.getY() && this.topRight.getY()>p.getY());
        }
    }
    
    public static void main(String[] args){
        /*Rectangle r1 = new Rectangle(new Coordinate2D(7, 7),-5,-6);
        Rectangle r2 = new Rectangle(new Coordinate2D(1,1),1,1);
        Coordinate2D c1 = new Coordinate2D(4, 6);
        if(r1.isAdjoining(r2))
            System.out.println("r1 is adjacent to r2");
        else {
            if(r1.isContaining(r2))
                System.out.println("r1 contains r2");
        }*/
        
        ArrayList<Coordinate2D> points2d= new ArrayList<Coordinate2D>();

        points2d.add(new Coordinate2D(2, 1));
        points2d.add(new Coordinate2D(2, 5));
        points2d.add(new Coordinate2D(5, 1));
        points2d.add(new Coordinate2D(5, 5));
        points2d.add(new Coordinate2D(3, 2));
        points2d.add(new Coordinate2D(3, 3));
        points2d.add(new Coordinate2D(4, 2));
        points2d.add(new Coordinate2D(4, 3));

        //points2d.add(new Coordinate2D(2, 3));
        points2d.sort(new Comparator<Coordinate2D>() {

            @Override
            public int compare(Coordinate2D arg0, Coordinate2D arg1) {
                if(arg0.getY()==arg1.getY()){
                    return (int)(arg1.getX()-arg0.getX());
                }else{
                    return (int)(arg0.getY()-arg1.getY());
                }
            }

        });
        Coordinate2D p = points2d.remove(0);
       // System.out.println(p);
        //points2d.remove(0);
        points2d.sort(new Comparator<Coordinate2D>() {

            @Override
            public int compare(Coordinate2D arg0, Coordinate2D arg1) {
                double area1=Utils.polarAngle(p, arg0);
                double area2=Utils.polarAngle(p, arg1);
                if(area1==area2)
                    return 0;
                else if(area1>area2)
                    return 1;
                else
                    return -1;
                       
                            
            }

        });
        
        /*for(Coordinate2D point2d:points2d){
            System.out.println(point2d);
        }*/

       Stack<Coordinate2D> hull = new Stack<Coordinate2D>();
       
       hull.push(p);
       hull.push(points2d.get(0));
       for(int i=1;i<points2d.size();i++){
           Coordinate2D last = hull.pop();
           while (Utils.getArea(hull.peek(), last, points2d.get(i))<=0) {
               //System.out.println(hull.peek()+":"+last+":"+points2d.get(i)+":"+Utils.getArea(hull.peek(), last, points2d.get(i)));
               last = hull.pop();
           }
           hull.push(last);
           hull.push(points2d.get(i));
       }
       
       while(!hull.isEmpty()){
           System.out.println(hull.pop());
       }
    }

}

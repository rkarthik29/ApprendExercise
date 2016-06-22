package org.apprenda.common;

public class Coordinate2D implements Comparable<Coordinate2D>,Cloneable{
    private double x;
    private double y;
    
    public Coordinate2D(double x, double y){
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String toString(){
        return "("+this.x+","+this.y+")";
    }

    @Override
    public int compareTo(Coordinate2D o) {
        // TODO Auto-generated method stub
        if(this.y==o.y){
            return (int)(o.x-this.x);
        }else{
            return (int)(this.y-o.y);
        }
    }
    @Override
    public boolean equals(Object b){
        if(b==null)
            return false;
        if(!Coordinate2D.class.isAssignableFrom(b.getClass()))
            return false;
        Coordinate2D point=(Coordinate2D)b;
        return this.x==point.x && this.y==point.y;
    }
    @Override
    public int hashCode(){
        return 1;
    }
}

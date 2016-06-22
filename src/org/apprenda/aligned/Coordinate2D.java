package org.apprenda.aligned;

public class Coordinate2D implements Comparable<Coordinate2D>,Cloneable{
    private long x;
    private long y;
    
    public Coordinate2D(long x, long y){
        this.x=x;
        this.y=y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public String toString(){
        return "("+this.x+","+this.y+")";
    }

    @Override
    public int compareTo(Coordinate2D o) {
        // TODO Auto-generated method stub
        if(this.y==o.y){
            return (int)(this.x-o.x);
        }else{
            return (int)(this.y-o.y);
        }
    }
    
    public boolean equals(Coordinate2D b){
        if(b==null)
            return false;
        return this.x==b.x && this.y==b.y;
    }
    @Override
    public int hashCode(){
        return 1;
    }
}

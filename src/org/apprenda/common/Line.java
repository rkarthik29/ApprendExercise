package org.apprenda.common;

public class Line {
    
    private Coordinate2D start;
    private Coordinate2D end;
    
    public Line(Coordinate2D start,Coordinate2D end){
        if(start.compareTo(end) >-1){
        this.start=start;
        this.end=end;
        }else{
            this.end=start;
            this.start=end;
        }
    }
    
    public boolean intersects(Line l){
        if(l==null)
            return false;
        return Utils.isCCW(this.start,l.start,l.end) && Utils.isCCW(this.end,l.start,l.end) 
                && Utils.isCCW(this.start,this.end,l.start) != Utils.isCCW(this.start,this.end,l.end);
    }
    
    public boolean colinear(Line l){
        if(l==null)
            return false;
        
        double area=Utils.getArea(l.start,this.start,this.end);
        double area2=Utils.getArea(l.end,this.start,this.end);
        return area==0.0 && area2==0.0;
    }
    
    public int hashCode(){
        return 1;
    }
    
    public String toString(){
        String pStr="[";
        pStr+=start.toString();
        pStr+=end.toString();
        return pStr+"]";
    }
    
    public boolean equals(Line l){
        if(l==null)
            return false;
        return (this.start.equals(l.start) && this.end.equals(l.end));
    }

    public static void main(String[] args){
        Line l1 = new Line(new Coordinate2D(4, 5),new Coordinate2D(5, 8));
        Line l2 = new Line(new Coordinate2D(5, 5),new Coordinate2D(8, 2));
        System.out.println(l1.intersects(l2));
        System.out.println(l1);
        System.out.println(l2);
        
    }
}

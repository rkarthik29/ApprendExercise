package org.apprenda.test;

import java.util.ArrayList;

import org.apprenda.common.Coordinate2D;
import org.apprenda.oriented.Polygon;
import org.apprenda.oriented.Rectangle;
import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {
   
    @Test
    public void testContainment(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(3,4),new Coordinate2D(4,4)
                        ,new Coordinate2D(3, 3),new Coordinate2D(4,3));
        Assert.assertTrue(r1.contains(r2));
        
    }
    
    @Test
    public void testContainmentfailureIfpolygonsareequal(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(2,2),new Coordinate2D(2,5)
                        ,new Coordinate2D(5,2),new Coordinate2D(5,5));
        Assert.assertFalse(r1.contains(r2));
        
    }
    @Test
    public void testContainmentPentagonwithrectangle(){
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 7)
                ,new Coordinate2D(7, 2),new Coordinate2D(7, 7));
        ArrayList< Coordinate2D> coordinates = new ArrayList<Coordinate2D>();
        coordinates.add(new Coordinate2D(3,3));
        coordinates.add(new Coordinate2D(3,4));
        coordinates.add(new Coordinate2D(4,5));
        coordinates.add(new Coordinate2D(5,4));
        coordinates.add(new Coordinate2D(5,3));
        Polygon p2 = new Polygon(coordinates);
        Assert.assertTrue(r1.contains(p2));
        
    }
    @Test
    public void testsingleedgeIntersection(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(3,4),new Coordinate2D(6,4)
                        ,new Coordinate2D(3, 3),new Coordinate2D(6,3));
        Assert.assertTrue(r1.isIntesecting(r2));
    }
    
    @Test
    public void testcornerIntersection(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(3,4),new Coordinate2D(6,4)
                        ,new Coordinate2D(3, 1),new Coordinate2D(6,1));
        ArrayList<Coordinate2D> intersects = r1.findIntersects(r2);
        Assert.assertTrue(intersects.contains(new Coordinate2D(5, 4)));
        Assert.assertTrue(intersects.contains(new Coordinate2D(3, 2)));
        Assert.assertTrue(r1.isIntesecting(r2));
    }
    
    @Test
    public void testparralleedgeIntersection(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(1,4),new Coordinate2D(6,4)
                        ,new Coordinate2D(1, 1),new Coordinate2D(6,1));
        ArrayList<Coordinate2D> intersects = r1.findIntersects(r2);
        Assert.assertTrue(intersects.contains(new Coordinate2D(2, 4)));
        Assert.assertTrue(intersects.contains(new Coordinate2D(5, 4)));
        Assert.assertTrue(r1.isIntesecting(r2));
    }
    
    @Test
    public void testedgeIntersectionwithpentagon(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        ArrayList< Coordinate2D> coordinates = new ArrayList<Coordinate2D>();
        coordinates.add(new Coordinate2D(3,3));
        coordinates.add(new Coordinate2D(3,4));
        coordinates.add(new Coordinate2D(5,4));
        coordinates.add(new Coordinate2D(6,4));
        coordinates.add(new Coordinate2D(6,3));
        Polygon p2 = new Polygon(coordinates);
        ArrayList<Coordinate2D> intersects = r1.findIntersects(p2);
        Assert.assertTrue(intersects.contains(new Coordinate2D(5, 4)));
        Assert.assertTrue(intersects.contains(new Coordinate2D(5, 3)));
        Assert.assertTrue(r1.isIntesecting(p2));
    }
    @Test
    public void testadjacencypartialedgenohang(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(5,4),new Coordinate2D(6,4)
                        ,new Coordinate2D(5, 3),new Coordinate2D(6,3));
        Assert.assertTrue(r1.isAdjacent(r2));
    }
    @Test
    public void testadjacencypartialedgehanging(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(5,4),new Coordinate2D(6,4)
                        ,new Coordinate2D(5, 0),new Coordinate2D(6,0));
        Assert.assertTrue(r1.isAdjacent(r2));
    }
    @Test
    public void testadjacencyfullledge(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(5,2),new Coordinate2D(6,2)
                        ,new Coordinate2D(5, 5),new Coordinate2D(6,5));
        Assert.assertTrue(r1.isAdjacent(r2));
    }
    
    @Test
    public void testadjacencytopedge(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(2, 2),new Coordinate2D(2, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(2,5),new Coordinate2D(5,5)
                        ,new Coordinate2D(2, 10),new Coordinate2D(5,10));
        Assert.assertTrue(r1.isAdjacent(r2));
    }
    
    @Test
    public void testadjacencytopedgehang(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(3, 2),new Coordinate2D(3, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(1,5),new Coordinate2D(6,5)
                        ,new Coordinate2D(1, 10),new Coordinate2D(6,10));
        Assert.assertTrue(r1.isAdjacent(r2));
    }
    
    @Test
    public void testadjacencytopedgepentagon(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(3, 2),new Coordinate2D(3, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        ArrayList< Coordinate2D> coordinates = new ArrayList<Coordinate2D>();
        coordinates.add(new Coordinate2D(3,5));
        coordinates.add(new Coordinate2D(5,5));
        coordinates.add(new Coordinate2D(3,6));
        coordinates.add(new Coordinate2D(5,6));
        coordinates.add(new Coordinate2D(4,7));
        Polygon p2 = new Polygon(coordinates);
        Assert.assertTrue(r1.isAdjacent(p2));
    }
    
    @Test
    public void testseperationpentagon(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(3, 2),new Coordinate2D(3, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        ArrayList< Coordinate2D> coordinates = new ArrayList<Coordinate2D>();
        coordinates.add(new Coordinate2D(3,6));
        coordinates.add(new Coordinate2D(5,6));
        coordinates.add(new Coordinate2D(3,7));
        coordinates.add(new Coordinate2D(5,7));
        coordinates.add(new Coordinate2D(4,8));
        Polygon p2 = new Polygon(coordinates);
        Assert.assertFalse(r1.isAdjacent(p2));
        Assert.assertFalse(r1.isIntesecting(p2));
        Assert.assertFalse(r1.contains(p2));
        Assert.assertFalse(p2.contains(r1));
    }
    
    @Test
    public void testseperation(){
        
        Rectangle r1 = new Rectangle(new Coordinate2D(3, 2),new Coordinate2D(3, 5)
                ,new Coordinate2D(5, 2),new Coordinate2D(5, 5));
        Rectangle r2 = new Rectangle(new Coordinate2D(1,6),new Coordinate2D(6,6)
                        ,new Coordinate2D(1, 10),new Coordinate2D(6,10));
        Assert.assertFalse(r1.isAdjacent(r2));
        Assert.assertFalse(r1.isIntesecting(r2));
        Assert.assertFalse(r1.contains(r2));
        Assert.assertFalse(r2.contains(r1));
    }
    @Test
    public void testhelpermethodrectangle(){
        Rectangle r1 = new Rectangle(new Coordinate2D(3, 3),2,2);
        Rectangle r2 = new Rectangle(new Coordinate2D(4, 4),3,3);
        Assert.assertFalse(r1.isAdjacent(r2));
        Assert.assertTrue(r1.isIntesecting(r2));
        Assert.assertTrue(r2.isIntesecting(r1));
        Assert.assertFalse(r1.contains(r2));
        Assert.assertFalse(r2.contains(r1));
    }
    @Test
    public void testPentagoncontainrectangle(){
        Rectangle r1 = new Rectangle(new Coordinate2D(4, 2),new Coordinate2D(4, 7)
                ,new Coordinate2D(7, 2),new Coordinate2D(7, 7));
        ArrayList< Coordinate2D> coordinates = new ArrayList<Coordinate2D>();
        coordinates.add(new Coordinate2D(0,0));
        coordinates.add(new Coordinate2D(0,8));
        coordinates.add(new Coordinate2D(5,10));
        coordinates.add(new Coordinate2D(10,8));
        coordinates.add(new Coordinate2D(10,0));
        Polygon p2 = new Polygon(coordinates);
        Assert.assertTrue(p2.contains(r1));
    }
}

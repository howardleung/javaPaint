import java.awt.Color;

/*
 * Name: Howard Leung
 * Date: 2018/05/02
 * Description: This is a class that has the basics of a shape
 */

abstract class FillableShape extends Shape{

    protected boolean fill;


    //Constructor: sets data
    public FillableShape(int x1,int y1,int x2,int y2,Color c, boolean fill){
        super(x1,y1,x2,y2,c);
        setFill(fill);

    }
    
    //Accessors
    public int getx1() {
        return x1;
    }
    
    public int getx2() {
        return x2;
    }
    
    public int gety1() {
        return y1;
    }
    
    public int gety2() {
        return y2;
    }
    
    public boolean getfill() {
        return fill;
    }
    
    public void setFill(boolean newValue) {
        fill = newValue;
    }
    
    public int getUpperLeftX(){
        if (x1<x2)
            return x1;
        return x2;
    }
    
    public int getUpperLeftY(){
        if (y1<y2)
            return y1;
        return y2;
    }
    
    public int getWidth(){
        return Math.abs(x1-x2);
    }
    
    public int getHeight(){
        return Math.abs(y1-y2);
    }
    

}
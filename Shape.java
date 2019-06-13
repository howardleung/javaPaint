import java.awt.Color;
import java.awt.Graphics;

/*
 * Name: Howard Leung
 * Date: 2018/05/02
 * Description: This is a class that defines the basics of a shape
 */

abstract class Shape{
    
    protected int x1;
    protected int x2;
    protected int y1;
    protected int y2;
    protected Color color;
    

    
    public Shape(int x1,int y1,int x2,int y2,Color c){
        setx1(x1);
        setx2(x2);
        sety1(y1);
        sety2(y2);
        setColor(c);
        
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
    
    public Color getColor() {
        return color;
    }
    
    public void setx1(int newValue) {
        if (newValue < 0){
            x1 = 0;
        }
        else
            x1 = newValue;
    }
    
    public void setx2(int newValue) {
        if (newValue < 0){
            x2 = 0;
        }
        else
            x2 = newValue;
        
    }
    
    public void sety1(int newValue) {
        if (newValue < 0){
            y1 = 0;
        }
        else
            y1 = newValue;
    }
    
    public void sety2(int newValue) {
        if (newValue < 0){
            y2 = 0;
        }
        else
            y2 = newValue;
    }
    
    public void setColor(Color c) {
        color = c;
    }
    
    abstract void draw(Graphics g);
}
import java.awt.Color;
import java.awt.Graphics;

/*
 * Name: Howard Leung
 * Date: 2018/05/02
 * Description: This class extends the shape class
 */
public class Line extends Shape{
        
    // Constructor: Set data 
    public Line(int x1,int y1,int x2,int y2,Color c){
        super(x1,y1,x2,y2,c);
    }
        
    // draw: draws the shape
    public void draw(Graphics g){
        g.setColor( getColor() );
        g.drawLine( getx1(), gety1(), getx2(), gety2() );  
    }
}
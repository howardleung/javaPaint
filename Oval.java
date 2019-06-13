import java.awt.Color;
import java.awt.Graphics;

/*
 * Name: Howard Leung
 * Date: 2018/05/02
 * Description: This is an Oval shape that extends FillabeShape
 */

public class Oval extends FillableShape{
    // Constructor: Set data 
    public Oval(int x1,int y1,int x2,int y2,Color c, boolean fill){
        super(x1,y1,x2,y2,c, fill);
    }
    // draw: draws the shape
    public void draw(Graphics g){
        g.setColor( getColor() );
        if (getfill())
            g.fillOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() );
        else
            g.drawOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() );  
    }
}
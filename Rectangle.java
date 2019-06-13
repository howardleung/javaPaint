import java.awt.Color;
import java.awt.Graphics;

/*
 * Name: Howard Leung
 * Date: 2018/05/02
 * Description: This is an Shape shape that extends FillabeShape
 */
public class Rectangle extends FillableShape{
    // Constructor: Set data 
    public Rectangle(int x1,int y1,int x2,int y2,Color c, boolean fill){
        super(x1,y1,x2,y2,c, fill);
    }
    // draw: draws the shape
    public void draw(Graphics g){
        //Color faceColor = new Color( r.nextInt(255),  r.nextInt(255),  r.nextInt(255) );
        g.setColor( getColor() );
        if (getfill())
            g.fillRect( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() );
        else
            g.drawRect( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }
}
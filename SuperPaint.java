import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/*
 * Name: Howard Leung
 * Date: 2018/05/02
 * Description: This is a Superpaint program that allows user to draw, undo, redo, clear, change colour, and fill
 */
public class SuperPaint {
    public static void main( String[] args ) {
        DrawFrame application = new DrawFrame();
        
        
        application.setSize( 700, 320 );
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.setVisible( true ); 
    }    
}
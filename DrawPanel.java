import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/*
 * Name: Howard Leung
 * Date: 2018/05/02
 * Description: This class is the canvas/panel that will have shapes drawn onto it
 */

public class DrawPanel extends JPanel {
    private JLabel statusBar; 
    private Shape currentShape = null;
    private DynamicStack<Shape> shapesDrawn = new DynamicStack<Shape>();
    private DynamicStack<Shape> shapesUndo = new DynamicStack<Shape>();
    private Color color;
    private boolean filled;
    private int shapeChosen;
    /**
     * instantiates an array of 10 Random Rectangle objects
     *
     */

    public DrawPanel( JLabel statusLabel,DynamicStack<Shape> shapesDrawn,DynamicStack<Shape> shapesUndo ) {
        this.shapesDrawn = shapesDrawn;
        this.shapesUndo = shapesUndo;
        statusBar = statusLabel;
        setBackground( Color.WHITE ); 
        
        // Create and register listener for mouse and mouse motion events
        MouseEventListener drawPanelListener = new MouseEventListener(); 
        addMouseListener( drawPanelListener ); 
        addMouseMotionListener( drawPanelListener );       
    } 

    // This mutator method accepts a boolean parameter to determine if the the shape is to be filled.
    public void setFilled (boolean filled){
        this.filled = filled;
    }
    
    // This accessor method returns a the color.
    public Color getColor(){
        return color;
    }
    
    // This mutator method accepts a Color as a parameter to determine the color to be used.
    public void setColor (Color colors){
        color = colors;
       
    }
    // This mutator method accepts a Color as a parameter to determine the color to be used.
    public void setBackgroundColor (Color bgcolor){
        setBackground( bgcolor ); 
       
    }
    // This mutator method sets the shape to be drawn
    public void setShape (int choice){
        shapeChosen = choice;
    }
    
    // Inner class to handle mouse events
    class MouseEventListener extends MouseAdapter {
        // Mouse press indicates a new line has been started
        @Override
        public void mousePressed( MouseEvent event ) {
            if (shapeChosen == 0)
                currentShape = new Rectangle( event.getX(), event.getY(), event.getX(), event.getY(), color,filled );
            else if (shapeChosen == 1)
                currentShape = new Line( event.getX(), event.getY(), event.getX(), event.getY(), color);
            else if (shapeChosen == 2)
                currentShape = new Oval( event.getX(), event.getY(), event.getX(), event.getY(), color,filled );
            // Tell JVM to call paintComponent( g )
            repaint();
        } 
        
        // Mouse release indicates the new line is finished
        @Override
        public void mouseReleased( MouseEvent event ) {
            // Update ending coordinates and switch color to BLACK
            currentShape.setx2( event.getX() );
            currentShape.sety2( event.getY() );
            shapesDrawn.push(currentShape);
            
            // Get ready for the next line to be drawn
            currentShape = null;
            shapesUndo.clear();
            repaint();            
        } 
        
        // As mouse is dragged, update ending coordinates of currentShape and statusBar
        @Override
        public void mouseDragged( MouseEvent event ) {
            currentShape.setx2( event.getX() );
            currentShape.sety2( event.getY() );
            statusBar.setText( String.format( "Mouse at (%d, %d)", 
                                             event.getX(), event.getY() ) );
            repaint();
        } 
        
        // As mouse is moved, just update the statusBar
        @Override
        public void mouseMoved( MouseEvent event ) {
            statusBar.setText( String.format( "Mouse at (%d, %d)", 
                                             event.getX(), event.getY() ) );
            
        } 
    } 
    
    // This method is called automatically by the JVM when the window needs to be (re)drawn.
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        // Call the draw() method for each Rectangle object in the array
        for ( int i = shapesDrawn.size()-1; i >= 0 ; i-- ) 
            (shapesDrawn.index(i)).draw( g );
        
        // If a line is in progress, draw it on top of all others
        if ( currentShape != null )
            currentShape.draw( g );
    } 
} 
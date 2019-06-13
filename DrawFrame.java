import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JColorChooser;


/*
 * Name: Howard Leung
 * Date: 2018/05/02
 * Description: This class is the frame which holds the button bar and a panel for drawing, and shows the
 *              mouse coordinates at the bottom.
 */

public class DrawFrame extends JFrame {
    private JPanel buttonBar = new JPanel();
    private JButton undoButton;
    private JButton redoButton;    
    private JButton clearButton;
    private JButton colorButton;
    private JButton bgcolorButton;
    private JComboBox<String> shapeChooser;
    
    private String[] shapeOptions = { "Rectangle", "Line", "Oval"};
    
    private DynamicStack<Shape> shapesDrawn = new DynamicStack<Shape>();
    private DynamicStack<Shape> shapesUndo = new DynamicStack<Shape>();
    
    private JCheckBox fillBox;
    private DrawPanel canvas;
    /**
     * Set data and create all the buttons and panels required for the program
     *
     */
    public DrawFrame() {
        super("Super Paint");
        
        JLabel statusLabel = new JLabel();
        add( statusLabel, BorderLayout.SOUTH );
        
        
        // Put DrawPanel in CENTER and pass reference to statusLabel for updates
        canvas = new DrawPanel( statusLabel,shapesDrawn,shapesUndo);
        add( canvas, BorderLayout.CENTER );   
        
        MouseEventListener drawPanelListener = new MouseEventListener(); 
        canvas.addMouseListener( drawPanelListener ); 
        canvas.addMouseMotionListener( drawPanelListener );   
        // Create a ButtonEventListener object (to be shared by all 3 buttons)
        ActionListener buttonListener = new ButtonEventListener();
        
        // Create JButtons and register our event listener for each

        // Add button to each region of the BorderLayout
        undoButton = new JButton("Undo");
        undoButton.setEnabled(false);
        undoButton.addActionListener( buttonListener );
        buttonBar.add( undoButton ); 
        
        redoButton = new JButton("Redo"); 
        redoButton.setEnabled(false);
        redoButton.addActionListener( buttonListener );
        buttonBar.add( redoButton ); 
        
        clearButton = new JButton("Clear");
        clearButton.addActionListener( buttonListener );
        buttonBar.add( clearButton); 
        
        
        bgcolorButton = new JButton ("Background");
        bgcolorButton.addActionListener( buttonListener );        
        buttonBar.add( bgcolorButton); 
        
        colorButton = new JButton ("Color");
        colorButton.addActionListener( buttonListener );        
        buttonBar.add( colorButton); 
        
        
        shapeChooser = new JComboBox<String>( shapeOptions );
        shapeChooser.setMaximumRowCount( 3 );
        ItemListener comboBoxListener = new ItemListener() {
            @Override 
            public void itemStateChanged( ItemEvent e ) {
                // Change the JFrame background based on the colour selected
                canvas.setShape(  shapeChooser.getSelectedIndex() );
            }         
        };
        // And associate it with the JComboBox
        shapeChooser.addItemListener( comboBoxListener );
        buttonBar.add( shapeChooser );     
        
        fillBox = new JCheckBox ("Fill");
        // Create a CheckBoxEventListener object
        ItemListener fillBoxListener = new CheckBoxEventListener();
        // And associate it with the JCheckBox
        fillBox.addItemListener( fillBoxListener );
        buttonBar.add( fillBox);
        
        add(buttonBar, BorderLayout.NORTH);

    }
    
    
    /**
     * tracks changes made to checkboxes
     * 
     */
     
    class CheckBoxEventListener implements ItemListener {
        // We override the itemStateChanged() method as required by the ActionListener Interface
        @Override 
        public void itemStateChanged( ItemEvent e ) {
            // When the user clicks the lockBox we enable or disable the userNameField
            if ( fillBox.isSelected() ) {
                canvas.setFilled(true);
            } 
            else {
                canvas.setFilled(false);
            }
        }         
    }
   /**
     * tracks changes made to buttons
     * 
     */
     
    class ButtonEventListener implements ActionListener {
        // We override the actionPerformed() method as required by the ActionListener Interface
        @Override 
        public void actionPerformed( ActionEvent e ) {
            if ( e.getSource() == undoButton ){
                redoButton.setEnabled(true);
                if (!shapesDrawn.isEmpty()){
                shapesUndo.push(shapesDrawn.pop()); 
                undoButton.setEnabled(!shapesDrawn.isEmpty());
                redoButton.setEnabled(!shapesUndo.isEmpty());
                canvas.repaint();
                }
            }

            else if ( e.getSource() == redoButton ){
                if (!shapesUndo.isEmpty()){
                shapesDrawn.push(shapesUndo.pop()); 
                canvas.repaint();
                }
                redoButton.setEnabled(!shapesUndo.isEmpty());
                undoButton.setEnabled(!shapesDrawn.isEmpty());
            }
            else if ( e.getSource() == colorButton )
                canvas.setColor(JColorChooser.showDialog( null, "Select a color", canvas.getColor() ));
            
            else if ( e.getSource() == bgcolorButton )
                canvas.setBackgroundColor(JColorChooser.showDialog( null, "Select a color", canvas.getColor() ));
            
            else if ( e.getSource() == clearButton ){
                shapesDrawn.clear();
                shapesUndo.clear();
                canvas.repaint();
                undoButton.setEnabled(false);
                redoButton.setEnabled(false);
            }
            
                
        }
        }
    
    class MouseEventListener extends MouseAdapter {
         @Override
        public void mouseReleased( MouseEvent event ) {
             undoButton.setEnabled(!shapesDrawn.isEmpty());
             redoButton.setEnabled(!shapesUndo.isEmpty());
             
         }
    }
    
    
            
   
     }
      

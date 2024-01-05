package paintbrushapp;

import javax.swing.JFrame;

/**
 *
 * @author marymnasr
 */
public class PaintBrushApp extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        JFrame myFrame = new JFrame();
        myFrame.setTitle("Paint");
        myFrame.setSize(800, 800);
        myFrame.setLocationRelativeTo(null);
        
        MyPanel myPanel = new MyPanel();
        myPanel.setLayout(null); // removing java gui grid --> to be able to move buttons freely
        
        myFrame.setContentPane(myPanel);
        
        myFrame.setVisible(true);
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
}

    
    


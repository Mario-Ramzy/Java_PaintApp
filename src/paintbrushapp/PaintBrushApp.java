package paintbrushapp;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.*;


/**
 *
 * @author marymnasr
 */
public class PaintBrushApp extends JFrame {

    public static void main(String[] args) {
      
        JFrame myFrame = new JFrame("Java Paint App");
        MainToolBar mainBar = new MainToolBar();
        JPanel p = new JPanel();
        MyPanel myPanel = new MyPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(mainBar);
        myFrame.add(mainBar, BorderLayout.NORTH);
        myFrame.add(myPanel, BorderLayout.CENTER);
        
        
        myFrame.setSize(900, 500);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
}

    
    


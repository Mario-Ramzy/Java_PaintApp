package paintbrushapp;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.min;
import static java.lang.Math.abs;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author marymnasr & Mario Ramzy
 */
public class MyPanel extends JPanel {//implements Runnable{

    int x1,y1,x2,y2;
    Line tempLine;
    Oval tempOval;
    Rectangle tempRect;
    FreeHand tempFShape;
    Color color;
    Stroke stroke;

    private Vector<Shape> history;
    private int historyCounter;

    boolean isColorClicked;
    boolean isLineClicked;
    boolean isRectClicked;
    boolean isOvalClicked;
    boolean isFreeHandClicked;
    boolean isEraserClicked;
    boolean isFilledChecked;

    public MyPanel() {
        this.setBackground(Color.WHITE);

        x1 = y1 = x2 = y2 = 0;

        history = new Vector();

        stroke = new BasicStroke(2f); // default stroke (Solid)

        this.setFocusable(false);   //make the default panel focus is false 

        MouseLis mLisObj = new MouseLis();   // Mouse Listener -> press & release
        this.addMouseListener(mLisObj);

        MouseMotLis mMLisObj = new MouseMotLis();  // Mouse Listener -> Drag 
        this.addMouseMotionListener(mMLisObj);

        JButton redBtn = new JButton();     //Red button

        RedActionList redObj = new RedActionList();
        redBtn.addActionListener(redObj);
        redBtn.setSize(30, 30);
        redBtn.setBackground(Color.RED);
        redBtn.setLocation(50, 25);
        //redBtn.setFocusPainted(true);
        this.add(redBtn);

        JButton greenBtn = new JButton();   // Green button
        GreenActionList greenObj = new GreenActionList();
        greenBtn.addActionListener(greenObj);
        greenBtn.setSize(30, 30);
        greenBtn.setBackground(Color.GREEN);
        greenBtn.setLocation(90, 25);
        this.add(greenBtn);

        JButton blueBtn = new JButton();    //Blue button
        BlueActionList blueObj = new BlueActionList();
        blueBtn.addActionListener(blueObj);
        blueBtn.setSize(30, 30);
        blueBtn.setBackground(Color.BLUE);
        blueBtn.setLocation(130, 25);
        this.add(blueBtn);

        JButton lineBtn = new JButton("Line");  // Draw line button
        LineActionList lineObj = new LineActionList();
        lineBtn.addActionListener(lineObj);
        lineBtn.setSize(120, 30);
        lineBtn.setLocation(180, 25);
        this.add(lineBtn);

        JButton rectBtn = new JButton("Rectangle");   //Draw rectangle button
        RectActionList rectObj = new RectActionList();
        rectBtn.addActionListener(rectObj);
        rectBtn.setSize(120, 30);
        rectBtn.setLocation(310, 25);
        this.add(rectBtn);

        JButton ovaltBtn = new JButton("Oval"); //Draw oval button
        OvalActionList ovalObj = new OvalActionList();
        ovaltBtn.addActionListener(ovalObj);
        ovaltBtn.setSize(120, 30);
        ovaltBtn.setLocation(440, 25);
        this.add(ovaltBtn);

        JCheckBox dottedCB = new JCheckBox("Dotted");    //Dotted Checkbox
        DottedItemList dottedObj = new DottedItemList();
        dottedCB.addItemListener(dottedObj);
        dottedCB.setSize(70, 30);
        dottedCB.setLocation(580, 25);
        this.add(dottedCB);

        JCheckBox filledCB = new JCheckBox("Filled");    //Filled Checkbox
        FilledItemList filledObj = new FilledItemList();
        filledCB.addItemListener(filledObj);
        filledCB.setSize(70, 30);
        filledCB.setLocation(660, 25);
        this.add(filledCB);

        JButton freeBtn = new JButton("Free Hand");  // Free hand button
        FreeHandActionList fbreeObj = new FreeHandActionList();
        freeBtn.addActionListener(fbreeObj);
        freeBtn.setSize(120, 30);
        freeBtn.setLocation(250, 70);
        this.add(freeBtn);

        JButton eraserBtn = new JButton("Eraser");    //Eraser button
        EraserActionList eraserObj = new EraserActionList();
        eraserBtn.addActionListener(eraserObj);
        eraserBtn.setSize(120, 30);
        eraserBtn.setLocation(380, 70);
        this.add(eraserBtn);

        JButton clearBtn = new JButton(" Clear All ");  // Clear All button
        ClearActionList clearObj = new ClearActionList();
        clearBtn.addActionListener(clearObj);
        clearBtn.setSize(120, 30);
        clearBtn.setLocation(510, 70);
        this.add(clearBtn);

        JButton undoBtn = new JButton(" Undo ");  // Undo button
        UndoActionList undoObj = new UndoActionList();
        undoBtn.addActionListener(undoObj);
        undoBtn.setSize(120, 30);
        undoBtn.setLocation(640, 70);
        this.add(undoBtn);
//        new Thread(this).start();
    }

    public class MouseLis implements MouseListener // Inner class to implement MouseListener  (press & release)
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println(isFocusable());  // testing the panel foucus
        }

        @Override
        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            if (isFreeHandClicked){
            tempFShape = new FreeHand(color,stroke);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (MyPanel.this.isFocusable()) {
                if (isLineClicked) {
                    x2 = e.getX();
                    y2 = e.getY();
                    history.add(new Line(x1, y1, x2, y2, color, stroke));
                    historyCounter++;
                    x1 = x2 = y1 = y2 = 0;

                } else if (isRectClicked) {
                    history.add(new Rectangle(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2), color, stroke, isFilledChecked));
                    historyCounter++;
                    x1 = x2 = y1 = y2 = 0;

                } else if (isOvalClicked) {
                    history.add(new Oval(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2), color, stroke, isFilledChecked));
                    historyCounter++;
                    x1 = x2 = y1 = y2 = 0;
                } else if (isFreeHandClicked) {
                    history.add(tempFShape);
                    historyCounter++;
                    System.out.println(historyCounter);
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    public class MouseMotLis implements MouseMotionListener // Inner class to implement MouseMotionListener  (drag)
    {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (MyPanel.this.isFocusable()) {
                if ((isLineClicked || isRectClicked || isOvalClicked)) {
                    x2 = e.getX();
                    y2 = e.getY();
                    updateUI();

                } else if (isFreeHandClicked) {
                    Graphics g = getGraphics();
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(color);
                    g2d.setStroke(stroke);
                    tempLine = new Line(x1, y1, e.getX(), e.getY(), color, stroke);
                    tempFShape.pushLine(tempLine);
                    g.drawLine(tempLine.getxStart(),tempLine.getyStart(),tempLine.getxEnd(),tempLine.getyEnd());
                    x1 = e.getX();
                    y1 = e.getY();
                    
                } else if (isEraserClicked) //Eraser dragging
                {
                    Graphics g = getGraphics();
                    g.setColor(Color.WHITE);
                    g.fillRect(e.getX(), e.getY(), 20, 15);
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

    }

    //*************** SHAPES BUTTONS  *****************\\
    public class LineActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isColorClicked == true) {
                MyPanel.this.setFocusable(true);  //setPanelFocus true after user choose color and click draw a line; 
                isLineClicked = true;
                isRectClicked = false;
                isOvalClicked = false;
                isFreeHandClicked = false;
                isEraserClicked = false;
            } else if (isColorClicked == false) {
                JOptionPane.showMessageDialog(null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class RectActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isColorClicked == true) {
                MyPanel.this.setFocusable(true);  //setPanelFocus true after user choose color and click draw a rect; 
                isRectClicked = true;
                isLineClicked = false;
                isOvalClicked = false;
                isFreeHandClicked = false;
                isEraserClicked = false;
            } else if (isColorClicked == false) {
                JOptionPane.showMessageDialog(null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public class OvalActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (isColorClicked == true) {
                MyPanel.this.setFocusable(true);  //setPanelFocus true after user choose color and click draw an oval; 
                isOvalClicked = true;
                isLineClicked = false;
                isRectClicked = false;
                isFreeHandClicked = false;
                isEraserClicked = false;
            } else if (isColorClicked == false) {
                JOptionPane.showMessageDialog(null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }

        }

    }
    //*************** Other BUTTONS  *****************\\

    public class FreeHandActionList implements ActionListener //check
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(isColorClicked == true && isFreeHandClicked == false)
            {
                MyPanel.this.setFocusable(true);  //setPanelFocus true after user choose color and click free hand; 
                isFreeHandClicked = true;
                isLineClicked = isRectClicked = isOvalClicked = false;
                MyPanel.this.updateUI();
            }
            else if (isColorClicked == false) {
                JOptionPane.showMessageDialog(null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public class EraserActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            isEraserClicked = true;
            isRectClicked = false;
            isLineClicked = false;
            isOvalClicked = false;
            isFreeHandClicked = false;

        }

    }

    public class ClearActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            linesVec.clear();   //clear all vectors (remove shapes)
//            rectVec.clear();
//            ovalVec.clear();
            history.clear();
            historyCounter = 0;
            x1 = 0;
            x2 = 0;
            y1 = 0;
            y2 = 0;
            MyPanel.this.updateUI();
        }

    }

    public class UndoActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            if (isFreeHandClicked) // if we clicked undo in free hand mode it will show an error message to the user
//            {
//                JOptionPane.showMessageDialog(null, "sorry, undo is not working in free hand mode", "undo in free hand", JOptionPane.ERROR_MESSAGE);
//            } else {
                if (historyCounter > 0) {
                    history.remove(history.lastElement());
                    historyCounter--;
                    x1 = x2 = y1 = y2 = 0;
                    MyPanel.this.repaint();
                } else {
                    JOptionPane.showInternalMessageDialog(null, "More undo pressing will not undo your choices in life!", "مفيش تانى!", JOptionPane.WARNING_MESSAGE);
                }
            //}
        }
    }

    //*************** Checklists  *****************\\
    public class DottedItemList implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) // if checkbox is checked
            {
                float[] dashingPattern1 = {2f, 2f};
                stroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
            } else // else if the checkbox in unchecked
            {
                stroke = new BasicStroke(2f);
            }

        }

    }

    public class FilledItemList implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                isFilledChecked = true;
            } else {
                isFilledChecked = false;
            }

        }

    }

    //*************** COLORS BUTTONS  *****************\\
    public class RedActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            color = Color.RED;
            isColorClicked = true;
        }
    }

    public class GreenActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            color = Color.GREEN;
            isColorClicked = true;
        }
    }

    public class BlueActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            color = Color.BLUE;
            isColorClicked = true;
        }
    }

    //*************** Paint Method  *****************\\
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (historyCounter > 0) {
            for (int i = 0; i < historyCounter; i++) {
                switch (history.elementAt(i).getType()) {
                    case Line.TYPE -> {
                        g2d.setStroke(history.elementAt(i).getStroke());
                        g2d.setColor(history.elementAt(i).getColor());
                        tempLine = (Line) history.elementAt(i); // TypeCasting!
                        g2d.drawLine(tempLine.getxStart(), tempLine.getyStart(), tempLine.getxEnd(), tempLine.getyEnd());
                    }

                    case Oval.TYPE -> {
                        g2d.setStroke(history.elementAt(i).getStroke());
                        g2d.setColor(history.elementAt(i).getColor());
                        tempOval = (Oval) history.elementAt(i); // TypeCasting!
                        if (tempOval.isIsFilled()) {
                            g2d.fillOval(tempOval.getX(), tempOval.getY(), tempOval.getWidth(), tempOval.getHeight());
                        } else {
                            g2d.drawOval(tempOval.getX(), tempOval.getY(), tempOval.getWidth(), tempOval.getHeight());
                        }
                    }

                    case Rectangle.TYPE -> {
                        g2d.setStroke(history.elementAt(i).getStroke());
                        g2d.setColor(history.elementAt(i).getColor());
                        tempRect = (Rectangle) history.elementAt(i); // TypeCasting!
                        if (tempRect.isIsFilled()) {
                            g2d.fillRect(tempRect.getX(), tempRect.getY(), tempRect.getWidth(), tempRect.getHeight());
                        } else {
                            g2d.drawRect(tempRect.getX(), tempRect.getY(), tempRect.getWidth(), tempRect.getHeight());
                        }
                    }
                    
                    case FreeHand.TYPE -> {
                        g2d.setStroke(history.elementAt(i).getStroke());
                        g2d.setColor(history.elementAt(i).getColor());
                        tempFShape = (FreeHand) history.elementAt(i); // TypeCasting!
                        
                       for(int j=0;j<tempFShape.freeshape.size();j++){
                       tempLine = tempFShape.freeshape.elementAt(j);
                       g.drawLine(tempLine.getxStart(),tempLine.getyStart(),tempLine.getxEnd(),tempLine.getyEnd());
                       }
                    }
                }

            }
        }
        if (this.isFocusable()) {
            g2d.setColor(color);
            g2d.setStroke(stroke);
            if (isLineClicked == true) //painting lines
            {
                g2d.drawLine(x1, y1, x2, y2);
            } else if (isOvalClicked) //painting Ovals
            {

                if (isFilledChecked) // if the current oval is checked to be filled, then fill it while drawing
                {
                    g2d.fillOval(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2));
                } else {
                    g2d.drawOval(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2));
                }

            } else if (isRectClicked) //painting Rectangles
            {
                if (isFilledChecked) // if the current rect is checked to be filled, then fill it while drawing
                {
                    g2d.fillRect(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2));  //-> this way of drawing rect is to be able to draw the rec in all directions
                } else {
                    g2d.drawRect(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2));
                }
            }
        }
    }

//    @Override
//    public void run() {
//        while (true) {
//            if (x1 > 0 && y1 > 0) {
//                this.repaint();
//                
//            }
//            try {
//                Thread.sleep(1);
//                System.out.println(historyCounter);
//                System.out.println(isOvalClicked);
//                System.out.println(isRectClicked);
//            } catch (InterruptedException ex) {
//                System.out.println("catched!");
//            }
//            
//        }
//    }

}

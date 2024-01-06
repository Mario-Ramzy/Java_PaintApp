package paintbrushapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 *
 * @author Mario
 */
public class MainToolBar extends JToolBar {

    public MainToolBar() {
        setRollover(true);

        JButton redBtn = new JButton();     //Red button
        RedActionList redObj = new RedActionList();
        redBtn.addActionListener(redObj);
        redBtn.setBackground(Color.RED);
        this.add(redBtn);

        JButton greenBtn = new JButton();   // Green button
        GreenActionList greenObj = new GreenActionList();
        greenBtn.addActionListener(greenObj);
        greenBtn.setBackground(Color.GREEN);
        this.add(greenBtn);

        JButton blueBtn = new JButton();    //Blue button
        BlueActionList blueObj = new BlueActionList();
        blueBtn.addActionListener(blueObj);
        blueBtn.setBackground(Color.BLUE);
        this.add(blueBtn);

        JButton lineBtn = new JButton("Line");  // Draw line button
        LineActionList lineObj = new LineActionList();
        lineBtn.addActionListener(lineObj);
        this.add(lineBtn);

        JButton rectBtn = new JButton("Rectangle");   //Draw rectangle button
        RectActionList rectObj = new RectActionList();
        rectBtn.addActionListener(rectObj);
        this.add(rectBtn);

        JButton ovaltBtn = new JButton("Oval"); //Draw oval button
        OvalActionList ovalObj = new OvalActionList();
        ovaltBtn.addActionListener(ovalObj);
        this.add(ovaltBtn);

        JCheckBox dottedCB = new JCheckBox("Dotted");    //Dotted Checkbox
        DottedItemList dottedObj = new DottedItemList();
        dottedCB.addItemListener(dottedObj);
        this.add(dottedCB);

        JCheckBox filledCB = new JCheckBox("Filled");    //Filled Checkbox
        FilledItemList filledObj = new FilledItemList();
        filledCB.addItemListener(filledObj);
        this.add(filledCB);

        JButton freeBtn = new JButton("Free Hand");  // Free hand button
        FreeHandActionList fbreeObj = new FreeHandActionList();
        freeBtn.addActionListener(fbreeObj);
        this.add(freeBtn);

        JButton eraserBtn = new JButton("Eraser");    //Eraser button
        EraserActionList eraserObj = new EraserActionList();
        eraserBtn.addActionListener(eraserObj);
        this.add(eraserBtn);

        JButton clearBtn = new JButton(" Clear All ");  // Clear All button
        ClearActionList clearObj = new ClearActionList();
        clearBtn.addActionListener(clearObj);
        this.add(clearBtn);

        JButton undoBtn = new JButton(" Undo ");  // Undo button
        UndoActionList undoObj = new UndoActionList();
        undoBtn.addActionListener(undoObj);
        this.add(undoBtn);

        JButton saveBtn = new JButton(" Save ");  // Save button
        SaveAsJPG saveObj = new SaveAsJPG();
        saveBtn.addActionListener(saveObj);
        this.add(saveBtn);
        
        JButton openBtn = new JButton(" Open ");  // Open button
        OpenJPG openObj = new OpenJPG();
        openBtn.addActionListener(openObj);
        this.add(openBtn);
    }

    //*************** SHAPES BUTTONS  *****************\\
    public class LineActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (MyPanel.isColorClicked == true) {
                MyPanel.isLineClicked = true;
                MyPanel.isRectClicked = false;
                MyPanel.isOvalClicked = false;
                MyPanel.isFreeHandClicked = false;
                MyPanel.isEraserClicked = false;
            } else if (MyPanel.isColorClicked == false) {
                JOptionPane.showMessageDialog(null, "Please choose the color first", "Choose Color", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class RectActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (MyPanel.isColorClicked == true) {
                MyPanel.isRectClicked = true;
                MyPanel.isLineClicked = false;
                MyPanel.isOvalClicked = false;
                MyPanel.isFreeHandClicked = false;
                MyPanel.isEraserClicked = false;
            } else if (MyPanel.isColorClicked == false) {
                JOptionPane.showMessageDialog(null, "Please choose the color first", "Choose Color", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class OvalActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (MyPanel.isColorClicked == true) {
                MyPanel.isOvalClicked = true;
                MyPanel.isLineClicked = false;
                MyPanel.isRectClicked = false;
                MyPanel.isFreeHandClicked = false;
                MyPanel.isEraserClicked = false;
            } else if (MyPanel.isColorClicked == false) {
                JOptionPane.showMessageDialog(null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //*************** Other BUTTONS  *****************\\

    public class FreeHandActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (MyPanel.isColorClicked == true && MyPanel.isFreeHandClicked == false) {
                MyPanel.isFreeHandClicked = true;
                MyPanel.isLineClicked = MyPanel.isRectClicked = MyPanel.isOvalClicked = false;

            } else if (MyPanel.isColorClicked == false) {
                JOptionPane.showMessageDialog(null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public class EraserActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyPanel.isEraserClicked = true;
            MyPanel.isRectClicked = false;
            MyPanel.isLineClicked = false;
            MyPanel.isOvalClicked = false;
            MyPanel.isFreeHandClicked = false;

        }

    }

    public class ClearActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyPanel.history.clear();
            MyPanel.historyCounter = 0;
            MyPanel.x1 = 0;
            MyPanel.x2 = 0;
            MyPanel.y1 = 0;
            MyPanel.y2 = 0;
            MyPanel.isChanged = true;
        }

    }

    public class UndoActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (MyPanel.historyCounter > 0) {
                MyPanel.history.remove(MyPanel.history.lastElement());
                MyPanel.historyCounter--;
                MyPanel.x1 = MyPanel.x2 = MyPanel.y1 = MyPanel.y2 = 0;
                MyPanel.isChanged = true;

            } else {
                JOptionPane.showInternalMessageDialog(null, "More undo pressing will not undo your choices in life!", "مفيش تانى!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class SaveAsJPG implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyPanel.isSaved=true;
        }

    }
    
    public class OpenJPG implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Mesh nasy 7aga??");
        }

    }

    //*************** Checklists  *****************\\
    public class DottedItemList implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) // if checkbox is checked
            {
                float[] dashingPattern1 = {2f, 2f};
                MyPanel.stroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
            } else // else if the checkbox in unchecked
            {
                MyPanel.stroke = new BasicStroke(2f);
            }

        }

    }

    public class FilledItemList implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                MyPanel.isFilledChecked = true;
            } else {
                MyPanel.isFilledChecked = false;
            }

        }

    }

    //*************** COLORS BUTTONS  *****************\\
    public class RedActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyPanel.color = Color.RED;
            MyPanel.isColorClicked = true;
        }
    }

    public class GreenActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyPanel.color = Color.GREEN;
            MyPanel.isColorClicked = true;
        }
    }

    public class BlueActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyPanel.color = Color.BLUE;
            MyPanel.isColorClicked = true;
        }
    }
//
}

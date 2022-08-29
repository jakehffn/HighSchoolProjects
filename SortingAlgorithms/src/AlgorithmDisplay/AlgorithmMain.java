/*
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
*/
package AlgorithmDisplay;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class AlgorithmMain {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                createAndShowGUI();
            }
        });

    }

    private static void createAndShowGUI(){
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        ExitableJFrame f = new ExitableJFrame("Algorithm Display");
        f.add(new MainJFrame().new MyPanel());
        f.setSize(1500,800);
        f.setVisible(true);

    }

}

class ValueRectangle{

    boolean updated = true;
    private int xPos = 50;
    private int yPos = 50;
    private int width = 1;
    private int height = 1;

    public void setUpdated(boolean update){
        this.updated = update;
    }

    public boolean getUpdated(){
        return updated;
    }

    public void setX(int xPos){
        this.xPos = xPos;
        this.updated = true;
    }

    public int getX(){
        return xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
        this.updated = true;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    }

    public void setHeight(int height){
        this.height = height;
        this.updated = true;
    }

    public int getHeight(){
        return height;
    }

    public void paintSquare(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(xPos,yPos,width,height);
    }
}

class ExitableJFrame extends JFrame {

    public ExitableJFrame(){

    }

    public ExitableJFrame(String title){
        super(title);
    }

    protected void frameInit(){
        super.frameInit();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

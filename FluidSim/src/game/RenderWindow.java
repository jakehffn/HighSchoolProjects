package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class RenderWindow extends JComponent implements KeyListener, MouseListener {

    private boolean mousePressed = false;
    private boolean vectorsDisplayed = false;
    private String windowTitle;
    private int width;
    private int height;

    public RenderWindow(String windowTitle, int width, int height){
        this.windowTitle = windowTitle;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g){
    }

    public Dimension getPreferredSize() {
        try {
            return new Dimension(width, height);
        } catch (NullPointerException e) {
            return new Dimension(1000,1000);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
            vectorsDisplayed = !vectorsDisplayed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) { }

    @Override
    public void mouseExited(MouseEvent arg0) { }

    @Override
    public void mousePressed(MouseEvent arg0) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        mousePressed = false;
    }



    public boolean getMousePressed() {
        return mousePressed;
    }

    public boolean getKeyPressed() {
        return vectorsDisplayed;
    }

    public void createAndShowGUI() {

        JFrame f = new JFrame(windowTitle);

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(this);
        f.pack();
        f.addKeyListener(this);
        f.addMouseListener(this);
        f.setVisible(true);
        f.setFocusable(true);

    }

}

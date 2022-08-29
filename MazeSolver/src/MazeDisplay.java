/**
 *
 * @Author: Jacob Hoffman
 * @Version: 1/11/18
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MazeDisplay extends JComponent {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    double height = screenSize.getHeight();

    private BufferedImage img;
    private BufferedImage lgImg;

    private int magIncrease;

    final int black = -16777216;
    final int white = -1;

    boolean resize;

    public MazeDisplay(String path, boolean resize) {

        this.resize = resize;
        img = loadImage(path);

        if (resize){
            magIncrease = (((int)(height/( img.getHeight() + 20)) < 1) ? 1 : (int)(height/(img.getHeight() + 20)));
            sizeImage();
        } else {
            lgImg = loadImage(path);
        }
    }

    public void setImg(BufferedImage img) {
        if (resize){
            this.img = img;
            sizeImage();
        } else {
            this.lgImg = img;
        }
    }

    public static BufferedImage loadImage(String path){
        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Exception at loadImage: ");
            img = new BufferedImage(0, 0, BufferedImage.TYPE_4BYTE_ABGR);
        }
        return img;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, lgImg.getWidth(), lgImg.getHeight());
        g.drawImage(lgImg, 0, 0, null);
        repaint();
    }

    private void sizeImage(){


        BufferedImage tempImg = new BufferedImage(img.getWidth() * magIncrease, img.getHeight() * magIncrease, BufferedImage.TYPE_INT_ARGB);
        Graphics g = tempImg.getGraphics();

        for (int xx = 0; xx < img.getWidth(); xx++){

            int setNumX = xx * magIncrease;

            for (int yy = 0; yy < img.getHeight(); yy++){

                int rgb = img.getRGB(xx, yy);

                int setNumY = yy * magIncrease;

                if (rgb == black){
                    g.setColor(Color.BLACK);
                    g.fillRect(setNumX,setNumY,magIncrease,magIncrease);
                } else if (rgb != white){
                    g.setColor(new Color(rgb));
                    g.fillRect(setNumX,setNumY,magIncrease,magIncrease);
                }


            }
        }

        lgImg = tempImg;
    }

    public Dimension getPreferredSize() {
        if (img == null) {
            System.out.println("null");
            return new Dimension(100,100);
        } else {
            return new Dimension(lgImg.getWidth(null), lgImg.getHeight(null));
       }
    }

    public void createAndShowGUI(){

        JFrame f = new JFrame("Maze Solver");

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(this);
        f.pack();
        f.setVisible(true);

    }
}

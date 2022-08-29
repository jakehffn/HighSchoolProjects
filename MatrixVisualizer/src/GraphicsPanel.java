import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsPanel {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;

    @SuppressWarnings("serial")
    public class MyPanel extends JPanel {

        private BufferedImage bi;

        public MyPanel(){

            bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(bi,0, 0,null);

            repaint();
        }

        public BufferedImage getBi(){
            return bi;
        }

        public void setBi(BufferedImage newImage){
            bi = newImage;
        }
    }
}

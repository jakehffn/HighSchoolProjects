import java.awt.*;
import java.awt.image.BufferedImage;

public class Vector extends Matrix {

    //Must be 2x1
    Vector(double[][] matrix){
        super(matrix);
    }

    public BufferedImage addToImage(BufferedImage addTo, int sLength){
        Graphics g = addTo.getGraphics();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        int x1 = (int)(width/2);
        int y1 = (int)(height/2);

        int x2 = x1 + (int)(matrix[0][0] * sLength);
        int y2 = y1 - (int)(matrix[1][0] * sLength);

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);


        return addTo;
    }
}

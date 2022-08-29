import java.awt.*;
import java.awt.image.BufferedImage;

public class BasisVectors extends Vector {

    //must be 2x2
    BasisVectors(double[][] matrix){

        super(matrix);
        if (matrix.length < 2 || matrix[0].length < 2){
            throw (new IllegalArgumentException("Basis vector matrix must be at least 2 x 2"));
        }

    }

    public BufferedImage addToImage(BufferedImage addTo, int sLength){
        Graphics g = addTo.getGraphics();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double width = screenSize.getWidth();
        double hight = screenSize.getHeight();

        int x1 = (int)(width/2);
        int y1 = (int)(hight/2);

        int x2 = x1 + (int)(matrix[0][0] * sLength);
        int y2 = y1 - (int)(matrix[1][0] * sLength);


        g.setColor(Color.RED);
        g.drawLine(x1, y1, x2, y2);

        x1 = (int)(width/2);
        y1 = (int)(hight/2);

        x2 = x1 + (int)(matrix[0][1] * sLength);
        y2 = y1 - (int)(matrix[1][1] * sLength);


        g.setColor(Color.BLUE);
        g.drawLine(x1, y1, x2, y2);

        return addTo;
    }

}

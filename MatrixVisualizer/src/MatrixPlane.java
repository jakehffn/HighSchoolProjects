import java.awt.*;
import java.awt.image.BufferedImage;

public class MatrixPlane extends BasisVectors {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    int ox1 = (int)(width/2), oy1 = (int)(height/2);

    double determinant;

    int sLength;

    MatrixPlane(double[][] matrix){
        super(matrix);
        determinant = matrix[0][0]*matrix[1][1] - matrix[1][0]*matrix[0][1];
    }

    public BufferedImage addToImage(BufferedImage addTo, int sLength){

        this.sLength = sLength;
        Graphics g = addTo.getGraphics();

        drawLines(0, sLength, g);
        drawLines(1, sLength, g);
        drawBasis(sLength, g);

        return addTo;

    }

    private void drawLines(int IOrJ, int sLength, Graphics g){

        g.setColor(Color.LIGHT_GRAY);

        if (matrix[0][IOrJ] == 0){

            int x1;
            int y1 = -oy1;

            int x2;
            int y2 = oy1;

            for (int xx = -500; xx < 500; xx++){

                x1 = (int)(xx*matrix[0][1 - IOrJ]);
                x2 = x1;

                line(x1, y1, x2, y2, g);
            }

        } else {

            double m = matrix[1][IOrJ]/matrix[0][IOrJ];

            int x1 = -ox1;
            int y1;

            int x2 = ox1;
            int y2;

            for (int xx = -500; xx < 500; xx++){

                y1 = (int)(m*(-ox1 - matrix[0][1 - IOrJ]*xx) + matrix[1][1 - IOrJ]*xx);
                y2 = (int)(m*(ox1 - matrix[0][1 - IOrJ]*xx) + matrix[1][1 - IOrJ]*xx);


                line(x1, y1, x2, y2, g);
            }

        }

    }

    private void drawBasis(int sLength, Graphics g){

        g.setColor(Color.RED);

        if (matrix[0][0] == 0){

            int x1 = 0;
            int y1 = oy1;

            int x2 = 0;
            int y2 = -oy1;

            line(x1, y1, x2, y2, g);

        } else {

            double m = matrix[1][0]/matrix[0][0];

            int x1 = -ox1;
            int y1 = (int)(m*-ox1);

            int x2 = ox1;
            int y2 = (int)(m*ox1);

            line(x1, y1, x2, y2, g);

        }

        g.setColor(Color.BLUE);

        if (matrix[0][1] == 0){

            int x1 = 0;
            int y1 = oy1;

            int x2 = 0;
            int y2 = -oy1;

            System.out.println("here");
            line(x1, y1, x2, y2, g);

        } else {

            double m = matrix[1][1]/matrix[0][1];

            int x1 = -ox1;
            int y1 = (int)(m*-ox1);

            int x2 = ox1;
            int y2 = (int)(m*ox1);

            line(x1, y1, x2, y2, g);

        }

    }

    private void line(int x1, int y1, int x2, int y2, Graphics g){
        g.drawLine((ox1 - x1* sLength) ,  (oy1 - y1* sLength), (ox1 - x2* sLength), (oy1 - y2* sLength));
    }


}

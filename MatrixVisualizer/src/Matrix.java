import java.awt.image.BufferedImage;

public abstract class Matrix {

    double[][] matrix;

    Matrix(double[][] matrix){
        this.matrix = matrix;
    }

    public BufferedImage addToImage;

    public static double[][] multiplyMatrices (double[][] m1, double[][] m2) {
        int r1 = m1.length;
        int c2 = m2[0].length;

        double[][] m3 = new double[r1][c2];

        for(int xx = 0; xx < r1; xx++) {
            for (int yy = 0; yy < c2; yy++) {
                for (int zz = 0; zz < m1[0].length; zz++) {
                    m3[xx][yy] += m1[xx][zz] * m2[zz][yy];
                }
            }
        }

        return m3;
    }
}

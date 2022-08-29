import javax.swing.*;
import java.util.ArrayList;

public class MatrixMain {

    public static void main(String[] args) {

        ArrayList<Vector> objects = new ArrayList<>();

        //objects.add(new BasisVectors(new double[][]{{100, 0},
        //                                            {0, 100}}));
        objects.add(new MatrixPlane(new double[][]{{1, 1},
                                                   {0, 8}}));

        MatrixView view = new MatrixView();
        MatrixModel model = new MatrixModel(objects, 10);
        MatrixController controller = new MatrixController(model, view);

        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                controller.show();
            }
        });



    }

}

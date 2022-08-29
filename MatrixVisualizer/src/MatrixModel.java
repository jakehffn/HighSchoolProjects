import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MatrixModel {

    //in pixels
    private int standardLength;

    private ArrayList<Vector> objects;

    MatrixModel(ArrayList<Vector> objects, int standardLength){

        this.objects = objects;
        this.standardLength = standardLength;

    }

    public BufferedImage createScene(BufferedImage addTo){
        for(Vector object : objects){
            addTo = object.addToImage(addTo, standardLength);
        }

        return addTo;
    }

}

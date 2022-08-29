import java.awt.*;
import java.awt.image.BufferedImage;

public class MatrixController {

    MatrixModel model;
    MatrixView view;

    MatrixController(MatrixModel model, MatrixView view){
        this.model = model;
        this.view = view;
    }

    public void show(){
        GraphicsPanel.MyPanel mainPanel = new GraphicsPanel().new MyPanel();


        view.createAndShowGUI(mainPanel);

        mainPanel.setBi(model.createScene(mainPanel.getBi()));



    }

}

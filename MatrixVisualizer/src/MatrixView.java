import java.awt.*;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class MatrixView {

    public void createAndShowGUI(GraphicsPanel.MyPanel mainPanel){

        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        ExitableJFrame f = new ExitableJFrame("Matrix Visual");
        f.add(mainPanel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(screenSize.width,screenSize.height);
        f.setVisible(true);

    }

    @SuppressWarnings("serial")
    public class ExitableJFrame extends JFrame {

        public ExitableJFrame(){

        }

        public ExitableJFrame(String title){
            super(title);
        }

        protected void frameInit(){
            super.frameInit();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

    }

}

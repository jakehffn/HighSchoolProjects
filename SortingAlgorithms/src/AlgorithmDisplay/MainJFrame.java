package AlgorithmDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainJFrame {


    public class MyPanel extends JPanel {

        ValueRectangle arrayValueRect[] = new ValueRectangle[1300];


        public MyPanel(){

            //TimeUnit.SECONDS.sleep(1);

            populateValueRectangle();

            setBorder(BorderFactory.createLineBorder(Color.black));

            int heightArray[] = new int[arrayValueRect.length];

            for (int xx = 0; xx < heightArray.length; xx++){
                heightArray[xx] = 1*(xx+1)/2;
            }

            for (int xx = 0; xx < arrayValueRect.length; xx++) {

                instantRectangle(arrayValueRect[xx].getWidth()*xx + 20,
                        (heightArray[heightArray.length - 1]) - heightArray[xx] + 20, heightArray[xx] , xx);
            }

            for (int xx = 0; xx < arrayValueRect.length; xx++) {
                refreshRectangle(xx);
            }


            addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent e){
                    for (int xx = 0; xx < 10000; xx++){

                        XX = 0;

                        switchRectangles((int)(Math.random() * arrayValueRect.length),
                                (int)(Math.random() * arrayValueRect.length));


                    }


                }
            });



        }

        private void populateValueRectangle(){

            for (int xx = 0; xx < arrayValueRect.length; xx++){
                arrayValueRect[xx] = new ValueRectangle();
            }

        }


        private void switchRectangles(int first, int second){

            int CURR_X = arrayValueRect[first].getX();
            int CURR_Y = arrayValueRect[first].getY();
            int CURR_W = arrayValueRect[first].getWidth();
            int CURR_H = arrayValueRect[first].getHeight();
            int OFFSET = 1;

            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            int tempHeight = arrayValueRect[first].getHeight();
            int tempY = arrayValueRect[first].getY();

            arrayValueRect[first].setHeight(arrayValueRect[second].getHeight());
            arrayValueRect[first].setY(arrayValueRect[second].getY());

            repaint(arrayValueRect[first].getHeight(), arrayValueRect[first].getHeight(),
                    arrayValueRect[first].getWidth()+OFFSET,
                    arrayValueRect[first].getHeight()+OFFSET);

            CURR_X = arrayValueRect[second].getX();
            CURR_Y = arrayValueRect[second].getY();
            CURR_W = arrayValueRect[second].getWidth();
            CURR_H = arrayValueRect[second].getHeight();

            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            arrayValueRect[second].setHeight(tempHeight);
            arrayValueRect[second].setY(tempY);

            repaint(arrayValueRect[second].getX(), arrayValueRect[second].getY(),
                    arrayValueRect[second].getWidth()+OFFSET,
                    arrayValueRect[second].getHeight()+OFFSET);

        }

        private void instantRectangle (int x, int y, int h, int xx) {
            arrayValueRect[xx].setX(x);
            arrayValueRect[xx].setY(y);
            arrayValueRect[xx].setHeight(h);
        }

        private void refreshRectangle (int xx){
            repaint(arrayValueRect[xx].getX(), arrayValueRect[xx].getY(),
                    arrayValueRect[xx].getWidth(),
                    arrayValueRect[xx].getHeight());
        }

        public Dimension getPreferredSize() {
            return new Dimension(250,250);
        }


        public void fastBubbleSort(){

            for (int zz = 0; zz < arrayValueRect.length -1; zz++ ){
                if (arrayValueRect[zz].getHeight() > arrayValueRect[zz + 1].getHeight()){
                    switchRectangles(zz, zz+1);
                }

            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }



        }

        int XX = 0; //For bubble sort and Radix sort

        public void bubbleSort(){

            if (arrayValueRect[XX].getHeight() > arrayValueRect[XX + 1].getHeight()){
                switchRectangles(XX, XX+1);
                System.out.println(XX);

            }

            XX++;

            if (XX == arrayValueRect.length - 1){
                XX = 0;
            }

        }

        int arrayMagnitude = 0; //For Radix sort

        public void messedUpRadixSort(){

            int arrayLength = arrayValueRect.length;

            while (arrayLength >= 1){
                arrayLength = (int)(arrayLength / 10.0);
                arrayMagnitude++;
            }

            arrayMagnitude++;
            int sumNum = 1;

            for (int XX = 0; XX < arrayValueRect.length - 1; XX++){

                if (arrayMagnitude > 0) {
                    String a = "0000" + arrayValueRect[XX].getHeight();
                    String b = "0000" + arrayValueRect[XX + 1].getHeight();
                    a = a.substring(a.length() - (arrayMagnitude - (arrayMagnitude - sumNum)),
                            a.length() - (arrayMagnitude - (arrayMagnitude - sumNum) - 1));
                    b = b.substring(b.length() - (arrayMagnitude - (arrayMagnitude - sumNum)),
                            b.length() - (arrayMagnitude - (arrayMagnitude - sumNum) - 1));


                    System.out.println(a);
                    System.out.println(b);
                    if (Integer.parseInt(a) < Integer.parseInt(b)) {
                        switchRectangles(XX, XX + 1);
                    }
                    System.out.println(XX);
                }
            }

            //XX++;

            //if (XX == arrayValueRect.length - 1) {
            //    XX = 0;
            sumNum++;
            //}


        }

        int YY = 0;
        int digits[] = {0,0,0,0,0,0,0,0,0,0};

        int lower = 650 - 1;
        int higher = 650 + 1;

        boolean isDoneRight = false;
        boolean isDoneLeft = false;

        public void radixSort(){



            if (XX == 3){
                XX =  0;
            }



            arrayMagnitude = 0;

            int arrayLength = arrayValueRect.length;


            while (arrayLength >= 1){
                arrayLength = (int)(arrayLength / 10.0);
                arrayMagnitude++;
            }

            //for (int YY = 0; YY < arrayValueRect.length - 1; YY++){
            //while (YY < 1300){
                int yy = 650;

                String a =  arrayValueRect[yy].getHeight() + "";

                String add = "0";

                while (a.length() < arrayMagnitude){
                    a += add;
                }

                int a2 = Character.getNumericValue(a.charAt(XX));

                //String b =  arrayValueRect[YY + 1].getHeight() + "" ;

                //while (b.length() < arrayMagnitude){
                //    b += add;
                //}

                //String b2 = b.substring(XX, XX + 1);

                System.out.println(a2);
                //System.out.println(b2);

                //if (Integer.parseInt(a2) < Integer.parseInt(b2)){
                //    switchRectangles(YY, YY + 1);

                //}




                if (a2 < 5){
                    switchRectangles(arrayValueRect.length/2, lower);
                    lower--;
                    System.out.println("Lower");
                } else {
                    switchRectangles(arrayValueRect.length/2, higher);
                    higher++;
                    System.out.println(higher);
                }

                if (lower < 0){
                    lower = 650;
                    isDoneLeft = true;
                }

                if (higher > 1299){
                    higher = 650;
                    isDoneRight = true;
                }

                if (isDoneLeft && isDoneRight){
                    XX++;
                }


            //}

            System.out.println("XX " + XX);

            /*
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            */
        }

        boolean switchDir = false;

        public void shakerSort(){
            XX++;

            if (switchDir) {

                for (int zz = 0; zz < arrayValueRect.length - 1 + XX/2; zz++) {
                    if (arrayValueRect[zz].getHeight() > arrayValueRect[zz + 1].getHeight()) {
                        switchRectangles(zz, zz + 1);
                    }

                }
                switchDir = !switchDir;


            } else {
                for (int zz = arrayValueRect.length - 1 + XX/2; zz > 0; zz--) {
                    if (arrayValueRect[zz].getHeight() > arrayValueRect[zz - 1].getHeight()) {
                        switchRectangles(zz, zz - 1);
                    }

                }
                switchDir = !switchDir;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        public void spinningLine(int length, int xPos, int yPos, Graphics g){

            g.setColor(Color.BLUE);

            int x = (int)(length/2*Math.cos(Math.toRadians(ang)));
            int y = (int)(length/2*Math.sin(Math.toRadians(ang)));


            g.drawLine(x + xPos, y + yPos,
                    -x + xPos, -y + yPos);

            ang += 0.21;
            // .12
            //.21
            //.42


            /**/
            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            //*/

        }

        double ang = 1;

        public void unitCircle(int width, int xPos, int yPos, Graphics g)
        {
            int pos = width/2;
            int textX = 50;

            int[][] point = new int[10][2];

            int x = 0;
            int y = 1;
            int pointCenter = 0;

            point[pointCenter][0] = xPos + pos;
            point[pointCenter][1] = yPos + pos;

            int xTrig = (int)(pos*Math.cos(Math.toRadians(ang)));
            int yTrig = (int)(pos*Math.sin(Math.toRadians(ang)));

            int pointOnCircle = 1;
            point[pointOnCircle][0] = xTrig + xPos + pos;
            point[pointOnCircle][1] = yTrig + yPos + pos;

            int sinLength = Math.abs(point[pointOnCircle][y] - point[pointCenter][y]);
            int cosLength = Math.abs(point[pointOnCircle][x] - point[pointCenter][x]);

            int arcWidth = width/4;
            double realAng = -ang % 360;

            //Reference angle
            g.setColor(Color.GRAY);
            g.drawArc(point[pointCenter][x] - arcWidth/2, point[pointCenter][y] - arcWidth/2 - 1, arcWidth,
                    arcWidth, 0, (int)realAng);

            double refAngle = Math.abs(((realAng > 90 && realAng < 180) || (realAng > 270 && realAng < 360))?
                    -(90 - (realAng % 90)): (realAng % 90));

            String refAngString = (refAngle + "00000").substring(0, (refAngle + "").indexOf('.') + 4);
            g.drawString(refAngString + "", textX, 113);

            //Angle
            g.setColor(Color.lightGray);
            g.drawArc(point[pointCenter][x] - arcWidth/2, point[pointCenter][y] - arcWidth/2 - 1, arcWidth,
                    arcWidth,
                    (realAng > 90 && realAng < 270)? 180: 0, ((realAng > 90 && realAng < 180) ||
                            (realAng > 270 && realAng < 360))? -(90 - (int)(realAng % 90)): (int)(realAng % 90));

            String angString = (realAng + "00000").substring(0, (realAng + "").indexOf('.') + 4);
            g.drawString(angString + "", textX, 100);

            //The circle, cross, and radius
            g.setColor(Color.BLACK);
            g.drawOval(xPos, yPos, width, width);
            g.drawLine(xPos, yPos + pos, xPos + width, yPos + pos);
            g.drawLine(xPos + pos, yPos, xPos + pos, yPos + width);
            g.drawLine(point[pointCenter][x], point[pointCenter][y], point[pointOnCircle][x], point[pointOnCircle][y]);

            //Graph of sine
            double periodChange = 1.5;

            g.setColor(Color.GREEN);
            g.drawLine(point[pointOnCircle][x], point[pointOnCircle][y], point[pointCenter][x] + 2*width/3,
                    point[pointOnCircle][y]);

            int stepTotal = 700;
            for (int xx = 0; xx < stepTotal; xx++){
                g.setColor(new Color(rgb(0, xx, stepTotal), rgb(1, xx, stepTotal), rgb(2, xx, stepTotal)));
                g.drawRect(point[pointCenter][x] + 2*width/3 + xx,
                        (int)(pos*Math.sin(Math.toRadians(ang + xx * periodChange))) + yPos + width/2, 1, 1);
            }

            //Graph of cosine
            double cosOfCircle = (point[pointOnCircle][x] - point[pointCenter][x])/(double)pos + 0.004;

            int arcCircleWidth = width*2 + (2*width/3 - pos);
            int smallerArcCircleWidth = (2*width/3 - pos);

            g.setColor(Color.GRAY);

            g.drawArc(xPos, yPos, arcCircleWidth, arcCircleWidth, 180, 90);
            g.drawArc(xPos + width, yPos + width, smallerArcCircleWidth, smallerArcCircleWidth, 180, 90);

            int partOfWidth = (int)((double)width * (2 - (cosOfCircle + 1.0))) + smallerArcCircleWidth;

            g.drawArc(arcCircleWidth/2 - partOfWidth/2 + xPos, arcCircleWidth/2 - partOfWidth/2 + yPos, partOfWidth, partOfWidth, 180, 90);
            g.drawLine(xPos + width + smallerArcCircleWidth/2,(int)(-pos*Math.cos(Math.toRadians(ang))) + yPos - 1 + 2 * pos + 2*width/3 ,
                    xPos + width + smallerArcCircleWidth,
                    (int)(-pos*Math.cos(Math.toRadians(ang))) + yPos  - 1 + 2 * pos + 2*width/3);

            g.setColor(Color.CYAN);
            g.drawLine(point[pointOnCircle][x], point[pointOnCircle][y], point[pointOnCircle][x] ,
                    point[pointCenter][y] + 2*width/3 + width);
            g.drawLine(xPos,(int)(pos*Math.cos(Math.toRadians(ang))) + yPos + 2 * pos + 2*width/3 ,
                    point[pointCenter][x] + 2*width/3 ,
                    (int)(pos*Math.cos(Math.toRadians(ang))) + yPos + 2 * pos + 2*width/3);

            g.setColor(Color.CYAN);

            for (int xx = 0; xx < 700; xx++){
                g.drawRect(point[pointCenter][x] + 2*width/3 + xx,
                        (int)(pos*Math.cos(Math.toRadians(ang + xx * periodChange))) + yPos + 2 * pos + 2*width/3, 1, 1);
            }

            g.setColor(Color.GRAY);
            for (int xx = 0; xx < 700; xx++){
                g.drawRect(point[pointCenter][x] + 2*width/3 + xx,
                        (int)(-pos*Math.cos(Math.toRadians(ang + xx * periodChange))) + yPos + 2 * pos + 2*width/3, 1, 1);
            }

            g.setColor(Color.CYAN);
            g.drawLine(xPos, yPos + pos + 2*width/3 , xPos + width, yPos + width + pos + 2*width/3);

            //Sine
            g.setColor(Color.RED);
            g.drawLine(point[pointOnCircle][x], point[pointCenter][y], point[pointOnCircle][x],
                    point[pointOnCircle][y]);
            double sinOfCircle = (point[pointCenter][y] - point[pointOnCircle][y])/(double)pos + 0.004;

            String sinString = (sinOfCircle + "00000").substring(0, (sinOfCircle + "").indexOf('.') + 4);

            g.drawString(sinString, textX, 139);

            //Cosine
            g.setColor(Color.BLUE);
            g.drawLine(point[pointCenter][x], point[pointOnCircle][y], point[pointOnCircle][x],
                    point[pointOnCircle][y]);


            String cosString = (cosOfCircle + "00000").substring(0, (cosOfCircle + "").indexOf('.') + 4);

            g.drawString(cosString, textX, 152);

            //Tangent
            g.setColor(Color.RED);
            g.drawRect(25, yPos - 78, sinLength * 4, 1);

            g.setColor(Color.BLUE);
            g.drawRect(25, yPos - 75, cosLength * 4, 1);

            double tanOfCircle = sinOfCircle/cosOfCircle;

            g.setColor(Color.yellow);
            String tanString = (tanOfCircle + "00000").substring(0, (tanOfCircle + "").indexOf('.') + 4);
            g.drawString(tanString + "", textX, 165);

            double tanMarker = cosLength * 4;

            do {
                g.fillRect( 25 + (int)tanMarker, yPos - 78, 2, 4);
                tanMarker += cosLength * 4;

            } while (tanMarker < Math.abs(sinOfCircle * pos * 4) && Math.abs(tanOfCircle) < 125);

        }

        public int rgb(int color, int step, int stepTotal){

            int base = 255;
            int colorValue = 0;
            int stepPart = step % (stepTotal/7);
            int basePart = (int)( (double)(stepPart)/(stepTotal/7) * base );

            if (color == 0) {
                if (step < stepTotal / 7) {
                    colorValue = base;

                }else if (step < 2*(stepTotal / 7)) {
                    colorValue = base;

                }else if (step < 3*(stepTotal / 7)) {
                    colorValue = base - basePart;

                }else if (step < 4*(stepTotal / 7)) {

                }else if (step < 5*(stepTotal / 7)) {

                }else if (step < 6*(stepTotal / 7)) {
                    colorValue = basePart;

                }else if (step < 7*(stepTotal / 7)) {
                    colorValue = base;

                }
            }

            if (color == 1) {
                if (step < stepTotal / 7) {

                }else if (step < 2*(stepTotal / 7)) {
                    colorValue = basePart;

                }else if (step < 3*(stepTotal / 7)) {
                    colorValue = base;

                }else if (step < 4*(stepTotal / 7)) {
                    colorValue = base;

                }else if (step < 5*(stepTotal / 7)) {
                    colorValue = base - basePart;

                }else if (step < 6*(stepTotal / 7)) {

                }else if (step < 7*(stepTotal / 7)) {

                }
            }

            if (color == 2) {
                if (step < stepTotal / 7) {

                }else if (step < 2*(stepTotal / 7)) {

                }else if (step < 3*(stepTotal / 7)) {

                }else if (step < 4*(stepTotal / 7)) {
                    colorValue = basePart;

                }else if (step < 5*(stepTotal / 7)) {
                    colorValue = base;

                }else if (step < 6*(stepTotal / 7)) {
                    colorValue = base;

                }else if (step < 7*(stepTotal / 7)) {
                    colorValue = base - basePart;

                }
            }

            return colorValue;
        }

        public int getPointOnCircle(int radius, int x){
            double yValue = Math.sqrt(Math.pow(radius, 2) - Math.pow(x - 2*(x%radius), 2));

            return (int)yValue;
        }

        public int getPower(int num, int pow){

            if (pow == 0){
                return 1;
            } else {
                return num * getPower(num, pow - 1);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(0,0,10000,10000);

            /**/
            for (int xx = 0; xx < arrayValueRect.length; xx++) {

                arrayValueRect[xx].paintSquare(g);

            }
            //**/

            //unitCircle(250, 125, 105, g);
            //ang -= .450;

            /* weird shape *
            double tempAng = ang + .5;
            for (int xx = 0; xx < 1000; xx++){
                spinningLine((int)((xx/1000.0)*300), 200 + xx, 325, g);
            }

            for (int xx = 1000; xx > 0; xx--){
                spinningLine((int)((xx/1000.0)*300), 200 + xx, 325, g);
            }

            ang = tempAng;
            //*/

            /*
            double tempAng = ang + .5;

            for (int xx = 1000; xx > 0; xx--){
                spinningLine((int)((xx/1000.0)*300), 200 + 1000 - xx, 175, g);
            }

            for (int xx = 0; xx < 1000; xx++){
                spinningLine((int)((xx/1000.0)*300), 200 + xx, 325, g);
            }

            for (int xx = 1000; xx > 0; xx--){
                spinningLine((int)((xx/1000.0)*300), 200 + 1000 - xx, 475, g);
            }

            ang = tempAng;
            //*/

            /**/
            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            //*/
            //fastBubbleSort();
            bubbleSort();
            //messedUpRadixSort();
            //radixSort();
            //shakerSort();

            repaint();
        }
    }
}

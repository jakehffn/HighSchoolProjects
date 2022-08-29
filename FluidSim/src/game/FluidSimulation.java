package game;

import engine.IGameLogic;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FluidSimulation implements IGameLogic {

    private final RenderWindow renderer;

    private int fluidSize;
    private Fluid fluid;
    private double[][] sources;

    private boolean mousePressed = false;
    private boolean vectorsDisplayed = false;
    private double[] prevPos;



    public FluidSimulation(RenderWindow renderer, int fluidSize) {
        this.renderer = renderer;
        this.fluidSize = fluidSize;
        clearFluid();
    }

    public void init() throws Exception{
        renderer.createAndShowGUI();
    }

    public void input(RenderWindow renderer){
        mousePressed = renderer.getMousePressed();
        vectorsDisplayed = renderer.getKeyPressed();
    }

    public void update(float interval){
        if(mousePressed){
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, renderer);

            Rectangle r = renderer.getBounds();
            int h = r.height;
            int w = r.width;

            double indivHeight = h / (double) fluidSize;
            double indivWidth = w / (double) fluidSize;

            int x = checkRange((int)((p.getX())/indivWidth));
            int y = checkRange((int)((p.getY())/indivHeight));

            addSource(x, y, 100.0);
            addVelocity(p.getX(), p.getY(), fluid.getParticle(x, y));

        } else {
            prevPos = new double[]{-1, -1};
        }

        diffuse(.001, .1);
        advect( .1);
        velocityDecay(.5,.1);
    }

    public void render(Graphics g){

        if (g != null) {

            Rectangle r = renderer.getBounds();
            int h = r.height;
            int w = r.width;

            double indivHeight = h / (double) fluidSize;
            double indivWidth = w / (double) fluidSize;

            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics bg = bi.getGraphics();

            for (int xx = 0; xx < fluidSize; xx++) {
                for (int yy = 0; yy < fluidSize; yy++) {
                    Particle p = fluid.getParticle(xx, yy);
                    bg.setColor(getColor(p.getDensity()));


                    bg.fillRect((int) (xx * indivWidth), (int) (yy * indivHeight), (int) indivWidth, (int) indivHeight);

                    //This will show lines that point in the direction of the velocity for each pixel
                    if (vectorsDisplayed) {
                        vectorLine((int)(indivWidth + 2), (int)(xx * indivWidth), (int)(yy * indivHeight), p, bg);
                    }
                    
                }
            }

            g.drawImage(bi, 0, 0, null);
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
    }

    private Color getColor(double density){
        int red, green, blue;

        red = 1;
        green = 1;
        blue = 1;

        int range1 = 25;
        int range2 = 50;
        
        if (density < range1){
            red = 240 - (int)((density/range1)*140);
            green = 240 - (int)((density/range1)*190);
            blue = 240 - (int)((density/range1)*220);

        } else if(density >= range1 && density < range2){
            red = 70 - (int)(((density - range1)/(range2 - range1))*70);
            green = 0;
            blue = 23;
        } else if(density >= range2){

            red = 0;
            green = 0;
            blue = 5;


        } 

        Color color = new Color(red, green, blue);

        return color;
    }



    private void clearFluid(){

        try{
            fluid.setFluid();

            for (double[] sourceList : sources){
                for (double source : sourceList){
                    source = 0.0;
                }
            }
        } catch(Exception e){

            fluid = new Fluid();
            sources = new double[fluidSize][fluidSize];

            for (int xx = 0; xx < fluidSize; xx++){
                for (int yy = 0; yy < fluidSize; yy++){
                    sources[xx][yy] = 0.0;
                }
            }

        }

    }

    public void vectorLine(int length, int xPos, int yPos, Particle p, Graphics g){
        g.setColor(Color.BLACK);

        double ang = Math.atan(p.getVelocityY()/p.getVelocityX());

        int x = (int)(length/2*Math.cos(ang));
        int y = (int)(length/2*Math.sin(ang));

        g.drawLine(x + xPos, y + yPos, xPos, yPos);
    }

    private void sourceRegister(){

        for (int xx = 0; xx < fluidSize; xx++){
            for (int yy = 0; yy < fluidSize; yy++){
                Particle p = fluid.getParticle(xx, yy);
                p.setDensity(p.getDensity() + sources[xx][yy]);
                fluid.setParticle(xx, yy, p);
            }
        }
    }

    private void addSource(int xPos, int yPos, double density){
        Particle p = fluid.getParticle(xPos, yPos);
        p.setDensity(p.getDensity() + density);
        fluid.setParticle(xPos, yPos, p);
    }

    private void addVelocity(double xPos, double yPos, Particle p){
        if(prevPos[0] == -1){
            prevPos = new double[]{xPos, yPos};
        } else {
            p.setVelocityX((xPos - prevPos[0])/100);
            p.setVelocityY((yPos - prevPos[1])/100);
        }
    }

    private void diffuse(double diffRate, double timeElapsed){

        double a = timeElapsed * diffRate * fluidSize * fluidSize;

        for (int xx = 0; xx < fluidSize; xx++){
            for (int yy = 0; yy < fluidSize; yy++){

                double density = fluid.getParticle(xx, yy).getDensityPrev();

                double upDensity, downDensity, leftDensity, rightDensity;

                int xx0 = checkRange(xx - 1);
                int xx1 = checkRange(xx + 1);
                int yy0 = checkRange(yy - 1);
                int yy1 = checkRange(yy + 1);

                upDensity = fluid.getParticle(xx, yy0).getDensity();
                downDensity = fluid.getParticle(xx, yy1).getDensity();
                leftDensity = fluid.getParticle(xx0, yy).getDensity();
                rightDensity = fluid.getParticle(xx1, yy).getDensity();

                double newDensity = (density + a * (upDensity + downDensity + leftDensity + rightDensity))/(1 + 4 * a);
                fluid.getParticle(xx, yy).setDensity(newDensity);
            }
        }
    }

    private void advect(double timeElapsed){
        double dt0 = fluidSize * timeElapsed;

        double xx0, yy0, xx1, yy1;
        double x, y, s0, t0, s1, t1;

        for (int xx = 0; xx < fluidSize; xx++){
            for (int yy = 0; yy < fluidSize; yy++){

                Particle p = fluid.getParticle(xx, yy);

                x = xx - dt0 * p.getVelocityX();
                y = yy - dt0 * p.getVelocityY();


                if (x < 0.5) x = 0.5;
                if (x > fluidSize + 0.5) x = fluidSize + 0.5;
                xx0 = (int)x;
                xx1 = xx0 + 1.0;
                if (y < 0.5) y = 0.5;
                if (y > fluidSize + 0.5) y = fluidSize - 1.5;
                yy0 = (int)y;
                yy1 = yy0 + 1;

                s1 = x - xx0;
                s0 = 1.0 - s1;
                t1 = y - yy0;
                t0 = 1.0 - t1;

                double d1, d2, d3, d4;
                int xx0i, xx1i, yy0i, yy1i;

                xx0i = checkRange((int)xx0);
                xx1i = checkRange((int)xx1);
                yy0i = checkRange((int)yy0);
                yy1i = checkRange((int)yy1);

                d1 = fluid.getParticle(xx0i, yy0i).getDensity();
                d2 = fluid.getParticle(xx0i, yy1i).getDensity();
                d3 = fluid.getParticle(xx1i, yy0i).getDensity();
                d4 = fluid.getParticle(xx1i, yy1i).getDensity();

                //d[IX(i,j)] = s0*(t0*d0[IX(i0,j0)]+t1*d0[IX(i0,j1)])+
                //       s1*(t0*d0[IX(i1,j0)]+t1*d0[IX(i1,j1)]);

                double newDensity = s0 *(t0 * d1 + t1 * d2) + s1 *(t0 * d3 + t1 * d4);

                p.setDensity(newDensity);

            }
        }
    }

    private void velocityDecay(double friction, double timeElapsed){
        double a = timeElapsed * friction * fluidSize * fluidSize;

        for (int xx = 0; xx < fluidSize; xx++){
            for (int yy = 0; yy < fluidSize; yy++){

                Particle p = fluid.getParticle(xx, yy);
                double vXP = p.getVelocityXPrev();
                double vYP = p.getVelocityYPrev();

                double uVX, dVX, lVX, rVX;
                double uVY, dVY, lVY, rVY;

                int xx0 = checkRange(xx - 1);
                int xx1 = checkRange(xx + 1);
                int yy0 = checkRange(yy - 1);
                int yy1 = checkRange(yy + 1);

                uVX = fluid.getParticle(xx, yy0).getVelocityX();
                uVY = fluid.getParticle(xx, yy0).getVelocityY();

                dVX = fluid.getParticle(xx, yy1).getVelocityX();
                dVY = fluid.getParticle(xx, yy1).getVelocityY();

                lVX = fluid.getParticle(xx0, yy).getVelocityX();
                lVY = fluid.getParticle(xx0, yy).getVelocityY();

                rVX = fluid.getParticle(xx1, yy).getVelocityX();
                rVY = fluid.getParticle(xx1, yy).getVelocityY();

                double newVX = .999*(vXP + a * (uVX + dVX + lVX + rVX))/(1 + 4 * a);
                double newVY = .999*(vYP + a * (uVY + dVY + lVY + rVY))/(1 + 4 * a);

                p.setVelocityX(newVX);
                p.setVelocityY(newVY);
            }
        }
    }

    private int checkRange(int num){
        if (num >= fluidSize){
            return num % fluidSize;
        } else if (num < 0){
            return fluidSize + num % fluidSize;
        } else {
            return num;
        }
    }

    private class Fluid{

        private Particle[][] fluid;

        Fluid(){
            setFluid();
        }

        Fluid(Fluid fluid){
            setFluid();
            setFluid(fluid);
        }

        public void setFluid(){
            fluid = new Particle[fluidSize][fluidSize];

            for (int xx = 0; xx < fluidSize; xx++){
                for (int yy = 0; yy < fluidSize; yy++){
                    fluid[xx][yy] = new Particle();
                }
            }
        }

        public void setFluid(Fluid fluid){
            for (int xx = 0; xx < fluidSize; xx++){
                for (int yy = 0; yy < fluidSize; yy++){
                    this.fluid[xx][yy] = fluid.getParticle(xx, yy);
                }
            }
        }

        public Particle getParticle(int x, int y){
            return fluid[x][y];
        }

        public void setParticle(int x, int y, Particle p){
            fluid[x][y] = p;
        }

    }

    private class Particle{

        private double velocityX;
        private double velocityXPrev;

        private double velocityY;
        private double velocityYPrev;

        private double density;
        private double densityPrev;

        Particle(){
            velocityX = 0.0;

            velocityY = 0.0;

            density = 0.0;
            densityPrev = 0.0;
        }

        public void setVelocityX(double velocityX) {
            velocityXPrev = this.velocityX;
            this.velocityX = velocityX;
        }

        public double getVelocityX() {
            return velocityX;
        }

        public double getVelocityXPrev() {
            return velocityXPrev;
        }

        public void setVelocityY(double velocityY) {
            velocityYPrev = this.velocityY;
            this.velocityY = velocityY;
        }

        public double getVelocityY() {
            return velocityY;
        }

        public double getVelocityYPrev() {
            return velocityYPrev;
        }

        private void setDensity(double density) {
            densityPrev = this.density;
            this.density = density;
        }

        private double getDensity() {
            return density;
        }

        private double getDensityPrev(){
            return densityPrev;
        }
    }

}

/**
 *
 * @Author: Jacob Hoffman
 * @Version: 1/11/18
 *
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class MazeNav {

    MazeDisplay maze;
    BufferedImage img;

    final int black = -16777216;
    final int white = -1;

    int height;
    int width;

    int[] startPoint;
    int[] endPoint;

    boolean breadthFirst;

    enum Dir {
        UP, DOWN, LEFT, RIGHT
    }

    public MazeNav(MazeDisplay maze, boolean breadthFirst){

        this.breadthFirst = breadthFirst;

        BufferedImage tempSize = maze.getImg();
        BufferedImage tempImg = new BufferedImage(tempSize.getWidth(), tempSize.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        tempImg.getGraphics().drawImage(maze.getImg(), 0, 0, null);
        tempImg.getGraphics().dispose();
        img = tempImg;

        height = img.getHeight();
        width = img.getWidth();

        this.maze = maze;
    }

    public int[][] findOpenings(){
        int height = img.getHeight();
        int width = img.getWidth();

        int[][] openings = new int[2][];
        int counter = 0;

        for (int xx = 1; xx < width - 1; xx++){
            if (img.getRGB(xx, 1) == white){
                openings[counter] = new int[]{xx, 1};
                counter++;
                System.out.println(xx);
            }
        }

        for (int yy = 1; yy < height - 1; yy++){
            if (img.getRGB(1, yy) == white){
                openings[counter] = new int[]{1, yy};
                counter++;
            }
        }

        for (int yy = 1; yy < height - 2; yy++){
            if (img.getRGB(width - 2, yy) == white){
                System.out.println(yy);
                openings[counter] = new int[]{width - 2, yy};
                counter++;
            }
        }

        for (int xx = 1; xx < width - 2; xx++){
            if (img.getRGB(xx, height - 2) == white){
                openings[counter] = new int[]{xx, height - 2};
                counter++;
            }
        }

        img.setRGB(openings[0][0], openings[0][1], Color.YELLOW.getRGB());
        img.setRGB(openings[1][0], openings[1][1], Color.blue.getRGB());

        return openings;
    }

    //This was a previous attempt at making a recursive solver. It works well at a small scale, but a stack overflow
    // will occur with anything too large. Because of this, I made an iterative solver instead.
    /**/
    public boolean solvePixel(int xPos, int yPos, int prevXPos, int prevYPos){

        maze.setImg(img);

        if (prevXPos == -1 || prevYPos == -1){
            if (xPos == 1){
                prevXPos = 0;
                prevYPos = yPos;
            }
            if (yPos == 1){
                prevXPos = xPos;
                prevYPos = 0;
            }
            if (xPos == width - 2){
                prevXPos = width - 1;
                prevYPos = yPos;
            }
            if (yPos == height - 2){
                prevXPos = xPos;
                prevYPos = height - 1;
            }
        }
        // System.out.println("(" + prevXPos + ", " + prevYPos + ")");

        switch (checkForEnd(xPos, yPos)){
            case 0: break;
            case 1:
                img.setRGB(xPos, yPos, Color.red.getRGB());
                return true;
            case 2:
                img.setRGB(xPos, yPos, white);
                return false;
        }

        int[] prevPos = new int[] {prevXPos, prevYPos};

        if (recursiveCheck(xPos, yPos, prevPos, Dir.UP)){
            return true;
        } else if (recursiveCheck(xPos, yPos, prevPos, Dir.RIGHT)){
            return true;
        } else if (recursiveCheck(xPos, yPos, prevPos, Dir.LEFT)){
            return true;
        } else if (recursiveCheck(xPos, yPos, prevPos, Dir.DOWN)){
            return true;
        } else {
            return false;
        }

    }
    //*/

    /**/
    private boolean recursiveCheck(int xPos, int yPos, int[] prevPos, Dir dir){

        int[] dirArray;

        if (dir == Dir.UP){
            dirArray = new int[] {xPos, yPos - 1};
        } else if (dir == Dir.RIGHT){
            dirArray = new int[] {xPos + 1, yPos};
        } else if (dir == Dir.LEFT){
            dirArray = new int[] {xPos - 1, yPos};
        } else if (dir == Dir.DOWN){
            dirArray = new int[] {xPos, yPos + 1};
        } else {
            dirArray = new int[] {-1, -1};
        }

        if (getDirRGB(xPos, yPos, dir) == white && !java.util.Arrays.equals(prevPos, dirArray)){
            img.setRGB(dirArray[0], dirArray[1], Color.cyan.getRGB());
            if (solvePixel(dirArray[0], dirArray[1], xPos, yPos)){
                img.setRGB(xPos, yPos, Color.red.getRGB());
                return true;
            } else {
                img.setRGB(xPos, yPos, white);
                return false;
            }
        }
        return false;
    }
    //*/

    public void breadthFirstSearch(int[] startPoint, boolean showSolving){
        ArrayList<ArrayList<int[]>> mazePaths = new ArrayList<>();

        ArrayList<int[]> mazeSolution = new ArrayList<>();

        boolean solved = false;


        if (startPoint[0] == 1){
            startPoint[0] = 2;
        }
        if (startPoint[1] == 1){
            startPoint[1] = 2;
        }
        if (startPoint[0] == width - 2){
            startPoint[0] = width - 3;
        }
        if (startPoint[1] == height - 2){
            startPoint[1] = height - 3;
        }

        mazePaths.add(new ArrayList<>());
        mazePaths.get(0).add(startPoint);


        while(!solved){

            for (int xx = 0; xx < mazePaths.size(); xx++) {

                if (showSolving){
                    maze.setImg(img);
                }

                ArrayList<int[]> mazePath = mazePaths.get(xx);

                int[] pixel = mazePath.get(mazePath.size() - 1);
                int identifier = checkForEnd(pixel[0], pixel[1]);

                if (identifier == 2){
                    mazePaths.remove(xx);
                    xx--;
                } else if (identifier == 1){
                    mazeSolution = mazePaths.get(xx);
                    solved = true;
                }

                boolean foundPath = false;

                int[] up = new int[]{pixel[0], pixel[1] - 1};
                int[] right = new int[]{pixel[0] + 1, pixel[1]};
                int[] down = new int[]{pixel[0], pixel[1] + 1};
                int[] left = new int[]{pixel[0] - 1, pixel[1]};

                if (getDirRGB(pixel[0], pixel[1], Dir.UP) == white){
                    mazePath.add(up);
                    img.setRGB(up[0], up[1],Color.cyan.getRGB());
                    foundPath = true;
                }
                if (getDirRGB(pixel[0], pixel[1], Dir.RIGHT) == white){

                    if (foundPath){
                        mazePaths.add(new ArrayList<>(mazePath));
                        mazePaths.get(mazePaths.size() - 1).add(right);
                    } else{
                        mazePath.add(right);
                        foundPath = true;
                    }
                    img.setRGB(right[0], right[1],Color.cyan.getRGB());
                }
                if (getDirRGB(pixel[0], pixel[1], Dir.DOWN) == white){

                    if (foundPath){
                        mazePaths.add(new ArrayList<>(mazePath));
                        mazePaths.get(mazePaths.size() - 1).add(down);
                    } else{
                        mazePath.add(down);
                        foundPath = true;
                    }
                    img.setRGB(down[0], down[1],Color.cyan.getRGB());
                }
                if (getDirRGB(pixel[0], pixel[1], Dir.LEFT) == white){

                    if (foundPath){
                        mazePaths.add(new ArrayList<>(mazePath));
                        mazePaths.get(mazePaths.size() - 1).add(left);
                    } else {
                        mazePath.add(left);
                    }
                    img.setRGB(left[0], left[1],Color.cyan.getRGB());
                }

            }


        }

        for (int xx = 0; xx < mazeSolution.size(); xx++){
            int[] pixel = mazeSolution.get(xx);

            img.setRGB(pixel[0], pixel[1], Color.red.getRGB());
        }

        maze.setImg(img);

    }

    public int checkForEnd(int xPos, int yPos){

        int up = getDirRGB(xPos,yPos,Dir.UP);
        int right = getDirRGB(xPos,yPos,Dir.RIGHT);
        int left = getDirRGB(xPos,yPos,Dir.DOWN);
        int down = getDirRGB(xPos,yPos,Dir.LEFT);
        int counter = 0;

        if (up == black){
            counter++;
        }

        if (right == black){
            counter++;
        }

        if (left == black){
            counter++;
        }

        if (down == black){
            counter++;
        }

        if (counter == 3){
            return 2;
        }

        if (up == Color.blue.getRGB() ||
                right == Color.blue.getRGB() ||
                left == Color.blue.getRGB() ||
                down == Color.blue.getRGB()){
            return 1;
        } else {return 0;}

    }

    public int getDirRGB(int xPos, int yPos, Dir dir){
        if (dir == Dir.UP){
            return img.getRGB(xPos, yPos - 1);
        }

        if (dir == Dir.DOWN){
            return img.getRGB(xPos, yPos + 1);
        }

        if (dir == Dir.LEFT){
            return img.getRGB(xPos - 1, yPos);
        }

        if (dir == Dir.RIGHT){
            return img.getRGB(xPos + 1, yPos);
        } else { return -1;}
    }


    public void solveMaze(){
        int[][] openings = findOpenings();

        startPoint = new int[] {openings[0][0], openings[0][1]};
        endPoint = new int[] {openings[1][0], openings[1][1]};


        if (breadthFirst) {
            breadthFirstSearch(startPoint, true);
        } else {
            solvePixel(startPoint[0], startPoint[1],-1,-1);
        }

        maze.setImg(img);

    }


}

public class MazeMain {
    public static void main(String[] args){

        String mazePath1 = "Mazes/maze.gif";
        String mazePath2 = "Mazes/Easier Maze.gif";
        String mazePath3 = "Mazes/Easiest Maze.gif";
        String mazePath4 = "Mazes/Hard Maze.gif";
        String mazePath5 = "Mazes/Hardest Maze.gif";
        String mazePath6 = "Mazes/SimpleMaze.gif";
        String mazePath7 = "Mazes/Largest Maze.gif";
        String mazePath8 = "Mazes/Huge Maze.gif";
        String mazePath9 = "Mazes/Huge Fitting Maze.gif";

        String exampleMaze = "Mazes/Example maze.gif";

        MazeDisplay maze = new MazeDisplay(mazePath4, true);
        maze.createAndShowGUI();

        MazeNav nav = new MazeNav(maze, false);

        nav.solveMaze();
    }
}

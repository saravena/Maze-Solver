import java.util.*;   // Needed for Scanner Class
import java.io.*;     // Needed for File and IOException

public class Assig4 {
  // global variable used to keep track of count
  public static int count = 0;

  public static void main(String [] args) throws IOException{
    // Initializes Variables
    int row = 0;
    int column = 0;
    int x = 0;
    int y = 0;
    int mazeArr[][];

    String fileIn = args[0];  // assigns text file to String var
    File file = new File(fileIn);

    // checks to see if file exists
		if (!file.exists()){
				System.out.println("\nThis file does not exist.");
		} // end if

    // opens file
    Scanner inputFile = new Scanner(file);
    // creates new ArrayList object
    ArrayList<Maze> list = new ArrayList<Maze>();

    // reads to EOF and adds to ArrayList
    while (inputFile.hasNextInt()) {
      Maze maze = new Maze();  // creates new Maze object
      // Dimensions
      row = inputFile.nextInt();
      maze.setDimRow(row);
      column = inputFile.nextInt();
      maze.setDimColumn(column);
      // Starting Coordinates
      x = inputFile.nextInt();
      maze.setX(x);
      y = inputFile.nextInt();
      maze.setY(y);
      // Maze
      mazeArr = new int[row][column];
      int temp = 0; // temporary variable used to store current int to array
      for (int i = 0; i < row; i++) {
        for(int j = 0; j < column; j++) {
          temp = inputFile.nextInt();
          mazeArr[i][j] = temp;
        } // end for
      } // end for
      maze.setMaze(mazeArr, row, column);
      // add to ArrayList
      list.add(maze);
    } // end while

    // finds solutions to every maze in file
    for (int m = 0; m < list.size(); m++) {
      // stores Coordinates
      ArrayList<Integer> store = new ArrayList<Integer>();
      // stores different paths
      ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
      // temporary variable
      int[][] tempMaze = new int[list.get(m).getRow()][list.get(m).getColumn()];

      System.out.println("-------------------------------------------------");
      System.out.println("\nThe new board is:\n");
      // print current maze
      for (int i = 0; i < list.get(m).getRow(); i++) {
        for (int j = 0; j < list.get(m).getColumn(); j++) {
          tempMaze[i][j] = list.get(m).getMaze(i, j);
          System.out.print(list.get(m).getMaze(i, j) + " ");
        } // end for
        System.out.println();
      } // end for

      // print starting Coordinates
      System.out.println("\nSearching for solutions starting at (" + list.get(m).getX() +
                            "," + list.get(m).getY() + ")");
      //solve maze
      solveMaze(tempMaze, list.get(m).getX(), list.get(m).getY(), store, paths);

      // Print every solution to maze
      for (int i = 0; i < paths.size(); i++) {
        // temp variables for path size
        int size = 0;
        size = paths.get(i).size() / 2;
        System.out.println("\nSolution Found with " + size + " segments");
        // print maze
        list.get(m).printMaze(list.get(m).getRow(), list.get(m).getColumn(), paths.get(i));
        System.out.println();
      }

      // print conclusion
      System.out.println();
      System.out.println("There were a total of " + paths.size() + " solutions found");
      System.out.println("A total of " + count + " recursive calls were made");
      if (paths.size() > 0){
        int tempIdx = returnIdx(paths);
        System.out.println("The shortest solution had " + (paths.get(tempIdx).size() / 2) +
                              " segments");
        list.get(m).printMaze(list.get(m).getRow(), list.get(m).getColumn(), paths.get(tempIdx));

        System.out.println();
      }
    } // end for
  } // end main

  // recursively solve maze
  public static int[][] solveMaze(int[][] maze, int x, int y, ArrayList<Integer> store, ArrayList<ArrayList<Integer>> paths) {
    count++;
    // checks if coordinates are out of bounds
    if (x >= maze.length || y >= maze[0].length || x < 0 || y < 0) {
      return maze;
    } else {
      // checks if current postion is equal to final destination
      if (maze[x][y] == 2) {
        // add final coordinates to path
        store.add(new Integer(x));
        store.add(new Integer(y));
        // add entire path to array list
        paths.add(new ArrayList<Integer>(store));
        // remove final coordinates for backtracking to find other solutions
        store.remove(store.size()-1);
        store.remove(store.size()-1);
        return maze;
      // checks if current coordinates is a wall
      } else if (maze[x][y] == 1){
        return maze;
      // checks if current coordinates is part of the previous path
      } else if (maze[x][y] == 'x') {
        return maze;
      // if current coordinates equal 0 (free path)
      } else {
        // adds coordinates to path
        store.add(new Integer(x));
        store.add(new Integer(y));
        // change value to 'x'
        maze[x][y] = 'x';
        // checks every direction
        solveMaze(maze, x, y+1, store, paths); // move Right
        solveMaze(maze, x+1, y, store, paths); // move Down
        solveMaze(maze, x, y-1, store, paths); // move Left
        solveMaze(maze, x-1, y, store, paths); // move Up
        // backtracking: removes last two coordinates
        store.remove(store.size()-1);
        store.remove(store.size()-1);
        return maze;
      }
    }
  } // end solveMaze(int[][], int, int)

  // returns index of shortest path
  public static int returnIdx(ArrayList<ArrayList<Integer>> path) {
    int temp = 0;
    for (int i = 0; i < path.size(); i++) {
      if(path.get(i).size() < path.get(temp).size()) {
        temp = i;
      }
    } // end for
    return temp;
  } // end returnIdx(ArrayList<Integer>)
} // end class Assig4

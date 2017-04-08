import java.util.*;

public class Maze {
  // private variables
  private int Row;
  private int Column;
  private int StartX;
  private int StartY;
  private int count;
  private int maze[][];

  //Ddefault Constructor: initializes all values to 0
  public void Maze() {
    Row = 0;
    Column = 0;
    StartX = 0;
    StartY = 0;
    maze = new int[Row][Column];
  } // end Maze()

  // Mutators
  // sets the number of rows in maze
  public void setDimRow(int row) {
    Row = row;
  } // end setDimX(int)
  // sets the number of columns in maze
  public void setDimColumn(int column) {
    Column = column;
  } // end setDimY(int)

  // sets the starting X coordinate
  public void setX(int x) {
    StartX = x;
  } // end SetX(int)
  // sets the starting Y coordinate
  public void setY(int y) {
    StartY = y;
  } // end SetY(int)

  // populates maze components into 2D array
  public void setMaze(int[][] mazeArr, int r, int c) {
    // sets size of 2D array
    maze = new int[r][c];
    // populates array
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        maze[i][j] = mazeArr[i][j];
      } // end for
    } // end for
  }  // end setMaze(int[][], int, int)

  // increments count
  public void addCount() {
    count++;
  } // end addCount()


  // Accessors
  // returns number of rows
  public int getRow() {
    return Row;
  } // end getRow()
  // returns numbers of columns
  public int getColumn() {
    return Column;
  } // end getColumn()

  // returns starting X coordinate
  public int getX() {
    return StartX;
  } // end getX()
  // returns starting Y coordinate
  public int getY() {
    return StartY;
  } // end getY()

  // returns maze coordinate
  public int getMaze(int x, int y) {
    return maze[x][y];
  } // end getMaze()

  // returns count
  public int getCount() {
    return count;
  } // end getCount()


  // prints the current maze
  public void printMaze(int r, int c,  ArrayList<Integer> path) {
    for (int i = 0; i < r; i ++) {
      for (int j = 0; j < c; j ++) {
        // used if X is found
        boolean foundX = false;
        for (int m = 0; m < path.size()-2; m+=2){
          if (i == path.get(m) && j == path.get(m+1)){
            // print x if matches with path coordinate
            System.out.print("x ");
            foundX = true;
          }
        } // end for
        // if not found print coordinate value
        if(!foundX){
          System.out.print(maze[i][j] + " ");
        }
      } // end for
      System.out.println();
    } // end for
    // print path coordinates
    System.out.print("Path: ");
    for(int i = 0; i < path.size(); i+=2) {
      System.out.print("(" + path.get(i) + "," + path.get(i+1) + ") ");
    } // end for
  } // end printMaze(int, int, ArrayList<Integer>)

} // end class Maze

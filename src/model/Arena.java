package model;

import model.element.Grid;
import model.element.Wall;

import java.awt.*;
import java.io.*;

/**
 * Kelas berisi Arena dari Game BacMan.
 */
public class Arena {

  private static Grid[][] map;
  private static final String MAP_PATH = "src\\view\\assets\\map.txt";
  private static int mapLength;
  private static int mapWidth;
  private static Point blinkyInitPos;
  private static Point pinkyInitPos;
  private static Point inkyInitPos;
  private static Point clydeInitPos;
  private static Point playerInitPos;

  public Arena() {
    loadMapFromFile(MAP_PATH);
  }

  public void loadMapFromFile(String path) {
    try {
      FileInputStream fstream;
      DataInputStream instream;
      BufferedReader buffread;
      fstream = new FileInputStream(String.format("%s", MAP_PATH));
      instream = new DataInputStream(fstream);
      buffread = new BufferedReader(new InputStreamReader(instream));
      String[] strSplit = buffread.readLine().split("\\s+");
      mapLength = Integer.parseInt(strSplit[0]);
      mapWidth = Integer.parseInt(strSplit[1]);
      map = new Grid[mapWidth][mapLength];
      String line;
      for (int row = 0; row < mapWidth; row++) {
        line = buffread.readLine();
        for (int col = 0; col < mapLength; col++) {
          switch (line.charAt(col)) {
            case '-':
              map[row][col] = new Wall();
              break;
            case 'o':
              map[row][col] = new Grid(true);
              break;
            case 'S':
              map[row][col] = new Grid(true, true);
              break;
            case 'P':
              map[row][col] = new Grid();
              playerInitPos = new Point(row, col);
              break;
            case 'A':
              map[row][col] = new Grid();
              blinkyInitPos = new Point(row, col);
              break;
            case 'B':
              map[row][col] = new Grid();
              pinkyInitPos = new Point(row, col);
              break;
            case 'C':
              map[row][col] = new Grid();
              inkyInitPos = new Point(row, col);
              break;
            case 'D':
              map[row][col] = new Grid();
              clydeInitPos = new Point(row, col);
            default:
              map[row][col] = new Grid();
          }
        }
      }
      buffread.close();
      instream.close();
      fstream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static int getMapLength() {
    return mapLength;
  }

  public static int getMapWidth() {
    return mapWidth;
  }

  public static Grid[][] getMap() {
    return map;
  }

  public static Grid getGrid(int i, int j) {
    return map[i][j];
  }

  public static Point getBlinkyInitPos() {
    return blinkyInitPos;
  }

  public static Point getPinkyInitPos() {
    return pinkyInitPos;
  }

  public static Point getInkyInitPos() {
    return inkyInitPos;
  }

  public static Point getClydeInitPos() {
    return clydeInitPos;
  }

  public static Point getPlayerInitPos() {
    return playerInitPos;
  }

  public static Point getRespawnPos() {
    return new Point((mapWidth / 2) - 1, mapLength / 2);
  }
}

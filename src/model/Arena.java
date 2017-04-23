package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import model.character.GhostTypeA;
import model.character.GhostTypeB;
import model.element.Grid;
import model.element.Wall;

/**
 * Kelas berisi Arena dari Game BacMan.
 */
public class Arena {
  private static Grid[][] map;
  private static final String MAP_PATH = "src/view/assets/map.txt";
  private static int mapLength;
  private static int mapWidth;

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
            default:
              map[row][col] = new Grid();
          }
          map[row][col] = new Grid();
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

  public static Grid getGrid(int i, int j) {
    return map[i][j];
  }
}

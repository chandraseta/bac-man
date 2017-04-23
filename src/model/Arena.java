package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import model.element.Grid;

/**
 * Kelas berisi Arena dari Game BacMan.
 */
public class Arena {
  private Grid[][] map;
  private static final String MAP_PATH = "src/view/assets/map.txt";
  private int map_length;
  private int map_width;

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
      map_length = Integer.parseInt(strSplit[0]);
      map_width = Integer.parseInt(strSplit[1]);
      map = new Grid[map_width][map_length];
      String line;
      for (int row = 0; row < map_width; row++) {
        line = buffread.readLine();
        for (int col = 0; col < map_length; col++) {
          map[row][col] = new Grid()
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

  public int getMapLength() {
    return map_length;
  }

  public int getMapWidth() {
    return map_width;
  }

  public Grid getGrid(int i, int j) {
    return map[i][j];
  }
}

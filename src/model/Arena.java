package model;

import model.element.Grid;
import model.element.Wall;

import java.awt.*;
import java.io.*;

/**
 * Kelas berisi Arena dari Game BacMan.
 */
public class Arena {

  /**
   * Map pada Arena.
   */
  private static Grid[][] map;

  /**
   * Path file eksternal map pada Arena.
   */
  private static final String MAP_PATH = "src\\view\\assets\\map.txt";

  /**
   * Panjang Arena.
   */
  private static int mapLength;

  /**
   * Lebar Arena.
   */
  private static int mapWidth;

  /**
   * Index row yang terdapat tunnel.
   */
  private static int tunnelRow;

  /**
   * Titik awal Ghost Blinky.
   */

  private static Point blinkyInitPos;

  /**
   * Titik awal Ghost Pinky.
   */
  private static Point pinkyInitPos;

  /**
   * Titik awal Ghost Inky.
   */
  private static Point inkyInitPos;

  /**
   * Titik awal Ghost Clyde.
   */
  private static Point clydeInitPos;

  /**
   * Titik awal Player.
   */
  private static Point playerInitPos;

  /**
   * Banyak Cookie pada Arena.
   */
  private int cookieCount;

  /**
   * Cookie yang tersisa di Arena.
   */
  private static int cookieLeft;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Arena.
   * </p>
   */
  public Arena() {
    cookieCount = 0;
    loadMapFromFile(MAP_PATH);
    cookieLeft = cookieCount;
  }

  /**
   * Melakukan load sebuah map (File eksternal) untuk dijadikkan Arena permainan.
   *
   * @param path Path direktori file eksternal map.
   */
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
              cookieCount++;
              if (col == 0 || col == mapLength - 1) {
                tunnelRow = row;
              }
              break;
            case 'S':
              map[row][col] = new Grid(true, true);
              cookieCount++;
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

  /**
   * Fungsi mengembalikan panjang Arena.
   *
   * @return Panjang Arena.
   */
  public static int getMapLength() {
    return mapLength;
  }

  /**
   * Fungsi mengembalikan lebar Arena.
   *
   * @return Lebar Arena.
   */
  public static int getMapWidth() {
    return mapWidth;
  }

  /**
   * Fungsi mengembalikan urutan row yang terdapat tunnel.
   *
   * @return Urutan row yang terdapat tunnel pada Arena.
   */
  public static int getTunnelRow() {
    return tunnelRow;
  }

  /**
   * Fungsi mengembalikan map Arena.
   *
   * @return Map yang dipakai Arena.
   */
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

  public static int getCookieLeft() {
    return cookieLeft;
  }

  public static void eatCookie() {
    cookieLeft--;
  }
}

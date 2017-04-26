package model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import model.element.Grid;
import model.element.Wall;

/**
 * Kelas berisi Arena dari Game BacMan.
 *
 * @author Oktavianus Handika - 13515035
 */
public class Arena {

  /**
   * Path file eksternal map pada Arena.
   */
  private static final String MAP_PATH = "src\\view\\assets\\map.txt";
  /**
   * Map pada Arena.
   */
  private static Grid[][] map;
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
   * <p>
   * Constructor
   *
   * Menciptakan Arena.
   * </p>
   */
  public Arena() {
    loadMapFromFile(MAP_PATH);
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

  /**
   * Fungsi mengembalikan Grid pada suatu titik.
   *
   * @param i Posisi y Grid.
   * @param j Posisi x Grid.
   * @return Grid pada suatu titik di Arena.
   */
  public static Grid getGrid(int i, int j) {
    return map[i][j];
  }

  /**
   * Fungsi mengembalikan posisi awal Blinky saat inisialisasi Arena.
   *
   * @return Posisi inisialisasi Blinky.
   */
  public static Point getBlinkyInitPos() {
    return blinkyInitPos;
  }

  /**
   * Fungsi mengembalikan posisi awal Pinky saat inisialisasi Arena.
   *
   * @return Posisi inisialisasi Pinky.
   */
  public static Point getPinkyInitPos() {
    return pinkyInitPos;
  }

  /**
   * Fungsi mengembalikan posisi awal Inky saat inisialisasi Arena.
   *
   * @return Posisi inisialisasi Inky.
   */
  public static Point getInkyInitPos() {
    return inkyInitPos;
  }

  /**
   * Fungsi mengembalikan posisi awal Clyde saat inisialisasi Arena.
   *
   * @return Posisi inisialisasi Clyde.
   */
  public static Point getClydeInitPos() {
    return clydeInitPos;
  }

  /**
   * Fungsi mengembalikan posisi awal Player saat inisialisasi Arena.
   *
   * @return Posisi inisialisasi Player.
   */
  public static Point getPlayerInitPos() {
    return playerInitPos;
  }

  /**
   * Fungsi mengembalikan posisi respawn Ghost.
   *
   * @return Posisi respawn Ghost.
   */
  public static Point getRespawnPos() {
    return new Point((mapWidth / 2) - 1, mapLength / 2);
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
              if (col == 0 || col == mapLength - 1) {
                tunnelRow = row;
              }
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
              break;
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
}

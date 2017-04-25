package model;

import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * Kelas VisibilityGraph mendefinisikan Graph pada Arena.
 */
public class VisibilityGraph {

  /**
   * Grid dapat diakses atau tidak untuk setiap Grid dalam Arena.
   */
  private static boolean[][] accessibilityMatrix;

  /**
   * Grid merupakan landmark atau tidak untuk setiap Grid dalam Arena
   */
  private static int[][] landmarkMatrix;

  /**
   * Matriks ketetangaan untuk setiap landmark di Arena.
   */
  private static int[][] adjacencyMatrix;

  /**
   * Matriks pergerakan dari satu landmark ke landmark lainnya di Arena.
   */
  private static int[][] movementMatrix;

  /**
   * Panjang Arena.
   */
  private static int length;

  /**
   * Lebar Arena.
   */
  private static int width;

  /**
   *
   */
  private static Vector<Point> landmarks;

  /**
   * Banyak landmark di Arena.
   */
  private static int numOfLandmarks = 0;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan VisibilityGraph dari Arena.
   * </p>
   */
  public VisibilityGraph() {
    length = Arena.getMapLength();
    width = Arena.getMapWidth();

    // Generate visibility graph
    findLandmarks();
    calculateAdjacency();
  }

  /**
   * Fungsi mengembalikan Matriks aksesibilitas Arena.
   *
   * @return Matriks aksesibilitas Arena.
   */
  public static boolean[][] getAccessibilityMatrix() {
    return accessibilityMatrix;
  }

  /**
   * Fungsi mengembalikan Matriks landmark Arena.
   *
   * @return Matriks landmark Arena.
   */
  public static int[][] getLandmarkMatrix() {
    return landmarkMatrix;
  }

  /**
   * Fungsi mengembalikan Matriks ketetangaan dari landmark pada Arena.
   *
   * @return Matriks ketetanggan dari landmark pada Arena.
   */
  public static int[][] getAdjacencyMatrix() {
    return adjacencyMatrix;
  }

  /**
   * Fungsi mengembalikan Matriks pergerakan dari landmark pada Arena.
   *
   * @return Matriks pergerakan dari landmark pada Arena.
   */
  public static int[][] getMovementMatrix() {
    return movementMatrix;
  }

  /**
   * Fungsi mengembalikan landmark yang ada pada Arena.
   *
   * @return kumpulan landmark pada Arena.
   */
  public static Vector<Point> getLandmarks() {
    return landmarks;
  }

  /**
   * Fungsi mengembalikan banyak landmark yang ada di Arena.
   *
   * @return Banyak landmark pada Arena.
   */
  public static int getNumOfLandmarks() {
    return numOfLandmarks;
  }

  /**
   * Method untuk mencari landmark yang ada pada Arena.
   */
  private static void findLandmarks() {
    accessibilityMatrix = new boolean[width][length];
    landmarkMatrix = new int[width][length];
    landmarks = new Vector<>();

    // Initialize matrices
    for (int i = 0; i < width; ++i)
      for (int j = 0; j < length; ++j)
        accessibilityMatrix[i][j] = Arena.getGrid(i, j).isAccessible();
    for (int[] row : landmarkMatrix)
      Arrays.fill(row, -1);

    // Find landmarks
    for (int i = 1; i < width - 1; ++i) {
      for (int j = 1; j < length - 1; ++j) {
        if (accessibilityMatrix[i][j]) {
          boolean isLandmark = false;
          boolean up = accessibilityMatrix[i - 1][j];
          boolean down = accessibilityMatrix[i + 1][j];
          boolean left = accessibilityMatrix[i][j - 1];
          boolean right = accessibilityMatrix[i][j + 1];

          int connectedTiles = (up ? 1 : 0) + (down ? 1 : 0) +
              (left ? 1 : 0) + (right ? 1 : 0);
          if (connectedTiles >= 3) {
            isLandmark = true;
          } else if (connectedTiles == 2) {
            isLandmark = !(up && down) && !(left && right);
          }

          if (isLandmark) {
            landmarks.add(new Point(i, j));
            landmarkMatrix[i][j] = numOfLandmarks++;
          }
        }
      }
    }
  }

  /**
   * Melakukan perhitungan pada matriks ketetanggaan dan pergerakan dari seluruh landmark
   * yang ada pada Arena.
   */
  private static void calculateAdjacency() {
    adjacencyMatrix = new int[numOfLandmarks][numOfLandmarks];
    movementMatrix = new int[numOfLandmarks][numOfLandmarks];
    for (int[] row : adjacencyMatrix)
      Arrays.fill(row, Integer.MAX_VALUE);
    for (int[] row : movementMatrix)
      Arrays.fill(row, 0);

    for (int i = 0; i < landmarks.size(); ++i) {
      findNeighbors(landmarks.elementAt(i), adjacencyMatrix[i], movementMatrix[i]);
    }
  }

  /**
   * Mencari
   * 
   * @param origin Titik mula-mula.
   * @param adjacencyArray
   * @param movementArray
   */
  public static void findNeighbors(Point origin, int[] adjacencyArray, int[] movementArray) {
    int i = origin.x;
    int j = origin.y;
    boolean found;
    int k;

    // Check up
    found = false;
    for (k = i - 1; k >= 0 && accessibilityMatrix[k][j] && !found; --k) {
      found = (landmarkMatrix[k][j] != -1);
    }
    if (found) {
      int v = landmarkMatrix[k][j];
      adjacencyArray[v] = i - k;
      movementArray[v] = 1;
    }

    // Check right
    found = false;
    for (k = j + 1; k < length && accessibilityMatrix[k][j] && !found; ++k) {
      found = (landmarkMatrix[i][k] != -1);
    }
    if (found) {
      int v = landmarkMatrix[i][k];
      adjacencyArray[v] = k - j;
      movementArray[v] = 2;
    }

    // Check down
    found = false;
    for (k = i + 1; k < width && accessibilityMatrix[k][j] && !found; ++k) {
      found = (landmarkMatrix[k][j] != -1);
    }
    if (found) {
      int v = landmarkMatrix[k][j];
      adjacencyArray[v] = k - i;
      movementArray[v] = 3;
    }

    // Check left
    found = false;
    for (k = j - 1; k >= 0 && accessibilityMatrix[k][j] && !found; --k) {
      found = (landmarkMatrix[i][k] != -1);
    }
    if (found) {
      int v = landmarkMatrix[i][k];
      adjacencyArray[v] = j - k;
      movementArray[v] = 4;
    }
  }
}

package model;

import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by ASUS on 23/04/17.
 */
public class VisibilityGraph {
    private boolean[][] accessibilityMatrix;
    private int[][] landmarkMatrix;
    private int[][] adjacencyMatrix;
    private int[][] movementMatrix;
    private int length;
    private int width;
    private Vector<Point> landmarks;
    private int numOfLandmarks = 0;

    public VisibilityGraph(final Arena arena) {
        length = arena.getMapLength();
        width = arena.getMapWidth();
        accessibilityMatrix = new boolean[width][length];
        landmarkMatrix = new int[width][length];
        adjacencyMatrix = new int[width][length];
        movementMatrix = new int[width][length];
        landmarks = new Vector<>();

        // Initialize matrices
        for (int i = 0; i < width; ++i)
            for (int j = 0; j < length; ++j)
                accessibilityMatrix[i][j] = arena.getGrid(i, j).isAccessible();
        for (int[] row : landmarkMatrix)
            Arrays.fill(row, -1);
        for (int[] row : adjacencyMatrix)
            Arrays.fill(row, Integer.MAX_VALUE);
        for (int[] row : movementMatrix)
            Arrays.fill(row, 0);

        // Generate visibility graph
        findLandmarks();
        calculateAdjacency();
    }

    private void findLandmarks() {
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

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int[][] getMovementMatrix() {
        return movementMatrix;
    }

    public Vector<Point> getLandmarks() {
        return landmarks;
    }

    public int getNumOfLandmarks() {
        return numOfLandmarks;
    }

    private void calculateAdjacency() {
        for (int u = 0; u < landmarks.size(); ++u) {
            int i = landmarks.elementAt(u).x;
            int j = landmarks.elementAt(u).y;
            boolean found;
            int k;

            // Check up
            found = false;
            for (k = i - 1; k >= 0 && !found; --k) {
                found = (landmarkMatrix[k][j] != -1);
            }
            if (found) {
                int v = landmarkMatrix[k][j];
                adjacencyMatrix[u][v] = i - k;
                movementMatrix[u][v] = 1;
            }

            // Check right
            found = false;
            for (k = j + 1; k < length && !found; ++k) {
                found = (landmarkMatrix[i][k] != -1);
            }
            if (found) {
                int v = landmarkMatrix[i][k];
                adjacencyMatrix[u][v] = k - j;
                movementMatrix[u][v] = 2;
            }

            // Check down
            found = false;
            for (k = i + 1; k < width && !found; ++k) {
                found = (landmarkMatrix[k][j] != -1);
            }
            if (found) {
                int v = landmarkMatrix[k][j];
                adjacencyMatrix[u][v] = k - i;
                movementMatrix[u][v] = 3;
            }

            // Check left
            found = false;
            for (k = j - 1; k >= 0 && !found; --k) {
                found = (landmarkMatrix[i][k] != -1);
            }
            if (found) {
                int v = landmarkMatrix[i][k];
                adjacencyMatrix[u][v] = j - k;
                movementMatrix[u][v] = 4;
            }
        }
    }
}

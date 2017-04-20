package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Holy on 20-Apr-17.
 */
public class GameView {
    private int scale;
    private int length;
    private int width;
    private char[][] asciiMap;
    private JLabel[][] map;
    private static final String MAP_PATH = "view/assets/map.txt";
    private static final int DEFAULT_SCALE = 30;

    public GameView() {
        scale = DEFAULT_SCALE;
        readMap();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setLayout(new GridLayout(length, width));
        map = new JLabel[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                setImageIcon(i, j);
                panel.add(map[i][j]);
            }
        }
        frame.add(panel);
        frame.setVisible(true);
    }

    public void readMap() {
        try {
            FileInputStream fstream;
            DataInputStream in;
            BufferedReader br;
            fstream = new FileInputStream(String.format("%s", MAP_PATH));
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
            String[] strSplit = br.readLine().split("\\s+");
            length = Integer.parseInt(strSplit[0]);
            width = Integer.parseInt(strSplit[1]);
            asciiMap = new char[length][width];
            String line = br.readLine();
            for (int row = 0; row < length; row++) {
                line = br.readLine();
                for (int col = 0; col < width; col++) {
                    asciiMap[row][col] = line.charAt(col);
                }
            }
            br.close();
            in.close();
            fstream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getImagePath(int row, int col) {
        String image_path = null;
        switch(asciiMap[row][col]) {
            case '-':
                image_path = "view/assets/wall.png";
                break;
            case 'o':
                image_path = "view/assets/cookie.png";
                break;
            case ' ':
                image_path = "view/assets/grid.png";
                break;
            case 'A':
                image_path = "view/assets/ghost_a.png";
                break;
            case 'B':
                image_path = "view/assets/ghost_b.png";
                break;
            case 'C':
                image_path = "view/assets/ghost_c.png";
                break;
            case 'D':
                image_path = "view/assets/ghost_d.png";
                break;
            case 'P':
                image_path = "view/assets/player.png";
                break;
            case 'S':
                image_path = "view/assets/super_cookie.png";
                break;
            default:
                break;
        }
        return image_path;
    }

    public void setImageIcon(int row, int col) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(getImagePath(row, col)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(bufferedImage);
        Image img = icon.getImage();
        Image new_img = bufferedImage.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
        icon = new ImageIcon(new_img);
        map[row][col] = new JLabel(icon);
    }

    public static void main(String[] args) {
        GameView gameView = new GameView();
    }
}

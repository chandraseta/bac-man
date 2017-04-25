package view;

import controller.BacMan;
import model.character.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Holy on 25-Apr-17.
 */
public class InfoPanel extends JComponent {
    public InfoPanel() {
        setPreferredSize(new Dimension(500, 950));
    }

    private Font getScreenFont() {
        Font scoreFont = null;
        try {
            scoreFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scoreFont;
    }

    private Image getBacbacImage(String image_path, int widthScale, int heightScale) {
        URL img_path = getClass().getResource(image_path);
        ImageIcon imageIcon = new ImageIcon(img_path);
        Image image = imageIcon.getImage();
        if(MapPanel.isGIF(image_path)) {
            return image;
        } else {
            return MapPanel.getScaledImage(image, widthScale, heightScale);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        int x = getWidth() / 10;
        int y = getHeight() / 9;
        g.drawImage(getBacbacImage("\\assets\\BacManLogo.png", 400, 100), x, y, this);

        x = getWidth() / 4;
        y = getHeight() / 3;
        if(Player.getStatus() == 1) {
            g.drawImage(getBacbacImage("\\assets\\super_bacbac.gif", 0, 0), x, y, this);
        } else {
            g.drawImage(getBacbacImage("\\assets\\hooray.gif", 0, 0), x, y, this);
        }

        x = getWidth() / 6;
        y = getHeight() * 3 / 4;
        g.setColor(Color.WHITE);
        g.setFont(getScreenFont().deriveFont(120f));
        String playerName;
        if(TitleView.playerName.length() > 6) {
          playerName = TitleView.playerName.substring(0, 6);
        } else {
            playerName = TitleView.playerName;
        }
        g.drawString("NAME: " + playerName.toUpperCase(), x, y);

        y = getHeight() * 4 / 5;
        g.drawString("SCORE: " + BacMan.score, x, y);

        g.dispose();
    }
}

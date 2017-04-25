package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Kelas GameOverView mendefinisikan tampilan program saat permainan telah berakhir.
 */
public class GameOverView extends JFrame {
    private JPanel gameOverPanel;

    /**
     * <p>
     * Constructor
     *
     * Menciptakan objek GameOverView sebagai tampilan program saat permainan berakhir.
     * </p>
     */
    public GameOverView() {
        setTitle("Game Over");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameOverPanel = new JPanel();
        gameOverPanel.setPreferredSize(new Dimension(1600, 950));
        gameOverPanel.setBackground(Color.BLACK);
        GridBagLayout gameOverLayout = new GridBagLayout();
        gameOverPanel.setLayout(gameOverLayout);
        GridBagConstraints gameOverConstraints = new GridBagConstraints();
        gameOverConstraints.fill = GridBagConstraints.BOTH;
        gameOverPanel.add(createGameOverBacBac(gameOverLayout, gameOverConstraints));
        gameOverPanel.add(createGameOverText(gameOverLayout, gameOverConstraints));

        add(gameOverPanel);
        pack();
    }

    /**
     * Fungsi mengembalikan sebuah sprite yang ditampilkan dalam window GameOver.
     *
     * @param gameOverLayout Layout untuk sprite.
     * @param gameOverConstraints
     * @return Sprite pada window.
     */
    public JLabel createGameOverBacBac(GridBagLayout gameOverLayout, GridBagConstraints gameOverConstraints) {
        gameOverConstraints.gridx = 0;
        gameOverConstraints.gridy = 0;
        URL gameover_bacbac_path = getClass().getResource("\\assets\\loading.gif");
        JLabel gameOverBacbac = new JLabel(new ImageIcon(gameover_bacbac_path), JLabel.CENTER);
        gameOverBacbac.setBorder(new EmptyBorder(10, 10, 40, 10));
        gameOverLayout.setConstraints(gameOverBacbac, gameOverConstraints);
        return gameOverBacbac;
    }

    public JLabel createGameOverText(GridBagLayout gameOverLayout, GridBagConstraints gameOverConstraints) {
        gameOverConstraints.gridx = 0;
        gameOverConstraints.gridy = 1;
        Font gameOverFont = null;
        try {
            gameOverFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel gameOverText = new JLabel("GAME OVER", JLabel.CENTER);
        gameOverText.setBorder(new EmptyBorder(20, 10, 10, 10));
        gameOverText.setFont(gameOverFont.deriveFont(400f));
        gameOverText.setForeground(Color.WHITE);
        gameOverLayout.setConstraints(gameOverText, gameOverConstraints);
        return gameOverText;
    }

    public static void main(String[] args) {
        GameOverView gameOverView = new GameOverView();
        gameOverView.setVisible(true);
    }
}

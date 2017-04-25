package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * Created by Holy on 25-Apr-17.
 */
public class GameOverView extends JFrame {
    private JPanel gameOverPanel;

    public GameOverView() {
        setTitle("Game Over");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameOverPanel = new JPanel();
        gameOverPanel.setBackground(Color.BLACK);
        GridBagLayout gameOverLayout = new GridBagLayout();
        gameOverPanel.setLayout(gameOverLayout);
        GridBagConstraints gameOverConstraints = new GridBagConstraints();
        gameOverConstraints.fill = GridBagConstraints.BOTH;

        add(gameOverPanel);
        pack();
    }

    public JLabel createGameOverBacBac(GridBagLayout gameOverLayout, GridBagConstraints gameOverConstraints) {
        gameOverConstraints.gridx = 0;
        gameOverConstraints.gridy = 0;
        URL gameover_bacbac_path = getClass().getResource("\\assets\\loading.gif");
        JLabel gameOverBacbac = new JLabel(new ImageIcon(gameover_bacbac_path), JLabel.CENTER);
        gameOverBacbac.setBorder(new EmptyBorder(10, 10, 40, 10));
        gameOverLayout.setConstraints(gameOverBacbac, gameOverConstraints);
        return gameOverBacbac;
    }
}

package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * Created by Holy on 24-Apr-17.
 */
public class LoadingView extends JFrame {
    protected JPanel loadingPanel;

    public LoadingView() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadingPanel = new JPanel();
        loadingPanel.setPreferredSize(new Dimension(1600, 950));
        loadingPanel.setBackground(new Color(0, 0, 0));
        GridBagLayout loadingLayout = new GridBagLayout();
        loadingPanel.setLayout(loadingLayout);
        GridBagConstraints loadingConstraints = new GridBagConstraints();
        loadingConstraints.fill = GridBagConstraints.BOTH;

        loadingConstraints.gridx = 0;
        loadingConstraints.gridy = 1;
        URL sleeping_bacbac_path = getClass().getResource("\\assets\\loading.gif");
        JLabel sleepingBacbac = new JLabel(new ImageIcon(sleeping_bacbac_path), JLabel.CENTER);
        sleepingBacbac.setBorder(new EmptyBorder(10, 10, 40, 10));
        loadingLayout.setConstraints(sleepingBacbac, loadingConstraints);
        loadingPanel.add(sleepingBacbac);

        loadingConstraints.gridx = 0;
        loadingConstraints.gridy = 0;
        JLabel loadingText = new JLabel("Loading...", JLabel.CENTER);
        loadingText.setFont(new Font("Arial", Font.CENTER_BASELINE, 100));
        loadingText.setForeground(Color.WHITE);
        loadingText.setBorder(new EmptyBorder(90, 10, 10, 10));
        loadingLayout.setConstraints(loadingText, loadingConstraints);
        loadingPanel.add(loadingText);

        loadingConstraints.gridx = 0;
        loadingConstraints.gridy = 2;
        URL loading_balls_path = getClass().getResource("\\assets\\loading-bar.gif");
        JLabel loadingBalls = new JLabel(new ImageIcon(loading_balls_path), JLabel.CENTER);
        loadingBalls.setBorder(new EmptyBorder(30, 10, 10, 10));
        loadingLayout.setConstraints(loadingBalls, loadingConstraints);
        loadingPanel.add(loadingBalls);
        add(loadingPanel);
        pack();
    }
}
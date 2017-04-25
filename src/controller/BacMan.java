package controller;

import model.Arena;
import model.character.Blinky;
import model.character.Clyde;
import model.character.Inky;
import model.character.Pinky;
import view.GameView;
import view.LoadingView;
import view.TitleView;

/**
 * Kelas berisi semua aspek game BacMan.
 *
 * @author
 */
public class BacMan {

    /**
     * Tampilan permainan BacMan.
     */
    private GameView gameView;

    /**
     * Controller untuk objek Player pada permainan.
     */
    private PlayerController bac;

    /**
     * Arena permainan BacMan.
     */
    private static Arena gameArena;

    /**
     * Controller untuk objek Ghost pada permainan.
     */
    private GhostController[] ghost;

    /**
     * Score Player.
     */
    public static int score;

    /**
     * Penanda permainan sudah berakhir atau belum.
     */
    private static boolean gameEnd;

    /**
     * Mengembalikan score Player.
     *
     * @return score yang didapat Player.
     */
    public static int getScore() { return score; }

    /**
     * Mengembalikan apakah permainan berakhir atau tidak.
     *
     * @return gameEnd permainan sudah berakhir atau belum.
     */
    public static boolean isGameEnd() { return gameEnd; }

    /**
     * Menambahkan score Player sejumlah value.
     *
     * @param value nilai yang ditambahkan ke score Player.
     */
    public static void addScore(int value) {
        score += value;
    }

    /**
     * Program utama.
     *
     * @param args Command line argument.
     */
    public static void main(String[] args) {
        gameEnd = false;
        score = 0;
        gameArena = new Arena();
        PlayerController bacman = new PlayerController();
        GhostController[] ghostList = new GhostController[4];
        ghostList[0] = new GhostController('a');
        ghostList[1] = new GhostController('b');
        ghostList[2] = new GhostController('c');
        ghostList[3] = new GhostController('d');
        TitleView titleView = new TitleView();
        while (TitleView.visibility) {
            System.out.println();
        }
        LoadingView loadingView = new LoadingView();
        loadingView.setVisible(true);
        GameView gameView = new GameView(bacman, (Blinky) ghostList[0].getGhost(),
                (Inky) ghostList[2].getGhost(),
                (Pinky) ghostList[1].getGhost(), (Clyde) ghostList[3].getGhost());
        loadingView.setVisible(false);
        gameView.setVisible(true);
        bacman.start();
        gameView.start();
        for (int i = 0; i < 4; i++) {
            ghostList[i].start();
        }
        while (!BacMan.isGameEnd()) {
            bacman.run();
            for (int i = 0; i < 4; i++) {
                ghostList[i].run();
            }
            gameView.run();
            gameEnd = (Arena.getCookieLeft() == 0);
        }
    }
}

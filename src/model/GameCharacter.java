package model;

import java.awt.Point;

/**
 * Kelas abstrak GameCharacter sebagai dasar dari semua karakter dalam game.
 *
 * @author
 * @version
 * @since
 */
public class GameCharacter extends GameElement {

    /**
     * Posisi karakter dalam permainan.
     */
    protected Point position;

    /**
     * <p>
     * Constructor
     *
     * Menciptakan sebuah karakter pada permainan.
     * </p>
     *
     * @param i Posisi y karakter.
     * @param j Posisi x karakter.
     * @param imgPath Path direktori gambar karakter.
     */
    public GameCharacter(int i, int j, String imgPath) {
        super(imgPath);
        position = new Point(i, j);
    }

    /**
     * Method pada karakter agar dapat melakukan teleport langsung ke suatu titik.
     *
     * @param i Posisi y pada arena.
     * @param j Posisi x pada arena.
     */
    public void teleport(int i, int j) {
        position.setLocation(i, j);
    }

    /**
     * Getter posisi y karakter.
     *
     * @return posisi y karakter.
     */
    public int getI() {
        return (int) position.getX();
    }

    /**
     * Getter posisi x karakter.
     *
     * @return posisi x karakter.
     */
    public int getJ() {
        return (int) position.getY();
    }

    /**
     * Method untuk menggerakkan karakter 1 unit panjang ke atas.
     */
    public void moveUp() {
        position.setLocation(getI() - 1, getJ());
    }

    /**
     * Method untuk menggerakkan karakter 1 unit panjang ke kanan.
     */
    public void moveRight() {
        position.setLocation(getI(), getJ() + 1);
    }

    /**
     * Method untuk menggerakkan karakter 1 unit panjang ke bawah.
     */
    public void moveDown() {
        position.setLocation(getI() + 1, getJ());
    }

    /**
     * Method untuk menggerakkan karakter 1 unit panjang ke kiri.
     */
    public void moveLeft() {
        position.setLocation(getI(), getJ() - 1);
    }
}

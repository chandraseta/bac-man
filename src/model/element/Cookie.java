package model.element;

import model.GameElement;

/**
 * Kelas berisi Cookie yang tersebar pada Grid dalam Arena.
 */
public class Cookie extends GameElement {

    /**
     * Atribut Cookie ada pada Arena atau tidak.
     */
    protected boolean available;

    /**
     * Atribut nilai Cookie yang akan ditambahkan ke score Player.
     */
    protected int value;

    /**
     * <p>
     * Constructor
     * <p>
     * Menciptakan Cookie dalam permainan.
     * </p>
     */
    public Cookie() {
        super("\\assets\\cookie.png");
        available = true;
        value = 10;
    }

    /**
     * <p>
     * Constructor
     * <p>
     * Menciptakan Cookie dengan parameter available.
     * </p>
     *
     * @param available Cookie dapat dimakan atau tidak oleh Player.
     */
    public Cookie(boolean available) {
        super("\\assets\\cookie.png");
        this.available = available;
        value = 10;
    }

    /**
     * <p>
     * Constructor
     * <p>
     * Menciptakan Cookie dengan parameter sprite dan value.
     * </p>
     *
     * @param sprite Path direktori sprite Cookie.
     * @param value  Nilai Cookie yang akan ditambahkan ke score Player apabila Cookie tersebut dimakan.
     */
    public Cookie(String sprite, int value) {
        super(sprite);
        available = true;
        this.value = value;
    }

    /**
     * Fungsi mengembalikan Cookie dapat dimakan atau tidak.
     *
     * @return Cookie dapat dimakan atau tidak.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Fungsi mengembalikan nilai Cookie.
     *
     * @return Nilai Cookie.
     */
    public int getValue() {
        return value;
    }

    /**
     * Menghapus Cookie pada arena.
     * Cookie dihapus dengan mengubah nilai atribut available.
     */
    public void remove() {
        available = false;
    }

    /**
     * Memasang value pada Cookie.
     *
     * @param value Nilai Cookie.
     */
    public void setValue(int value) {
        this.value = value;
    }
}

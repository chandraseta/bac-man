package model.element;

import model.GameElement;

/**
 * Kelas berisi Grid yang merupakan komponen dari Arena.
 */
public class Grid extends GameElement {

    /**
     * Objek Cookie pada grid.
     */
    private Cookie cookie;

    /**
     * Grid dapat diakses atau tidak oleh karakter.
     */
    private boolean accessible;

    /**
     * <p>
     * Constructor
     *
     * Menciptakan Grid yang tidak memiliki Cookie.
     * </p>
     */
    public Grid() {
        super("\\assets\\grid.png");
        cookie = new Cookie();
        removeCookie();
        accessible = true;
    }

    /**
     * <p>
     * Constructor
     *
     * Menciptakan Grid dengan parameter containCookie.
     * </p>
     *
     * @param containCookie Cookie ada pada Grid atau tidak.
     */
    public Grid(boolean containCookie) {
        super("\\assets\\cookie.png");
        cookie = new Cookie();
        if (!containCookie) {
            removeCookie();
        }
        accessible = true;
    }

    /**
     * <p>
     * Constructor
     *
     * Menciptakan Grid dengan parameter containCookie dan isSuperCookie.
     * </p>
     *
     * @param containCookie Cookie ada pada Grid atau tidak.
     * @param isSuperCookie Cookie merupakan SuperCookie atau tidak.
     */
    public Grid(boolean containCookie, boolean isSuperCookie) {
        super("\\assets\\super_cookie.gif");
        if (isSuperCookie) {
            cookie = new SuperCookie();
        } else {
            cookie = new Cookie();
        }
        if (!containCookie) {
            removeCookie();
        }
        accessible = true;
    }

    /**
     * <p>
     * Constructor
     *
     * Menciptakan Grid dengan parameter sprite dan accessible.
     * </p>
     *
     * @param sprite     Path direktori sprite.
     * @param accessible Grid dapat diakses oleh karakter atau tidak.
     */
    public Grid(String sprite, boolean accessible) {
        super(sprite);
        this.accessible = accessible;
    }

    /**
     * Fungsi mengembalikan objek Cookie.
     *
     * @return Cookie pada Grid.
     */
    public Cookie getCookie() {
        return cookie;
    }

    /**
     * Fungsi mengembalikan pernyataan Grid dapat diakses oleh karakter atau tidak.
     *
     * @return accessible Pernyataan Grid dapat diakses oleh karakter.
     */
    public boolean isAccessible() {
        return accessible;
    }

    /**
     * Menghapus Cookie pada Grid.
     */
    public void removeCookie() {
        cookie.remove();
        setNewImage("\\assets\\grid.png");
    }
}

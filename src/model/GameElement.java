package model;

/**
 * Kelas abstrak GameElement berisi dasar dari semua elemen permainan.
 */
public abstract class GameElement {

  /**
   * Path direktori sprite untuk setiap element permainan yang ada.
   */
  private String imgPath;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan GameElement sebagai dasar dari semua elemen permainan.
   * </p>
   *
   * @param imgPath Path direktori sprite elemen permainan.
   */
  public GameElement(String imgPath) {
    this.imgPath = imgPath;
  }

  /**
   * Melakukan set direktori baru untuk GameElement.
   *
   * @param newImg path direktori baru GameElement.
   */
  public void setNewImage(String newImg) {
    imgPath = newImg;
  }

  /**
   * Fungsi mengembalikan path direktori GameElement.
   *
   * @return Path direktori GameElement.
   */
  public String getImgPath() {
    return imgPath;
  }
}
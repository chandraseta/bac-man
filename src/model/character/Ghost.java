package model.character;

import model.GameCharacter;

/**
 * Kelas abstract Ghost yang menjadi dasar berbagai tipe Ghost.
 */
public abstract class Ghost extends GameCharacter {

  /**
   * Berisi informasi status Ghost
   * 0 = Normal
   * 1 = Vulnerable
   * 2 = Dead
   */
  protected int state;

  public Ghost(int x, int y, String sprite) {
    super(x, y, sprite);
    state = 0;
  }

  /**
   * Setter nilai state.
   * PREKONDISI: _state [0..2]
   *
   * @param _state Nilai state baru.
   */
  public void setState(int _state) {
    state = _state;
  }

  public boolean isNormal() {
    return state == 0;
  }

  public boolean isVulnerable() {
    return state == 1;
  }

  public boolean isDead() {
    return state == 2;
  }

  /**
   * Menggerakkan Ghost menuju Player saat kondisi normal.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost pada status normal.
   */
  public abstract int moveTowardsPlayer();

  /**
   * Menggerakkan Ghost menjauhi Player saat Player dalam mode Super.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost pada status vulnerable.
   */
  public int moveAwayFromPlayer() {
    // TODO: IMPLEMENT
  }

  /**
   * Menggerakkan Ghost menuju Base setelah dimakan oleh Player.
   *
   * @return Nilai integer yang menentukan arah gerak Ghost pada status dead.
   */
  public int returnToBase() {
    // TODO: IMPLEMENT
  }
}

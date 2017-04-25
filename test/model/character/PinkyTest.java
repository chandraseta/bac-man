package model.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import model.Arena;
import org.junit.Before;
import org.junit.Test;

/**
 * Kelas PinkyTest mengaplikasikan unit test pada kelas Pinky.
 */
public class PinkyTest {

  private Pinky pinky;
  private Player player;
  private Arena arena;

  /**
   * <p>
   * Before
   *
   * Membuat objek Pinky dan Arena untuk menjalani test.
   * </p>
   */
  @Before
  public void PinkySetUp() {
    arena = new Arena();
    player = new Player();
    pinky = new Pinky();
  }

  /**
   * Saat inisialisasi, Pinky berada pada ordinat 12 dan absis 11.
   */
  @Test
  public void matchPosition() {
    assertEquals("Ordinate doesn't match", 8, pinky.getI());
    assertEquals("Absis doesn't match", 11, pinky.getJ());
  }

  /**
   * Pergerakan Pinky sesuai dengan yang diharapkan.
   */
  @Test
  public void matchPinkyMovement() {
    pinky.moveLeft();
    assertEquals("Wrong movement", 10, pinky.getJ());
    pinky.moveRight();
    assertEquals("Wrong movement", 11, pinky.getJ());
    pinky.moveRight();
    pinky.moveUp();
    pinky.moveUp();
    assertEquals("Wrong movement", 6, pinky.getI());
    pinky.moveDown();
    assertEquals("Wrong movement", 7, pinky.getI());
  }

  /**
   * Status pada Pinky sesuai skenario.
   */
  @Test
  public void matchState() {
    assertTrue("Wrong state",pinky.isNormal());
  }
}

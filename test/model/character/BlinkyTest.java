package model.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import model.Arena;
import org.junit.Before;
import org.junit.Test;

/**
 * Kelas BlinkyTest mengaplikasikan unit test pada kelas Blinky.
 */
public class BlinkyTest {

  private Blinky blinky;
  private Player player;
  private Arena arena;

  /**
   * <p>
   * Before
   *
   * Membuat objek Blinky dan Arena untuk menjalani test.
   * </p>
   */
  @Before
  public void BlinkySetUp() {
    arena = new Arena();
    player = new Player();
    blinky = new Blinky();
  }

  /**
   * Saat inisialisasi, Blinky berada pada ordinat 7 dan absis 12.
   */
  @Test
  public void matchPosition() {
    assertEquals("Ordinate doesn't match", 7, blinky.getI());
    assertEquals("Absis doesn't match", 12, blinky.getJ());
  }

  /**
   * Pergerakan Blinky sesuai dengan yang diharapkan.
   */
  @Test
  public void matchBlinkyMovement() {
    blinky.moveUp();
    blinky.moveUp();
    assertEquals("Wrong movement", 5, blinky.getI());
    blinky.moveDown();
    assertEquals("Wrong movement", 6, blinky.getI());
    blinky.moveLeft();
    assertEquals("Wrong movement", 11, blinky.getJ());
    blinky.moveRight();
    assertEquals("Wrong movement", 12, blinky.getJ());
  }

  /**
   * Status pada Blinky sesuai skenario.
   */
  @Test
  public void matchState() {
    assertTrue("Wrong state", blinky.isNormal());
  }
}

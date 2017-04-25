package model.character;

import static org.junit.Assert.assertEquals;

import model.Arena;
import org.junit.Before;
import org.junit.Test;

/**
 * Kelas InkyTest mengaplikasikan unit test pada kelas Inky.
 */
public class InkyTest {

  private Inky inky;
  private Player player;
  private Arena arena;

  /**
   * <p>
   * Before
   *
   * Membuat objek Inky dan Arena untuk menjalani test.
   * </p>
   */
  @Before
  public void InkySetUp() {
    arena = new Arena();
    player = new Player();
    inky = new Inky();
  }

  /**
   * Saat inisialisasi, Inky berada pada ordinat 12 dan absis 12.
   */
  @Test
  public void matchPosition() {
    assertEquals("Ordinate doesn't match", 12, inky.getI());
    assertEquals("Absis doesn't match", 12, inky.getJ());
  }

  /**
   * Pergerakan Inky sesuai dengan yang diharapkan.
   */
  @Test
  public void matchInkyMovement() {
    inky.moveUp();
    inky.moveUp();
    assertEquals("Wrong movement", 10, inky.getI());
    inky.moveDown();
    inky.moveDown();
    assertEquals("Wrong movement", 12, inky.getI());
    inky.moveLeft();
    assertEquals("Wrong movement", 11, inky.getJ());
    inky.moveRight();
    assertEquals("Wrong movement", 12, inky.getJ());
  }
}

package model.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import model.Arena;
import org.junit.Before;
import org.junit.Test;

/**
 * Kelas ClydeTest mengaplikasikan unit test pada kelas Clyde.
 */
public class ClydeTest {

  private Clyde clyde;
  private Player player;
  private Arena arena;

  /**
   * <p>
   * Before
   *
   * Membuat objek Clyde dan Arena untuk menjalani test.
   * </p>
   */
  @Before
  public void ClydeSetUp() {
    arena = new Arena();
    player = new Player();
    clyde = new Clyde();
  }

  /**
   * Saat inisialisasi, Clyde berada pada ordinat 8 dan absis 13.
   */
  @Test
  public void matchPosition() {
    assertEquals("Ordinate doesn't match", 8, clyde.getI());
    assertEquals("Absis doesn't match", 13, clyde.getJ());
  }

  /**
   * Pergerakan Inky sesuai dengan yang diharapkan.
   */
  @Test
  public void matchInkyMovement() {
    clyde.moveLeft();
    assertEquals("Wrong movement", 12, clyde.getJ());
    clyde.moveRight();
    assertEquals("Wrong movement", 13, clyde.getJ());
    clyde.moveLeft();
    clyde.moveUp();
    clyde.moveUp();
    assertEquals("Wrong movement", 6, clyde.getI());
    clyde.moveDown();
    assertEquals("Wrong movement", 7, clyde.getI());
  }

  /**
   * Status pada Clyde sesuai skenario.
   */
  @Test
  public void matchState() {
    assertTrue("Wrong state",clyde.isNormal());
  }
}

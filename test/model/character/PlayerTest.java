package model.character;

import static org.junit.Assert.assertEquals;

import model.Arena;
import org.junit.Before;
import org.junit.Test;

/**
 * Kelas PlayerTest mengaplikasikan unit test pada kelas Player.
 */
public class PlayerTest {

  private Player player;
  private Arena arena;

  /**
   * <p>
   * Before
   *
   * Membuat objek Player dan Arena untuk menjalani test.
   * </p>
   */
  @Before
  public void PlayerSetUp() {
    arena = new Arena();
    player = new Player();
  }

  /**
   * Saat inisialisasi, Player berada pada ordinat 20 dan absis 12.
   */
  @Test
  public void matchPosition() {
    assertEquals("Ordinate doesn't match", 20, player.getI());
    assertEquals("Absis doesn't match", 12, player.getJ());
  }

  /**
   * Pergerakan Player sesuai dengan yang diharapkan.
   */
  @Test
  public void matchPlayerMovementAndOrientation() {
    player.moveLeft();
    assertEquals("Wrong movement", 11, player.getJ());
    player.moveRight();
    assertEquals("Wrong movement", 12, player.getJ());
    player.moveUp();
    assertEquals("Wrong movement", 19, player.getI());
    player.moveDown();
    assertEquals("Wrong movement", 20, player.getI());
  }

  /**
   * Status Player sesuai skenario.
   */
  @Test
  public void matchStatus() {
    assertEquals("Status doesn't match", 0, player.getStatus());
  }

}

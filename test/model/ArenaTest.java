package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Kelas ArenaTest mengaplikasikan unit test pada kelas Arena.
 */
public class ArenaTest {

  private Arena arena;

  /**
   * <p>
   * Before
   *
   * Membuat objek Arena untuk menjalani test.
   * </p>
   */
  @Before
  public void ArenaSetUp() {
    arena = new Arena();
  }

  /**
   * Arena yang diciptakan sesuai dengan map pada file eksternal.
   */
  @Test
  public void matchArena() {
    assertEquals("Arena length doesn't match", 25, arena.getMapLength());
    assertEquals("Arena width doesn't match", 28, arena.getMapWidth());
    assertEquals("Cookie's in arena doesn't match", 244, arena.getCookieLeft());
    assertEquals("Tunnel row in arena doesn't match", 12, arena.getTunnelRow());
  }
}

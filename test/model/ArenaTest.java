package model;

import static org.junit.Assert.assertEquals;

import model.element.Cookie;
import org.junit.Before;
import org.junit.Test;

/**
 * Kelas ArenaTest mengaplikasikan unit test pada kelas Arena.
 */
public class ArenaTest {

  private Arena arena;
  private Cookie cookie;

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
    assertEquals("Arena width doesn't match", 19, arena.getMapWidth());
    assertEquals("Cookie's in arena doesn't match", 204, arena.getCookieLeft());
    assertEquals("Tunnel row in arena doesn't match", 8, arena.getTunnelRow());
  }

  /**
   * Setiap Cookie pada Arena sesuai dengan atribut yang semestinya.
   */
  @Test
  public void matchCookieInArena() {
    cookie = new Cookie();
    for (int i = 0; i < arena.getMapWidth(); ++i) {
      for (int j = 0; j < arena.getMapLength(); ++j) {
        if ((arena.getGrid(i,j).isAccessible() == true)) {
          arena.getGrid(i,j).removeCookie();
        }
      }
    }
    assertEquals("Cookie's in arena doesn't match", 4, arena.getCookieLeft());
  }
}

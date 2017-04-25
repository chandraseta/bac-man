package model.element;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

/**
 * Kelas WallTest mengaplikasikan unit test pada kelas Wall.
 */
public class WallTest {

  private Wall wall;

  /**
   * <p>
   * Before
   *
   * Membuat objek Wall untuk menjalani test.
   * </p>
   */
  @Before
  public void setUpCookie() {
    wall = new Wall();
  }

  /**
   * Wall tidak dapat diakses.
   */
  @Test
  public void matchAccessible() {
    assertFalse("Characters can go through wall", wall.isAccessible());
  }
}

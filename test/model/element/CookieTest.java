package model.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Kelas CookieTest mengaplikasikan unit test pada kelas Cookie.
 */
public class CookieTest {

  private Cookie cookie;

  /**
   * <p>
   * Before
   *
   * Membuat objek Cookie untuk menjalani test.
   * </p>
   */
  @Before
  public void setUpCookie() {
    cookie = new Cookie();
  }

  /**
   * Value pada Cookie seharusnya bernilai 10.
   */
  @Test
  public void matchValue() {
    assertEquals("Value doesn't match",10,cookie.getValue());
  }

  /**
   * Saat penciptaan objek Cookie (konstruktor tanpa parameter), Cookie dapat diambil Player.
   * Saat melakukan remove Cookie, available = false
   */
  @Test
  public void matchAvailability() {
    assertTrue("Cookie isn't available", cookie.isAvailable());
    cookie.remove();
    assertFalse("Method doesn't work", cookie.isAvailable());
  }

  /**
   * Setter bekerja sesuai prosedur kerjanya.
   */
  @Test
  public void testSetter() {
    cookie.setValue(99);
    assertEquals("Wrong value",99,cookie.getValue());
  }
}

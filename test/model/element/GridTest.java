package model.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Kelas GridTest mengaplikasikan unit test pada kelas Grid.
 */
public class GridTest {

  private Grid grid;

  /**
   * <p>
   * Before
   *
   * Membuat objek Grid untuk menjalani test.
   * </p>
   */
  @Before
  public void setUpGrid() {
    grid = new Grid();
  }

  /**
   * Saat penciptaan Grid, Grid dapat diakses dan tidak terdapat Cookie.
   */
  @Test
  public void matcAttributes() {
    assertTrue("Grid isn't accessible",grid.isAccessible());
    assertFalse("There's a Cookie",grid.getCookie().isAvailable());
  }

  /**
   * Uji penciptaan Grid dengan parameter.
   */
  @Test
  public void matchAttributes2() {
    Grid grid2 = new Grid(true,true);
    assertEquals("Wrong value",100,grid2.getCookie().getValue());
    Grid grid3 = new Grid(true,false);
    assertEquals("Wrong value",10,grid3.getCookie().getValue());
  }
}

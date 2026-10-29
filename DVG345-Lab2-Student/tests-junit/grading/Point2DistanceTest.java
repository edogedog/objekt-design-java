package grading;
import geometry.Point2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class Point2DistanceTest
  {
  @Test @DisplayName("Point2.distance: euklidiskt avstånd i båda koordinatleden")
  void euclidean()
    {
    var a = new Point2(-2, 7);
    var b = new Point2(1, 3);
    assertEquals(5, a.distance(b), 1e-12);
    assertEquals(5, b.distance(a), 1e-12);
    assertEquals(Math.sqrt(2), new Point2(0, 0).distance(new Point2(1, 1)), 1e-12);
    assertEquals(8, a.distance(new Point2(6, 7)), 1e-12);
    assertEquals(6, a.distance(new Point2(-2, 1)), 1e-12);
    }

  @Test @DisplayName("Point2.distance: samma punkt och lika punktvärden ger noll")
  void zero()
    {
    var a = new Point2(2, -3);
    assertEquals(0, a.distance(a), 0);
    assertEquals(0, a.distance(new Point2(2, -3)), 0);
    }

  @Test @DisplayName("Point2.distance: ändrar inget punktvärde och avvisar null")
  void immutableAndNull()
    {
    var a = new Point2(2, -3);
    var b = new Point2(-1, 1);
    a.distance(b);
    assertEquals(new Point2(2, -3), a);
    assertEquals(new Point2(-1, 1), b);
    assertThrows(NullPointerException.class, () -> a.distance(null));
    }

  @Test @DisplayName("Point2.distance: stora och små representerbara avstånd")
  void magnitude()
    {
    var origin = new Point2(0, 0);
    assertEquals(5e200, origin.distance(new Point2(3e200, 4e200)), 1e186);
    assertEquals(5e-200, origin.distance(new Point2(3e-200, 4e-200)), 1e-214);
    assertEquals(Double.POSITIVE_INFINITY,
        new Point2(-Double.MAX_VALUE, 0).distance(new Point2(Double.MAX_VALUE, 0)));
    }
  }

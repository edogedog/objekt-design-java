package grading;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import figures.*;
import geometry.Point2;
import java.util.List;

@Timeout(5)
public class RectangleTest
  {
  @Test @DisplayName("Rektangel: null-hörn avvisas")
  void nullCorner()
    {
    assertThrows(NullPointerException.class, () -> new Rectangle(null, 2, 3));
    }

  @Test @DisplayName("Rektangel: fyra korrekta sidor, valfri riktning och ordning")
  void rectangleGeometry()
    {
    var a = new Point2(10, 20);
    var b = new Point2(40, 20);
    var c = new Point2(40, 60);
    var d = new Point2(10, 60);
    Support.assertDrawing(new Rectangle(a, 30, 40), List.of(
        new Support.Stroke(a, b), new Support.Stroke(b, c),
        new Support.Stroke(c, d), new Support.Stroke(d, a)));
    }

  @Test @DisplayName("Rektangel: ogiltiga mått avvisas")
  void invalidDimensions()
    {
    for (double value : new double[] {0, -1, Double.NaN, Double.POSITIVE_INFINITY})
      {
      assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Point2(1, 2), value, 3));
      assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Point2(1, 2), 3, value));
      }
    }

  @Test @DisplayName("Rektangel: hörn med överflöd avvisas")
  void overflow()
    {
    assertThrows(IllegalArgumentException.class,
        () -> new Rectangle(new Point2(Double.MAX_VALUE, 0), Double.MAX_VALUE, 1));
    }

  @Test @DisplayName("Rektangel: upprepad ritning ändrar inte geometrin")
  void rectangleDrawIsStable()
    {
    var rectangle = new Rectangle(new Point2(2, 3), 4, 5);
    var before = Support.drawing(rectangle);
    Support.assertDrawing(rectangle, before);
    }

  }

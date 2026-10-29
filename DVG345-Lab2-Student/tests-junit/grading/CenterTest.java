package grading;
import figures.*;
import geometry.Point2;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(5)
public class CenterTest
  {
  @Test @DisplayName("Figure är Drawable och getCenter är en fråga som returnerar Point2")
  void contract() throws Exception
    {
    assertTrue(Drawable.class.isAssignableFrom(Figure.class));
    var draw = Drawable.class.getMethod("draw", rendering.DrawingContext.class);
    assertEquals(void.class, draw.getReturnType());
    assertTrue(java.lang.reflect.Modifier.isAbstract(draw.getModifiers()));
    assertFalse(draw.isDefault());
    assertDoesNotThrow(() -> assertEquals(Point2.class, Figure.class.getMethod("getCenter").getReturnType()));
    }

  @Test @DisplayName("Härlett centrum för linje, cirkel, rektangel och kvadrat")
  void centers()
    {
    assertEquals(new Point2(6, 10), new Line(new Point2(2, 4), new Point2(10, 16)).getCenter());
    assertEquals(new Point2(2, 4), new Circle(new Point2(2, 4), 8).getCenter());
    assertEquals(new Point2(5, 9), new Rectangle(new Point2(2, 4), 6, 10).getCenter());
    assertEquals(new Point2(5, 7), new Square(new Point2(2, 4), 6).getCenter());
    }

  @Test @DisplayName("getCenter ändrar inte figuren eller dess ritning")
  void centerIsQuery()
    {
    for (Figure f : List.of(new Line(new Point2(2, 4), new Point2(10, 16)),
        new Circle(new Point2(2, 4), 8), new Rectangle(new Point2(2, 4), 6, 10),
        new Square(new Point2(2, 4), 6)))
      {
      var before = Support.drawing(f);
      var center = f.getCenter();
      assertEquals(center, f.getCenter());
      assertTrue(Double.isFinite(center.x()) && Double.isFinite(center.y()));
      Support.assertDrawing(f, before);
      }
    }

  @Test @DisplayName("Mittpunkt med stora ändliga koordinater får inte ge additionsöverflöd")
  void largeCenter()
    {
    var p = new Point2(Double.MAX_VALUE, Double.MAX_VALUE);
    assertEquals(p, new Line(p, p).getCenter());
    }
  }

package grading;
import figures.Figure;
import geometry.Point2;
import rendering.DrawingContext;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;

/** Testdubbel för geometri och kontroll av det uttryckliga inkapslingskravet.
 * Inga antaganden om fältnamn, fälttyper eller intern representation.
 */
final class Support implements DrawingContext
  {
  record Stroke(Point2 start, Point2 end)
    {
    }
  record Ring(Point2 center, double radius)
    {
    }
  private final List<Object> operations = new ArrayList<>();

  static void assertPrivateInstanceFields(Class<?> implementation)
    {
    for (Class<?> type = implementation; type != null && type != Object.class;
        type = type.getSuperclass())
      for (var field : type.getDeclaredFields())
        {
        int modifiers = field.getModifiers();
        if (field.isSynthetic() || Modifier.isStatic(modifiers))
          continue;
        assertTrue(Modifier.isPrivate(modifiers),
            "Inkapsling: " + type.getSimpleName() + "." + field.getName()
                + " ska vara privat, även om fältet är final.");
        }
    }

  public void line(Point2 start, Point2 end)
    {
    operations.add(new Stroke(start, end));
    }

  public void circle(Point2 center, double radius)
    {
    operations.add(new Ring(center, radius));
    }

  static List<Object> drawing(Figure figure)
    {
    var port = new Support();
    figure.draw(port);
    return List.copyOf(port.operations);
    }

  private static boolean near(double a, double b)
    {
    return Double.isFinite(a) && Double.isFinite(b) && Math.abs(a - b) <= 1e-8;
    }

  private static boolean near(Point2 a, Point2 b)
    {
    return near(a.x(), b.x()) && near(a.y(), b.y());
    }

  private static boolean same(Object a, Object b)
    {
    if (a instanceof Ring x && b instanceof Ring y)
      return near(x.center(), y.center()) && near(x.radius(), y.radius());
    if (a instanceof Stroke x && b instanceof Stroke y)
      return (near(x.start(), y.start()) && near(x.end(), y.end()))
          || (near(x.start(), y.end()) && near(x.end(), y.start()));
    return false;
    }

  static void assertDrawing(Figure figure, List<Object> expected)
    {
    var actual = new ArrayList<>(drawing(figure));
    assertEquals(expected.size(), actual.size(), "Fel antal ritprimitiv");
    for (Object wanted : expected)
      {
      int index = -1;
      for (int i = 0; i < actual.size(); i++)
        if (same(wanted, actual.get(i)))
          {
          index = i;
          break;
          }
      assertTrue(index >= 0, "Saknad geometri: " + wanted + "; återstår: " + actual);
      actual.remove(index);
      }
    }
  }

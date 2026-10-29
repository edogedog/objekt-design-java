package grading;
import figures.*;
import geometry.HitTest;
import geometry.Point2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class HitTestTest
  {
  @Test @DisplayName("HitTest: cirkelns insida och tolerans, men inte avlägsna punkter")
  void circle()
    {
    var circle = new Circle(new Point2(20, 20), 10);
    assertTrue(HitTest.hits(circle, new Point2(20, 20)));
    assertTrue(HitTest.hits(circle, new Point2(35, 20)));
    assertFalse(HitTest.hits(circle, new Point2(37, 20)));
    }

  @Test @DisplayName("HitTest: närmaste punkt på segmentets inre och båda ändar")
  void segment()
    {
    var line = new Line(new Point2(10, 10), new Point2(30, 10));
    assertTrue(HitTest.hits(line, new Point2(20, 15)));
    assertFalse(HitTest.hits(line, new Point2(20, 17)));
    assertTrue(HitTest.hits(line, new Point2(6, 7)));
    assertFalse(HitTest.hits(line, new Point2(3, 10)));
    assertTrue(HitTest.hits(line, new Point2(34, 13)));
    assertFalse(HitTest.hits(line, new Point2(37, 10)));
    }

  @Test @DisplayName("HitTest: linje med sammanfallande ändpunkter")
  void zeroLength()
    {
    var line = new Line(new Point2(10, 10), new Point2(10, 10));
    assertTrue(HitTest.hits(line, new Point2(13, 14)));
    assertFalse(HitTest.hits(line, new Point2(17, 10)));
    }
  }

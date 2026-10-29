package figures;

import geometry.Point2;
import rendering.DrawingContext;
import java.util.Objects;

/** Exempelfigur: en sträcka mellan två punkter. */
public final class Line implements Figure
  {
  private Point2 start;
  private Point2 end;

  /** Skapar en sträcka. Sammanfallande ändpunkter är tillåtna. */
  public Line(Point2 start, Point2 end)
    {
    this.start = Objects.requireNonNull(start, "start");
    this.end = Objects.requireNonNull(end, "end");
    }

  @Override
  public void draw(DrawingContext context)
    {
    context.line(start, end);
    }

  @Override
  public void move(double dx, double dy)
    {
    // Beräkna båda nya värdena först. Om någon punkt blir ogiltig
    // kastas undantaget innan någon av de gamla punkterna ersätts.
    Point2 nextStart = start.translated(dx, dy);
    Point2 nextEnd = end.translated(dx, dy);
    start = nextStart;
    end = nextEnd;
    }

  @Override
  public Point2 getCenter()
    {
    return start.midpoint(end);
    }
  }

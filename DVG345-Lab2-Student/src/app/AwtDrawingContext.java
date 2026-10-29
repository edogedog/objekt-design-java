package app;

import geometry.Point2;
import rendering.DrawingContext;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Objects;

/** Infrastruktur: översätter ritportens operationer till Java2D. */
public final class AwtDrawingContext implements DrawingContext
  {
  private final Graphics2D graphics;

  public AwtDrawingContext(Graphics2D graphics)
    {
    this.graphics = Objects.requireNonNull(graphics);
    }

  @Override
  public void line(Point2 start, Point2 end)
    {
    graphics.draw(new Line2D.Double(start.x(), start.y(), end.x(), end.y()));
    }

  @Override
  public void circle(Point2 center, double radius)
    {
    graphics.draw(new Ellipse2D.Double(center.x() - radius, center.y() - radius,
        2 * radius, 2 * radius));
    }
  }

package tools;
import figures.Circle;
import geometry.Point2;
import model.DrawingDocument;
import rendering.DrawingContext;

/** Centrum vid nedtryckning, radie från avståndet till aktuell musposition. */
public final class CircleTool implements Tool
  {
  private final DrawingDocument document;
  private Point2 center;
  private Point2 end;

  public CircleTool(DrawingDocument document)
    {
    this.document = document;
    }

  @Override
  public void pressed(Point2 point, boolean extend)
    {
    center = point;
    end = point;
    }

  @Override
  public void dragged(Point2 point)
    {
    end = point;
    }

  @Override
  public void released(Point2 point)
    {
    if (center == null) {
    	return;
    }
    double r = center.distance(point);
    if (r<=0) {
    	return;
    }
    new Circle(point, radie);
    }

  @Override
  public void cancel()
    {
    center = null;
    end = null;
    }

  @Override
  public void drawPreview(DrawingContext context)
    {
    if (center != null && end != null)
      {
      double radius = center.distance(end);
      if (radius > 0)
        context.circle(center, radius);
      }
    }
  }
//hej
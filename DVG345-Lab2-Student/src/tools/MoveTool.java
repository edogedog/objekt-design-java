package tools;
import figures.Figure;
import geometry.HitTest;
import geometry.Point2;
import model.DrawingDocument;
import model.Selection;
import rendering.DrawingContext;

/** Exempel på State. V2–V3 flyttar direkt; Escape avslutar gesten.
 * V4 ersätter detta med avbrytbar förhandsvisning och ett kommando.
 */
public final class MoveTool implements Tool
  {
  private final DrawingDocument document;
  private final Selection selection;
  private Figure target;
  private Point2 last;

  public MoveTool(DrawingDocument document, Selection selection)
    {
    this.document = document;
    this.selection = selection;
    }

  @Override
  public void pressed(Point2 point, boolean extend)
    {
    target = null;
    if (!extend)
      selection.clear();
    var figures = document.figures();
    for (int i = figures.size() - 1; i >= 0; i--)
      if (HitTest.hits(figures.get(i), point))
        {
        target = figures.get(i);
        selection.toggle(target);
        break;
        }
    // Shift används enbart för att bygga markering, inte för flyttning.
    if (extend)
      target = null;
    last = point;
    }

  @Override
  public void dragged(Point2 point)
    {
    if (target != null)
      {
      target.move(point.x() - last.x(), point.y() - last.y());
      document.changed();
      }
    last = point;
    }

  @Override
  public void released(Point2 point)
    {
    dragged(point);
    cancel();
    }

  @Override
  public void cancel()
    {
    target = null;
    }

  @Override
  public void drawPreview(DrawingContext context)
    {
    }
  }

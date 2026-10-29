package app;
import figures.Figure;
import geometry.Point2;
import model.DrawingDocument;
import model.Selection;
import tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/** Swing-adapter: omvandlar musdata, visar dokument, markering och förhandsvisning. */
public final class DrawingPanel extends JPanel
  {
  private static final long serialVersionUID = 1L;
  private final DrawingDocument document;
  private final Selection selection;
  private Tool tool;

  public DrawingPanel(List<Figure> figures)
    {
    this(new DrawingDocument(figures), new Selection());
    }

  public DrawingPanel(DrawingDocument document, Selection selection)
    {
    this.document = document;
    this.selection = selection;
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(720, 480));
    document.addListener(this::repaint);
    MouseAdapter mouse = new MouseAdapter()
      {
      private Point2 point(MouseEvent event)
        {
        return new Point2(event.getX(), event.getY());
        }

      @Override
      public void mousePressed(MouseEvent event)
        {
        if (tool != null && SwingUtilities.isLeftMouseButton(event))
          {
          requestFocusInWindow();
          tool.pressed(point(event), event.isShiftDown());
          repaint();
          }
        }

      @Override
      public void mouseDragged(MouseEvent event)
        {
        if (tool != null && (event.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0)
          {
          tool.dragged(point(event));
          repaint();
          }
        }

      @Override
      public void mouseReleased(MouseEvent event)
        {
        if (tool != null && SwingUtilities.isLeftMouseButton(event))
          {
          tool.released(point(event));
          repaint();
          }
        }
      };
    addMouseListener(mouse);
    addMouseMotionListener(mouse);
    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "cancel");
    getActionMap().put("cancel", new AbstractAction()
      {
      @Override
      public void actionPerformed(ActionEvent event)
        {
        cancelGesture();
        }
      });
    }

  public void cancelGesture()
    {
    if (tool != null)
      tool.cancel();
    repaint();
    }

  public void setTool(Tool tool)
    {
    cancelGesture();
    this.tool = tool;
    repaint();
    }

  @Override
  protected void paintComponent(Graphics graphics)
    {
    super.paintComponent(graphics);
    Graphics2D copy = (Graphics2D) graphics.create();
    try
      {
      copy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      var context = new AwtDrawingContext(copy);
      copy.setColor(Color.BLACK);
      for (Figure figure : document.figures())
        figure.draw(context);
      copy.setColor(new Color(0, 110, 190));
      copy.setStroke(new BasicStroke(2));
      for (Figure figure : selection.figures())
        figure.draw(context);
      copy.setColor(new Color(210, 90, 0));
      if (tool != null)
        tool.drawPreview(context);
      }
    finally
      {
      copy.dispose();
      }
    }
  }

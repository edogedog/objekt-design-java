package grading;

import app.DrawingPanel;
import figures.Line;
import geometry.Point2;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.SwingUtilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

/** Startmiljön kan kontrolleras innan studentens figurklasser är färdiga. */
@Timeout(5)
public class StarterTest
  {
  @Test @DisplayName("Startmiljö: den givna linjen skickas till ritporten")
  void lineUsesDrawingPort()
    {
    var a = new Point2(10, 20);
    var b = new Point2(50, 20);
    Support.assertDrawing(new Line(a, b), List.of(new Support.Stroke(a, b)));
    }

  @Test @DisplayName("Startmiljö: ritytan visar en linje mot vit bakgrund")
  void panelPaintsLine() throws Exception
    {
    SwingUtilities.invokeAndWait(() ->
      {
      var panel = new DrawingPanel(List.of(new Line(new Point2(10, 20), new Point2(50, 20))));
      panel.setSize(100, 100);
      var image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
      var graphics = image.createGraphics();
      try
        {
        panel.paint(graphics);
        }
      finally
        {
        graphics.dispose();
        }
      assertNotEquals(Color.WHITE.getRGB(), image.getRGB(30, 20), "Linjen ska synas i ritytan");
      assertEquals(Color.WHITE.getRGB(), image.getRGB(90, 90), "Bakgrunden ska vara vit");
      });
    }
  }

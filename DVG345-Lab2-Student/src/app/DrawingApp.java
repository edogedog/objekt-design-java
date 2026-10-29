package app;
import model.DrawingDocument;
import model.Selection;
import tools.CircleTool;
import tools.MoveTool;
import java.awt.BorderLayout;
import javax.swing.*;


/** Kopplingspunkt för dokument, verktyg och vy. */
public final class DrawingApp
  {
  public static void main(String[] args)
    {
    SwingUtilities.invokeLater(() ->
      {
      var document = new DrawingDocument(DemoContent.create());
      var selection = new Selection();
      var panel = new DrawingPanel(document, selection);
      
      var toolbar = new JPanel();
      var status = new JLabel("Verktyg: Flytta");
      JButton move = new JButton("Flytta / markera");
      JButton circle = new JButton("Cirkel");
      move.addActionListener(event ->
        {
        panel.setTool(new MoveTool(document, selection));
        status.setText("Verktyg: Flytta");
        });
      circle.addActionListener(event ->
        {
        panel.setTool(new CircleTool(document));
        status.setText("Verktyg: Cirkel");
        });
      toolbar.add(move);
      toolbar.add(circle);
      
      toolbar.add(status);
      panel.setTool(new MoveTool(document, selection));
      JFrame frame = new JFrame("DVG345 – labb 2");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(toolbar, BorderLayout.NORTH);
      frame.add(panel, BorderLayout.CENTER);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
      });
    }
  }

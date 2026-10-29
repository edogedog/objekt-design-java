package figures;

import java.util.Objects;

import geometry.Point2;
import rendering.DrawingContext;

/** STUDENTUPPGIFT: axelparallell kvadrat med positiv, ändlig sidlängd. */
public final class Square implements Figure
  {
	private Point2 topLeft;
	private final double side;
	private final double x;
	private final double y;
	
	
  public Square(Point2 topLeft, double side)
    {
		this.topLeft = Objects.requireNonNull(topLeft, "rectangle side");
		
		// checkar om bra höjd
		if (side <= 0) {
			throw new IllegalArgumentException("större än noll!");
		}
		// checkar om finite
		if (!Double.isFinite(topLeft.y()+side)) {
			throw new IllegalArgumentException("måste vara vanligt double");
		}
		// checkar om finite
		if (!Double.isFinite(topLeft.x()+side)) {
			throw new IllegalArgumentException("måste vara vanligt double");
		}
		// checkar om finite
		if (!Double.isFinite(side)) {
			throw new IllegalArgumentException("måste vara vanligt double");
		}
		this.side = side;
		
		x = topLeft.x() + side/2;
		y = topLeft.y() + side/2;
    }

  @Override
  public void draw(DrawingContext context)
    {
		Point2 topRight = new Point2(topLeft.x()+side,topLeft.y()); 
		Point2 bottomLeft = new Point2(topLeft.x(),topLeft.y()+side);
		Point2 bottomright = new Point2(topLeft.x()+side,topLeft.y()+side);
		
		context.line(topLeft,topRight);
		context.line(topLeft,bottomLeft);
		context.line(bottomLeft, bottomright);
		context.line(topRight, bottomright);
	
    }

  @Override
  public Point2 getCenter()
    {
	  return new Point2(x, y);
    }

  @Override
  public void move(double dx, double dy) {
	// TODO Auto-generated method stub
		Point2 newPoint = topLeft.translated(dx, dy);
		Square test = new Square(newPoint,side);
		topLeft = newPoint;
  }
  }

package animations.figures;

import animations.CollisionDetector;
import animations.Side;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Rect extends Figure{
    public Rect(Graphics2D buffer, int DELAY, int panelWidth, int panelHeight, Point[] coordinates) {
        super(buffer, DELAY, panelWidth, panelHeight);

        // Ustawienie kierunku poruszania się figury
        dx = 8;
        dy = 0;

        Point firstPoint = coordinates[0];
        Point secondPoint = coordinates[1];
        shape = new Rectangle2D.Double(calculateX(firstPoint, secondPoint), calculateY(firstPoint, secondPoint), calculateWidth(firstPoint, secondPoint), calculateHeight(firstPoint, secondPoint));
        aft = new AffineTransform();
        area = new Area(shape);
    }

    @Override
    protected AffineTransform move() {
        aft = new AffineTransform();
        area = new Area(shape);
        Rectangle bounds = area.getBounds();

        Side collisionSide = CollisionDetector.isSideCollision(this);

        if(collisionSide != null && collisionSide == Side.RIGHT){
            aft.translate(-panelWidth + bounds.getWidth(), 0);
        }

        aft.translate(dx, dy);
        return aft;
    }

    private double calculateWidth(Point firstPoint, Point secondPoint){
        return Math.abs(secondPoint.getX() - firstPoint.getX());
    }

    private double calculateHeight(Point firstPoint, Point secondPoint){
        return Math.abs(secondPoint.getY() - firstPoint.getY());
    }

    private double calculateX(Point firstPoint, Point secondPoint){
        return Math.min(firstPoint.getX(), secondPoint.getX());
    }

    private double calculateY(Point firstPoint, Point secondPoint){
        return Math.min(firstPoint.getY(), secondPoint.getY());
    }
}

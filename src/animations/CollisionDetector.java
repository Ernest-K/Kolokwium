package animations;

import animations.figures.Figure;

import java.awt.*;
import java.awt.geom.Area;

public class CollisionDetector {
    private static int panelWidth;
    private static int panelHeight;
    private CollisionDetector() {
    }

    // Zwraca ścianę która została dotknięta lub null jeżeli żadna nie została dotknięta
    public static Side isSideCollision(Figure figure){
        Area area = new Area(figure.getShape());
        Rectangle figureBounds = area.getBounds();

        int cx = (int) (figureBounds.getX() + figureBounds.getWidth() / 2);
        int cy = (int) (figureBounds.getY() + figureBounds.getHeight() / 2);

        if (cy - figureBounds.getHeight() / 2 <= 0){
            return Side.TOP;
        }

        if (cx + figureBounds.getWidth() / 2 >= panelWidth){
            return Side.RIGHT;
        }

        if (cy + figureBounds.getHeight() / 2 >= panelHeight){
            return Side.BOTTOM;
        }

        if (cx - figureBounds.getWidth() / 2 <= 0){
            return Side.LEFT;
        }

        return null;
    }

    // Setters
    public static void setPanelWidth(int panelWidth) {
        CollisionDetector.panelWidth = panelWidth;
    }

    public static void setPanelHeight(int panelHeight) {
        CollisionDetector.panelHeight = panelHeight;
    }
}

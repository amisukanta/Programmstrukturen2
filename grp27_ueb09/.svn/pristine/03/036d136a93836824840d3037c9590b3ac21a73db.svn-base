package viergewinnt;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Diese Klasse repräsentiert eine Zelle. Eine Zelle besteht in jedem Fall aus
 * einem Pane-Objekt und gehört optional zu einem Spieler.
 *
 * @author Jan Riemer
 */
public class Cell {

    /**
     * Das Pane-Objekt innerhalb dieser Zelle.
     */
    private final Pane pane;

    /**
     * Eine optionale Id, die anzeigt zu welchem Spieler die Zelle gehört.
     */
    private Integer belongsToPlayer;

    /**
     * Initialisiert eine Zelle mit einem neuen Pane-Objekt. Die Zelle gehört zu
     * keinem Spieler.
     */
    public Cell() {
        this.pane = new Pane();
        this.belongsToPlayer = null;
    }

    /**
     * Gibt das Pane-Objekt innerhalb dieser Zelle zurück.
     *
     * @return Das Pane-Objekt dieser Zelle.
     */
    public Pane getPane() {
        return this.pane;
    }

    /**
     * Gibt an, ob diese Zelle von einem Spieler belegt ist.
     *
     * @return true, wenn diese Zelle von einem Spieler belegt ist, ansonsten
     * false.
     */
    public boolean isReserved() {
        return this.belongsToPlayer != null;
    }

    /**
     * Setzt den Spieler, welcher diese Zelle belegt.
     *
     * @param playerId Die Spieler-Id des Spielers, welcher diese Zelle belegen
     * soll.
     */
    public void setPlayerOwner(int playerId) {
        this.belongsToPlayer = playerId;
    }

    /**
     * Gibt die Spieler-Id desjenigen Spielers zurück, der diese Zelle belegt.
     *
     * @return Wenn diese Zelle belegt ist, die Id des dazugehörigen Spielers,
     * ansonsten null.
     */
    public Integer getPlayerOwner() {
        return this.belongsToPlayer;
    }

    /**
     * Färbt die Zelle mit einer Farbe ein. Dabei werden die Außenränder der
     * Zelle nicht mit eingefärbt.
     *
     * @param color Die Farbe, die zur Einfärbung benutzt werden soll.
     */
    public void colorize(Color color) {
        final double INSET = 3.0;
        this.pane.setBackground(
                new Background(
                        new BackgroundFill(
                                color,
                                CornerRadii.EMPTY,
                                new Insets(INSET))
                )
        );
    }

    /**
     * Färbt den Rand des Pane-Objekts der Zelle in der übermittelten Farbe
     *
     * @param borderColor Randfarbe
     */
    public void colorizeBorder(Color borderColor) {
        final double BORDER_WIDTH = 2.0;
        this.pane.setBorder(new Border(new BorderStroke[]{
            new BorderStroke(borderColor,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(BORDER_WIDTH))
        }));
    }

    /**
     * Färbt den Rand des Pane-Objekts der Zelle in der übermittelten Farbe
     *
     * @param borderColor Randfarbe
     * @param borderWidth Randdicke 
     * @param borderStroke  Linienart
     */
    public void colorizeBorder(Color borderColor, double borderWidth, BorderStrokeStyle borderStroke) {
        this.pane.setBorder(new Border(new BorderStroke[]{
            new BorderStroke(borderColor,
            borderStroke,
            CornerRadii.EMPTY,
            new BorderWidths(borderWidth))
        }));
    }

    /**
     * Setzt den Event-Handler für das Mouse-Click Event auf der Zelle.
     *
     * @param clickHandler Der Handler für das Mouse-Click Event.
     */
    public void setOnMouseClicked(ClickHandler clickHandler) {
        this.pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickHandler.handle(event);
            }
        });
    }

    /**
     * Entfernt den Event-Handler für das Mouse-Click Event auf der Zelle.
     */
    public void removeOnMouseClicked() {
        this.pane.setOnMouseClicked(null);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp27_ueb05;

/**
 *
 * @author Robin
 */
public class Square extends Figure {

    private final double edge;

    Square(Color color, double edge) {
        super(color);
        this.edge = edge;
    }

    Square(double edge) {
        super();
        this.edge = edge;
    }

    @Override
    public double getArea() {
        return Math.pow(edge, 2);
    }

    @Override
    public double getPerimeter() {
        return 4 * edge;
    }

    public String getDescription() {
        return String.format("%-8s %-16s (%5.2f),\t\t", "Quadrat", "mit Kantenlänge", edge);
    }

    @Override
    public String toString() {
        if (color != null) {
            return String.format("%10s %s %s", color.toString(), getDescription(), super.toString());
        } else{
            return String.format("%10s %s %s", "farblos", getDescription(), super.toString());
        }
            
    }

}

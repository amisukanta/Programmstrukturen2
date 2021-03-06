package grp27_ueb05;

/**
 *
 * @author Robin
 */
public class Grp27_ueb05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        printList(erstelleFiguren());
        
    }

    private static Element erstelleFiguren() {

        System.out.println("Füge folgende Figuren zu:");
        
        Figure figure1 = new Triangle(7, 8, 4);
        Element list = new Element(figure1); 
        System.out.printf(" 1. %s",figure1.toString());
        
        Figure figure2 = new Circle(Color.stringToColor("green"), 3);        
        System.out.printf(" 2. %s",figure2.toString());
        list = list.addElementInOrder(figure2);
        
        Figure figure3 = new Rectangle(Color.stringToColor("red"), 7, 8);
        System.out.printf(" 3. %s",figure3.toString());
        list = list.addElementInOrder(figure3);
        
        Figure figure4 = new Square(Color.stringToColor("yellow"), 2.5);
        System.out.printf(" 4. %s",figure4.toString());
        list = list.addElementInOrder(figure4);
        
        list = list.addElementInOrder(new Rectangle(Color.stringToColor("red"), 14, 16));
        list = list.addElementInOrder(new Triangle(14, 16, 8));
        list = list.addElementInOrder(new Circle(Color.stringToColor("green"), 6));
        list = list.addElementInOrder(new Square(Color.stringToColor("yellow"), 5));
        list = list.addElementInOrder(new Triangle(Color.stringToColor("white"), 3.5,4,2));
        list = list.addElementInOrder(new Circle(Color.stringToColor("green"), 1.5));
        list = list.addElementInOrder(new Square(new Color(new int[]{10, 128, 255}), 1.25));
        
        return list;

    }
    
    private static void printList(Element list){
        System.out.printf("\nListe neu erzeugt mit %d Elementen.\n", list.size());
        System.out.println("------------------------------");
        System.out.printf("%s",list.toString());
        System.out.println("------------------------------");
        
    }

}

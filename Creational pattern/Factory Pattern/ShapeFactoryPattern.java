import java.util.Scanner;

// Abstract Shape class
abstract class Shape {
    public abstract void draw();
}

// Concrete Shape classes
class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Triangle");
    }
}

// ShapeFactory class
class ShapeFactory {
    public static Shape createShape(String type) {
        switch (type.toLowerCase()) {
            case "circle":
                return new Circle();
            case "rectangle":
                return new Rectangle();
            case "triangle":
                return new Triangle();
            default:
                throw new UnsupportedOperationException("Unsupported shape type: " + type);
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the type of shape (circle, rectangle, triangle): ");
        String shapeType = scanner.nextLine();

        try {
            Shape shape = ShapeFactory.createShape(shapeType);
            shape.draw();
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

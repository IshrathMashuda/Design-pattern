import java.util.Scanner;

public class Product {
    private final String productId;
    private final String name;
    private final String color;
    private final double price;

    private Product(String productId, String name, String color, double price) {
        this.productId = productId;
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public static ProductBuilder builder(String productId) {
        return new ProductBuilder(productId);
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public static class ProductBuilder {
        private final String productId;
        private String name;
        private String color;
        private double price;

        public ProductBuilder(String productId) {
            this.productId = productId;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder color(String color) {
            this.color = color;
            return this;
        }

        public ProductBuilder price(double price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(productId, name, color, price);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Color: ");
        String color = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        Product product = Product.builder(productId)
                .name(name)
                .color(color)
                .price(price)
                .build();

        System.out.println("Product ID: " + product.getProductId());
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Color: " + product.getColor());
        System.out.println("Product Price: " + product.getPrice());

        scanner.close();
    }
}

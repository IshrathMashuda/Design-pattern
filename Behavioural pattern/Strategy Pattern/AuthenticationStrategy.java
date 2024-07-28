import java.util.Scanner;

// Strategy Interface
interface AuthenticationStrategy {
    boolean authenticate(String username, String credential);
}

// Concrete Strategy for Username/Password Authentication
class UsernamePasswordAuthentication implements AuthenticationStrategy {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    @Override
    public boolean authenticate(String username, String credential) {
        return USERNAME.equals(username) && PASSWORD.equals(credential);
    }
}

// Concrete Strategy for OTP Authentication
class OTPAuthentication implements AuthenticationStrategy {
    private static final String USERNAME = "user";
    private static final String OTP = "123456";

    @Override
    public boolean authenticate(String username, String credential) {
        return USERNAME.equals(username) && OTP.equals(credential);
    }
}

// Concrete Strategy for Biometric Authentication
class BiometricAuthentication implements AuthenticationStrategy {
    private static final String USERNAME = "user";
    private static final String BIOMETRIC = "biometric123";

    @Override
    public boolean authenticate(String username, String credential) {
        return USERNAME.equals(username) && BIOMETRIC.equals(credential);
    }
}

// Context
class AuthenticationContext {
    private AuthenticationStrategy strategy;

    public void setStrategy(AuthenticationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean authenticate(String username, String credential) {
        return strategy.authenticate(username, credential);
    }
}

public class AuthenticationExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthenticationContext context = new AuthenticationContext();

        while (true) {
            System.out.println("Choose authentication method: 1. Username/Password 2. OTP 3. Biometric 4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 4) {
                break;
            }

            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter credential: ");
            String credential = scanner.nextLine();

            switch (choice) {
                case 1:
                    context.setStrategy(new UsernamePasswordAuthentication());
                    System.out.println("Authenticating user using username and password.");
                    break;
                case 2:
                    context.setStrategy(new OTPAuthentication());
                    System.out.println("Authenticating user using OTP.");
                    break;
                case 3:
                    context.setStrategy(new BiometricAuthentication());
                    System.out.println("Authenticating user using biometric data.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            if (context.authenticate(username, credential)) {
                System.out.println(username + " is authenticated.");
            } else {
                System.out.println(username + " is not authenticated.");
            }
        }

        scanner.close();
    }
}

import java.util.Scanner;

// Interface 5
interface PaymentMethod {
    void pay(double amount);
}

// Abstract class 
abstract class Payment implements PaymentMethod {

    private double amount;  // encapsulation

    public Payment(double amount) {  // constructor overloading 
        this.amount = amount;
    }

    public double getAmount() {      // getter (encapsulation)
        return amount;
    }

    public void setAmount(double amount) { // setter (encapsulation)
        this.amount = amount;
    }

    // common method for all payments
    public void showAmount() {
        System.out.println("Amount to be paid: ₹" + amount);
    }

    // abstract method - must be implemented by child classes
    public abstract void showPaymentType();
}

// Child class 1 - UPI Payment (Inheritance from Payment)
class UpiPayment extends Payment {

    private String upiId;

    public UpiPayment(double amount, String upiId) {
        super(amount);        // calling parent constructor
        this.upiId = upiId;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void showPaymentType() {
        System.out.println("Payment Type: UPI Payment");
    }

    @Override
    public void pay(double amount) {
        setAmount(amount);
        showPaymentType();
        showAmount();
        System.out.println("Paying using UPI ID: " + upiId);
        System.out.println("UPI payment completed.\n");
    }
}

// Child class 2 - Card Payment (Inheritance from Payment)
class CardPayment extends Payment {

    private String cardNumber;
    private String cardHolderName;

    public CardPayment(double amount, String cardNumber, String cardHolderName) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void showPaymentType() {
        System.out.println("Payment Type: Card Payment");
    }

    @Override
    public void pay(double amount) {
        setAmount(amount);
        showPaymentType();
        showAmount();
        System.out.println("Paying using Card Holder Name: " + cardHolderName);
        System.out.println("Card Number (last 4 digits): " + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Card payment completed.\n");
    }
}


class WalletPayment extends Payment {

    private String walletName;

    public WalletPayment(double amount, String walletName) {
        super(amount);
        this.walletName = walletName;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    @Override
    public void showPaymentType() {
        System.out.println("Payment Type: Wallet Payment");
    }

    @Override
    public void pay(double amount) {
        setAmount(amount);
        showPaymentType();
        showAmount();
        System.out.println("Paying using Wallet: " + walletName);
        System.out.println("Wallet payment completed.\n");
    }
}


public class PaymentApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== PAYMENT GATEWAY (OOP DEMO) =====");
        System.out.print("Enter amount to pay (₹): ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("\nSelect Payment Method:");
        System.out.println("1. UPI Payment");
        System.out.println("2. Card Payment");
        System.out.println("3. Wallet Payment");
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        PaymentMethod paymentMethod;  // polymorphic reference 

        switch (choice) {
            case 1:
                System.out.print("Enter UPI ID: ");
                String upiId = scanner.nextLine();
                paymentMethod = new UpiPayment(amount, upiId);
                break;

            case 2:
                System.out.print("Enter Card Number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter Card Holder Name: ");
                String cardHolderName = scanner.nextLine();
                paymentMethod = new CardPayment(amount, cardNumber, cardHolderName);
                break;

            case 3:
                System.out.print("Enter Wallet Name: ");
                String walletName = scanner.nextLine();
                paymentMethod = new WalletPayment(amount, walletName);
                break;

            default:
                System.out.println("Invalid choice. Program will exit.");
                scanner.close();
                return;
        }

        // Runtime polymorphism
        paymentMethod.pay(amount);

        System.out.println("Thank you for using the payment gateway.");
        scanner.close();
    }
}

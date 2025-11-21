import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Karta va PIN
        String correctCard = "986000000000";
        String correctPin = "1234";

        // Balans
        double balance = 1000000;
        String currentPin = correctPin;

        // PIN kiritish va tekshirish
        System.out.println("===== BANKOMAT =====");
        System.out.print("Karta raqamini kiriting: ");
        String enteredCard = sc.nextLine();

        if (!enteredCard.equals(correctCard)) {
            System.out.println("Karta topilmadi!");
            sc.close();
            return;
        }
        int attempts = 3;
        boolean pinCorrect = false;

        while (attempts > 0) {
            System.out.print("PIN-kodini kiriting: ");
            String enteredPin = sc.nextLine();

            if (enteredPin.equals(currentPin)) {
                pinCorrect = true;
                break;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("PIN xato! " + attempts + " urinish qoldi.");
                } else {
                    System.out.println("PIN-kod 3 marta xato kiritildi. Karta bloklandi!");
                    sc.close();
                    return;
                }
            }
        }

        // Asosiy menu (do-while loop)
        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 → Balansni tekshirish");
            System.out.println("2 → Pul yechish");
            System.out.println("3 → Pul qo'shish");
            System.out.println("4 → PIN o'zgartirish");
            System.out.println("5 → Chiqish");
            System.out.print("Operatsiyani tanlang (1-5): ");

            choice = sc.nextInt();
            sc.nextLine(); // Buffer tozalash

            switch (choice) {
                case 1: // Balansni tekshirish
                    System.out.println("\n--- Balans ---");
                    System.out.printf("Sizning balansingiz: %.2f so'm\n", balance);
                    break;

                case 2: // Pul yechish
                    System.out.println("\n--- Pul Yechish ---");
                    System.out.print("Yechmoqchi bo'lgan summa: ");
                    double withdrawAmount = sc.nextDouble();
                    sc.nextLine();

                    if (withdrawAmount <= 0) {
                        System.out.println("Xato! Summa musbat bo'lishi kerak.");
                    } else if (withdrawAmount > balance) {
                        System.out.println("Xato! Yetarli pul yo'q. Balansingiz: " + balance);
                    } else {
                        balance -= withdrawAmount;
                        System.out.printf("Muvaffaqiyatli! Yechilgan summa: %.2f so'm\n", withdrawAmount);
                        System.out.printf("Yangi balans: %.2f so'm\n", balance);
                    }
                    break;

                case 3: // Pul qo'shish
                    System.out.println("\n--- Pul Qo'shish ---");
                    System.out.print("Qo'shmoqchi bo'lgan summa: ");
                    double depositAmount = sc.nextDouble();
                    sc.nextLine();

                    if (depositAmount <= 0) {
                        System.out.println("Xato! Summa musbat bo'lishi kerak.");
                    } else {
                        balance += depositAmount;
                        System.out.printf("Muvaffaqiyatli! Qo'shilgan summa: %.2f so'm\n", depositAmount);
                        System.out.printf("Yangi balans: %.2f so'm\n", balance);
                    }
                    break;

                case 4: // PIN o'zgartirish
                    System.out.println("\n--- PIN O'zgartirish ---");
                    System.out.print("Eski PIN-kodini kiriting: ");
                    String oldPin = sc.nextLine();

                    if (!oldPin.equals(currentPin)) {
                        System.out.println("Xato! PIN-kod noto'g'ri.");
                    } else {
                        System.out.print("Yangi PIN-kodini kiriting: ");
                        String newPin = sc.nextLine();
                        currentPin = newPin;
                        System.out.println("PIN-kod muvaffaqiyatli o'zgartirildi!");
                    }
                    break;

                case 5: // Chiqish
                    System.out.println("\nBankomatdan chiqyapsiz...");
                    System.out.println("Rahmat foydalanganingiz uchun!");
                    break;

                default:
                    System.out.println("Xato tanlov! Iltimos 1-5 orasidan tanlang.");
                    break;
            }

        } while (choice != 5);

        sc.close();
    }
}
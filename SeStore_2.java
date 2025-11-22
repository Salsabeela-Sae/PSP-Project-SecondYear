/********************************************************/
/*Program Assignment : SE STORE#2*/
/*Student ID : 66160349*/
/*Student Name : Salsabeela Sa-e*/
/*Date : 21/ August 2024*/
/*โปรแกรมแสดงรายละเอียดเมนูจากไฟล์ PRODUCT.txt และไฟล์ CATEGORY.txt*/
/********************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeStore_2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            System.out.println("===== SE STORE =====");
            System.out.println("1. Show Category");
            System.out.println("2. Exit");
            System.out.println("====================");
            System.out.print("Select (1-2): ");

            if (scanner.hasNextInt()) { // ตรวจสอบว่าผู้ใช้ป้อนเลขจำนวนเต็มหรือไม่
                input = scanner.nextInt();

                if (input == 1) {
                    showCategory();
                } else if (input == 2) {
                    System.out.println("Thank you for using our service :3");
                    break;
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 2.");
                scanner.next(); // อ่านค่าที่ไม่ใช่จำนวนเต็มเพื่อข้ามไป
            }
        }
    }

    public static void showCategory() throws FileNotFoundException {
        List<String[]> categories = new ArrayList<>();
        try (Scanner readFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/CATEGORY.txt"))) {
            while (readFile.hasNextLine()) {
                String[] data = readFile.nextLine().split("\t");
                if (data.length == 2) {
                    categories.add(data);// เพิ่มข้อมูลหมวดหมู่ลงใน List
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== SE STORE's Product Categories =====");
            System.out.println("#\tCategory");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + "\t" + categories.get(i)[1]);
            }
            System.out.println("=========================================");

            System.out.print("Select Category to Show Product (1-" + categories.size() + ") or Q for exit: ");
            String select = scanner.next();
            if (select.matches("[1-9]|10")) {//อินพุตของผู้ใช้ (select) เป็นเลข 1 ถึง 9 หรือเลข 10 หรือไม่ ถ้าใช่ ก็จะเข้าไปทำงานในบล็อกโค้ดใน { } ต่อไป
                int lineNum = Integer.parseInt(select) - 1;
                String idCategory = categories.get(lineNum)[0];
                String nameCategory = categories.get(lineNum)[1];
                if (idCategory != null) {
                    System.out.println("============ " + nameCategory + " ============");
                    System.out.println("#\t\tName\t\t\tPrice\t\tQuantity");

                    try (Scanner scanFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/PRODUCT.txt"))) {
                        int i = 0;

                        while (scanFile.hasNextLine()) {
                            String[] products = scanFile.nextLine().split("\t");
                            if (products[4].equals(idCategory)) {
                                i++;
                                System.out.printf("%-7d %-15s %-9s %5s%n", i, products[1], products[2], products[3]);
                            }
                        }
                    }
                    System.out.println("=========================================");
                }
                while (true) {
                    System.out.print("Press Q to Exit: ");
                    String exitInput = scanner.next();

                    if (exitInput.equalsIgnoreCase("q")) {
                        break;  // ออกจากลูปและกลับไปที่การแสดงหมวดหมู่
                    } else {
                        System.out.println("Invalid input. Please press Q to exit.");
                    }
                }
            } else if (select.equalsIgnoreCase("q")) {
                break;
            }else {
                System.out.println("Invalid input. Please press Q to exit.");

            }
        }
    }
}
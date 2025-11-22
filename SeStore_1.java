/********************************************************/
/*Program Assignment : SE STORE#1*/
/*Student ID : 66160349*/
/*Student Name : Salsabeela Sa-e*/
/*Date : 14/ August 2024*/
/*โปรแกรมแสดงรายละเอียดเมนูจากไฟล์ PRODUCT.txt*/
/********************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SeStore_1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(System.in);
        int input = 0;
        while (input != 2) {
            System.out.println("===== SE STORE =====");
            System.out.println("1. Show Product");
            System.out.println("2. Exit");
            System.out.println("====================");
            System.out.print("Select (1-2): ");
            input = kb.nextInt();
            if (input == 1) {
                showProduct();
            } else if (input == 2) {
                System.out.println("===== SE STORE =====\t\t\t\n" +
                        "Thank you for using our service :3\t");
            }
        }

    }

    public static void showProduct() throws FileNotFoundException {
        System.out.println("=========== SE STORE's Products ===========\t\t\t\n" +
                "#\t  Name  \t\t  Price    \t\tQuantity");
        try (Scanner fileScanner = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/PRODUCT.txt"))) {
            int lineNum = 0;
            while (fileScanner.hasNext()) {
                String id = fileScanner.next();
                String name = fileScanner.next();
                String price = fileScanner.next();
                String quantity = fileScanner.nextLine();
                lineNum++;
                System.out.printf("%-5s %-15s %-9s %-10s%n" , lineNum , name, price, quantity);
            }
        }
    }
}
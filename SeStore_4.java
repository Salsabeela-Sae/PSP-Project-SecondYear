/********************************************************/
/*Program Assignment : SE STORE#4*/
/*Student ID : 66160349*/
/*Student Name : Salsabeela Sa-e*/
/*Date : 07 September 2024*/
/*โปรแกรมแสดงรายละเอียดเมนูจากไฟล์ PRODUCT.txt และไฟล์ CATEGORY.txt และการเข้าถึงระบบโดยผ่านการ LOGIN*/
/********************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SeStore_4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true){//ลูปแสดง Display1
            System.out.println("===== SE STORE =====");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.println("====================");
            System.out.print("Select (1-2): ");

            if (scanner.hasNextInt()) { //ตรวจสอบว่าผู้ใช้ป้อนตัวเลขจำนวนเต็มหรือไม่
                input = scanner.nextInt();//รับค่า

                if (input == 1) {
                    showLogin();//ถ้าเลือก 1 ไปที่เมธอด showLogin เพื่อเข้าสู่ระบบ
                } else if (input == 2) {
                    System.out.println("Thank you for using our service :3");
                    break;//ออกจากลูปและสิ้นสุดโปรแกรม
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 2.");
                scanner.next(); // อ่านค่าที่ไม่ใช่จำนวนเต็มเพื่อข้ามไป
            }
        }
    }
    //เมธอดสำหรับเข้าสู่ระบบ
    private static void showLogin() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String email;
        String pass;
        int i = 0;//นับการเข้าระบบผิด
        String[] activeUserData = null; // ตัวแปรเก็บข้อมูลผู้ใช้ที่ login สำเร็จ
        while (true) {
            System.out.println("===== LOGIN =====");
            System.out.print("Email: ");
            email = scanner.next();//รับอีเมล
            System.out.print("Password: ");
            pass = scanner.next();//รับรหัสผ่าน
            boolean loginSuccess = false;
            try (Scanner readFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/Member.txt"))) {
                while (readFile.hasNextLine()) {
                    String dataLogin = readFile.nextLine();
                    String[] arr_data = dataLogin.split("\t");
                    String dataEmail = arr_data[3];//ข้อมูลตำแหน่งของอีเมล
                    String dataPass = "" + arr_data[4].charAt(9) + arr_data[4].charAt(10) + arr_data[4].charAt(13) + arr_data[4].charAt(14) + arr_data[4].charAt(15) + arr_data[4].charAt(16);//การดึงรหัสผ่านตามตำแหน่ง
                    if (email.equals(dataEmail) && pass.equals(dataPass)) {//ถ้าอีเมลแลรหัสผ่านที่รับมาตรงกับข้อมูลที่มีอยู่
                        loginSuccess = true;//การเข้าระบบสำเร็จ
                        char accountStatus = arr_data[4].charAt(2);//ตรวจสอบสถานะบัญชี
                        if (accountStatus == '1') {//ถ้าบัญชีมีสถานะใช้งานได้
                            activeUserData = arr_data;//เก็บข้อมูลในอาร์เรย์
                            activeUser(activeUserData);//ส่งข้าค่าข้อมุลที่เก็บไว้ไปที่เมธอดactiveUser และเรียกใช้งาน
                            return;//ออกจากเมธอด showLogin
                        } else if (accountStatus == '0') {//ถ้าบัญชีหมดอายุ
                            nonActiveUser();//เรียกใช้เมธอด nonActiveUser
                            return;
                        }
                        break;
                    }
                }
                if (!loginSuccess) {//ถ้าเข้าสู่ระบบไม่สำเร็จ
                    i++;//นับค่า
                    System.out.println("Error! - Email or Password is Incorrect (" + i + ")");
                    if (i == 3) {//ถ้าใส่รหัสผ่านผิดเกิน 3 ครั้ง
                        break;
                    }
                }
            }
        }
    }
    private static void activeUser(String[] activeUserData) throws FileNotFoundException {
            System.out.println("===== SE STORE =====");
            System.out.println("Hello, " + activeUserData[2].substring(0, 1) + ". " + activeUserData[1]);//นามสกุลเอาแค่อักษรแรก ชื่อ
        //อีเมล์อักษรตำแหน่งที่0,1 แล้วแสดง ***@ อีเมล์อักษรตำแหน่งหลัง@ 2 ตำแหน่ง หลังจากนั้นแสดง ***
            System.out.println("Email: " + activeUserData[3].substring(0, 2) + "**@" + activeUserData[3].charAt(activeUserData[3].indexOf("@") + 1) + activeUserData[3].charAt(activeUserData[3].indexOf("@") + 2) + "**");
            System.out.println("Phone: " + activeUserData[5].substring(0, 3) + "-" + activeUserData[5].substring(3, 6) + "-" + activeUserData[5].substring(6, 10));
            double point = Double.parseDouble(activeUserData[6]);//แปลงเป็น double ปกติก่อน
            System.out.println("You have " + (int) point + " Point");//แปลงให้เป็น int
            System.out.println("====================");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Show Category");
                System.out.println("2. Logout");
                System.out.println("====================");
                System.out.print("Select (1-2): ");
                if (scanner.hasNextInt()) { // ตรวจสอบว่าผู้ใช้ป้อนเลขจำนวนเต็มหรือไม่
                    int input;
                    input = scanner.nextInt();
                    if (input == 1) {
                        showCategory();
                    } else if (input == 2) {
                        return;
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
        Scanner scanner = new Scanner(System.in);
        List<String[]> categories = new ArrayList<>();
        try (Scanner readFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/CATEGORY.txt"))) {
            while (readFile.hasNextLine()) {
                String[] data = readFile.nextLine().split("\t");
                if (data.length == 2) {
                    categories.add(data);// เพิ่มข้อมูลหมวดหมู่ลงใน List
                }
            }
        }
        double exchangeRate = 34.00;
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
                int lineNum = Integer.parseInt(select) - 1;//บรรทัดนี้ทำหน้าที่แปลงค่าที่ผู้ใช้เลือก (ในตัวแปร select) จากรูปแบบ String เป็น int เนื่องจากการอ้างอิงข้อมูลใน List จะเริ่มนับจาก 0 (index base 0) ดังนั้นจึงมีการลบด้วย 1 (-1) เพื่อให้ตรงกับ index ของ List
                String idCategory = categories.get(lineNum)[0];
                String nameCategory = categories.get(lineNum)[1];
                if (idCategory != null) {
                    System.out.println("============ " + nameCategory + " ============");
                    System.out.printf("%-5s %-15s %-15s %-10s%n", "#", "Name", "Price", "Quantity");
                    try (Scanner scanFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/PRODUCT.txt"))) {
                        int i = 0;

                        while (scanFile.hasNextLine()) {
                            String[] products = scanFile.nextLine().split("\t");
                            if (products[4].equals(idCategory)) {
                                i++;
                                double priceInDollar = Double.parseDouble(products[2].replace("$", "")); //แทน$ เป็นช่องว่าง
                                double convertToBaht = priceInDollar * exchangeRate;
                                System.out.printf("%-5d %-15s %-15.2f %-10s%n", i, products[1], convertToBaht, products[3]);
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

    private static void nonActiveUser() throws FileNotFoundException {
        System.out.println("Error! - Your Account are Expired! \n");
    }
}
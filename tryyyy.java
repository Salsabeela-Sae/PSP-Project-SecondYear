
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class tryyyy {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {//ลูปแสดง Display1
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
    private static void showLogin() throws IOException {
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

    private static void activeUser(String[] activeUserData) throws IOException {
        if (activeUserData[4].charAt(6) == '0') {
            System.out.println("===== SE STORE =====");
            System.out.println("Hello, " + activeUserData[2].substring(0, 1) + ". " + activeUserData[1] + " (STAFF)");//นามสกุลเอาแค่อักษรแรก ชื่อ
            //อีเมล์อักษรตำแหน่งที่0,1 แล้วแสดง ***@ อีเมล์อักษรตำแหน่งหลัง@ 2 ตำแหน่ง หลังจากนั้นแสดง ***
            System.out.println("Email: " + activeUserData[3].substring(0, 2) + "**@" + activeUserData[3].charAt(activeUserData[3].indexOf("@") + 1) + activeUserData[3].charAt(activeUserData[3].indexOf("@") + 2) + "**");
            System.out.println("Phone: " + activeUserData[5].substring(0, 3) + "-" + activeUserData[5].substring(3, 6) + "-" + activeUserData[5].substring(6, 10));
            double point = Double.parseDouble(activeUserData[6]);//แปลงเป็น double ปกติก่อน
            System.out.println("You have " + (int) point + " Point");//แปลงให้เป็น int
            System.out.println("====================");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Show Category");
                System.out.println("2. Add Member");
                System.out.println("3. Logout");
                System.out.println("====================");
                System.out.print("Select (1-3): ");
                if (scanner.hasNextInt()) { // ตรวจสอบว่าผู้ใช้ป้อนเลขจำนวนเต็มหรือไม่
                    int input;
                    input = scanner.nextInt();
                    if (input == 1) {
                        showCategory(activeUserData);
                    } else if (input == 2) {
                        addMember();
                        break;
                    } else if (input == 3) {
                        return;
                    } else {
                        System.out.println("Invalid selection. Please enter a number between 1 and 2.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 2.");
                    scanner.next(); // อ่านค่าที่ไม่ใช่จำนวนเต็มเพื่อข้ามไป
                }
            }
        } else if (activeUserData[4].charAt(6) == '1' || activeUserData[4].charAt(6) == '2' || activeUserData[4].charAt(6) == '3') {
            System.out.println("===== SE STORE =====");
            System.out.println("Hello, " + activeUserData[2].substring(0, 1) + ". " + activeUserData[1] + checkRole(activeUserData));//นามสกุลเอาแค่อักษรแรก ชื่อ
            //อีเมล์อักษรตำแหน่งที่0,1 แล้วแสดง ***@ อีเมล์อักษรตำแหน่งหลัง@ 2 ตำแหน่ง หลังจากนั้นแสดง ***
            System.out.println("Email: " + activeUserData[3].substring(0, 2) + "**@" + activeUserData[3].charAt(activeUserData[3].indexOf("@") + 1) + activeUserData[3].charAt(activeUserData[3].indexOf("@") + 2) + "**");
            System.out.println("Phone: " + activeUserData[5].substring(0, 3) + "-" + activeUserData[5].substring(3, 6) + "-" + activeUserData[5].substring(6, 10));
            double point = Double.parseDouble(activeUserData[6]);//แปลงเป็น double ปกติก่อน
            System.out.println("You have " + (int) point + " Point");//แปลงให้เป็น int
            System.out.println("====================");
            selectShowCategory(activeUserData);
        }
    }

    public static void selectShowCategory(String[] activeUserData) throws FileNotFoundException {
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
                    showCategory(activeUserData);
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

    public static void showCategory(String[] activeUserData) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        List<String[]> categories = new ArrayList<>();
        List<String[]> productList = new ArrayList<>(); // สร้าง productList เพื่อเก็บข้อมูลสินค้า
        try (Scanner readFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/CATEGORY"))) {
            while (readFile.hasNextLine()) {
                String[] data = readFile.nextLine().split("\t");
                if (data.length == 2) {
                    categories.add(data); // เพิ่มข้อมูลหมวดหมู่ลงใน List
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
            if (select.matches("[1-9]|10")) {
                int lineNum = Integer.parseInt(select) - 1;
                String idCategory = categories.get(lineNum)[0];
                String nameCategory = categories.get(lineNum)[1];
                if (idCategory != null) {
                    System.out.println("============ " + nameCategory + " ============");
                    System.out.printf("%-5s %-15s %-25s %-10s%n", "#", "Name", "Price (฿)", "Quantity");

                    try (Scanner scanFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/PRODUCT.txt"))) {
                        int i = 0;
                        productList.clear(); // เคลียร์ข้อมูลสินค้าเก่าออกก่อนเพิ่มสินค้าใหม่ในหมวดหมู่ที่เลือก

                        while (scanFile.hasNextLine()) {
                            String[] products = scanFile.nextLine().split("\t");
                            if (products[4].equals(idCategory)) {
                                productList.add(products); // เพิ่มข้อมูลสินค้าใน productList
                                i++;
                                double priceInDollar = Double.parseDouble(products[2].replace("$", ""));
                                double convertToBaht = priceInDollar * exchangeRate;

                                if (activeUserData[4].charAt(6) == '0' || activeUserData[4].charAt(6) == '1') {
                                    System.out.printf("%-5d %-15s %-25.2f %-10s%n", i, products[1], convertToBaht, products[3]);
                                } else if (activeUserData[4].charAt(6) == '2') {
                                    double convertToDiscount = convertToBaht - (convertToBaht * 0.05);
                                    System.out.printf("%-5d %-15s %-25s %-10s%n", (i + 1), products[1],
                                            String.format("%.2f (%.2f)", convertToDiscount, convertToBaht), products[3]);
                                } else if (activeUserData[4].charAt(6) == '3') {
                                    double convertToDiscount = convertToBaht - (convertToBaht * 0.1);
                                    System.out.printf("%-5d %-15s %-25s %-10s%n", (i + 1), products[1],
                                            String.format("%.2f (%.2f)", convertToDiscount, convertToBaht), products[3]);
                                }
                            }
                        }
                    }
                    System.out.println("=========================================");
                }


                while (true) {
                    System.out.println("1. Show Name By DESC");
                    System.out.println("2. Show Quantity By ASC");
                    System.out.print("or Press Q to Exit : ");
                    String exitInput = scanner.next();

                    if (exitInput.equals("1")) {
                        shellSortByName(productList);
                        // แสดงรายการสินค้าที่เรียงตามชื่อ
                        System.out.println("============ " + nameCategory + " ============");
                        System.out.printf("%-5s %-15s %-25s %-10s%n", "#", "Name", "Price (฿)", "Quantity");

                        for (int i = 0; i < productList.size(); i++) {
                            String[] product = productList.get(i);
                            double priceInDollar = Double.parseDouble(product[2].replace("$", ""));
                            double convertToBaht = priceInDollar * exchangeRate;
                            double convertToDiscount = 0;

                            if (activeUserData[4].charAt(6) == '0' || activeUserData[4].charAt(6) == '1') {
                                // ไม่ได้รับส่วนลด
                                System.out.printf("%-5d %-15s %-25.2f %-10s%n", (i + 1), product[1], convertToBaht, product[3]);
                            } else if (activeUserData[4].charAt(6) == '2') {
                                // รับส่วนลด 5%
                                convertToDiscount = convertToBaht - (convertToBaht * 0.05);
                                System.out.printf("%-5d %-15s %-25s %-10s%n", (i + 1), product[1],
                                        String.format("%.2f (%.2f)", convertToDiscount, convertToBaht), product[3]);
                            } else if (activeUserData[4].charAt(6) == '3') {
                                // รับส่วนลด 10%
                                convertToDiscount = convertToBaht - (convertToBaht * 0.1);
                                System.out.printf("%-5d %-15s %-25s %-10s%n", (i + 1), product[1],
                                        String.format("%.2f (%.2f)", convertToDiscount, convertToBaht), product[3]);
                            }
                        }

                        System.out.println("=========================================");
                    } else if (exitInput.equals("2")) {
                        bubbleSortByQuantity(productList);
                        // แสดงรายการสินค้าที่เรียงตามจำนวน
                        System.out.println("============ " + nameCategory + " ============");
                        System.out.printf("%-5s %-15s %-25s %-10s%n", "#", "Name", "Price (฿)", "Quantity");

                        for (int i = 0; i < productList.size(); i++) {
                            String[] product = productList.get(i);
                            double priceInDollar = Double.parseDouble(product[2].replace("$", ""));
                            double convertToBaht = priceInDollar * exchangeRate;
                            double convertToDiscount = 0;

                            if (activeUserData[4].charAt(6) == '0' || activeUserData[4].charAt(6) == '1') {
                                // ไม่ได้รับส่วนลด
                                System.out.printf("%-5d %-15s %-25.2f %-10s%n", (i + 1), product[1], convertToBaht, product[3]);
                            } else if (activeUserData[4].charAt(6) == '2') {
                                // รับส่วนลด 5%
                                convertToDiscount = convertToBaht - (convertToBaht * 0.05);
                                System.out.printf("%-5d %-15s %-25s %-10s%n", (i + 1), product[1],
                                        String.format("%.2f (%.2f)", convertToDiscount, convertToBaht), product[3]);
                            } else if (activeUserData[4].charAt(6) == '3') {
                                // รับส่วนลด 10%
                                convertToDiscount = convertToBaht - (convertToBaht * 0.1);
                                System.out.printf("%-5d %-15s %-25s %-10s%n", (i + 1), product[1],
                                        String.format("%.2f (%.2f)", convertToDiscount, convertToBaht), product[3]);
                            }
                        }

                        System.out.println("=========================================");
                    }
                    if (exitInput.equalsIgnoreCase("q")) {
                        break;  // ออกจากลูปและกลับไปที่การแสดงหมวดหมู่
                    } else {
                        System.out.println("Invalid input. Please press Q to exit.");
                    }
                }


            } else if (select.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("Invalid input. Please press Q to exit.");
            }
        }
    }






    private static void nonActiveUser() throws FileNotFoundException {
        System.out.println("Error! - Your Account are Expired! \n");
    }

    public static void addMember() throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("===== Add Member =====");
            System.out.print("Firstname :");
            String addFirstname = input.nextLine();
            System.out.print("Lastname :");
            String addLastname = input.nextLine();
            System.out.print("Email :");
            String addEmail = input.nextLine();
            System.out.print("Phone :");
            String addPhone = input.nextLine();

            boolean checkInfo = false;

            if (addFirstname.length() > 2 && addLastname.length() > 2 && addEmail.length() > 2 && addEmail.contains("@") && addPhone.length() == 10) {
                checkInfo = true;
            }

            File file = new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/Member.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // หา ID สูงสุดในไฟล์
            String line;
            int maxID = 0;
            while ((line = reader.readLine()) != null) {
                String[] memberData = line.split("\t");
                int currentID = Integer.parseInt(memberData[0]);  // ฟิลด์แรกคือ ID
                if (currentID > maxID) {
                    maxID = currentID;
                }
            }
            reader.close();

            // เพิ่ม ID ใหม่โดยใช้ maxID + 1
            int newID = maxID + 1;
            if (checkInfo == true) {
                writer.write("\n" + newID + "\t");
                writer.write(addFirstname + "\t");
                writer.write(addLastname + "\t");
                writer.write(addEmail + "\t");
                writer.write(generatePassword() + "\t");
                writer.write(addPhone + "\t");
                writer.write("0.00");
                writer.close();
                System.out.println("Success - New Member has been created!");
                // ดึงข้อมูลจากสมาชิกล่าสุด
                try (Scanner readFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/Member.txt"))) {
                    String lastLine = "";  // เก็บบรรทัดสุดท้าย
                    while (readFile.hasNextLine()) {
                        lastLine = readFile.nextLine();  // อัปเดตบรรทัดล่าสุด
                    }

                    // ตรวจสอบว่าเป็นสมาชิกที่เพิ่งถูกสร้างล่าสุดหรือไม่
                    String[] arr_data = lastLine.split("\t");
                    if (Integer.parseInt(arr_data[0]) == newID) {
                        // ดึงรหัสผ่านตามตำแหน่งที่ต้องการ
                        String dataPass = "" + arr_data[4].charAt(9) + arr_data[4].charAt(10) + arr_data[4].charAt(13) + arr_data[4].charAt(14) + arr_data[4].charAt(15) + arr_data[4].charAt(16);
                        System.out.println(addFirstname + "'s Password is " + dataPass);
                    }
                }
                break;
            } else {
                System.out.println("Error! - Your Information are Incorrect!");
            }
        }
    }

    public static String generatePassword() {
        int passwordLength = 19;
        char[] password = new char[passwordLength];

        // ตัวอักษรภาษาอังกฤษที่ใช้สุ่ม
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // ตัวเลขที่ใช้สุ่ม
        String digits = "0123456789";

        Random random = new Random();

        // สุ่มตัวอักษรภาษาอังกฤษในตำแหน่งที่ระบุ
        int[] letterIndices = {0, 1, 3, 4, 5, 7, 8, 11, 12, 17, 18};
        for (int index : letterIndices) {
            password[index] = letters.charAt(random.nextInt(letters.length()));
        }

        // สุ่มตัวเลข 6 หลักในตำแหน่งที่ระบุ
        int[] digitIndices = {9, 10, 13, 14, 15, 16};
        for (int index : digitIndices) {
            password[index] = digits.charAt(random.nextInt(digits.length()));
        }

        //  (สถานะบัญชี) active
        password[2] = '1';

        // ตั้งค่า Index ที่ 6 ให้เป็น '1' (Regular Member)
        password[6] = '1';

        // แปลงเป็นสตริงและแสดงผล
        return String.valueOf(password);
    }

    public static String checkRole(String[] activeUserData) throws FileNotFoundException {
        String role = null;
        if (activeUserData[4].charAt(6) == '0') {
            role = " (STAFF)";
        } else if (activeUserData[4].charAt(6) == '1') {
            role = " (REGULAR)";
        } else if (activeUserData[4].charAt(6) == '2') {
            role = " (SILVER)";
        } else if (activeUserData[4].charAt(6) == '3') {
            role = " (GOLD)";
        }
        return role;
    }

    public static void shellSortByName(List<String[]> productList) {
        int size = productList.size();
        // Start with a big gap, then reduce the gap
        for (int gap = size / 2; gap > 0; gap /= 2) {
            // Perform a gapped insertion sort
            for (int i = gap; i < size; i++) {
                String[] temp = productList.get(i);
                int j;
                for (j = i; j >= gap && productList.get(j - gap)[1].compareTo(temp[1]) > 0; j -= gap) {
                    productList.set(j, productList.get(j - gap));
                }
                productList.set(j, temp);
            }
        }

    }

    public static void bubbleSortByQuantity(List<String[]> productList) {
        int size = productList.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = size - 1; j > i; j--) {
                int quantityJ = Integer.parseInt(productList.get(j)[3]);
                int quantityJ1 = Integer.parseInt(productList.get(j - 1)[3]);
                if (quantityJ < quantityJ1) {
                    // Swap
                    String[] temp = productList.get(j);
                    productList.set(j, productList.get(j - 1));
                    productList.set(j - 1, temp);
                }
            }
        }
    }
}

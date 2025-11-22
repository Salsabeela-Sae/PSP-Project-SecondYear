/********************************************************/
/*Program Assignment : SE STORE#9*/
/*Student ID : 66160349*/
/*Student Name : Salsabeela Sa-e*/
/*Date : 09 October 2024*/
/*โปรแกรมการ order product ข้อมูลในไฟล์*/
/********************************************************/
import java.io.*;
import java.util.*;

public class SeStore_9 {
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (activeUserData[4].charAt(6) == '0') {
                System.out.println("===== SE STORE =====");
                System.out.println("Hello, " + activeUserData[2].substring(0, 1) + ". " + activeUserData[1] + " (STAFF)");//นามสกุลเอาแค่อักษรแรก ชื่อ
                //อีเมล์อักษรตำแหน่งที่0,1 แล้วแสดง ***@ อีเมล์อักษรตำแหน่งหลัง@ 2 ตำแหน่ง หลังจากนั้นแสดง ***
                System.out.println("Email: " + activeUserData[3].substring(0, 2) + "**@" + activeUserData[3].charAt(activeUserData[3].indexOf("@") + 1) + activeUserData[3].charAt(activeUserData[3].indexOf("@") + 2) + "**");
                System.out.println("Phone: " + activeUserData[5].substring(0, 3) + "-" + activeUserData[5].substring(3, 6) + "-" + activeUserData[5].substring(6, 10));
                double point = Double.parseDouble(activeUserData[6]);//แปลงเป็น double ปกติก่อน
                System.out.println("You have " + (int) point + " Point");//แปลงให้เป็น int
                System.out.println("====================");

                System.out.println("1. Show Category");
                System.out.println("2. Add Member");
                System.out.println("3. Edit Member");
                System.out.println("4. Edit Product");
                System.out.println("5. Logout");
                System.out.println("====================");
                System.out.print("Select (1-5): ");
                if (scanner.hasNextInt()) { // ตรวจสอบว่าผู้ใช้ป้อนเลขจำนวนเต็มหรือไม่
                    int input;
                    input = scanner.nextInt();
                    if (input == 1) {
                        showCategory(activeUserData);
                    } else if (input == 2) {
                        addMember();
                        break;
                    } else if (input == 3) {
                        editMember();
                    } else if (input == 4) {
                        editProduct();
                    } else if (input == 5) {
                        return;
                    } else {
                        System.out.println("Invalid selection. Please enter a number between 1 and 2.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 2.");
                    scanner.next(); // อ่านค่าที่ไม่ใช่จำนวนเต็มเพื่อข้ามไป
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

                System.out.println("1. Show Category");
                System.out.println("2. Order Product");
                System.out.println("3. Logout");
                System.out.println("====================");
                System.out.print("Select (1-3): ");
                if (scanner.hasNextInt()) { // ตรวจสอบว่าผู้ใช้ป้อนเลขจำนวนเต็มหรือไม่
                    int input;
                    input = scanner.nextInt();
                    if (input == 1) {
                        showCategory(activeUserData);
                    } else if (input == 2) {
                        orderProduct(activeUserData[0]);
                    } else if (input == 3) {
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
    }


    public static void showCategory(String[] activeUserData) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        List<String[]> categories = new ArrayList<>();
        List<String[]> productList = new ArrayList<>();
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
                    System.out.printf("%-5s %-15s %-15s %-10s%n", "#", "Name", "Price (฿)", "Quantity");
                    try (Scanner scanFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/PRODUCT.txt"))) {
                        int i = 0;
                        while (scanFile.hasNextLine()) {
                            String[] products = scanFile.nextLine().split("\t");
                            if (products[4].equals(idCategory)) {
                                productList.add(products);// เก็บสินค้าไว้ใน List
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
                    System.out.println("1. Show Name By DESC");
                    System.out.println("2. Show Quantity By ASC");
                    System.out.print("or Press Q to Exit : ");
                    String exitInput = scanner.next();
                    if (exitInput.equals("1")) {
                        productList = shellSortByName(productList);  // เรียงตามชื่อสินค้า
                        System.out.println("============ " + nameCategory + " ============");
                        System.out.printf("%-5s %-15s %-15s %-10s%n", "#", "Name", "Price (฿)", "Quantity");
                        displayProducts(productList, exchangeRate, activeUserData);
                    } else if (exitInput.equals("2")) {
                        productList = bubbleSortByQuantity(productList);// เรียงตามจำนวนสินค้า
                        System.out.println("============ " + nameCategory + " ============");
                        System.out.printf("%-5s %-15s %-15s %-10s%n", "#", "Name", "Price (฿)", "Quantity");
                        displayProducts(productList, exchangeRate, activeUserData);
                    } else if (exitInput.equalsIgnoreCase("q")) {
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

    private static void showProduct() throws FileNotFoundException {
        List<String[]> productList = new ArrayList<>();
        double exchangeRate = 34.00;
        System.out.printf("%-5s %-15s %-15s %-10s%n", "#", "Name", "Price (฿)", "Quantity");
        try (Scanner scanFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/PRODUCT.txt"))) {
            int i = 0;
            while (scanFile.hasNextLine()) {
                String[] products = scanFile.nextLine().split("\t");
                productList.add(products);// เก็บสินค้าไว้ใน List
                i++;
                double priceInDollar = Double.parseDouble(products[2].replace("$", "")); //แทน$ เป็นช่องว่าง
                double convertToBaht = priceInDollar * exchangeRate;
                System.out.printf("%-5d %-15s %-15.2f %-10s%n", i, products[1], convertToBaht, products[3]);
            }
        }
        System.out.println("=========================================");
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

    // เมธอดสำหรับแสดงสินค้า
    private static void displayProducts(List<String[]> productList, double exchangeRate, String[] activeUserData) {

        int i = 0;
        for (String[] products : productList) {
            if (activeUserData[4].charAt(6) == '0' || activeUserData[4].charAt(6) == '1') {
                i++;
                double priceInDollar = Double.parseDouble(products[2].replace("$", "")); // Replace $ with empty string
                double convertToBaht = priceInDollar * exchangeRate;
                System.out.printf("%-5d %-15s %-15.2f %-10s%n", i, products[1], convertToBaht, products[3]);
            } else if (activeUserData[4].charAt(6) == '2') {
                i++;
                double priceInDollar = Double.parseDouble(products[2].replace("$", "")); // Replace $ with empty string
                double convertToBaht = priceInDollar * exchangeRate;
                double convertToDiscount = convertToBaht - (convertToBaht * 0.05);
                System.out.printf("%-5d %-15s %-15s %-10s%n", i, products[1], String.format("%.2f (%.2f)", convertToDiscount, convertToBaht), products[3]);
            } else if (activeUserData[4].charAt(6) == '3') {
                i++;
                double priceInDollar = Double.parseDouble(products[2].replace("$", "")); // Replace $ with empty string
                double convertToBaht = priceInDollar * exchangeRate;
                double convertToDiscount = convertToBaht - (convertToBaht * 0.1);
                System.out.printf("%-5d %-15s %-15s %-10s%n", i, products[1], String.format("%.2f (%.2f)", convertToDiscount, convertToBaht), products[3]);
            }
        }
        System.out.println("=========================================");
    }

    // เมธอดสำหรับการเรียงลำดับสินค้าโดยชื่อ (Shell Sort)
    private static List<String[]> shellSortByName(List<String[]> productList) {
        int n = productList.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                String[] temp = productList.get(i);
                int j;
                for (j = i; j >= gap && productList.get(j - gap)[1].compareTo(temp[1]) < 0; j -= gap) {
                    productList.set(j, productList.get(j - gap));
                }
                productList.set(j, temp);
            }
        }
        return productList;
    }

    // เมธอดสำหรับการเรียงลำดับจำนวนสินค้า (Bubble Sort)
    public static List<String[]> bubbleSortByQuantity(List<String[]> productList) {
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
        return productList;
    }

    public static void editMember() throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<String[]> member = new ArrayList<>();
        try (Scanner readFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/Member.txt"))) {
            while (readFile.hasNextLine()) {
                String[] data = readFile.nextLine().split("\t");
                if (data.length == 7) {
                    member.add(data);// เพิ่มข้อมูลหมวดหมู่ลงใน List
                }
            }
        }
        while (true) {
            System.out.println("===== SE STORE's Member =====");
            System.out.printf("%-5s %-15s %-15s\n", "#", "Name", "Email");
            for (int i = 0; i < member.size(); i++) {
                System.out.println((i + 1) + "\t" + member.get(i)[1] + "\t" + member.get(i)[2] + "\t" + member.get(i)[3]);
            }
            System.out.println("=========================================");
            System.out.println("Type Member Number, You want to edit or Press Q to Exit");
            System.out.print("Select (1-" + member.size() + ") : ");
            String select = scanner.next();
            if (select.matches("[1-9]|1[0-9]|2[0-9]") && Integer.parseInt(select) <= member.size()) { // ตรวจสอบว่าเลือกตามจำนวนสมาชิกที่มีอยู่
                int lineNum = Integer.parseInt(select) - 1; // index ของ member
                String[] selectedMember = member.get(lineNum);
                // แก้ไขข้อมูล
                System.out.println("==== Edit info of " + selectedMember[1] + " ====");
                System.out.print("Firstname: ");
                String addFirstname = scanner.next();
                boolean checkInfo = false;
                String addCode = null;
                if (!addFirstname.equals("-")) {
                    selectedMember[1] = addFirstname;
                }

                System.out.print("Lastname: ");
                String addLastname = scanner.next();
                if (!addLastname.equals("-")) {
                    selectedMember[2] = addLastname;
                }

                System.out.print("Email: ");
                String addEmail = scanner.next();
                if (!addEmail.equals("-")) {
                    selectedMember[3] = addEmail;
                }

                System.out.print("Phone: ");
                String addPhone = scanner.next();
                if (!addPhone.equals("-")) {
                    selectedMember[5] = addPhone;
                }
                if (addFirstname.length() > 2 && addLastname.length() > 2 && addEmail.length() > 2 && addEmail.contains("@") && addPhone.length() == 10) {
                    checkInfo = true;
                }
                if (checkInfo == true) {
                    // อัปเดตข้อมูลใน list
                    member.set(lineNum, selectedMember);

                    // เขียนข้อมูลที่แก้ไขใหม่กลับไปยังไฟล์
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/Member.txt"))) {
                        for (String[] members : member) {
                            writer.write(String.join("\t", members));
                            writer.newLine();
                        }
                    }
                    System.out.println("Success - Member has been updated!");
                    break;
                } else if (checkInfo == false) {
                    System.out.println("Error! - Your Information are Incorrect!");
                    break;
                }
            } else if (select.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("Invalid input. Please press Q to exit.");
            }
        }
    }

    public static void editProduct() throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<String[]> products = new ArrayList<>();

        // อ่านข้อมูลสินค้าจากไฟล์ PRODUCT.txt
        try (Scanner readFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/PRODUCT.txt"))) {
            while (readFile.hasNextLine()) {
                String[] data = readFile.nextLine().split("\t");
                if (data.length == 5) { // ตรวจสอบว่าข้อมูลถูกต้องและมี 5 ฟิลด์
                    products.add(data); // เพิ่มข้อมูลลงใน List
                }
            }
        }

        // แสดงรายการสินค้าที่มีอยู่
        while (true) {
            System.out.println("===== SE STORE's Products =====");
            showProduct(); // แสดงรายการสินค้า
            System.out.println("Type Product Number, You want to edit or Press Q to Exit");
            System.out.print("Select (1-" + products.size() + ") : ");
            String select = scanner.next();

            // ตรวจสอบว่าผู้ใช้เลือกสินค้าหมายเลข 1-50 หรือกด Q เพื่อออก
            if (select.matches("[1-9]|[1-4][0-9]|50")) {
                int lineNum = Integer.parseInt(select) - 1; // index ของ product
                String[] selectedProduct = products.get(lineNum);

                // แก้ไขข้อมูลสินค้า
                System.out.println("==== Edit info of " + selectedProduct[1] + " ====");
                System.out.print("Name : ");
                String addName = scanner.next();

                // ตรวจสอบว่าแก้ไขชื่อหรือไม่
                if (!addName.equals("-")) {
                    selectedProduct[1] = addName;
                }

                // แก้ไขจำนวนสินค้า
                System.out.print("Quantity (+ or -) : ");
                String addQuantity = scanner.next();

                boolean checkInfo = false; // ตัวแปรตรวจสอบความถูกต้องของข้อมูล

                // ตรวจสอบว่าผู้ใช้ป้อนเครื่องหมาย + หรือ - ตามด้วยตัวเลข
                if (addQuantity.matches("\\+\\d+")) { // กรณีมีเครื่องหมาย +
                    int currentQuantity = Integer.parseInt(selectedProduct[3]);
                    int changeQuantity = Integer.parseInt(addQuantity.substring(1)); // ตัด + ออก
                    selectedProduct[3] = String.valueOf(currentQuantity + changeQuantity); // เพิ่มจำนวนสินค้า
                    checkInfo = true;
                } else if (addQuantity.matches("-\\d+")) { // กรณีมีเครื่องหมาย -
                    int currentQuantity = Integer.parseInt(selectedProduct[3]);
                    int changeQuantity = Integer.parseInt(addQuantity); // ค่าเป็น - อยู่แล้ว
                    selectedProduct[3] = String.valueOf(currentQuantity + changeQuantity); // ลดจำนวนสินค้า
                    checkInfo = true;
                } else if (addQuantity.equals("-")) {
                    // กรณีที่ผู้ใช้กรอก "-" ไม่ต้องแก้ไขข้อมูล
                    checkInfo = true; // ถือว่าข้อมูลถูกต้อง
                } else {
                    System.out.println("Error! - Your Information is Incorrect!");
                    continue; // กลับไปยังหน้าจอการแก้ไขใหม่
                }

                // หากข้อมูลถูกต้อง
                if (checkInfo) {
                    // อัปเดตข้อมูลใน list
                    products.set(lineNum, selectedProduct);

                    // เขียนข้อมูลที่แก้ไขใหม่กลับไปยังไฟล์
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/PRODUCT.txt"))) {
                        for (String[] product : products) {
                            writer.write(String.join("\t", product));
                            writer.newLine();
                        }
                    }
                    System.out.println("Success - " + selectedProduct[1] + " has been updated!");
                    break; // กลับไป Display#1
                }
            } else if (select.equalsIgnoreCase("q")) {
                break; // ออกจากฟังก์ชัน
            } else {
                System.out.println("Invalid input. Please press Q to exit.");
            }
        }
    }

    public static void orderProduct(String idMember) throws IOException {
        Scanner scanner = new Scanner(System.in);  // ประกาศตัวแปร scanner เพื่อรับอินพุตจากผู้ใช้
        List<String[]> member = new ArrayList<>();  // ประกาศลิสต์เพื่อเก็บข้อมูลสมาชิก
        List<String[]> products = new ArrayList<>(); // ประกาศลิสต์เพื่อเก็บข้อมูลสินค้า
        List<String[]> cart = new ArrayList<>();    // ประกาศลิสต์เพื่อเก็บข้อมูลตะกร้าสินค้า

        // อ่านข้อมูลสมาชิกจากไฟล์ Member
        try (Scanner readFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/Member.txt"))) {
            while (readFile.hasNextLine()) {  // อ่านข้อมูลทีละบรรทัด
                String[] data = readFile.nextLine().split("\t");  // แยกข้อมูลแต่ละบรรทัดด้วย "\t"
                if (data.length == 7) {  // ถ้าข้อมูลมีครบ 7 คอลัมน์
                    member.add(data);  // เก็บข้อมูลสมาชิกลงในลิสต์
                }
            }
        }

        // โหลดสินค้าจากไฟล์ Product
        try (Scanner readProductFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/PRODUCT.txt"))) {
            while (readProductFile.hasNextLine()) {  // อ่านข้อมูลสินค้าแต่ละบรรทัด
                String[] productData = readProductFile.nextLine().split("\t");  // แยกข้อมูลแต่ละบรรทัดด้วย "\t"
                if (productData.length == 5) {  // ถ้าข้อมูลสินค้าในบรรทัดนั้นมีครบ 5 คอลัมน์
                    products.add(productData);  // เก็บข้อมูลสินค้าลงในลิสต์
                }
            }
        }

        showProduct(); // แสดงรายการสินค้าทั้งหมด
        System.out.println("Enter the product number followed by the quantity.");  // แจ้งให้ผู้ใช้ป้อนหมายเลขสินค้าและจำนวน
        System.out.println("1. How to Order");  // ตัวเลือกที่ 1 สำหรับวิธีการสั่งซื้อ
        System.out.println("2. List Products");  // ตัวเลือกที่ 2 สำหรับแสดงรายการสินค้า
        System.out.println("Q. Exit");  // ตัวเลือก Q สำหรับออกจากโปรแกรม

        // วนลูปสำหรับการทำงานจนกว่าผู้ใช้จะออก (เลือก 'Q')
        for (; ; ) {
            System.out.print("Enter: ");  // รอให้ผู้ใช้ป้อนคำสั่ง
            String select = scanner.nextLine();  // รับคำสั่งจากผู้ใช้

            // ถ้าผู้ใช้ป้อน "q" หรือ "Q" เพื่อออกจากโปรแกรม
            if (select.equalsIgnoreCase("q")) {
                // บันทึกข้อมูลตะกร้าสินค้าลงไฟล์ CART.txt
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/CART.txt"))) {
                    for (String[] cartItem : cart) {  // วนลูปข้อมูลในตะกร้าสินค้า
                        writer.write(String.join("\t", cartItem));  // เขียนข้อมูลแต่ละรายการลงไฟล์ด้วยการแยกด้วย "\t"
                        writer.newLine();  // ขึ้นบรรทัดใหม่สำหรับแต่ละรายการสินค้า
                    }
                }
                System.out.println("Your cart has been saved!");  // แสดงข้อความแจ้งว่าตะกร้าถูกบันทึกแล้ว
                break;  // ออกจากลูปและโปรแกรม
            }

            String[] values = select.split("\\s+");  // แยกคำสั่งที่ผู้ใช้ป้อนด้วยช่องว่าง

            // ถ้าผู้ใช้ป้อนตัวเลือกเดียว เช่น "1", "2", หรือ "Q"
            if (values.length == 1) {
                if (values[0].equalsIgnoreCase("1")) {  // ถ้าผู้ใช้เลือก "1" สำหรับวิธีการสั่งซื้อ
                    System.out.println("How to Order:");
                    System.out.println("\tTo Add Product:");
                    System.out.println("\t\tEnter the product number followed by the quantity.");
                    System.out.println("\t\tExample: 1 50 (Adds 50 chips)");
                    System.out.println("\tTo Adjust Quantity:");
                    System.out.println("\t\t+ to add more items: 1 +50 (Adds 50 more chips)");
                    System.out.println("\t\t- to reduce items: 1 -50 (Removes 50 chips)");
                } else if (values[0].equalsIgnoreCase("2")) {  // ถ้าผู้ใช้เลือก "2" เพื่อดูรายการสินค้า
                    System.out.println("=========== SE STORE's Products ===========");
                    showProduct(); // เรียกฟังก์ชันแสดงรายการสินค้า
                } else {
                    System.out.println("Your input is invalid!");  // ถ้าผู้ใช้ป้อนคำสั่งไม่ถูกต้อง
                }
            } else if (values.length == 2) {  // ถ้าผู้ใช้ป้อนหมายเลขสินค้าและจำนวน (หรือการปรับจำนวน)
                String productNumber = values[0];  // เก็บหมายเลขสินค้าที่ผู้ใช้ป้อน
                String quantityInput = values[1];  // เก็บจำนวนที่ผู้ใช้ป้อน
                int quantity = Integer.parseInt(quantityInput);

                // ตรวจสอบว่าหมายเลขสินค้าเป็นตัวเลขหรือไม่
                if (!productNumber.matches("\\d+")) {
                    System.out.println("Invalid product number!");  // ถ้าหมายเลขสินค้าไม่เป็นตัวเลข
                    continue;  // ข้ามการทำงานในลูปและเริ่มรอบใหม่
                }

                int productIndex = Integer.parseInt(productNumber) - 1;  // แปลงหมายเลขสินค้าเป็นดัชนีในลิสต์สินค้า

                // ตรวจสอบว่าหมายเลขสินค้าที่ผู้ใช้ป้อนไม่เกินขอบเขตของลิสต์สินค้า
                if (productIndex < 0 || productIndex >= products.size()) {
                    System.out.println("Product number out of range!");  // ถ้าหมายเลขสินค้าเกินจากที่มีในลิสต์
                    continue;  // ข้ามการทำงานในลูปและเริ่มรอบใหม่
                }

                String[] selectedProduct = products.get(productIndex);  // ดึงข้อมูลสินค้าที่ผู้ใช้เลือกจากลิสต์สินค้า
                String productId = selectedProduct[0];  // เก็บ product ID
                String productName = selectedProduct[1];  // เก็บชื่อสินค้า
                int availableStock = Integer.parseInt(selectedProduct[3]);  // ดึงจำนวนสินค้าในสต็อกจากลิสต์สินค้า

                // โหลดข้อมูลตะกร้าสินค้าจากไฟล์ CART.txt
                cart = new ArrayList<>();  // เคลียร์ตะกร้าสินค้า
                try (Scanner cartFile = new Scanner(new File("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/CART.txt"))) {
                    while (cartFile.hasNextLine()) {  // อ่านข้อมูลจากไฟล์ CART.txt ทีละบรรทัด
                        String[] cartData = cartFile.nextLine().split("\t");  // แยกข้อมูลด้วย "\t"
                        if (cartData.length == 3) {  // ถ้าข้อมูลมี 3 คอลัมน์
                            cart.add(cartData);  // เพิ่มข้อมูลลงในตะกร้าสินค้า
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Cart file not found!");  // ถ้าไม่พบไฟล์ตะกร้า
                    continue;  // ข้ามการทำงานในลูปและเริ่มรอบใหม่
                }

                // ตรวจสอบว่าจำนวนสินค้าที่ผู้ใช้ป้อนเป็นรูปแบบที่ถูกต้อง (เพิ่ม, ลด, หรือแทนที่)
                if (quantityInput.matches("\\+\\d+") || quantityInput.matches("-\\d+") || quantityInput.matches("\\d+")) {
                    int changeQuantity = Integer.parseInt(quantityInput.replace("+", ""));  // แปลงจำนวนที่ผู้ใช้ป้อนเป็นตัวเลข (ถ้ามี + ให้เอาออก)
                    boolean found = false;  // ตัวแปรสำหรับตรวจสอบว่าสินค้านี้มีอยู่ในตะกร้าหรือไม่

                    // วนลูปเพื่อเช็คว่าสินค้านี้มีอยู่ในตะกร้าหรือไม่
                    for (String[] cartItem : cart) {
                        if (cartItem[0].equals(idMember) && cartItem[1].equals(productId)) {  // ถ้ารหัสสมาชิกและสินค้าในตะกร้าตรงกัน
                            int currentQuantity = Integer.parseInt(cartItem[2]);  // ดึงจำนวนสินค้าที่มีอยู่ในตะกร้า

                            //System.out.println(""+quantity+""+availableStock);

                            // กรณีที่ผู้ใช้ต้องการเพิ่มสินค้า
                            if (quantityInput.startsWith("+")) {
                                int newQuantity = currentQuantity + changeQuantity;  // คำนวณจำนวนสินค้าใหม่หลังจากเพิ่ม
                                if (newQuantity > availableStock) {  // ตรวจสอบว่าจำนวนสินค้าใหม่เกินสต็อกหรือไม่
                                    System.out.println("Not enough stock available for " + productName + "!");  // แจ้งเตือนถ้าสินค้าในสต็อกไม่พอ
                                } else {
                                    cartItem[2] = String.valueOf(newQuantity);  // อัปเดตจำนวนสินค้าในตะกร้า
                                    System.out.println(productName + " quantity increased to " + cartItem[2]);  // แจ้งจำนวนสินค้าที่เพิ่มขึ้น
                                }
                                // กรณีที่ผู้ใช้ต้องการลดสินค้า
                            } else if (quantityInput.startsWith("-")) {
                                int newQuantity = currentQuantity + changeQuantity;  // คำนวณจำนวนสินค้าใหม่หลังจากลด
                                if (newQuantity <= 0) {  // ถ้าจำนวนสินค้าเหลือ 0 หรือน้อยกว่า
                                    cart.remove(cartItem);  // ลบสินค้านั้นออกจากตะกร้า
                                    System.out.println(productName + " removed from cart.");  // แจ้งว่าสินค้าถูกลบ
                                } else {
                                    cartItem[2] = String.valueOf(newQuantity);  // อัปเดตจำนวนสินค้าในตะกร้า
                                    System.out.println(productName + " quantity decreased to " + cartItem[2]);  // แจ้งจำนวนสินค้าที่ลดลง
                                }
                                // กรณีที่ผู้ใช้ระบุจำนวนที่ต้องการแทนที่
                            } else {
                                if (changeQuantity > availableStock) {  // ถ้าจำนวนสินค้าเกินสต็อก
                                    System.out.println("Not enough stock available for " + productName + "!");  // แจ้งเตือนว่าจำนวนสินค้าเกินสต็อก
                                } else {
                                    cartItem[2] = String.valueOf(changeQuantity);  // อัปเดตจำนวนสินค้าในตะกร้า
                                    System.out.println(productName + " quantity set to " + cartItem[2]);  // แจ้งจำนวนสินค้าที่แทนที่
                                }
                            }


                            found = true;  // กำหนดว่าพบสินค้านี้ในตะกร้าแล้ว
                            break;  // หยุดการค้นหาสินค้าในตะกร้า
                        }
                    }

                    // ถ้าไม่พบสินค้านี้ในตะกร้าและจำนวนที่ผู้ใช้ป้อนไม่ใช่การลด
                    if (!found && !quantityInput.startsWith("-")) {
                        if (changeQuantity > availableStock) {  // ตรวจสอบว่าจำนวนสินค้าเกินสต็อกหรือไม่
                            System.out.println("Not enough stock available for " + productName + "!");  // แจ้งเตือนว่าจำนวนสินค้าเกินสต็อก
                        } else {
                            String[] newItem = {idMember, productId, String.valueOf(changeQuantity)};  // สร้างรายการใหม่ในตะกร้า
                            cart.add(newItem);  // เพิ่มรายการสินค้าในตะกร้า
                            System.out.println(productName + " added to cart with quantity " + changeQuantity);  // แจ้งสินค้าที่ถูกเพิ่ม
                        }
                    }

                    // บันทึกตะกร้าสินค้าลงในไฟล์ CART.txt
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/salsabeelasa-e/Documents/Year2_Semester1/PSP/src/CART.txt"))) {
                        for (String[] cartItem : cart) {  // วนลูปเพื่อเขียนข้อมูลตะกร้าลงไฟล์
                            writer.write(String.join("\t", cartItem));  // เขียนข้อมูลแต่ละรายการลงไฟล์ด้วยการแยกด้วย "\t"
                            writer.newLine();  // ขึ้นบรรทัดใหม่สำหรับแต่ละรายการสินค้า
                        }
                    } catch (IOException e) {
                        System.out.println("Error writing to cart file!");  // แสดงข้อความเตือนถ้ามีข้อผิดพลาดในการเขียนไฟล์
                    }

                } else {
                    System.out.println("Invalid quantity input!");  // ถ้าผู้ใช้ป้อนจำนวนสินค้าไม่ถูกต้อง
                }
            } else {
                System.out.println("Your input is invalid!");  // ถ้าผู้ใช้ป้อนคำสั่งผิดรูปแบบ
            }
        }
    }
}



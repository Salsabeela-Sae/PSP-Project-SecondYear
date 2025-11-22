import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        main_menu();// เรียกใช้งานเมนูหลัก

    }
    static String oldline = "";
    public static void main_menu() throws IOException {
        Scanner input = new Scanner(System.in);
        int checker;
        System.out.println("-------------------");
        System.out.println("   SE BUU Drink"); // แสดงชื่อร้าน
        System.out.println("-------------------");
        System.out.println("1. Ordering your drink"); // เลือกสั่งเครื่องดื่ม
        System.out.println("2. Virtual machine"); // เข้าถึงเครื่องจำลอง
        System.out.println("3. Login"); // เข้าสู่ระบบ
        System.out.println("4. Exit"); // ออกจากระบบ
        System.out.println("-------------------");
        System.out.print("Enter Number : "); // รับค่าเมนูจากผู้ใช้
        checker = input.nextInt();
        System.out.println("-------------------");

        // ตรวจสอบค่าที่ผู้ใช้กรอกและเรียกใช้เมนูที่ตรงกัน
        if (checker == 1) {
            Order_Menu(); // เรียกเมนูสั่งเครื่องดื่ม
        } else if (checker == 2) {
            VirtualMachine(); // เข้าสู่เมนูเครื่องจำลอง
        } else if (checker == 3) {
            login_menu(); // เข้าสู่ระบบ
        }
    }

    public static void Recommend_menu() throws IOException {
        System.out.println("-------------------------");
        System.out.println("Recommend the drink menu"); // เมนูแนะนำเครื่องดื่ม
        System.out.println("-------------------------");
        System.out.println("1. By Age"); // แนะนำตามอายุ
        System.out.println("2. By Gender"); // แนะนำตามเพศ
        System.out.println("3. By Type"); // แนะนำตามประเภท
        System.out.println("4. By Keywords"); // แนะนำตามคำค้นหา
        System.out.println("5. Exit"); // ออกจากเมนู
        System.out.println("-------------------------");
        System.out.print("Enter Number : "); // รับค่าเมนูจากผู้ใช้
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        System.out.println("-------------------------");

        // เรียกเมนูแนะนำตามตัวเลือกที่ผู้ใช้กรอก
        if (num == 1) {
            BY_AGE(); // แนะนำตามอายุ
        } else if (num == 2) {
            BY_Gender(); // แนะนำตามเพศ
        } else if (num == 3) {
            BY_Type(); // แนะนำตามประเภท
        } else if (num == 4) {
            BY_Keywords(); // แนะนำตามคำค้นหา
        } else {
            main_menu(); // กลับไปที่เมนูหลัก
        }
    }

    public static void BY_Keywords() throws IOException {
        System.out.print("Enter Keywords : "); // รับคำค้นหาจากผู้ใช้
        Scanner input = new Scanner(System.in);
        String keywords = input.next();
        System.out.println("-------------------------");
        System.out.println("ID\t\tMenu");
        System.out.println("-------------------------");
        // อ่านไฟล์เมนูและค้นหาข้อมูลตามคำค้นหา
        Scanner sc_menu = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Menu.txt"));
        ArrayList<String> menu_id = new ArrayList<>();
        ArrayList<String> menu_name = new ArrayList<>();
        ArrayList<String> menu_key = new ArrayList<>();
        while (sc_menu.hasNextLine()){
            String[] arr_menu = sc_menu.nextLine().split("\t");
            menu_id.add(arr_menu[0]);
            menu_name.add(arr_menu[1]);
            menu_key.add(arr_menu[6]);
        }
        // แสดงรายการที่ตรงกับคำค้นหา
        for (int i = 0 ; i < menu_key.size() ; i++){
            int index;
            index = menu_key.get(i).toLowerCase().indexOf(keywords);
            if (index != -1){
                System.out.println(menu_id.get(i) + "\t\t" + menu_name.get(i));
            }
        }
        Recommend_menu();// กลับไปที่เมนูแนะนำ
    }

    public static void BY_Type() throws IOException {
        System.out.print("Enter type (SD/HD) : ");// รับประเภท SD หรือ HD จากผู้ใช้
        Scanner input = new Scanner(System.in);
        String type = input.next();
        System.out.println("-------------------------");
        System.out.println("ID\t\tMenu");
        System.out.println("-------------------------");
        // อ่านข้อมูลจากไฟล์เมนูและแยกประเภท SD และ HD
        Scanner sc_menu = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Menu.txt"));
        ArrayList<String> arr_SD_name = new ArrayList<>();
        ArrayList<String> arr_SD_id = new ArrayList<>();
        ArrayList<String> arr_HD_id = new ArrayList<>();
        ArrayList<String> arr_HD_name = new ArrayList<>();
        while (sc_menu.hasNextLine()){
            String[] arr_menu = sc_menu.nextLine().split("\t");
            if (arr_menu[5].equalsIgnoreCase("SD")){
                arr_SD_id.add(arr_menu[0]);
                arr_SD_name.add(arr_menu[1]);
            } else if (arr_menu[5].equalsIgnoreCase("HD")) {
                arr_HD_id.add(arr_menu[0]);
                arr_HD_name.add(arr_menu[1]);
            }
        }
        // แสดงเมนูตามประเภทที่ผู้ใช้เลือก
        if (type.equalsIgnoreCase("SD")){
            for (int i = 0 ; i < arr_SD_id.size() ; i++){
                System.out.println(arr_SD_id.get(i) + "\t\t" + arr_SD_name.get(i));
            }
        } else if (type.equalsIgnoreCase("HD")) {
            for (int i = 0 ; i < arr_HD_id.size() ; i++){
                System.out.println(arr_HD_id.get(i) + "\t\t" + arr_HD_name.get(i));
            }
        }
        Recommend_menu();// กลับไปที่เมนูแนะนำ
    }

    public static void BY_Gender() throws IOException {
        System.out.print("Enter your gender (F/M) : ");
        Scanner input = new Scanner(System.in);
        String gender = input.next();
        System.out.println("-------------------------");
        System.out.println("ID\t\tMenu");
        System.out.println("-------------------------");
        // สร้างตัวอ่านไฟล์ (Scanner) สำหรับอ่านไฟล์ "Menu.txt"
        Scanner sc_menu = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Menu.txt"));
        ArrayList<String> male_id = new ArrayList<>(); // เก็บ ID สำหรับเพศชาย
        ArrayList<String> female_id = new ArrayList<>(); // เก็บ ID สำหรับเพศหญิง
        ArrayList<String> all_id = new ArrayList<>(); // เก็บ ID สำหรับเมนูที่เข้าถึงได้ทุกเพศ
        ArrayList<String> male_name = new ArrayList<>(); // เก็บชื่อเมนูเพศชาย
        ArrayList<String> female_name = new ArrayList<>(); // เก็บชื่อเมนูเพศหญิง
        ArrayList<String> all_name = new ArrayList<>(); // เก็บชื่อเมนูที่เข้าถึงได้ทุกเพศ

        // อ่านข้อมูลจากไฟล์ Menu.txt
        while (sc_menu.hasNextLine()){
            String[] arr_menu = sc_menu.nextLine().split("\t");
            if (arr_menu[4].equalsIgnoreCase("m")){ // ตรวจสอบว่าข้อมูลเป็นของเพศชาย
                male_id.add(arr_menu[0]);
                male_name.add(arr_menu[1]);
            } else if (arr_menu[4].equalsIgnoreCase("f")) { // ตรวจสอบว่าข้อมูลเป็นของเพศหญิง
                female_id.add(arr_menu[0]);
                female_name.add(arr_menu[1]);
            } else if (arr_menu[4].equalsIgnoreCase("a")) { // เมนูสำหรับทุกเพศ
                all_id.add(arr_menu[0]);
                all_name.add(arr_menu[1]);
            }
        }

        // แสดงเมนูตามเพศที่ผู้ใช้กรอก
        if (gender.equalsIgnoreCase("m")){
            for (int i = 0 ; i < male_name.size() ; i++){
                System.out.println(male_id.get(i) + "\t\t" + male_name.get(i));
            }
            System.out.println("-------------------------");
            for (int i = 0 ; i < all_name.size() ; i++){
                System.out.println(all_id.get(i) + "\t\t" + all_name.get(i));
            }
        } else if (gender.equalsIgnoreCase("f")) {
            for (int i = 0 ; i < female_name.size() ; i++){
                System.out.println(female_id.get(i) + "\t\t" + female_name.get(i));
            }
            System.out.println("-------------------------");
            for (int i = 0 ; i < all_name.size() ; i++){
                System.out.println(all_id.get(i) + "\t\t" + all_name.get(i));
            }
        }
        Recommend_menu(); // เรียกเมธอด Recommend_menu เพื่อแสดงเมนูแนะนำ
    }

    public static void BY_AGE() throws IOException {
        System.out.print("Enter your Age : ");
        Scanner input = new Scanner(System.in);
        int age = input.nextInt();
        System.out.println("-------------------------");
        System.out.println("ID\t\tMenu");
        System.out.println("-------------------------");

        // อ่านไฟล์ Menu.txt เพื่อกรองเมนูตามอายุของผู้ใช้
        Scanner sc_menu = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Menu.txt"));
        ArrayList<String> menu_id = new ArrayList<>();
        ArrayList<String> menu_name = new ArrayList<>();
        ArrayList<String> menu_age = new ArrayList<>();
        while (sc_menu.hasNextLine()){
            String[] arr_menu = sc_menu.nextLine().split("\t");
            menu_id.add(arr_menu[0]);
            menu_name.add(arr_menu[1]);
            menu_age.add(arr_menu[3]);
        }
        // แสดงเมนูตามช่วงอายุที่กำหนด
        for (int i = 0 ; i < menu_age.size() ; i++){
            if (age >= Integer.parseInt(menu_age.get(i))){
                System.out.println(menu_id.get(i) + "\t\t" + menu_name.get(i));
            }
        }
        Recommend_menu();
    }

    public static void VirtualMachine() throws IOException{
        Scanner input = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("ID     City");
        System.out.println("----------------------");
        // อ่านไฟล์ Machine.txt เพื่อแสดงรายการเครื่อง
        Scanner sc_machine = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Machine.txt"));
        ArrayList<String> mac_id = new ArrayList<>();
        ArrayList<String> format_mac_id = new ArrayList<>();
        ArrayList<String> mac_city = new ArrayList<>();
        while (sc_machine.hasNextLine()){
            String[] arr_mac = sc_machine.nextLine().split("\t");
            System.out.println(arr_mac[0].substring(3 , 5) + arr_mac[0].charAt(8) + "   " + arr_mac[1]);
            mac_id.add(arr_mac[0]);
            format_mac_id.add(arr_mac[0].substring(3 , 5) + arr_mac[0].charAt(8));
            mac_city.add(arr_mac[1]);
        }
        sc_machine.close();
        System.out.println("----------------------");
        System.out.print("Enter Machine ID : ");
        String input_mac_id = input.next();
        System.out.println("----------------------");
        System.out.println();
        // ค้นหาและแสดงรายละเอียดของเครื่องตาม ID ที่ป้อน
        for (int i = 0 ; i < format_mac_id.size() ; i++) {
            if (input_mac_id.equalsIgnoreCase(format_mac_id.get(i))) {
                System.out.println("----------------------");
                System.out.println("Machine ID : " + format_mac_id.get(i) + " (" + mac_city.get(i) + ")");
                System.out.println("-------------------------");
                System.out.println("1. Use your PIN to get a drink.");
                System.out.println("2. Most popular drink");
                System.out.println("3. Recommend the drink menu.");
                System.out.println("4. Exit.");
                System.out.println("-------------------------");
                System.out.print("Enter Number : ");
                int choice = input.nextInt();

                // จัดการการเลือกตามหมายเลขที่ผู้ใช้เลือก
                if (choice == 1){
                    System.out.println("-----------------------");
                    System.out.print("Enter your PIN : ");
                    String input_pin = input.next();
                    System.out.println("----------------------");

                    // อ่านไฟล์ Order.txt เพื่อเช็คสถานะการสั่งเครื่องดื่มตาม PIN
                    Scanner sc_order = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Order.txt"));
                    ArrayList<String> pins = new ArrayList<>();
                    ArrayList<String> order_menu_ids = new ArrayList<>();
                    ArrayList<String> order_mac_city = new ArrayList<>();
                    ArrayList<String> order_status = new ArrayList<>();
                    ArrayList<String> order_id = new ArrayList<>();
                    while (sc_order.hasNextLine()){
                        String[] arr_order = sc_order.nextLine().split("\t");
                        pins.add(arr_order[4]);
                        order_menu_ids.add(arr_order[1]);
                        order_mac_city.add(arr_order[2].substring(3 , 5) + arr_order[2].charAt(8));
                        order_status.add(arr_order[5]);
                        order_id.add(arr_order[0]);
                    }
                    sc_order.close();
                    Scanner sc_menu = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Menu.txt"));
                    ArrayList<String> menu_names = new ArrayList<>();
                    ArrayList<String> menu_ids = new ArrayList<>();
                    ArrayList<String> menu_prices = new ArrayList<>();
                    while (sc_menu.hasNextLine()) {
                        String[] arr_menu = sc_menu.nextLine().split("\t");
                        menu_names.add(arr_menu[1]);
                        menu_ids.add(arr_menu[0]);
                        menu_prices.add(arr_menu[2]);
                    }
                    sc_menu.close();

                    // เช็คสถานะการสั่งเครื่องดื่มตาม PIN และ ID ของเครื่อง
                    for (int j = 0 ; j < pins.size() ; j++){
                        if (input_pin.equals(pins.get(j)) && input_mac_id.equals(order_mac_city.get(j))){
                            if (order_status.get(j).equals("0")){
                                for (int k = 0 ; k < menu_ids.size() ; k++){
                                    if (menu_ids.get(k).equals(order_menu_ids.get(j))){
                                        double format_prices = Integer.parseInt(menu_prices.get(k)) / 36.96;
                                        System.out.println("Your \"" + menu_names.get(k) + "\" is currently in the process of being prepared, please wait a moment.");

                                        //เขียน Order ใหม่
                                        sc_order = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Order.txt"));
                                        LocalDate today = LocalDate.now();
                                        String Date = String.valueOf(today);
                                        String formatDate = Date.substring(0 , 4) + "." + Date.substring(5 , 7) + "." + Date.substring(8);
                                        ArrayList<String> new_data_order = new ArrayList<>();
                                        while (sc_order.hasNextLine()){
                                            String order_info = sc_order.nextLine();
                                            String[] arr_order = order_info.split("\t");
                                            if (arr_order[0].equals(order_id.get(j))){
                                                new_data_order.add(arr_order[0] + "\t" + arr_order[1] + "\t" + arr_order[2] + "\t" + arr_order[3] + "\t" + arr_order[4] +"\t1\t" + formatDate);
                                            }else {
                                                new_data_order.add(order_info);
                                            }
                                        }
                                        try (FileWriter fw = new FileWriter("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Order.txt")) {
                                            for (int l = 0 ; l < new_data_order.size() ; l++){
                                                fw.write(new_data_order.get(l));
                                                fw.write("\n");
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }

                                        //เขียน mechine ใหม่
                                        sc_machine = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Machine.txt"));
                                        ArrayList<String> new_data_mac = new ArrayList<>();
                                        while (sc_machine.hasNextLine()){
                                            String mac_info = sc_machine.nextLine();
                                            String[] arr_mac = mac_info.split("\t");

                                            if (input_mac_id.equals(arr_mac[0].substring(3 , 5) + arr_mac[0].charAt(8))){
                                                double format_balance = Integer.parseInt(arr_mac[4].charAt(1) + arr_mac[4].substring(3 , 6)) + format_prices;
                                                String string_balance = "$" + String.valueOf(format_balance).charAt(0) + "," + String.valueOf(format_balance).substring(1 , 7);
                                                new_data_mac.add(arr_mac[0] + "\t" + arr_mac[1] + "\t" + arr_mac[2] + "\t" + arr_mac[3] + "\t" + string_balance + "\t" + arr_mac[5]);
                                            }else {
                                                new_data_mac.add(mac_info);
                                            }
                                        }try (FileWriter fw = new FileWriter("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Machine.txt")) {
                                            for (int l = 0 ; l < new_data_mac.size() ; l++){
                                                fw.write(new_data_mac.get(l));
                                                fw.write("\n");
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                        // สั่งเครื่องดื่มและบันทึกข้อมูลออเดอร์และยอดคงเหลือของเครื่อง
                                        main_menu();
                                    }
                                }
                            } else {
                                System.out.println("Your PIN was used");
                                main_menu();
                            }
                        }
                    }
                    System.out.println("Invalid PIN.");
                    main_menu();
                } else if (choice == 2) {
                    Most_drink(format_mac_id.get(i) , mac_city.get(i));
                } else if (choice == 3) {
                    Recommend_menu();
                }else {
                    main_menu();
                }
            }
        }
        main_menu();
    }

    static void Most_drink(String mac_id , String mac_city) throws IOException {
        Scanner input = new Scanner(System.in);

        // แสดงข้อมูลเครื่องจักร ID และเมือง
        System.out.println("----------------------");
        System.out.println("Machine ID : " + mac_id + " (" + mac_city + ")");
        System.out.println("----------------------");

        // แสดงตัวเลือกหมวดเครื่องดื่ม
        System.out.println("1. For-Men");
        System.out.println("2. For-Women");
        System.out.println("3. For-All");
        System.out.println("4. Exit");
        System.out.println("--------------------");

        // รับหมายเลขตัวเลือกจากผู้ใช้
        System.out.print("Enter Number : ");
        int num = input.nextInt();
        System.out.println("--------------------");
        System.out.println();
        // สร้าง ArrayLists สำหรับเก็บข้อมูลของเครื่องดื่มแต่ละประเภท
        ArrayList<String> arr_men = new ArrayList<>();
        ArrayList<String> arr_women = new ArrayList<>();
        ArrayList<String> arr_all = new ArrayList<>();
        ArrayList<String> men = new ArrayList<>();
        ArrayList<String> women = new ArrayList<>();
        ArrayList<String> all = new ArrayList<>();

        Scanner read = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Order.txt"));
        // อ่านแต่ละบรรทัดจากไฟล์ Order.txt
        while (read.hasNextLine()){
            String line = read.nextLine();
            String[] str = line.split("\t");
            // ตรวจสอบประเภทเครื่องดื่มและเพิ่มลงใน ArrayList ที่เกี่ยวข้อง
            if (str[1].equals("412") || str[1].equals("415") || str[1].equals("416")){
                arr_men.add(line);
                men.add(str[1]);
            } else if (str[1].equals("405") || str[1].equals("407") || str[1].equals("409")) {
                arr_women.add(line);
                women.add(str[1]);
            } else if (str[1].equals("401") || str[1].equals("402") || str[1].equals("403") || str[1].equals("404") || str[1].equals("406") || str[1].equals("408") || str[1].equals("410") || str[1].equals("411") || str[1].equals("413") || str[1].equals("414") || str[1].equals("417")) {
                arr_all.add(line);
                all.add(str[1]);
            }
        }
        // กำหนด ArrayList list ที่ใช้เก็บ ID เครื่องดื่มที่มีการดื่มมากที่สุดตามหมวดหมู่
        ArrayList<String> list = new ArrayList<>();
        //most drink men กรณีเลือกหมวด For-Men
        if (num == 1) {
            Map<String, Integer> countMap = countDuplicates(men); // นับจำนวนซ้ำของเครื่องดื่ม
            List<Map.Entry<String, Integer>> sortedList = sortMapByValue(countMap); // จัดเรียงตามจำนวนครั้งที่ซ้ำ

            for (Map.Entry<String, Integer> entry : sortedList) {
                list.add(entry.getKey()); // เพิ่มเครื่องดื่มที่นับแล้วไปที่ list
            }

            // อ่านข้อมูลเมนูเครื่องดื่มจากไฟล์ Menu.txt
            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> name = new ArrayList<>();
            Scanner readmenu = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Menu.txt"));
            while (readmenu.hasNextLine()) {
                String line = readmenu.nextLine();
                String[] str = line.split("\t");
                id.add(str[0]);
                name.add(str[1]);
            }
            // แสดงผลเครื่องดื่ม Top 3 สำหรับ For-Men
            int number = 1;
            System.out.println("--------------------");
            System.out.println("Top 3 For-Men");
            System.out.println("--------------------");
            // เชื่อมโยง ID เครื่องดื่มกับชื่อเครื่องดื่มและแสดงผล
            for (int i = 0 ; i < list.size() ; i++){
                for (int j = 0 ; j < id.size() ; j++){
                    if (list.get(i).equals(id.get(j))){
                        System.out.println("# " + number + " : " + list.get(i) + " " + name.get(j));
                    }
                }
                number++;
            }
            System.out.println("--------------------");
            main_menu();
            //most drink women กรณีเลือกหมวด For-Women
        } else if (num == 2) {
            Map<String, Integer> countMap = countDuplicates(women);
            List<Map.Entry<String , Integer>> sortedList = sortMapByValue(countMap);

            for (Map.Entry<String , Integer> entry : sortedList) {
                list.add(entry.getKey());
            }
            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> name = new ArrayList<>();
            Scanner readmenu = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Menu.txt"));
            while (readmenu.hasNextLine()){
                String line = readmenu.nextLine();
                String[] str = line.split("\t");
                id.add(str[0]);
                name.add(str[1]);
            }
            int number = 1;
            System.out.println("--------------------");
            System.out.println("Top 3 For-Women");
            System.out.println("--------------------");
            for (int i = 0 ; i < list.size() ; i++){
                for (int j = 0 ; j < id.size() ; j++){
                    if (list.get(i).equals(id.get(j))){
                        System.out.println("# " + number + " : " + list.get(i) + " " + name.get(j));
                    }
                }
                number++;
            }
            System.out.println("--------------------");
            main_menu();
            //most drink all กรณีเลือกหมวด For-All
        } else if (num == 3) {
            Map<String, Integer> countMap = countDuplicates(all);
            List<Map.Entry<String , Integer>> sortedList = sortMapByValue(countMap);

            for (Map.Entry<String , Integer> entry : sortedList) {
                list.add(entry.getKey());
            }
            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> name = new ArrayList<>();
            Scanner readmenu = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Menu.txt"));
            while (readmenu.hasNextLine()){
                String line = readmenu.nextLine();
                String[] str = line.split("\t");
                id.add(str[0]);
                name.add(str[1]);
            }
            int number = 1;
            System.out.println("--------------------");
            System.out.println("Top 3 For-All");
            System.out.println("--------------------");
            for (int i = 0 ; i < list.size() ; i++){
                for (int j = 0 ; j < id.size() ; j++){
                    if (i == 3){
                        break;
                    } else if (list.get(i).equals(id.get(j))) {
                        System.out.println("# " + number + " : " + list.get(i) + " " + name.get(j));
                    }
                }
                number++;
            }
            System.out.println("--------------------");
            main_menu();
            // กรณีเลือกออกจากเมนู
        } else if (num == 4) {
            main_menu();
        }
    }

    //จะเก็บคำที่พบใน list พร้อมจำนวนครั้งที่ซ้ำกัน
    public static Map<String , Integer> countDuplicates(ArrayList<String> list) {
        //สร้าง HashMap ชื่อ countMap สำหรับเก็บคู่ของ String และจำนวนครั้งที่พบ (Integer)
        Map<String , Integer> countMap = new HashMap<>();

        //loop วนซ้ำผ่านทุก String ใน list
        for (String str : list) {
            //ตรวจสอบว่าคำ (str) มีใน countMap แล้วหรือไม่
            //ถ้าไม่มีกำหนดค่าเริ่มต้นเป็น 0 โดยใช้ getOrDefault
            //นำค่าที่ได้บวก 1 แล้วอัปเดตใน countMap
            countMap.put(str , countMap.getOrDefault(str , 0) + 1);
        }
        //จำนวนครั้งที่ซ้ำกัน
        return countMap;
    }

    public static List<Map.Entry<String , Integer>> sortMapByValue(Map<String , Integer> map) {
        List<Map.Entry<String , Integer>> sortedList = new ArrayList<>(map.entrySet());

        // ใช้ Collections.sort เพื่อเรียงลำดับตามจำนวนครั้งที่ซ้ำ
        Collections.sort(sortedList , (entry1 , entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        //เรียงตามค่าที่ซ้ำเรียบร้อยแล้ว
        return sortedList;
    }

    static void Order_Menu() throws IOException {
        System.out.println("------------------------");
        System.out.println("ID\t\t\tCity        ");
        System.out.println("------------------------");
        //เก็บข้อมูลของเครื่องจักร
        File file = new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Machine.txt");
        ArrayList<String> MacList = new ArrayList<>();

        Scanner read = new Scanner(file);
        Scanner input = new Scanner(System.in);
        //การอ่านค่าทีละบรรทัดและเกบข้อมูลแต่ละบรรทัดไว้ใน Array list
        while (read.hasNextLine()){
            //เก้บข้อมูลที่อ่านได้ บบรทัดนั้นไว้ใน MacLine
            String MacLine = read.nextLine();
            //การตัดแต่ละท่อนๆไว้ใน array
            String[] strID = MacLine.split("\t");
            //เป็นการเลือกเอาแค่บางตัวจาก array ตำแหน่่งที่ 0 ตัวที่ 3 , 4 , 8 มาไว้ใน MacID
            String MacID = strID[0].substring(3 , 5) + strID[0].substring(8 , 9) ;
            System.out.println(MacID + "\t\t\t" + strID[1]);
            //เพิ่มบรรทัดที่อ่านมานั้นไปใน array list
            MacList.add(MacLine);
        }
        System.out.println("------------------------");
        System.out.print("Enter Machine ID : ");
        String MacInput = input.next();
        System.out.println("------------------------");
        //เช็คว่า ID ของตู้ที่กรอกมานั้นมีในฐานข้อมูลหรือไม่
        boolean isCorrect = false;
        //การวนเช็คตั้งแต่ตัวแรกไปจนถึงตัวสุดท้าย
        for (int i  = 0 ; i < MacList.size() ; i++){
            //ให้เก็บค่าของ array list ตำแหน่งที่ i นั้นไว้ใน line
            String Line = MacList.get(i);
            //ตัดเป็นช่วงๆและเก็บไว้ใน array
            String[] strID = Line.split("\t");
            //การเลือกเฉลาะตัวที่ต้องการจาก array ตำแหน่งที่ 0 ตัวที่ 3 , 4 , 8
            String MacID = strID[0].substring(3 , 5) + strID[0].substring(8 , 9);
            //และก็เช็คว่ามีตรงกับที่รับข้อมูลมามั้ย
            if (MacID.equals(MacInput)){
                //ถ้ามีตรง
                //เป็นยังการทำงานต่อจากนี้ และส่งค่า MacID , strID[1] , str[0] ไปด้วย
                Menu_menu(MacID , strID[1] , strID[0]);
                isCorrect = true;
            }
        }
        //ถ้าข้อมูลที่รับมาไม่ตรง
        if (!isCorrect){
            main_menu();
        }
    }
    public static void Menu_menu(String ID , String City , String FullID) throws IOException{
        Scanner input = new Scanner(System.in);
        System.out.println("Machine ID : " + ID + " (" + City + ")");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("ID\t\t\t\t\t\tMenu\t\t\t\t\t\t\t\t\t\t\tPrice");
        System.out.println("-----------------------------------------------------------------------------------");
        File file = new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Menu.txt");
        ArrayList<String> MacList = new ArrayList<>();

        Scanner read = new Scanner(file);
        while (read.hasNextLine()){
            //เก็บค่าบรรทัดที่อ่าน
            String MacLine = read.nextLine();
            //ตัดแต่ละท่อน
            String[] strID = MacLine.split("\t");
            //ปริ้นโดนการจัดหน้า
            System.out.printf("%-22s%-50s%-10s\n" , strID[0] , strID[1] ,  strID[2]);
            //เพิ่มไปใน array list
            MacList.add(MacLine);
        }
        String TelInput;
        System.out.println("------------------------");
        System.out.print("Enter Menu ID : ");
        String MacInput = input.next();
        System.out.println("------------------------");
        boolean isCorrect = false;
        for (int i  = 0 ; i < MacList.size() ; i++){
            String Line = MacList.get(i);
            String[] strID = Line.split("\t");
            String MacID = strID[0];
            if (MacID.equals(MacInput)){
                isCorrect = true;
                System.out.print("Enter Tel. : ");
                TelInput = input.next();
                if(TelInput.toCharArray().length == 10) {
                    TelInput = TelInput.substring(0 , 3) + "-" + TelInput.substring(3 , 6) + "-" + TelInput.substring(6 , 10);
                    System.out.println("------------------------");
                    String PIN = randomPIN();
                    System.out.print("Your PIN is :  " + PIN + "\n");
                    System.out.println("------------------------");
                    writeOrder(MacID , FullID , TelInput , PIN);
                }else{
                    main_menu();
                }

            }
        }
        if (!isCorrect){
            main_menu();
        }
    }
    public static String randomPIN(){
        //สร้างตัวแปรสำหรับเก็บค่า PIN
        String PIN = "";
        //สร้าง obj ของการ Random ชื่อว่า rand
        Random rand = new Random();
        //เป็นการวนลลูป 4 ครั้ง เพราะเราต้องการ 4 ตัว
        for (int i = 0 ; i < 4 ; i++){
            //ให้ PIN นั้นเพิ่มค่าไปต่อท้ายค่าที่สุ่มมา (10) หมายถึงสุ่มแค่ ตั้งแต่ 0 - 9
            PIN += String.valueOf(rand.nextInt(10));
        }
        for (int i = 0 ; i < 2 ; i++){
            //เป็นการสุ่มค่าเหมือนกันแต่เป็น ตัวอักษร 'A' หมายถึงให้มันเป็นตัวพิมพ์ใหญ่
            char randchar = (char) (rand.nextInt(26) + 'A');
            //เพิ่มไปเก็บใน PIN เหมือนเดิม
            PIN += randchar;
        }
        return PIN;
    }
    public static void writeOrder(String MenuID , String MacID , String Tel , String PIN) throws IOException{
        File file = new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Order.txt");
        Scanner file_input_2 = new Scanner(file);
        //สร้างตัวแปรเพื่อเช็คว่าค่า ID ตอนนี้คือเท่าไหร่
        int id_now = 0;
        //วนลูปเพื่อเก็บค่า อ่านจนหมด
        while (file_input_2.hasNext()) {
            //สร้าง array และเก็บค่าที่อ่านโดนการตัดทุกๆ การกด tap
            String[] str = file_input_2.nextLine().split("\t");
            //เก็บค่าตำแหน่งแรกของ array ที่เป็นค่า ID ไว้ใน id_now
            id_now = Integer.parseInt(str[0]);
        }
        //เพิ่มค่า id_now ไป 1 ค่าเพื่อที่จะเป็นค่าที่เรานั้นจะเพิ่มเข้าไปในไฟล์
        id_now++ ;
        FileWriter writer = new FileWriter("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Order.txt" , true);
        writer.write("\n");
        writer.write(id_now + "\t" + MenuID + "\t" + MacID + "\t" + Tel + "\t" + PIN + "\t" + "0" + "\t" + "-");
        writer.close();
        System.out.println("Your order has been successfully ordered.");
        main_menu();
    }
    public static void login_menu() throws IOException {
        Scanner input = new Scanner(System.in);
        String U_name , U_pass , ipUser , ipPassword;
        int error = 0;
        int num;
        do {
            Scanner iplogin = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Login.txt"));
            int count_file = 0;
            //ปริ้นตามโจทย์ปกติ
            System.out.println("-------------------");
            System.out.println("       Login");
            System.out.println("-------------------");
            System.out.print("Username : ");
            ipUser = input.next();
            System.out.print("Password : ");
            ipPassword = input.next();
            System.out.println("-------------------");
            while (iplogin.hasNext()) {
                String login_Data = iplogin.nextLine();
                String[] arr_login = login_Data.split("\t");
                U_name = arr_login[2];
                //การเอาแต่รหัสที่เป็นตัวเลขในรหัสทั้งหมด
                U_pass = String.valueOf(arr_login[3].charAt(3)) + String.valueOf(arr_login[3].charAt(4)) + String.valueOf(arr_login[3].charAt(11)) + String.valueOf(arr_login[3].charAt(12)) + String.valueOf(arr_login[3].charAt(13)) + String.valueOf(arr_login[3].charAt(14));
                //เช็คว่าชื่อกับรหัสนั้นถูกมั้ย
                if (ipUser.equals(U_name) && ipPassword.equals(U_pass)) {
                    //การช็ค ว่ารหัสตัวที่ 8 นั้นเป้นเลข 1 รึป่าว
                    if (arr_login[3].charAt(8) == '1') {
                        do {
                            System.out.print("Welcome : ");
                            String[] name_details = arr_login[1].split(" ");
                            System.out.println(name_details[0].charAt(0) + ". " + name_details[1]);
                            System.out.println("Email : " + U_name);
                            //ปริ้นเบอร์โทรตั้งแต่ตัวที่ 1 - 6 และที่เหลืออีก 4 ตัวให้เปลี่ยนเป็น xxxx
                            System.out.println("Tel. : " + arr_login[4].substring(0 , 8) + "xxxx");

                            Scanner kb = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Machine.txt"));
                            //ปริ้นต่างๆตามที่โจทย์ให้มา
                            System.out.println("          Menu          ");
                            System.out.println("------------------------");
                            System.out.println("1. Macsine dertails");
                            System.out.println("2. Member details");
                            System.out.println("3. Add member");
                            System.out.println("4. Edit member");
                            System.out.println("5. Exit");
                            System.out.println("-------------------------");
                            System.out.print("Enter Number : ");
                            //รับค่าของตัวเลข
                            num = input.nextInt();
                            System.out.println("-------------------------");
                            //พอได้มาแล้วก็ นำมาเช็คว่าเป็นเลขอะไร เป็นเลข 1 หรือไม่
                            if (1 == num) {
                                //ถ้าเป็นเลข 1
                                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("ID                                 City                               Position                           Account Number                     Balance");
                                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                // loop เพื่ออ่านไฟล์ ทีละ 1 บรรทัด
                                while (kb.hasNextLine()) {
                                    //สร้างตัวแปรมาเก็บค่าไว้
                                    String data = kb.nextLine();
                                    //สร้าง array มาเพื่อเก็บค่า ที่ได้อ่านมาทีละ 1 บรรทัด โดยที่เรานั้นจะแบ่ง จาก 1 บรรทัด เป็นถ้ามีการ tap เมื่อไหร่ก็ให้แบ่งตอนนั้น
                                    String[] arr_data = data.split("\t");
                                    //เป็นการวนเพื่อปริ้นค่าทั้งหมดใน array
                                    for (String info : arr_data) {
                                        //ปริ้นค่าใน array
                                        System.out.printf("%-35s" , info);
                                    }
                                    System.out.println();
                                }
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                            } else if (2 == num) {
                                menumember();
                            } else if (num == 3) {
                                Addmember();
                            } else if (num == 4) {
                                EditMember();
                            } else if (num == 5) {
                                main_menu();
                            }
                        } while (num != 5);
                        error = 3;
                        break;
                    } else {
                        System.out.println("The users account has expired");
                        error = 3;
                        break;
                    }
                } else {
                    count_file++;
                }
            }
            if (count_file == 3) {
                error++;
                System.out.println("The username or password is incorrect. (" + error + ")");
            }
            iplogin.close();
        } while (error < 3);
        System.out.println("Thank you for using the service.");
    }

    //เพิ่มข้อมูลใหม่เข้าไป
    public static void Addmember() throws FileNotFoundException {
        //เขียนวันที่ (โค้ดหาเอาจากในอินเตอร์เน็ต)
        Date CurrentDate = new Date();
        SimpleDateFormat dateformate = new SimpleDateFormat("Y.MM.dd");

        //การรับค่า ชื่อ นามสกุล เบอร์โทร
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Firstname : ");
        //รับชื่อที่ป้อนเข้ามา(input)
        String ipFirstname = input.nextLine();
        //เช็คว่าชื่อที่รับเข้ามานั้น มีตัวอักษรเกิน 2 ตัวหรือไม่
        if (ipFirstname.length() <= 2) {
            System.out.println("Please enter first name more than 2 digits");
            return;
        }
        //รับนามสกุลที่ป้อนเข้ามา(input)
        System.out.print("Enter Lastname : ");
        String ipLastname = input.nextLine();
        //เช็คนามสกุลว่าที่รับมาเกิด 2 ตัวหรือไม่
        if (ipLastname.length() <= 2) {
            System.out.println("Please enter last name more than 2 digits");
            return;
        }
        //รับเบอร์โทรศัพท์
        System.out.print("Enter Telephone number : ");
        String ipPhonenum = input.nextLine();
        //เช็คว่าเบอร์โทรนั้นมีครบ 10 ตัวหรือไม่
        if (ipPhonenum.length() != 10) {
            System.out.println("Please enter a valid 10 digits");
            return;
        }
        System.out.println("The member added successfully!!!");

        int LID = 0;
        Scanner read = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Member.txt"));
        //เป็นการลูปเพื่อเก็บค่า id ล่าสุดใน fild
        while (read.hasNextLine()) {
            LID = Integer.parseInt(read.nextLine().split("\\s+")[0]);
        }
        //และให้เพิ่มค่าไป 1 ค่า สำหรับจะเพิ่มเข้าไปใน fild เช่น ล่าสุดคือ id 119 ให้ +1 เพิ่มจะได้เป็น 120 เมื่อเพิ่ม
        LID++;
        //เป็นการตัดช่วงขอเบอร์ เช่นจ่กเดิม 0001112222 เป็น 000-111-2222
        ipPhonenum = ipPhonenum.substring(0 , 3) + "-" + ipPhonenum.substring(3 , 6) + "-" + ipPhonenum.substring(6);
        try {
            //การเขียนข้อมูลเพิ่มลงไปใน file เดิมที่มีอยู่แล้ว
            //เป็นการอ่าน file เพื่อที่จะได้เขียนต่อ (true ข้างหลังคือจะลบข้อมูลเก่าหรือไม่ ถ้าเป็น true นั้นคือไม่ลบ)
            FileWriter w = new FileWriter("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Member.txt", true);
            //เป็นการสร้างตัวแปรไว้สำหรับสั่งเขียน
            PrintWriter printfile = new PrintWriter(w);
            //เป็นการใช้คำสั่งเขียน file และก็กำหนดว่าเราขะเขยนไปว่าอย่างไร
            printfile.print("\n" + LID + "\t" + ipFirstname + " " + ipLastname + "\t" + ipPhonenum + "\t" + dateformate.format(CurrentDate));
            printfile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //เช็คว่ามี id นั้นในซานข้อมูลหรือไม่
    public static boolean CheckID(String id) throws FileNotFoundException {
        int data_id = 0;
        boolean check = false;
        Scanner read = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Member.txt"));
        while (read.hasNextLine()) {
            String current = read.nextLine();
            data_id = Integer.parseInt(current.split("\\s+")[0]);
            if (id.equalsIgnoreCase(String.valueOf(data_id))) {
                check = true;
                oldline = current;
                break;
            } else {
                check = false;
            }
        }
        return check;
    }

    //เปลี่ยนข้อมูล
    public static void EditMember() throws IOException {
        Scanner kb = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("ID                  Name                    Tel.                     Application Date");
        System.out.println("-------------------------------------------------------------------------------------");
        //loop ในการอ่านไฟล์
        // .hasnext คือการอ่านข้อมูลในไฟล์จนหมด
        Scanner f = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Member.txt"));
        while (f.hasNext()) {
            //สร้าง array สำหรับเก็บค่า และตัดประดยคจากการเว้นวรรค "\\s+"
            String[] str = f.nextLine().split("\\s+");
            //สร้างตัวแปร String มาเก็บค่าไว้ จากในข้อมูลที่ให้มาและนำไปตัด ตัวแรกจะเป็น id ตัวที่ 2 จะเป็น name sername phone และ day
            //ตามลำดับ
            String id = str[0];
            String name = str[1];
            String sername = str[2];
            String phone = str[3];
            String day = str[4];
            //สร้าง array สำหรับเก็บค่า
            //จากโจทย์ที่ให้เรานั้นสลับค่าเป็น วัน เดือน ปี จาก ปี เดือน วันww
            //โดยที่เรานั้นอ่าค่าจาก day โดยที่แบ่งข้อความตาม . จากด้านบน
            String[] inv = day.split("[.]");
            //และให้เก็บค่าใสไปที่ day ใหม่ โดนที่สลับตำแหน่งเอา
            // inv[0] คือปี inv[1] คือเดือน และ inv[2] คือวันตามข้อมูลที่ได้มาจากอาจารย์
            day = inv[2] + "." + inv[1] + "." + inv[0];
            //ปริ้นค่าต่างๆตามปกติ
            System.out.printf("%-20s%-24s%-25s%-15s\n" , id , name + " " + sername , phone , day);
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.print("Enter member ID : ");
        String id = kb.nextLine();
        if (CheckID(id)) {
            System.out.print("Enter Firstname : ");
            String chFirstName = kb.nextLine();
            if (chFirstName.length() <= 2) {
                System.out.println("Please enter first name more than 2 digits");
                return;
            }
            //รับนามสกุลที่ป้อนเข้ามา(input)
            System.out.print("Enter Lastname : ");
            String chLastname = kb.nextLine();
            //เช็คนามสกุลว่าที่รับมาเกิด 2 ตัวหรือไม่
            if (chLastname.length() <= 2) {
                System.out.println("Please enter last name more than 2 digits");
                return;
            }
            //รับเบอร์โทรศัพท์
            System.out.print("Enter Telephone number : ");
            String chPhonenum = kb.nextLine();
            //เช็คว่าเบอร์โทรนั้นมีครบ 10 ตัวหรือไม่
            if (chPhonenum.length() != 10) {
                System.out.println("Please enter a valid 10 digits");
                return;
            }
            System.out.println("The member information was successfully edited!!");
            Date CurrentDate = new Date();
            SimpleDateFormat dateformate = new SimpleDateFormat("Y.MM.dd");

            //การเพิ่ม "-" ระหว่างเบอร์ ตัวที่ 3 และตัวที่ 6
            chPhonenum = chPhonenum.substring(0 , 3) + "-" + chPhonenum.substring(3 , 6) + "-" + chPhonenum.substring(6);

            File tempF = new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Member.txt");
            String Old = "";
            BufferedReader readd = new BufferedReader(new FileReader(tempF));
            String data_info = readd.readLine();
            while (data_info != null){
                //เก็บค่าข้อมูลอันเก่าที่ละบรรทัดไปเรื่อยๆบอจบบรรทัดแรกก็ให้ขึ้นบรรทัดใหม่
                Old = Old + data_info + System.lineSeparator();
                data_info = readd.readLine();
            }
            String Newline = (id + "\t" + chFirstName + " " + chLastname + "\t" + chPhonenum + "\t" + dateformate.format(CurrentDate));
            String New = Old.replaceAll(oldline , Newline);
            FileWriter write = new FileWriter("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Member.txt");
            write.write(New);
            write.close();
            readd.close();
        }else {
            System.out.println("Invalid Member ID.!!!!");
        }
    }

    //ปริ้น member
    public static void memberdetails() throws FileNotFoundException {
        //ถ้าใช่
        //ก็สร้างตัวแปรอ่านไฟล์เหมือนที่เคยทำ
        Scanner f = new Scanner(new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Member.txt"));
        //ปริ้นต่างๆ
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("ID                  Name                    Tel.                     Application Date");
        System.out.println("-------------------------------------------------------------------------------------");
        //loop ในการอ่านไฟล์
        // .hasnext คือการอ่านข้อมูลในไฟล์จนหมด
        while (f.hasNext()) {
            //สร้าง array สำหรับเก็บค่า และตัดประดยคจากการเว้นวรรค "\\s+"
            String[] str = f.nextLine().split("\\s+");
            //สร้างตัวแปร String มาเก็บค่าไว้ จากในข้อมูลที่ให้มาและนำไปตัด ตัวแรกจะเป็น id ตัวที่ 2 จะเป็น name sername phone และ day
            //ตามลำดับ
            String id = str[0];
            String name = str[1];
            String sername = str[2];
            String phone = str[3];
            String day = str[4];
            //สร้าง array สำหรับเก็บค่า
            //จากโจทย์ที่ให้เรานั้นสลับค่าเป็น วัน เดือน ปี จาก ปี เดือน วัน
            //โดยที่เรานั้นอ่าค่าจาก day โดยที่แบ่งข้อความตาม . จากด้านบน
            String[] inv = day.split("[.]");
            //และให้เก็บค่าใสไปที่ day ใหม่ โดนที่สลับตำแหน่งเอา
            // inv[0] คือปี inv[1] คือเดือน และ inv[2] คือวันตามข้อมูลที่ได้มาจากอาจารย์
            day = inv[2] + "." + inv[1] + "." + inv[0];
            //ปริ้นค่าต่างๆตามปกติ
            System.out.printf("%-20s%-24s%-25s%-15s\n" , id , name + " " + sername , phone , day);
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    //ปริ้นหน้าต่าง manu member
    public static void menumember() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------");
        System.out.println("        Menu");
        System.out.println("-------------------");
        System.out.println("1. Sorting by ID (DESC)");
        System.out.println("2. Sorting by name (ASC)");
        System.out.println("3. Exit");
        System.out.println("-------------------");
        System.out.print("Enter Number (1-3) :");
        int number = input.nextInt();
        if (number == 1){
            sortid();
        } else if (number == 2) {
            sortname();
        } else if (number == 3) {
            return;
        }
    }

    //เรียงตาม id
    public static void sortid() throws IOException {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("ID                  Name                    Tel.                     Application Date");
        System.out.println("-------------------------------------------------------------------------------------");
        //สร้าง arraylist ไว้สำหรับเก็บค่าเก็บข้อมูลแต่ละ บรรทัด
        ArrayList<String> arr = new ArrayList<>();

        File f = new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Member.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(f))){
            //สร้างตัวแปร line ไว้เก็บค่าแต่ละ บรรทัด
            String line;
            //ให้เก็บค่าที่อ่านในแต่ละ บรรทัดนั้นไว้ในตัวแปร line และอ่านค่าจนกว่าจะหมดค่าให้อ่านหรือไม่มี บรรทัด ต่อไปให้อ่านแล้ว
            while ((line = reader.readLine()) != null){
                //เก็บค่าแต่ละ บรรทัด ใส่ไปใน arraylist
                arr.add(line);
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
            return;
        }
        //เป็นการจัดลำดับใน array ใหม่ ให้เป็นการเรียงจากน้อยไปหามากก
        Collections.sort(arr);
        //เป็นการสลับค่าจากอีกด้านมาอีกด้าน
        Collections.reverse(arr);
        //ปริ้นตามปกติ ที่เคยปริ้น
        for (int i = 0 ; i < arr.size() ; i++){
            String[] str = arr.get(i).split("\\s+");
            String id = str[0];
            String name = str[1];
            String sername = str[2];
            String phone = str[3];
            String day = str[4];
            //การจัดวรรค ให้สวยงาม
            System.out.printf("%-20s%-24s%-25s%-15s\n" , id , name + " " + sername , phone , day);
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    //การเรียงตามตัวหนังสือ
    public static void sortname(){
        ArrayList<String> arr = new ArrayList<>();
        File f = new File("C:\\Users\\pond0\\IdeaProjects\\apisit_2\\src\\Member.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(f))){
            //สร้างตัวแปร line ไว้เก็บค่าแต่ละ บรรทัด
            String line;
            //ให้เก็บค่าที่อ่านในแต่ละ บรรทัดนั้นไว้ในตัวแปร line และอ่านค่าจนกว่าจะหมดค่าให้อ่านหรือไม่มี บรรทัด ต่อไปให้อ่านแล้ว
            while ((line = reader.readLine()) != null){
                //เก็บค่าแต่ละ บรรทัด ใส่ไปใน arraylist
                arr.add(line);
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
            return;
        }
        arr.sort(new Comparator<String>() {
            @Override
            //เป็นการเปรียนเทียบระหว่างชื่อของตัว 2 ตัวนั้น ว่าอันไหนมากกว่าน้อยกว่า
            public int compare(String o1, String o2) {
                String name1 = o1.split("\t")[1];
                String name2 = o2.split("\t")[1];
                return name1.compareToIgnoreCase(name2);
            }
        });
        //ปริ้นตามปกติที่เคยปริ้น
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("ID                  Name                    Tel.                     Application Date");
        System.out.println("-------------------------------------------------------------------------------------");
        for (String member : arr){
            String[] details = member.split("\t");
            if (details.length > 0){
                String id = details[0];
                String name = details[1];
                String phone = details[2];
                String day = details[3];
                System.out.printf("%-20s%-24s%-25s%-15s\n" , id , name , phone , day);
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
//import java.util.ArrayList;
//public class Store {
//
//    private ArrayList<Product> products = new ArrayList<>();
//    private ArrayList<Category> categories = new ArrayList<>();
//    private ArrayList<Member> members = new ArrayList<>();
//
//    //Category
//    //เพิ่ม category
//    public void addCategory(Category category) {
//        categories.add(category);
//    }
//    //คืนค่าจำนวนรายการ category ทั้งหมด
//    public int getSizeCategory() {
//        return categories.size();
//    }
//    //คืน data ของ category ด้วยตำเเหน่ง
//    public Category getCategory(int index) {
//        return categories.get(index - 1);
//    }
//    // โช category ทั้งหมด
//    public void showAllCategory() {
//        for (int i = 0; i < categories.size(); i++) {
//            System.out.println(i + 1 + categories.get(i).getName());
//        }
//    }
//    //End Category
//
//    //Product
//    //เพิ่ม product
//    public void addProduct(Product product) {
//        products.add(product);
//    }
//    //โช product ด้วยตำเเหน่ง
//    public void showProduct(int index) {
//        int number = 1;
//        for (int i = 0; i < products.size(); i++) {
//            if (products.get(i).getType().equals(categories.get(index - 1).getId())) {
//                System.out.print(number);
//                products.get(i).toStringProduct();
//                number++;
//            }
//        }
//    }
//    //End Product
//
//    //Member
//    //เพิ่ม member
//    public void addMember(String id, String firstname, String lastname, String email, String password, String phone, String point) {
//        members.add(new Member(id, firstname, lastname, email, password, phone, point));
//    }
//
//    //คืนค่า email ของสมาชิกจากชื่อ
//    public Member getMember(String email) {
//        for (int i = 0; i < members.size(); i++) {
//            if (members.get(i).getEmail().equals(email)) {
//                return members.get(i);
//            }
//        }
//        return null;
//    }
//    //เช็คว่ารหัสเเละอีเมลที่รับมาอยู่ในสถานะไหน
//    /*
//     * รหัสถูกคืนค่า 2
//     * รหัส+ยังไม่หมดอายุ คืนค่า 1
//     * รหัสหรืออีเมลผิด คืนค่า 0
//     */
//    public int checktypeMember(String email, String password) {
//        int numcheck = 0;
//        String password1 = "";
//        //ลูปกรณีที่รับมาเป็นรหัสเต็ม จะกรองเอาเเค่รหัส ุ ตัว
//        for (int i = 0; i < password.length(); i++) {
//            if (i == 9 || i == 10 || i == 13 || i == 14 || i == 15 || i == 16) {
//                password1 += password.charAt(i);
//            }
//        }
//        for (int i = 0; i < members.size(); i++) {
//            if (members.get(i).getEmail().equals(email) && (members.get(i).getTruePassword().equals(password1) || members.get(i).getTruePassword().equals(password))) {
//                numcheck = 2;
//                if (members.get(i).checkStatus()) {
//                    numcheck = 1;
//                }
//            }
//        }
//        return numcheck;
//    }
//    //โชเมื่อถูกต้องทั้งหมด
//    public void showMember (String email){
//        for (int i = 0; i < members.size(); i++) {
//            if (members.get(i).getEmail().equals(email)) {
//                System.out.print("Hello, ");
//                System.out.println(members.get(i).getLastname().charAt(0) + ". " + members.get(i).getFirstname());
//                System.out.print("Email: ");
//                System.out.println(members.get(i).getCensorEmail());
//                System.out.print("Phone: ");
//                System.out.println(members.get(i).getFullPhone());
//                System.out.print("You have ");
//                System.out.printf("%.0f", members.get(i).getPoint());
//                System.out.println(" Point");
//                System.out.println("====================");
//            }
//        }
//    }
//    //โชกรณีรหัสหมดอายุ
//    public void showMemberExpired (String email){
//        System.out.println("===== LOGIN =====");
//        System.out.println("Email : " + email);
//        System.out.println("Password : " + getMember(email).getTruePassword());
//        System.out.println("====================");
//        System.out.println("Error! - Your Account are Expired! ");
//    }
//    //MemberEnd
//}
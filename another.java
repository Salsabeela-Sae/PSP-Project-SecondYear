//public class another {
//    //เมื่อผู้ใช้ กรอกเลขเข้ามาสองค่า
//    private static void handleProductSelection(String[] values, List<String[]> products, List<String[]> cart, String memberId) throws IOException {
//        String productInput = values[0];  // รหัสสินค้าที่ต้องการ order
//        int quantityInput = Integer.parseInt(values[1]); //จำนวนสินค้าที่ต้องการลบ
//        int productIndex = Integer.parseInt(productInput) - 1;  // แปลงหมายเลขให้ตรงกับรายการสินค้า
//
//        // ตรวจสอบว่าค่าแรกที่รับเข้ามาเป็นตัวเลขหรือไม่
//        if (!values[0].matches("\\d+")) {
//            System.out.println("Invalid product ID format.");
//            return;
//        }
//        // ตรวจสอบว่า productIndex อยู่ในขอบเขตของรายการสินค้า
//        if (productIndex < 0 || productIndex >= products.size()) {
//            System.out.println("Product number out of range!");
//            return;
//        }
//
//        String[] ProductInStock = products.get(productIndex);  // ดึงข้อมูลสินค้าที่ผู้ใช้เลือกจากลิสต์สินค้า
//        String productId = ProductInStock[0];  // เก็บ product ID รายการสินค้า
//        String productName = ProductInStock[1];  // เก็บชื่อสินค้า
//        int availableStock = Integer.parseInt(ProductInStock[3]);  // ดึงจำนวนสินค้าในสต็อกจากลิสต์สินค้า
//
//        if (quantityInput > availableStock) {
//            System.out.println("Not enough stock for " + productName + "! Only " + availableStock + " items available.");
//            return;
//        }
//
//        boolean itemExistsInCart = false;
//        for (String[] cartItem : cart) {
//            if (cartItem[0].equals(memberId) && cartItem[1].equals(productId)) {
//                itemExistsInCart = true;
//                int currentQuantity = Integer.parseInt(cartItem[2]);
//
//                if (values[1].startsWith("+")) {
//                    if (currentQuantity + quantityInput > availableStock) {
//                        System.out.println("Not enough stock for " + productName + "! Only " + availableStock + " items available.");
//                        return;
//                    }
//                    cartItem[2] = Integer.toString(currentQuantity + quantityInput);
//                    System.out.println("Quantity increased to " + cartItem[2]);
//                } else if (values[1].startsWith("-")) {
//                    if (currentQuantity + quantityInput < 0) {
//                        System.out.println("You cannot reduce more than the current quantity for " + productName + "! Current quantity: " + currentQuantity);
//                        return;
//                    }
//                    cartItem[2] = Integer.toString(currentQuantity + quantityInput);
//                    System.out.println("Quantity decreased to " + cartItem[2]);
//                } else {
//                    if (quantityInput > availableStock) {
//                        System.out.println("Not enough stock available for " + productName + "!");
//                    } else {
//                        cartItem[2] = String.valueOf(quantityInput);
//                        System.out.println(productName + " quantity set to " + cartItem[2]);
//                    }
//                }
//
//                if (Integer.parseInt(cartItem[2]) <= 0) {
//                    System.out.println("Removed from cart");
//                    cart.remove(cartItem);
//                }
//                break;
//            }
//        }
//
//        if (!itemExistsInCart) {
//            cart.add(new String[]{memberId, productId, Integer.toString(quantityInput)});
//        }
//
//        saveCartToFile(cart);
//    }
//    public static List<String[]> loadCartFromFile() {
//        List<String[]> cart = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\m2\\src\\SeStore\\Cart.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] cartItem = line.split("\t"); // แยกข้อมูลโดยใช้ tab เป็นตัวแบ่ง
//                cart.add(cartItem);
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading CART.txt: " + e.getMessage());
//        }
//        return cart;
//    }
//    private static void saveCartToFile(List<String[]> cart) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\User\\IdeaProjects\\m2\\src\\SeStore\\Cart.txt"))) {
//            for (String[] cartItem : cart) {
//                writer.write(String.join("\t", cartItem));
//                writer.newLine();
//            }
//        }
//    }
//    //เมื่อผู้ใช้ กรอกเลขเข้ามาสองค่า
//    private static void handleProductSelection(String[] values, List<String[]> products, List<String[]> cart, String memberId) throws IOException {
//        String productInput = values[0];  // รหัสสินค้าที่ต้องการ order
//        int quantityInput = Integer.parseInt(values[1]); //จำนวนสินค้าที่ต้องการลบ
//        int productIndex = Integer.parseInt(productInput) - 1;  // แปลงหมายเลขให้ตรงกับรายการสินค้า
//
//        // ตรวจสอบว่าค่าแรกที่รับเข้ามาเป็นตัวเลขหรือไม่
//        if (!values[0].matches("\\d+")) {
//            System.out.println("Invalid product ID format.");
//            return;
//        }
//        // ตรวจสอบว่า productIndex อยู่ในขอบเขตของรายการสินค้า
//        if (productIndex < 0 || productIndex >= products.size()) {
//            System.out.println("Product number out of range!");
//            return;
//        }
//
//        String[] ProductInStock = products.get(productIndex);  // ดึงข้อมูลสินค้าที่ผู้ใช้เลือกจากลิสต์สินค้า
//        String productId = ProductInStock[0];  // เก็บ product ID รายการสินค้า
//        String productName = ProductInStock[1];  // เก็บชื่อสินค้า
//        int availableStock = Integer.parseInt(ProductInStock[3]);  // ดึงจำนวนสินค้าในสต็อกจากลิสต์สินค้า
//
//        if (quantityInput > availableStock) {
//            System.out.println("Not enough stock for " + productName + "! Only " + availableStock + " items available.");
//            return;
//        }
//
//        boolean itemExistsInCart = false;
//        for (String[] cartItem : cart) {
//            if (cartItem[0].equals(memberId) && cartItem[1].equals(productId)) {
//                itemExistsInCart = true;
//                int currentQuantity = Integer.parseInt(cartItem[2]);
//
//                if (values[1].startsWith("+")) {
//                    if (currentQuantity + quantityInput > availableStock) {
//                        System.out.println("Not enough stock for " + productName + "! Only " + availableStock + " items available.");
//                        return;
//                    }
//                    cartItem[2] = Integer.toString(currentQuantity + quantityInput);
//                    System.out.println("Quantity increased to " + cartItem[2]);
//                } else if (values[1].startsWith("-")) {
//                    if (currentQuantity + quantityInput < 0) {
//                        System.out.println("You cannot reduce more than the current quantity for " + productName + "! Current quantity: " + currentQuantity);
//                        return;
//                    }
//                    cartItem[2] = Integer.toString(currentQuantity + quantityInput);
//                    System.out.println("Quantity decreased to " + cartItem[2]);
//                } else {
//                    if (quantityInput > availableStock) {
//                        System.out.println("Not enough stock available for " + productName + "!");
//                    } else {
//                        cartItem[2] = String.valueOf(quantityInput);
//                        System.out.println(productName + " quantity set to " + cartItem[2]);
//                    }
//                }
//
//                if (Integer.parseInt(cartItem[2]) <= 0) {
//                    System.out.println("Removed from cart");
//                    cart.remove(cartItem);
//                }
//                break;
//            }
//        }
//
//        if (!itemExistsInCart) {
//            cart.add(new String[]{memberId, productId, Integer.toString(quantityInput)});
//        }
//
//        saveCartToFile(cart);
//    }
//    public static List<String[]> loadCartFromFile() {
//        List<String[]> cart = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\m2\\src\\SeStore\\Cart.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] cartItem = line.split("\t"); // แยกข้อมูลโดยใช้ tab เป็นตัวแบ่ง
//                cart.add(cartItem);
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading CART.txt: " + e.getMessage());
//        }
//        return cart;
//    }
//    private static void saveCartToFile(List<String[]> cart) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\User\\IdeaProjects\\m2\\src\\SeStore\\Cart.txt"))) {
//            for (String[] cartItem : cart) {
//                writer.write(String.join("\t", cartItem));
//                writer.newLine();
//            }
//        }
//    }
//    //กำหนดเวลา
//    private static void updateCart(List<String[]> cart, String idMember, String productId, String productName, int availableStock, int changeQuantity, String quantityInput) {
//        boolean found = false;
//
//        for (String[] cartItem : cart) {
//            if (cartItem[0].equals(idMember) && cartItem[1].equals(productId)) {
//                int currentQuantity = Integer.parseInt(cartItem[2]);
//
//                if (quantityInput.startsWith("+")) {
//                    int newQuantity = currentQuantity + changeQuantity;
//                    if (newQuantity > availableStock) {
//                        System.out.println("ไม่สามารถเพิ่มจำนวนได้เนื่องจากสต็อกไม่เพียงพอสำหรับ " + productName + "!");
//                    } else {
//                        cartItem[2] = String.valueOf(newQuantity);
//                        System.out.println(productName + " จำนวนเพิ่มขึ้นเป็น " + cartItem[2]);
//                    }
//                } else if (quantityInput.startsWith("-")) {
//                    int newQuantity = currentQuantity - changeQuantity;
//                    if (newQuantity <= 0) {
//                        cart.remove(cartItem);
//                        System.out.println(productName + " ถูกลบออกจากตะกร้า.");
//                    } else {
//                        cartItem[2] = String.valueOf(newQuantity);
//                        System.out.println(productName + " จำนวนลดลงเป็น " + cartItem[2]);
//                    }
//                } else {
//                    if (changeQuantity > availableStock) {
//                        System.out.println("ไม่สามารถตั้งจำนวนใหม่ได้เนื่องจากสต็อกไม่เพียงพอสำหรับ " + productName + "!");
//                    } else {
//                        cartItem[2] = String.valueOf(changeQuantity);
//                        System.out.println(productName + " จำนวนตั้งใหม่เป็น " + cartItem[2]);
//                    }
//                }
//
//                found = true;
//                break;
//            }
//        }
//
//        // ถ้าไม่พบในตะกร้าและไม่ใช่การลบ
//        if (!found && !quantityInput.startsWith("-")) {
//            if (changeQuantity > availableStock) {
//                System.out.println("ไม่สามารถเพิ่มจำนวนได้เนื่องจากสต็อกไม่เพียงพอสำหรับ " + productName + "!");
//            } else {
//                String orderDateTime = LocalDateTime.now().toString(); // รับวันที่และเวลาปัจจุบัน
//                String[] newItem = {idMember, productId, String.valueOf(changeQuantity), orderDateTime}; // เพิ่มวันที่และเวลา
//                cart.add(newItem);
//                System.out.println(productName + " ถูกเพิ่มลงในตะกร้าพร้อมจำนวน " + changeQuantity);
//            }
//        }
//
//        // บันทึกตะกร้าหลังจากการอัปเดต
//        try {
//            saveCart(cart);
//        } catch (IOException e) {
//            System.out.println("เกิดข้อผิดพลาดในการบันทึกตะกร้า!");
//        }
//    }
//}

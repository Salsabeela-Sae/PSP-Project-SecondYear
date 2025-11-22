/********************************************************/
/*Program Assignment : 1/ MIXUE*/
/*Student ID : 66160349*/
/*Student Name : Salsabeela Sa-e*/
/*Date : 07/ August 2024*/
/*โปรแกรมนับค่า E ในข้อความ*/
/********************************************************/

import java.util.Scanner;

public class mixue {
    public static void main(String[] args) {
        Scanner kb  = new Scanner(System.in);
        int cont = 0;//กำหนดตัวแปรนับค่่า
        String text;//กำหนดตัวแปรข้อความ
        //แสดงผลทางหน้าจอ
        System.out.println("----------------------");
        System.out.print("Enter Text : ");
        text = kb.nextLine();//รับข้อความ
        System.out.println("----------------------");
        System.out.println("Text : " + text);//แสดงข้อความที่รับค่ามา
        for (int i = 0; i < text.length(); i++) {//ลูปข้อความที่รับค่ามา
            if (text.charAt(i) == 'e' || text.charAt(i) == 'E'){//ถ้าตตำแหน่งของ i เท่ากับ e,E
                cont++;//เพิ่มค่า
            }
        }
        //แสดงผลลัพธ์
        System.out.println("----------------------");
        System.out.println("Total number of 'e or E' in Text = " + cont);
    }
}

package Team9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup {
   
   static String st_ID;
   static String st_PW; //�α��� ��й�ȣ
   static String name;
   static String birth;
   static String phoneNum;
   static String account_PW; //���� ��й�ȣ
   static String account_num; //���¹�ȣ
   static String bank;
   static int choice = 0; //���� ���� ��ȣ
   static String money;
   static String path = "C:\\AccountFile\\";
   static File Folder = new File(path);
   Scanner scan = new Scanner(System.in);
   
   public signup(String birth2, String phoneNum2) {
      // TODO Auto-generated constructor stub
      this.birth = birth2;
      this.phoneNum = phoneNum2;
   }

   public signup() {
      // TODO Auto-generated constructor stub
   }

   //�˻纸�� 2.2
   public boolean IDcheck(String ID) {
      
      //7�ڸ� üũ
      if(ID.length() != 7) {
         return false;
      }
      //ID ���� ���Խ�
      if(ID.contains(" ")) {
         return false;
      }
      //�ѱ� ���� �Ǵ�
      if(ID.matches(".*[��-��|��-��|��-�R].*")) {
         return false;
      }
      //Ư������ ���� �Ǵ�
      if(ID.matches(".*[!|@|#|$|%|^|&|*|(|)].*")) {
         return false;
      }
      //���� �빮�� ���� �Ǵ�
      if(ID.matches(".*[A-Z].*")) {
         return false;
      }
      
      if(ID.matches(".*[a-z].*")) {
         if(ID.matches(".*[0-9].*")) {
            return true;
         }
      }
      return false;
   }
   
   //�˻纸�� 2.3
   public boolean checkName(String name) {
      
      //���� ���� �Ǵ�
      if(name.contains(" ")) {
         return false;
      }
      //�ѱ۸� ����
      if(name.matches("^[��-�R]+$")) {
         return true;
      }
      return false;
   }
   
   //�˻纸�� 2.4
   public boolean checkFile(String content, int category) {
      
      String _content = "";
      switch(category) {
      case 1: _content = "��ȭ��ȣ"; break;
      case 2: _content = "���¹�ȣ"; break;
      default: System.out.println("�˻��� �� ���� �׸��Դϴ�."); return false;
      }
      
      File[] filelist = Folder.listFiles();
      String line;
      for(int i=0; i<filelist.length; i++) {
         if(filelist[i].exists()) {
            File[] sublist = filelist[i].listFiles();
            for(int j=0; j<sublist.length; j++) {
               if(sublist[j].getName().equals(_content + ".txt")) {
                  FileReader filereader;
                  try {
                     filereader = new FileReader(sublist[i]);
                     BufferedReader buffer = new BufferedReader(filereader);
                     try {
                        line = buffer.readLine();
                        filereader.close();
                        buffer.close();
                        
                        if(content.equals(line)) { //��ȭ��ȣ �ߺ���
                           return false;
                        }
                        
                     } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
                  } catch (FileNotFoundException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                     return false;
                  }
               }
            }
         }
      }
      return true;
   }
   
   ///�˻纸�� 2.5
   public boolean checkBirthday(String birth) {
      
      int year, month, day;
      String syear, smonth, sday;
      
      //19990614
        String check = "^\\d{8}$";
        //1999 06 14
        String check1 = "^(\\d{4})\\s+(\\d{2})\\s+(\\d{2})$";
        //1999/06/14
        String check2 = "^(\\d{4})\\s*/\\s*(\\d{2})\\s*/\\s*(\\d{2})$";
        //1999.06.14
        String check3 = "^(\\d{4})\\s*\\.\\s*(\\d{2})\\s*\\.\\s*(\\d{2})$";
        
        if(Pattern.matches(check, birth)) {
            //System.out.println("ok");
        }
        else if(Pattern.matches(check1, birth)) {
           //System.out.println("1ok");
        }
        else if(Pattern.matches(check2, birth)) {
           //System.out.println("2ok");
        }
        else if(Pattern.matches(check3, birth)) {
           //System.out.println("3ok");
        }
        else {
           System.out.println("�ùٸ� �������� �Է��ϼ���.");
           return false;
        }
        
        birth = birth.replaceAll("[ ./]", "");
        syear = birth.substring(0, 4);
        smonth = birth.substring(4, 6);
        sday = birth.substring(6);
        year = Integer.parseInt(syear);
        month = Integer.parseInt(smonth.replaceAll("^0", ""));
        day = Integer.parseInt(sday.replaceAll("^0", ""));
        
        if(year < 1900) {
            System.out.println("1900�� ���� ����ڸ� ������ �� �ֽ��ϴ�.");
            return false;
        }
        if(month > 12) {
            System.out.println("�ùٸ� �������� �Է��ϼ���.");
            return false;
        }
        if(day > 31) {
            System.out.println("�ùٸ� �������� �Է��ϼ���.");
            return false;
        }
        
        Calendar now = Calendar.getInstance(); //���糯¥
        Calendar calendar = Calendar.getInstance(); //�Է¹�����¥
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(day > maxDays) {
            System.out.println("�������� �ʴ� ���Դϴ�.");
            return false;
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        int over = now.compareTo(calendar);
        if(over < 0) { //�̷��� ��¥�� ���
           System.out.println("�ùٸ� �������� �Է��ϼ���.");
           return false;
        }
        calendar.set(Calendar.YEAR, year + 14);
        int result = now.compareTo(calendar);
        if(result < 0) {
           System.out.println("�� 14�� �̻���� ������ �� �ֽ��ϴ�.");
           return false;
        }
        return true;
   }
   
   ///�˻纸�� 2.6
   public boolean checkMoney(String money) {
	    // ���� �ݾ� �Է� => ��ǥ ���
	             int judgement;
	            //��ǥ Ȯ��
	             boolean comcheck = false;
	             
	             //����Ȯ��
	              if(money.contains(" ")) {
	                 return false;
	              }
	              
	             for(int i = 0;i<money.length();i++) {
	                if(money.charAt(i) == ',') {
	                      comcheck = true;
	                     break;
	                }else
	                   continue;
	             }
	             if(comcheck) {
	                //�����Է��� ���� ���� ��
	                String amount = money;
	                //��� ,ǥ�ø� ���ֱ�
	                amount = amount.replaceAll(",", "");
	                
	                String strResult = amount; //����� ����� ������ ����
	                   Pattern p = Pattern.compile("(^[+-]?\\d+)(\\d{3})"); //����ǥ���� 
	                   Matcher regexMatcher = p.matcher(amount); 
	                   
	                   try {
	                       //int cnt = 0;==>����Ȯ�� ī��Ʈ ����
	                       while(regexMatcher.find()) {
	                                       
	                           strResult = regexMatcher.replaceAll("$1,$2"); //ġȯ : �׷�1 + "," + �׷�2
	                           //����� ,�� �ٴ��� Ȯ��
	                           //System.out.println("����("+ (++cnt) +"):"+strResult);
	                           
	                           //ġȯ�� ���ڿ��� �ٽ� matcher��ü ��� 
	                           //regexMatcher = p.matcher(strResult); 
	                           regexMatcher.reset(strResult); 
	                       }            
	                   } catch (Exception e) {
	                       e.printStackTrace();
	                   }
	                   
	                   if(!strResult.equals(money)) {
	                      System.out.println("�߸��� �����Դϴ�. �ݾ� �����ڸ� ����� �Է��ϼ���.");
	                      return false;
	                   }
	                   money = money.replaceAll(",", "");
	             }
	           //���� Ȯ��
	             for(int ii=0 ;ii<money.length();ii++) {
	                if(!(money.charAt(ii)>='0'&& money.charAt(ii)<='9')) {
	                   System.out.println("���� ������ �ƴմϴ�.");
	                   return false;
	                }
	             }
//	             
//	             try {
//	                judgement = Integer.parseInt(��);
//	            } catch (Exception e) {
//	                // TODO: handle exception
//	                System.out.println("���� ������ �ƴմϴ�");
//	                return false;
//	            }   
	             
	             //�տ� 0 �پ����� Ȯ��
	             for(int i=0;i<money.length();i++) {
	                if(money.equals("0")) {
	                    break;
	                 }
	                if(money.charAt(i)=='0') {
	                   System.out.println("�߸��� �����Դϴ�. 0�� �ֻ��� ���ڰ� �� �� �����ϴ�.");
	                   return false;
	                }
	                else {
	                   break;
	                }
	             }
	           //�ڸ��� Ȯ��
	             if(money.length()>9) {
	                System.out.println("������ �ִ� �ݾ��� �Ѿ����ϴ�.");
	                return false;
	             }else if(money.length()==0) {
	                System.out.println("�ݾ��� �Է��ϼ���.");
	                return false;
	             }
	              

	             return true;

	    }

   //�˻纸�� 2.7
   public boolean checkBank() {
      
      String c = scan.nextLine();
      c = c.trim();
      //����˻�
      if(c.contains(" ")) {
         System.out.println("�ùٸ� ������� �Է��ϼ���.");
         return false;
      }
      //�����˻�
      try {
         choice = Integer.parseInt(c);
      }catch(InputMismatchException | NumberFormatException e) {
         scan = new Scanner(System.in);
         System.out.println("�ùٸ� ������� �Է��ϼ���.");
         return false;
      }

      switch(choice) {
        case 1: bank="��������"; break;
        case 2: bank="��������"; break;
        case 3: bank="�츮����"; break;
        case 4: bank="�ϳ�����"; break;
        case 5: bank="��������"; break;
        case 6: bank="�������"; break;
        case 7: bank="īī����ũ"; break;
        case 8: bank="���̹�ũ"; break;
        default:
           System.out.println("1-8 ������ ���ڸ� �Է����ּ���.");
           return false;
      }
      return true;
   }   
   
   ///�˻纸�� 2.8
   public boolean checkPhone(String number) {
      
      number = number.trim();
      
      //01055998427
        String check = "^010\\d{8}$";
        //010 5599 8427
        String check1 = "^010\\s+(\\d{4})\\s+(\\d{4})$";
        //010-5599-8427
        String check2 = "^010\\s*-\\s*(\\d{4})\\s*-\\s*(\\d{4})$";
        //010.5599.8427
        String check3 = "^010\\s*\\.\\s*(\\d{4})\\s*\\.\\s*(\\d{4})$";
        
        if(Pattern.matches(check, number)) {
            //System.out.println("ok");
        }
        else if(Pattern.matches(check1, number)) {
            //System.out.println("1ok");
        }
        else if(Pattern.matches(check2, number)) {
            //System.out.println("2ok");
        }
        else if(Pattern.matches(check3, number)) {
            //System.out.println("3ok");
        }
        else {
            System.out.println("�ùٸ� �������� �Է��ϼ���.");
            return false;
        }
        number = number.replaceAll("[ .-]", "");
        
        if(!checkFile(number,1)) { //�ߺ� �˻�
            System.out.println("�̹� ��� ���� �޴��� ��ȣ�Դϴ�.");
            return false;
        }
      return true;
   }
   
   ///�˻纸�� 2.9
   public boolean checkAccountPW(String pw) {
      
      pw = pw.trim();
      if(pw.length() != 4) {
            System.out.println("��й�ȣ�� 4�ڸ��� �̷������ �մϴ�.");
            return false;
      }
      
      if(pw.contains(" ")) {
            System.out.println("��й�ȣ ���̿��� ������ ������ �� �����ϴ�.");
            return false;
      }
      
      for(int i=0; i<pw.length(); i++) {
         char temp;
         temp = pw.charAt(i);
         if('0'>temp || temp>'9') {
            System.out.println("��й�ȣ�� ���ڷ� �̷������ �մϴ�.");
            return false;
         }
      }
      if(checkPhoneOverlap(pw)) {
            System.out.println("��й�ȣ�� ���������� ������ �� �����ϴ�.");
            return false;
      }
      if(checkBirthOverlap(pw)) {
            System.out.println("��й�ȣ�� ���������� ������ �� �����ϴ�.");
            return false;
      }
      return true;
   }
   
   ///�˻纸�� 2.10
   public boolean checkBirthOverlap(String pw) {
      
      boolean checkOverlap = false;
      String tempBirth = birth;
      for(int i=0; i<pw.length(); i++) {
         if(!checkOverlap) {
            char temp = pw.charAt(i);
            if(temp >= '0' && temp <= '9') {
               for(int j=0; j<tempBirth.length(); j++) {
                  if(temp == tempBirth.charAt(j)) {
                     if(i+1 < pw.length() && j+1 < tempBirth.length()) {
                        if(pw.charAt(i+1) == tempBirth.charAt(j+1)) {
                           if(i+2 < pw.length() && j+2 < tempBirth.length()) {
                              if(pw.charAt(i+2) == tempBirth.charAt(j+2)) {
                                 checkOverlap = true;
                                 break;
                              }
                              else {
                                 continue;
                              }
                           }
                        }
                        else {
                           continue;
                        }
                     }
                  }
               }
            }
         }
         else {
            break;
         }
      }
      return checkOverlap;
   }

   //�˻纸�� 2.11
   public boolean checkPhoneOverlap(String pw) {
      
      boolean checkOverlap = false;
      if(phoneNum.substring(0, 3).equals("010")) {
         String tempPhoneNum = phoneNum.substring(3, 11);
         for(int i=0; i<pw.length(); i++) {
            if(!checkOverlap) {
               char temp = pw.charAt(i);
               if(temp >= '0' && temp <= '9') {
                  for(int j=0; j<tempPhoneNum.length(); j++) {
                     if(temp == tempPhoneNum.charAt(j)) {
                        if(i+1 < pw.length() && j+1 < tempPhoneNum.length()) {
                           if(pw.charAt(i+1) == tempPhoneNum.charAt(j+1)) {
                              if(i+2 < pw.length() && j+2 < tempPhoneNum.length()) {
                                 if(pw.charAt(i+2) == tempPhoneNum.charAt(j+2)) {
                                    checkOverlap = true;
                                    break;
                                 }
                                 else {
                                    continue;
                                 }
                              }
                           }
                           else {
                              continue;
                           }
                        }
                     }
                  }
               }
            }
            else {
               break;
            }
         }
      }
      return checkOverlap;
   }
   
   ///�˻纸�� 2.12
   public boolean checkLoginPW(String pw) {
      
      pw = pw.trim();
      if(8 > pw.length() || pw.length() > 12) {//������ 8-12�ڸ����� Ȯ��
         System.out.println("��й�ȣ�� 8-12�ڸ��� �̷������ �մϴ�.");
         return false;
      }
      if(pw.contains(" ")) {
         System.out.println("��й�ȣ ���̿��� ������ ������ �� �����ϴ�.");
         return false;
      }
      
      boolean checkA = false, checka = false, checkO = false;
      for(int i=0; i<pw.length(); i++) {
         char temp;
            temp = pw.charAt(i);
            if('a'<=temp && temp<='z') {
               checka = true;
            }
            else if('A'<=temp && temp<='Z') {
               checkA = true;
            }
            else if('~'==temp || temp == '!' || temp == '@' || temp=='#' || 
                  temp == '$' || temp == '%' || temp == '^' || temp=='&' || temp == '*') {
               checkO = true;
            }
      }
      if(checka != true) {
            System.out.println("��й�ȣ�� �ҹ��ڸ� �ݵ�� 1�� �̻� �����ؾ� �մϴ�.");
            return false;
      }
      if(checkA != true) {
            System.out.println("��й�ȣ�� �빮�ڸ� �ݵ�� 1�� �̻� �����ؾ� �մϴ�.");
            return false;
      }
      if(checkO != true) {
            System.out.println("��й�ȣ�� Ư�����ڸ� �ݵ�� 1�� �̻� �����ؾ� �մϴ�.");
            return false;
      }
      if(checkPhoneOverlap(pw)) {
            System.out.println("��й�ȣ�� ���������� ������ �� �����ϴ�.");
            return false;
      }
      if(checkBirthOverlap(pw)) {
            System.out.println("��й�ȣ�� ���������� ������ �� �����ϴ�.");
            return false;
      }
      return true;
   }
   
   ///�˻纸�� 2.13
   public boolean checkAccount(String account_num, int choice) {
      
      //�����˻�
      try {
         Long.parseLong(account_num);
      } catch (Exception e) {
         System.out.println("�ùٸ� ������� �Է��ϼ���.");
         return false;
      }
      
      //���࿡ �´� �ڸ��� �˻�
      switch(choice) {
      case 1: case 8:
         if(account_num.length() != 12) { //��������, ���̹�ũ
            System.out.println("�ش� ������ ���¹�ȣ�� �Է��ϼ���.");
            return false;
         }
         break;
      case 2: case 4: case 6:
         if(account_num.length() != 14) { //��������, �ϳ�����, �������
            System.out.println("�ش� ������ ���¹�ȣ�� �Է��ϼ���.");
            return false;
         }
         break;
      case 3: case 5: case 7:
         if(account_num.length() != 13) { //�츮����, ��������, īī����ũ
            System.out.println("�ش� ������ ���¹�ȣ�� �Է��ϼ���.");
            return false;
         }
         break;   
      }
      
      //�ߺ��˻�
      if(Folder.exists()) {
         if(!checkFile(account_num, 2)) {
            System.out.println("�̹� ��� ���� �����Դϴ�.");
            return false;
         }
      }
      return true;
   }
   
   ///�˻纸�� 2.14
   public void SignUp() {
      
      File s_Folder;
      
      //���̵�-------------------------------------------------------
      while(true) {
         System.out.print("���̵� �Է��ϼ��� : ");
            st_ID = scan.nextLine();
            if(!IDcheck(st_ID)) {
               System.out.println("�ùٸ� �������� �Է��ϼ���.");
                continue;
            }
            path = "C://AccountFile//" + st_ID; //���� ���� ���
            
            boolean check = false; //���̵� �ߺ�üũ
            if(Folder.exists()) {
               File idList[] = Folder.listFiles();
               for(int i=0; i<idList.length; i++) {
                  if(idList[i].getName().equals(st_ID)) {
                     System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
                     check = true;
                     break;
                  }
               }
               if(check) {
                  continue;
               }
            }
            break;
      }
      //id ��� ���� �˻� ��� ��, ���� ����
      s_Folder = new File(path);
      
      //�̸�-------------------------------------------------------
      while(true) {
         System.out.print("�̸��� �Է��ϼ��� : ");
            name = scan.nextLine();
            if(!checkName(name)) {
               System.out.println("�ùٸ� ������� �Է��ϼ���.");
               continue;
            }  
            break;
      }
      
      //��ȭ��ȣ-------------------------------------------------------
      while(true) {
            System.out.print("��ȭ��ȣ�� �Է��ϼ��� : ");
            phoneNum = scan.nextLine().trim();
            if(checkPhone(phoneNum)) break;
      }
      
      //�������-------------------------------------------------------
      while(true) {
            System.out.print("��������� 8�ڸ��� �Է��ϼ��� : ");
            birth = scan.nextLine().trim();
            if(checkBirthday(birth)) break;
      }
      
      //�α��� ��й�ȣ-------------------------------------------------------
      while(true) {
            System.out.print("�α��� ��й�ȣ�� �Է��ϼ��� : ");
            st_PW = scan.nextLine();
            if(checkLoginPW(st_PW)) break;
      }
      
      //���� ��й�ȣ-------------------------------------------------------
      while(true) {
            System.out.print("���º�й�ȣ ���� 4�ڸ��� �Է��ϼ��� : ");
            account_PW = scan.nextLine();
            if(checkAccountPW(account_PW)) break;
      }
      
      //���� ��й�ȣ-------------------------------------------------------
      while(true) {
         System.out.println("1.��������        2.��������        3.�츮����        4.�ϳ�����        5.��������        6.�������        7.īī����ũ        8.���̹�ũ");
         System.out.print("����ϰ��� �ϴ� ������ ��ȣ�� �Է��ϼ��� : ");
         if(checkBank()) {
            if(choice != 0) {
               break;
            }
         }
      }
              
      //���¹�ȣ-------------------------------------------------------
      while(true) {
         System.out.print("���¹�ȣ�� �Է��ϼ��� : ");
         account_num = scan.nextLine().trim();
         if(checkAccount(account_num, choice)) break;
      }
      
      //���±ݾ�-------------------------------------------------------
      while(true) {
         System.out.print("�ش� ������ �ܾ��� �Է��ϼ��� : ");
         money = scan.nextLine();
         if(checkMoney(money)) break;
      }
      
      
      // ���� ����
      if(!s_Folder.exists()) {
         s_Folder.mkdir();
         
         // ���� �� �ؽ�Ʈ ���ϵ� ����
         TextCreate(path, "�̸�", name);
           TextCreate(path, "��ȭ��ȣ", phoneNum);
           TextCreate(path, "�������", birth);
           TextCreate(path, "�α��κ�й�ȣ", st_PW);
           TextCreate(path, "���º�й�ȣ", account_PW);
           TextCreate(path, "�����", bank);
           TextCreate(path, "���¹�ȣ", account_num);
           TextCreate(path, "�ܾ�", money);
           TextCreate(path,"��ü����","");
           
           System.out.println("ȸ�������� ���ϵ帳�ϴ�.");
      }
      else {
         System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
      }
   }
   
   // �ؽ�Ʈ ����, path �� ��� ,FileName.txt �����̸� ,input �� ���빰
   public void TextCreate(String path, String FileName, String input) {
      
      File file = new File(path + "\\" + FileName + ".txt");
      FileWriter fw;
      try {
         fw = new FileWriter(file, true);
         fw.write(input);
         fw.flush();
         fw.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
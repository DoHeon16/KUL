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
   static String st_PW; //·Î±×ÀÎ ºñ¹Ð¹øÈ£
   static String name;
   static String birth;
   static String phoneNum;
   static String account_PW; //°èÁÂ ºñ¹Ð¹øÈ£
   static String account_num; //°èÁÂ¹øÈ£
   static String bank;
   static int choice = 0; //ÀºÇà ¼±ÅÃ ¹øÈ£
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

   //°Ë»çº¸°í¼­ 2.2
   public boolean IDcheck(String ID) {
      
      //7ÀÚ¸® Ã¼Å©
      if(ID.length() != 7) {
         return false;
      }
      //ID °ø¹é Æ÷ÇÔ½Ã
      if(ID.contains(" ")) {
         return false;
      }
      //ÇÑ±Û Æ÷ÇÔ ÆÇ´Ü
      if(ID.matches(".*[¤¡-¤¾|¤¿-¤Ã|°¡-ÆR].*")) {
         return false;
      }
      //Æ¯¼ö¹®ÀÚ Æ÷ÇÔ ÆÇ´Ü
      if(ID.matches(".*[!|@|#|$|%|^|&|*|(|)].*")) {
         return false;
      }
      //¿µ¾î ´ë¹®ÀÚ Æ÷ÇÔ ÆÇ´Ü
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
   
   //°Ë»çº¸°í¼­ 2.3
   public boolean checkName(String name) {
      
      //°ø¹é Æ÷ÇÔ ÆÇ´Ü
      if(name.contains(" ")) {
         return false;
      }
      //ÇÑ±Û¸¸ Æ÷ÇÔ
      if(name.matches("^[°¡-ÆR]+$")) {
         return true;
      }
      return false;
   }
   
   //°Ë»çº¸°í¼­ 2.4
   public boolean checkFile(String content, int category) {
      
      String _content = "";
      switch(category) {
      case 1: _content = "ÀüÈ­¹øÈ£"; break;
      case 2: _content = "°èÁÂ¹øÈ£"; break;
      default: System.out.println("°Ë»çÇÒ ¼ö ¾ø´Â Ç×¸ñÀÔ´Ï´Ù."); return false;
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
                        
                        if(content.equals(line)) { //ÀüÈ­¹øÈ£ Áßº¹½Ã
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
   
   ///°Ë»çº¸°í¼­ 2.5
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
           System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
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
            System.out.println("1900³â ÀÌÈÄ Ãâ»ýÀÚ¸¸ °¡ÀÔÇÒ ¼ö ÀÖ½À´Ï´Ù.");
            return false;
        }
        if(month > 12) {
            System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
            return false;
        }
        if(day > 31) {
            System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
            return false;
        }
        
        Calendar now = Calendar.getInstance(); //ÇöÀç³¯Â¥
        Calendar calendar = Calendar.getInstance(); //ÀÔ·Â¹ÞÀº³¯Â¥
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(day > maxDays) {
            System.out.println("Á¸ÀçÇÏÁö ¾Ê´Â ³¯ÀÔ´Ï´Ù.");
            return false;
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        int over = now.compareTo(calendar);
        if(over < 0) { //¹Ì·¡ÀÇ ³¯Â¥ÀÏ °æ¿ì
           System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
           return false;
        }
        calendar.set(Calendar.YEAR, year + 14);
        int result = now.compareTo(calendar);
        if(result < 0) {
           System.out.println("¸¸ 14¼¼ ÀÌ»óºÎÅÍ °¡ÀÔÇÒ ¼ö ÀÖ½À´Ï´Ù.");
           return false;
        }
        return true;
   }
   
   ///°Ë»çº¸°í¼­ 2.6
   public boolean checkMoney(String money) {
	    // °èÁÂ ±Ý¾× ÀÔ·Â => ½°Ç¥ Çã¿ë
	             int judgement;
	            //½°Ç¥ È®ÀÎ
	             boolean comcheck = false;
	             
	             //°ø¹éÈ®ÀÎ
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
	                //½ÇÁ¦ÀÔ·ÂÇÒ °ª°ú ºñ±³ÇÒ °ª
	                String amount = money;
	                //¸ðµç ,Ç¥½Ã¸¦ ¾ø¾Ö±â
	                amount = amount.replaceAll(",", "");
	                
	                String strResult = amount; //Ãâ·ÂÇÒ °á°ú¸¦ ÀúÀåÇÒ º¯¼ö
	                   Pattern p = Pattern.compile("(^[+-]?\\d+)(\\d{3})"); //Á¤±ÔÇ¥Çö½Ä 
	                   Matcher regexMatcher = p.matcher(amount); 
	                   
	                   try {
	                       //int cnt = 0;==>°úÁ¤È®ÀÎ Ä«¿îÆ® º¯¼ö
	                       while(regexMatcher.find()) {
	                                       
	                           strResult = regexMatcher.replaceAll("$1,$2"); //Ä¡È¯ : ±×·ì1 + "," + ±×·ì2
	                           //Á¦´ë·Î ,°¡ ºÙ´ÂÁö È®ÀÎ
	                           //System.out.println("°úÁ¤("+ (++cnt) +"):"+strResult);
	                           
	                           //Ä¡È¯µÈ ¹®ÀÚ¿­·Î ´Ù½Ã matcher°´Ã¼ ¾ò±â 
	                           //regexMatcher = p.matcher(strResult); 
	                           regexMatcher.reset(strResult); 
	                       }            
	                   } catch (Exception e) {
	                       e.printStackTrace();
	                   }
	                   
	                   if(!strResult.equals(money)) {
	                      System.out.println("Àß¸øµÈ Çü½ÄÀÔ´Ï´Ù. ±Ý¾× ±¸ºÐÀÚ¸¦ Á¦´ë·Î ÀÔ·ÂÇÏ¼¼¿ä.");
	                      return false;
	                   }
	                   money = money.replaceAll(",", "");
	             }
	           //¼ýÀÚ È®ÀÎ
	             for(int ii=0 ;ii<money.length();ii++) {
	                if(!(money.charAt(ii)>='0'&& money.charAt(ii)<='9')) {
	                   System.out.println("¼ýÀÚ Çü½ÄÀÌ ¾Æ´Õ´Ï´Ù.");
	                   return false;
	                }
	             }
//	             
//	             try {
//	                judgement = Integer.parseInt(µ·);
//	            } catch (Exception e) {
//	                // TODO: handle exception
//	                System.out.println("¼ýÀÚ Çü½ÄÀÌ ¾Æ´Õ´Ï´Ù");
//	                return false;
//	            }   
	             
	             //¾Õ¿¡ 0 ºÙ¾ú´ÂÁö È®ÀÎ
	             for(int i=0;i<money.length();i++) {
	                if(money.equals("0")) {
	                    break;
	                 }
	                if(money.charAt(i)=='0') {
	                   System.out.println("Àß¸øµÈ Çü½ÄÀÔ´Ï´Ù. 0ÀÌ ÃÖ»óÀ§ ¼ýÀÚ°¡ µÉ ¼ö ¾ø½À´Ï´Ù.");
	                   return false;
	                }
	                else {
	                   break;
	                }
	             }
	           //ÀÚ¸´¼ö È®ÀÎ
	             if(money.length()>9) {
	                System.out.println("°¡´ÉÇÑ ÃÖ´ë ±Ý¾×À» ³Ñ¾ú½À´Ï´Ù.");
	                return false;
	             }else if(money.length()==0) {
	                System.out.println("±Ý¾×À» ÀÔ·ÂÇÏ¼¼¿ä.");
	                return false;
	             }
	              

	             return true;

	    }

   //°Ë»çº¸°í¼­ 2.7
   public boolean checkBank() {
      
      String c = scan.nextLine();
      c = c.trim();
      //°ø¹é°Ë»ç
      if(c.contains(" ")) {
         System.out.println("¿Ã¹Ù¸¥ ¾ç½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
         return false;
      }
      //Á¤¼ö°Ë»ç
      try {
         choice = Integer.parseInt(c);
      }catch(InputMismatchException | NumberFormatException e) {
         scan = new Scanner(System.in);
         System.out.println("¿Ã¹Ù¸¥ ¾ç½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
         return false;
      }

      switch(choice) {
        case 1: bank="½ÅÇÑÀºÇà"; break;
        case 2: bank="±¹¹ÎÀºÇà"; break;
        case 3: bank="¿ì¸®ÀºÇà"; break;
        case 4: bank="ÇÏ³ªÀºÇà"; break;
        case 5: bank="³óÇùÀºÇà"; break;
        case 6: bank="±â¾÷ÀºÇà"; break;
        case 7: bank="Ä«Ä«¿À¹ðÅ©"; break;
        case 8: bank="ÄÉÀÌ¹ðÅ©"; break;
        default:
           System.out.println("1-8 »çÀÌÀÇ ¼ýÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
           return false;
      }
      return true;
   }   
   
   ///°Ë»çº¸°í¼­ 2.8
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
            System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
            return false;
        }
        number = number.replaceAll("[ .-]", "");
        
        if(!checkFile(number,1)) { //Áßº¹ °Ë»ç
            System.out.println("ÀÌ¹Ì »ç¿ë ÁßÀÎ ÈÞ´ëÆù ¹øÈ£ÀÔ´Ï´Ù.");
            return false;
        }
      return true;
   }
   
   ///°Ë»çº¸°í¼­ 2.9
   public boolean checkAccountPW(String pw) {
      
      pw = pw.trim();
      if(pw.length() != 4) {
            System.out.println("ºñ¹Ð¹øÈ£´Â 4ÀÚ¸®·Î ÀÌ·ç¾îÁ®¾ß ÇÕ´Ï´Ù.");
            return false;
      }
      
      if(pw.contains(" ")) {
            System.out.println("ºñ¹Ð¹øÈ£ »çÀÌ¿¡´Â °ø¹éÀ» Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
            return false;
      }
      
      for(int i=0; i<pw.length(); i++) {
         char temp;
         temp = pw.charAt(i);
         if('0'>temp || temp>'9') {
            System.out.println("ºñ¹Ð¹øÈ£´Â ¼ýÀÚ·Î ÀÌ·ç¾îÁ®¾ß ÇÕ´Ï´Ù.");
            return false;
         }
      }
      if(checkPhoneOverlap(pw)) {
            System.out.println("ºñ¹Ð¹øÈ£´Â °³ÀÎÁ¤º¸¸¦ Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
            return false;
      }
      if(checkBirthOverlap(pw)) {
            System.out.println("ºñ¹Ð¹øÈ£´Â °³ÀÎÁ¤º¸¸¦ Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
            return false;
      }
      return true;
   }
   
   ///°Ë»çº¸°í¼­ 2.10
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

   //°Ë»çº¸°í¼­ 2.11
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
   
   ///°Ë»çº¸°í¼­ 2.12
   public boolean checkLoginPW(String pw) {
      
      pw = pw.trim();
      if(8 > pw.length() || pw.length() > 12) {//Á¤º¸°¡ 8-12ÀÚ¸®ÀÎÁö È®ÀÎ
         System.out.println("ºñ¹Ð¹øÈ£´Â 8-12ÀÚ¸®·Î ÀÌ·ç¾îÁ®¾ß ÇÕ´Ï´Ù.");
         return false;
      }
      if(pw.contains(" ")) {
         System.out.println("ºñ¹Ð¹øÈ£ »çÀÌ¿¡´Â °ø¹éÀ» Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
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
            System.out.println("ºñ¹Ð¹øÈ£´Â ¼Ò¹®ÀÚ¸¦ ¹Ýµå½Ã 1°³ ÀÌ»ó Æ÷ÇÔÇØ¾ß ÇÕ´Ï´Ù.");
            return false;
      }
      if(checkA != true) {
            System.out.println("ºñ¹Ð¹øÈ£´Â ´ë¹®ÀÚ¸¦ ¹Ýµå½Ã 1°³ ÀÌ»ó Æ÷ÇÔÇØ¾ß ÇÕ´Ï´Ù.");
            return false;
      }
      if(checkO != true) {
            System.out.println("ºñ¹Ð¹øÈ£´Â Æ¯¼ö¹®ÀÚ¸¦ ¹Ýµå½Ã 1°³ ÀÌ»ó Æ÷ÇÔÇØ¾ß ÇÕ´Ï´Ù.");
            return false;
      }
      if(checkPhoneOverlap(pw)) {
            System.out.println("ºñ¹Ð¹øÈ£´Â °³ÀÎÁ¤º¸¸¦ Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
            return false;
      }
      if(checkBirthOverlap(pw)) {
            System.out.println("ºñ¹Ð¹øÈ£´Â °³ÀÎÁ¤º¸¸¦ Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
            return false;
      }
      return true;
   }
   
   ///°Ë»çº¸°í¼­ 2.13
   public boolean checkAccount(String account_num, int choice) {
      
      //Á¤¼ö°Ë»ç
      try {
         Long.parseLong(account_num);
      } catch (Exception e) {
         System.out.println("¿Ã¹Ù¸¥ ¾ç½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
         return false;
      }
      
      //ÀºÇà¿¡ ¸Â´Â ÀÚ¸´¼ö °Ë»ç
      switch(choice) {
      case 1: case 8:
         if(account_num.length() != 12) { //½ÅÇÑÀºÇà, ÄÉÀÌ¹ðÅ©
            System.out.println("ÇØ´ç ÀºÇàÀÇ °èÁÂ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
            return false;
         }
         break;
      case 2: case 4: case 6:
         if(account_num.length() != 14) { //±¹¹ÎÀºÇà, ÇÏ³ªÀºÇà, ±â¾÷ÀºÇà
            System.out.println("ÇØ´ç ÀºÇàÀÇ °èÁÂ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
            return false;
         }
         break;
      case 3: case 5: case 7:
         if(account_num.length() != 13) { //¿ì¸®ÀºÇà, ³óÇùÀºÇà, Ä«Ä«¿À¹ðÅ©
            System.out.println("ÇØ´ç ÀºÇàÀÇ °èÁÂ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
            return false;
         }
         break;   
      }
      
      //Áßº¹°Ë»ç
      if(Folder.exists()) {
         if(!checkFile(account_num, 2)) {
            System.out.println("ÀÌ¹Ì »ç¿ë ÁßÀÎ °èÁÂÀÔ´Ï´Ù.");
            return false;
         }
      }
      return true;
   }
   
   ///°Ë»çº¸°í¼­ 2.14
   public void SignUp() {
      
      File s_Folder;
      
      //¾ÆÀÌµð-------------------------------------------------------
      while(true) {
         System.out.print("¾ÆÀÌµð¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
            st_ID = scan.nextLine();
            if(!IDcheck(st_ID)) {
               System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
                continue;
            }
            path = "C://AccountFile//" + st_ID; //¸¸µé Æú´õ °æ·Î
            
            boolean check = false; //¾ÆÀÌµð Áßº¹Ã¼Å©
            if(Folder.exists()) {
               File idList[] = Folder.listFiles();
               for(int i=0; i<idList.length; i++) {
                  if(idList[i].getName().equals(st_ID)) {
                     System.out.println("ÀÌ¹Ì Á¸ÀçÇÏ´Â ¾ÆÀÌµðÀÔ´Ï´Ù.");
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
      //id ¸ðµç Á¶°Ç °Ë»ç Åë°ú ½Ã, ÆÄÀÏ »ý¼º
      s_Folder = new File(path);
      
      //ÀÌ¸§-------------------------------------------------------
      while(true) {
         System.out.print("ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä : ");
            name = scan.nextLine();
            if(!checkName(name)) {
               System.out.println("¿Ã¹Ù¸¥ ¾ç½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
               continue;
            }  
            break;
      }
      
      //ÀüÈ­¹øÈ£-------------------------------------------------------
      while(true) {
            System.out.print("ÀüÈ­¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
            phoneNum = scan.nextLine().trim();
            if(checkPhone(phoneNum)) break;
      }
      
      //»ý³â¿ùÀÏ-------------------------------------------------------
      while(true) {
            System.out.print("»ý³â¿ùÀÏÀ» 8ÀÚ¸®·Î ÀÔ·ÂÇÏ¼¼¿ä : ");
            birth = scan.nextLine().trim();
            if(checkBirthday(birth)) break;
      }
      
      //·Î±×ÀÎ ºñ¹Ð¹øÈ£-------------------------------------------------------
      while(true) {
            System.out.print("·Î±×ÀÎ ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
            st_PW = scan.nextLine();
            if(checkLoginPW(st_PW)) break;
      }
      
      //°èÁÂ ºñ¹Ð¹øÈ£-------------------------------------------------------
      while(true) {
            System.out.print("°èÁÂºñ¹Ð¹øÈ£ ¼ýÀÚ 4ÀÚ¸®¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
            account_PW = scan.nextLine();
            if(checkAccountPW(account_PW)) break;
      }
      
      //°èÁÂ ºñ¹Ð¹øÈ£-------------------------------------------------------
      while(true) {
         System.out.println("1.½ÅÇÑÀºÇà        2.±¹¹ÎÀºÇà        3.¿ì¸®ÀºÇà        4.ÇÏ³ªÀºÇà        5.³óÇùÀºÇà        6.±â¾÷ÀºÇà        7.Ä«Ä«¿À¹ðÅ©        8.ÄÉÀÌ¹ðÅ©");
         System.out.print("µî·ÏÇÏ°íÀÚ ÇÏ´Â ÀºÇàÀÇ ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
         if(checkBank()) {
            if(choice != 0) {
               break;
            }
         }
      }
              
      //°èÁÂ¹øÈ£-------------------------------------------------------
      while(true) {
         System.out.print("°èÁÂ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
         account_num = scan.nextLine().trim();
         if(checkAccount(account_num, choice)) break;
      }
      
      //°èÁÂ±Ý¾×-------------------------------------------------------
      while(true) {
         System.out.print("ÇØ´ç °èÁÂÀÇ ÀÜ¾×À» ÀÔ·ÂÇÏ¼¼¿ä : ");
         money = scan.nextLine();
         if(checkMoney(money)) break;
      }
      
      
      // Æú´õ »ý¼º
      if(!s_Folder.exists()) {
         s_Folder.mkdir();
         
         // Æú´õ ¾È ÅØ½ºÆ® ÆÄÀÏµé »ý¼º
         TextCreate(path, "ÀÌ¸§", name);
           TextCreate(path, "ÀüÈ­¹øÈ£", phoneNum);
           TextCreate(path, "»ý³â¿ùÀÏ", birth);
           TextCreate(path, "·Î±×ÀÎºñ¹Ð¹øÈ£", st_PW);
           TextCreate(path, "°èÁÂºñ¹Ð¹øÈ£", account_PW);
           TextCreate(path, "ÀºÇà¸í", bank);
           TextCreate(path, "°èÁÂ¹øÈ£", account_num);
           TextCreate(path, "ÀÜ¾×", money);
           TextCreate(path,"ÀÌÃ¼³»¿ª","");
           
           System.out.println("È¸¿ø°¡ÀÔÀ» ÃàÇÏµå¸³´Ï´Ù.");
      }
      else {
         System.out.println("È¸¿ø°¡ÀÔ¿¡ ½ÇÆÐÇÏ¿´½À´Ï´Ù.");
      }
   }
   
   // ÅØ½ºÆ® »ý¼º, path ´Â °æ·Î ,FileName.txt ÆÄÀÏÀÌ¸§ ,input Àº ³»¿ë¹°
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
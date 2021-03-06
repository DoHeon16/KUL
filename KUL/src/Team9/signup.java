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
   static String st_PW; //로그인 비밀번호
   static String name;
   static String birth;
   static String phoneNum;
   static String account_PW; //계좌 비밀번호
   static String account_num; //계좌번호
   static String bank;
   static int choice = 0; //은행 선택 번호
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

   //검사보고서 2.2
   public boolean IDcheck(String ID) {
      
      //7자리 체크
      if(ID.length() != 7) {
         return false;
      }
      //ID 공백 포함시
      if(ID.contains(" ")) {
         return false;
      }
      //한글 포함 판단
      if(ID.matches(".*[ㄱ-ㅎ|ㅏ-ㅓ|가-힣].*")) {
         return false;
      }
      //특수문자 포함 판단
      if(ID.matches(".*[!|@|#|$|%|^|&|*|(|)].*")) {
         return false;
      }
      //영어 대문자 포함 판단
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
   
   //검사보고서 2.3
   public boolean checkName(String name) {
      
      //공백 포함 판단
      if(name.contains(" ")) {
         return false;
      }
      //한글만 포함
      if(name.matches("^[가-힣]+$")) {
         return true;
      }
      return false;
   }
   
   //검사보고서 2.4
   public boolean checkFile(String content, int category) {
      
      String _content = "";
      switch(category) {
      case 1: _content = "전화번호"; break;
      case 2: _content = "계좌번호"; break;
      default: System.out.println("검사할 수 없는 항목입니다."); return false;
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
                        
                        if(content.equals(line)) { //전화번호 중복시
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
   
   ///검사보고서 2.5
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
           System.out.println("올바른 형식으로 입력하세요.");
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
            System.out.println("1900년 이후 출생자만 가입할 수 있습니다.");
            return false;
        }
        if(month > 12) {
            System.out.println("올바른 형식으로 입력하세요.");
            return false;
        }
        if(day > 31) {
            System.out.println("올바른 형식으로 입력하세요.");
            return false;
        }
        
        Calendar now = Calendar.getInstance(); //현재날짜
        Calendar calendar = Calendar.getInstance(); //입력받은날짜
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(day > maxDays) {
            System.out.println("존재하지 않는 날입니다.");
            return false;
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        int over = now.compareTo(calendar);
        if(over < 0) { //미래의 날짜일 경우
           System.out.println("올바른 형식으로 입력하세요.");
           return false;
        }
        calendar.set(Calendar.YEAR, year + 14);
        int result = now.compareTo(calendar);
        if(result < 0) {
           System.out.println("만 14세 이상부터 가입할 수 있습니다.");
           return false;
        }
        return true;
   }
   
   ///검사보고서 2.6
   public boolean checkMoney(String money) {
	    // 계좌 금액 입력 => 쉼표 허용
	             int judgement;
	            //쉼표 확인
	             boolean comcheck = false;
	             
	             //공백확인
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
	                //실제입력할 값과 비교할 값
	                String amount = money;
	                //모든 ,표시를 없애기
	                amount = amount.replaceAll(",", "");
	                
	                String strResult = amount; //출력할 결과를 저장할 변수
	                   Pattern p = Pattern.compile("(^[+-]?\\d+)(\\d{3})"); //정규표현식 
	                   Matcher regexMatcher = p.matcher(amount); 
	                   
	                   try {
	                       //int cnt = 0;==>과정확인 카운트 변수
	                       while(regexMatcher.find()) {
	                                       
	                           strResult = regexMatcher.replaceAll("$1,$2"); //치환 : 그룹1 + "," + 그룹2
	                           //제대로 ,가 붙는지 확인
	                           //System.out.println("과정("+ (++cnt) +"):"+strResult);
	                           
	                           //치환된 문자열로 다시 matcher객체 얻기 
	                           //regexMatcher = p.matcher(strResult); 
	                           regexMatcher.reset(strResult); 
	                       }            
	                   } catch (Exception e) {
	                       e.printStackTrace();
	                   }
	                   
	                   if(!strResult.equals(money)) {
	                      System.out.println("잘못된 형식입니다. 금액 구분자를 제대로 입력하세요.");
	                      return false;
	                   }
	                   money = money.replaceAll(",", "");
	             }
	           //숫자 확인
	             for(int ii=0 ;ii<money.length();ii++) {
	                if(!(money.charAt(ii)>='0'&& money.charAt(ii)<='9')) {
	                   System.out.println("숫자 형식이 아닙니다.");
	                   return false;
	                }
	             }
//	             
//	             try {
//	                judgement = Integer.parseInt(돈);
//	            } catch (Exception e) {
//	                // TODO: handle exception
//	                System.out.println("숫자 형식이 아닙니다");
//	                return false;
//	            }   
	             
	             //앞에 0 붙었는지 확인
	             for(int i=0;i<money.length();i++) {
	                if(money.equals("0")) {
	                    break;
	                 }
	                if(money.charAt(i)=='0') {
	                   System.out.println("잘못된 형식입니다. 0이 최상위 숫자가 될 수 없습니다.");
	                   return false;
	                }
	                else {
	                   break;
	                }
	             }
	           //자릿수 확인
	             if(money.length()>9) {
	                System.out.println("가능한 최대 금액을 넘었습니다.");
	                return false;
	             }else if(money.length()==0) {
	                System.out.println("금액을 입력하세요.");
	                return false;
	             }
	              

	             return true;

	    }

   //검사보고서 2.7
   public boolean checkBank() {
      
      String c = scan.nextLine();
      c = c.trim();
      //공백검사
      if(c.contains(" ")) {
         System.out.println("올바른 양식으로 입력하세요.");
         return false;
      }
      //정수검사
      try {
         choice = Integer.parseInt(c);
      }catch(InputMismatchException | NumberFormatException e) {
         scan = new Scanner(System.in);
         System.out.println("올바른 양식으로 입력하세요.");
         return false;
      }

      switch(choice) {
        case 1: bank="신한은행"; break;
        case 2: bank="국민은행"; break;
        case 3: bank="우리은행"; break;
        case 4: bank="하나은행"; break;
        case 5: bank="농협은행"; break;
        case 6: bank="기업은행"; break;
        case 7: bank="카카오뱅크"; break;
        case 8: bank="케이뱅크"; break;
        default:
           System.out.println("1-8 사이의 숫자를 입력해주세요.");
           return false;
      }
      return true;
   }   
   
   ///검사보고서 2.8
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
            System.out.println("올바른 형식으로 입력하세요.");
            return false;
        }
        number = number.replaceAll("[ .-]", "");
        
        if(!checkFile(number,1)) { //중복 검사
            System.out.println("이미 사용 중인 휴대폰 번호입니다.");
            return false;
        }
      return true;
   }
   
   ///검사보고서 2.9
   public boolean checkAccountPW(String pw) {
      
      pw = pw.trim();
      if(pw.length() != 4) {
            System.out.println("비밀번호는 4자리로 이루어져야 합니다.");
            return false;
      }
      
      if(pw.contains(" ")) {
            System.out.println("비밀번호 사이에는 공백을 포함할 수 없습니다.");
            return false;
      }
      
      for(int i=0; i<pw.length(); i++) {
         char temp;
         temp = pw.charAt(i);
         if('0'>temp || temp>'9') {
            System.out.println("비밀번호는 숫자로 이루어져야 합니다.");
            return false;
         }
      }
      if(checkPhoneOverlap(pw)) {
            System.out.println("비밀번호는 개인정보를 포함할 수 없습니다.");
            return false;
      }
      if(checkBirthOverlap(pw)) {
            System.out.println("비밀번호는 개인정보를 포함할 수 없습니다.");
            return false;
      }
      return true;
   }
   
   ///검사보고서 2.10
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

   //검사보고서 2.11
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
   
   ///검사보고서 2.12
   public boolean checkLoginPW(String pw) {
      
      pw = pw.trim();
      if(8 > pw.length() || pw.length() > 12) {//정보가 8-12자리인지 확인
         System.out.println("비밀번호는 8-12자리로 이루어져야 합니다.");
         return false;
      }
      if(pw.contains(" ")) {
         System.out.println("비밀번호 사이에는 공백을 포함할 수 없습니다.");
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
            System.out.println("비밀번호는 소문자를 반드시 1개 이상 포함해야 합니다.");
            return false;
      }
      if(checkA != true) {
            System.out.println("비밀번호는 대문자를 반드시 1개 이상 포함해야 합니다.");
            return false;
      }
      if(checkO != true) {
            System.out.println("비밀번호는 특수문자를 반드시 1개 이상 포함해야 합니다.");
            return false;
      }
      if(checkPhoneOverlap(pw)) {
            System.out.println("비밀번호는 개인정보를 포함할 수 없습니다.");
            return false;
      }
      if(checkBirthOverlap(pw)) {
            System.out.println("비밀번호는 개인정보를 포함할 수 없습니다.");
            return false;
      }
      return true;
   }
   
   ///검사보고서 2.13
   public boolean checkAccount(String account_num, int choice) {
      
      //정수검사
      try {
         Long.parseLong(account_num);
      } catch (Exception e) {
         System.out.println("올바른 양식으로 입력하세요.");
         return false;
      }
      
      //은행에 맞는 자릿수 검사
      switch(choice) {
      case 1: case 8:
         if(account_num.length() != 12) { //신한은행, 케이뱅크
            System.out.println("해당 은행의 계좌번호를 입력하세요.");
            return false;
         }
         break;
      case 2: case 4: case 6:
         if(account_num.length() != 14) { //국민은행, 하나은행, 기업은행
            System.out.println("해당 은행의 계좌번호를 입력하세요.");
            return false;
         }
         break;
      case 3: case 5: case 7:
         if(account_num.length() != 13) { //우리은행, 농협은행, 카카오뱅크
            System.out.println("해당 은행의 계좌번호를 입력하세요.");
            return false;
         }
         break;   
      }
      
      //중복검사
      if(Folder.exists()) {
         if(!checkFile(account_num, 2)) {
            System.out.println("이미 사용 중인 계좌입니다.");
            return false;
         }
      }
      return true;
   }
   
   ///검사보고서 2.14
   public void SignUp() {
      
      File s_Folder;
      
      //아이디-------------------------------------------------------
      while(true) {
         System.out.print("아이디를 입력하세요 : ");
            st_ID = scan.nextLine();
            if(!IDcheck(st_ID)) {
               System.out.println("올바른 형식으로 입력하세요.");
                continue;
            }
            path = "C://AccountFile//" + st_ID; //만들 폴더 경로
            
            boolean check = false; //아이디 중복체크
            if(Folder.exists()) {
               File idList[] = Folder.listFiles();
               for(int i=0; i<idList.length; i++) {
                  if(idList[i].getName().equals(st_ID)) {
                     System.out.println("이미 존재하는 아이디입니다.");
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
      //id 모든 조건 검사 통과 시, 파일 생성
      s_Folder = new File(path);
      
      //이름-------------------------------------------------------
      while(true) {
         System.out.print("이름을 입력하세요 : ");
            name = scan.nextLine();
            if(!checkName(name)) {
               System.out.println("올바른 양식으로 입력하세요.");
               continue;
            }  
            break;
      }
      
      //전화번호-------------------------------------------------------
      while(true) {
            System.out.print("전화번호를 입력하세요 : ");
            phoneNum = scan.nextLine().trim();
            if(checkPhone(phoneNum)) break;
      }
      
      //생년월일-------------------------------------------------------
      while(true) {
            System.out.print("생년월일을 8자리로 입력하세요 : ");
            birth = scan.nextLine().trim();
            if(checkBirthday(birth)) break;
      }
      
      //로그인 비밀번호-------------------------------------------------------
      while(true) {
            System.out.print("로그인 비밀번호를 입력하세요 : ");
            st_PW = scan.nextLine();
            if(checkLoginPW(st_PW)) break;
      }
      
      //계좌 비밀번호-------------------------------------------------------
      while(true) {
            System.out.print("계좌비밀번호 숫자 4자리를 입력하세요 : ");
            account_PW = scan.nextLine();
            if(checkAccountPW(account_PW)) break;
      }
      
      //계좌 비밀번호-------------------------------------------------------
      while(true) {
         System.out.println("1.신한은행        2.국민은행        3.우리은행        4.하나은행        5.농협은행        6.기업은행        7.카카오뱅크        8.케이뱅크");
         System.out.print("등록하고자 하는 은행의 번호를 입력하세요 : ");
         if(checkBank()) {
            if(choice != 0) {
               break;
            }
         }
      }
              
      //계좌번호-------------------------------------------------------
      while(true) {
         System.out.print("계좌번호를 입력하세요 : ");
         account_num = scan.nextLine().trim();
         if(checkAccount(account_num, choice)) break;
      }
      
      //계좌금액-------------------------------------------------------
      while(true) {
         System.out.print("해당 계좌의 잔액을 입력하세요 : ");
         money = scan.nextLine();
         if(checkMoney(money)) break;
      }
      
      
      // 폴더 생성
      if(!s_Folder.exists()) {
         s_Folder.mkdir();
         
         // 폴더 안 텍스트 파일들 생성
         TextCreate(path, "이름", name);
           TextCreate(path, "전화번호", phoneNum);
           TextCreate(path, "생년월일", birth);
           TextCreate(path, "로그인비밀번호", st_PW);
           TextCreate(path, "계좌비밀번호", account_PW);
           TextCreate(path, "은행명", bank);
           TextCreate(path, "계좌번호", account_num);
           TextCreate(path, "잔액", money);
           TextCreate(path,"이체내역","");
           
           System.out.println("회원가입을 축하드립니다.");
      }
      else {
         System.out.println("회원가입에 실패하였습니다.");
      }
   }
   
   // 텍스트 생성, path 는 경로 ,FileName.txt 파일이름 ,input 은 내용물
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
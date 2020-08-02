package Team9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {

   
   static String ID;
   static String PW;
   static String path="C://AccountFile//";
   
   signup s=new signup();//signup 객체 생성->아이디 양식 체크 위함
   Scanner scan=new Scanner(System.in);
   
  
   //검사보고서 2.1
   public void login() {
      
      boolean check=true;//인자 일치 확인 변수
      File Folder=new File(path);
      
      //아이디 입력
      while(check) {
         
         System.out.println("ID를 입력하세요.");
         ID=scan.nextLine();
               
          if (Folder.exists()) {//accountfile 내부에 들어가기
               //System.out.println(path);
                  try {
                        
                    File idList[]=Folder.listFiles();//존재하는 아이디 파일 목록 받기
                    
                    for(int i=0;i<idList.length;i++) {
                        //System.out.println(idList[i].getName());
                       
                       if(idList[i].getName().equals(ID)) {
                          //일치하는 아이디 존재
                          check=false;//일치하는 아이디가 존재하므로 반복 종료
               
                          break;
                       }
                    }
                    if(check) {//아이디가 존재하지 않을 때
                       System.out.println("해당 아이디가 존재하지 않습니다.");
                       continue;
                    }
                    
                }
                  catch (Exception e) {
                     // TODO: handle exception
                  }
            }
         
      }
      
      
      //비밀번호 입력
      check=true;
      path=path+ID;//path를 해당 아이디에 해당하는 path로 수정
      File ID_folder=new File(path);
      while(check) {
         System.out.println("비밀번호를 입력하세요.");
         PW=scan.nextLine();
         
          //비밀번호 일치 확인
         if(ID_folder.exists()) {
            File[] filelist=ID_folder.listFiles();
            String line;
            for(int i=0;i<filelist.length;i++) {
               if(filelist[i].getName().equals("로그인비밀번호.txt")) {
                  FileReader filereader;
                  
                  try {
                     filereader = new FileReader(filelist[i]);
                      BufferedReader buffer=new BufferedReader(filereader);
                      
                      try {
                        line=buffer.readLine();
                        if(PW.equals(line)) {//비밀번호 일치
                            System.out.println("로그인에 성공했습니다.");
                           check=false;//반복 종료
                         }
                        else {
                           System.out.println("비밀번호가 옳지 않습니다.");
                        }
                     } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
                      
                  } catch (FileNotFoundException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                     
                  }
               }
            }
         }
      }
      user u=new user(path,ID);//user 객체 생성(메뉴로 이어짐)
      
      //로그인 성공 후
      while(true) {
         
         System.out.println("1.계좌이체          2.개인정보수정          3.계좌변동          4.이체내역 조회          5.로그아웃");
         int choice;
         try {
            choice=scan.nextInt();
            scan.nextLine();
         }catch(InputMismatchException e) {//정수 외 문자가 입력되었을 때 예외처리
            scan=new Scanner(System.in);
            System.out.println("숫자 형식이 아닙니다.");
            continue;
         }
         //scan.nextLine();//버퍼 처리
         
         switch(choice) {
         case 1://이체
            u.transferMenu();
            break;
         case 2://수정
            u.change();
            break;
         case 3://변동
            u.transferAccount();
            break;
         case 4://이체 내역 조회
            u.searchFile();
            break;
         default: path="C://AccountFile//"; Folder=null; return;
         }
      }
      
      
      
   }
}
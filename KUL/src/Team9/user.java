package Team9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Team9.signup;

public class user {
   
   String userid; //로그인한 사용자 아이디
   String path; //로그인한 사용자 path
   String rid = null; //수취인 id

   String birth = "";
   String phoneNum = "";
   File ID_folder;
   signup signup = new signup();
   static String[] receiver = new String[100]; //동명이인 path 저장하는 배열
   static String root = "C://AccountFile//";
   static int exist;
   static String[] checkname = new String[100]; //이름 확인하면서 path 저장할 배열
   
   Scanner scan = new Scanner(System.in);
   
   public user(String path, String userid) {
      super();
      this.path = path;
      this.userid = userid;
      ID_folder = new File(path);
      FileReader fileRead;
      try {
         fileRead = new FileReader(path + "\\생년월일.txt");
         while(true) {
            try {
               int temp = fileRead.read();
               if(temp == -1) break;
               birth += (char)temp;
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      try {
         fileRead = new FileReader(path + "\\전화번호.txt");
         while(true) {
            try {
               int temp = fileRead.read();
               if(temp == -1) break;
               phoneNum += (char)temp;
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   ///검사보고서 2.15
   public void transferMenu() {
      
      boolean flag = true;
      while(flag) {
         System.out.println("1.수취인 검색    2.수취인 입력");
         System.out.print("메뉴를 입력하세요 : ");
         int menu;
         try {
            menu=scan.nextInt();
               scan.nextLine();    
         }catch(InputMismatchException e) {//정수 외 문자가 입력되었을 때 예외처리
               scan = new Scanner(System.in);
               System.out.println("숫자 형식이 아닙니다.");
               continue;
         }
         if(!(menu == 1 || menu == 2)) {
            continue;
         }
         boolean second = true;
         while(second) {
            switch(menu) {
            case 1:
               System.out.print("검색할 이름을 입력하세요 : ");
                   String search = scan.nextLine();
                   if(search(search)) {
                      if(transfer()) return; //송금 성공 시, 함수 종료
                      else { //송금 실패
                         second = false;
                         break;
                      }
                   }
                   else {
                      System.out.println("검색하신 결과가 존재하지 않습니다.");
                      continue;
                   }   
            case 2:
               if(transfer()) return;
               else second = false;
            }
         }
      }
   }
  
   ///검사보고서 2.16
   public boolean transfer() {
      
      boolean flag1 = true, flag2 = true;
      BufferedReader br;
      String name; //수취인 이름
      String bank; //수취인 은행
      String account; //수취인 계좌
      String money; //이체금액
      String pw; //계좌 비밀번호
      String info; //사용자 확인용 송금 정보
      int choice; //은행선택정보
      
      while(true) {
         System.out.print("수취인의 이름을 입력하세요 : ");
         name = scan.nextLine();
         if(checkFile(name)) {
            info = name;
            break;   
         }
         else {
            System.out.println("존재하지 않는 사용자입니다.");
         }
      }
      
      while(flag1) {
         System.out.println("1.신한은행        2.국민은행        3.우리은행        4.하나은행        5.농협은행        6.기업은행        7.카카오뱅크        8.케이뱅크");
         System.out.print("수취인의 은행에 해당하는 번호를 입력하세요 : ");
         if(!signup.checkBank()) {
            continue;
         }
         else {
            bank = signup.bank;
         }
         for(int i=0; i<exist; i++) {
            File save = new File(receiver[i]);
            try {
               br = new BufferedReader(new FileReader(save.getPath()+"\\은행명.txt"));
               String s_bank = null;
               try {
                  s_bank = br.readLine();
                  if(bank.equals(s_bank)) {
                     info += "(" + bank + "  ";
                     flag1 = false; //while문 탈출
                     break; //은행 선택 성공
                  }
                  br.close();
               } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            } catch (FileNotFoundException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
         if(info == name) { //이름이 같은 수취인 목록 중 누구의 은행과도 일치하지 않을 때
            System.out.println("수취인의 은행 정보와 일치하지 않습니다.");
         }
      }
      
      while(flag2) {
         System.out.print("수취인의 계좌번호를 입력하세요 : ");
         account = scan.nextLine().trim();
         
         //숫자 확인
         try {
            Long.parseLong(account);
         } catch (Exception e) {
            System.out.println("올바른 양식으로 입력하세요.");
            continue;
         }
         
         for(int i=0; i<exist; i++) {
            if(receiver[i] != null) {
               File save = new File(receiver[i]);
               try {
                  br = new BufferedReader(new FileReader(save.getPath()+"\\계좌번호.txt"));
                  String s_account = null;
                  try {
                     s_account = br.readLine();
                     if(account.equals(s_account)) {
                        rid = save.getName(); //수취인 아이디 가져오기
                        if(rid.equals(userid)) {
                           System.out.println("자기 자신에게 송금할 수 없습니다.");
                           return false;
                        }
                        else {
                           info += account + ")님에게 ";
                           flag2 = false; //while문 탈출
                           break; //수취인 선택 성공
                        }
                     }
                     if(i == exist -1) {
                        System.out.println("수취인의 계좌번호와 일치하지 않습니다.");
                     }
                     br.close();
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
      
      while(true) {
         try {
            br = new BufferedReader(new FileReader(path+"\\잔액.txt"));
            String s_money = null;
            try {
               s_money = br.readLine();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            
            System.out.print("송금할 금액을 입력하세요 : ");
            money = scan.nextLine();
            
            if(!signup.checkMoney(money)) continue;
            else {
               money = money.replace(",", "");
            }
            
            if(Integer.parseInt(money) > Integer.parseInt(s_money)) {
               System.out.println("잔액이 부족합니다.");
            }
            else if(Integer.parseInt(money) > 5000000) {
               System.out.println("송금 최대 금액은 500만원입니다.");
            }
            else {
               info += money + "원 송금\n";
               break; 
            }
         } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      while(true) {
         try {
            br = new BufferedReader(new FileReader(path+"\\계좌비밀번호.txt"));
            String s_pw;
            try {
               s_pw = br.readLine();
               System.out.print("계좌 비밀번호를 입력하세요 : ");
               pw = scan.nextLine();
               
               //숫자 확인
               try {
                  Double.parseDouble(pw);
               } catch (Exception e) {
                  System.out.println("올바른 양식으로 입력하세요.");
                  continue;
               }
               if(pw.length() != 4) {
                  System.out.println("계좌 비밀번호는 4자리입니다.");
                  continue;
               }
               
               if(!s_pw.equals(pw)) {
                  System.out.println("계좌 비밀번호가 일치하지 않습니다.");
                  continue;
               }
               else {
                  break;
               }
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         }
      }
      
      System.out.println(info);
      System.out.print("송금정보가 올바르면 y, 올바르지 않다면 아무키나 눌러주세요 : ");
      String ok = scan.nextLine();
      if(ok.equals("y")) {
         if(update(rid, money)) {// 송금인아이디, 수취인아이디, 금액 인자로 전달
            System.out.println("송금이 완료되었습니다.");
               return true;
            }
            else {
               System.out.println("송금에 실패하였습니다.");
               return false;
            }
      }
      else { //송금 실패
         System.out.println("송금에 실패하였습니다.");
         return false;
      }
   }
 
   ///검사보고서 2.17
   public boolean update(String receive, String money) {
      
      BufferedReader br;
         FileWriter fw;
         String gname = null, rname = null;
         boolean check=false;
         
         // 송금인, 수취인 이름 가져오기
         try {
            br = new BufferedReader(new FileReader(path + "\\이름.txt"));
            try {
               gname = br.readLine(); //송금인 이름 저장
               br.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            br = new BufferedReader(new FileReader(root +receive + "\\이름.txt"));
            try {
               rname = br.readLine(); //수취인 이름 저장
               br.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         
         //송금인,수취인 잔액 변경
         String sending="",transmit="";
         int send, tran;
         int mon=Integer.parseInt(money);
         File _file=new File(path+"\\잔액.txt");
         File _file1=new File(root+receive+"\\잔액.txt");
         try {
            br = new BufferedReader(new FileReader(path + "\\잔액.txt"));
            try {
               sending = br.readLine(); //송금인 잔액 저장
               br.close();
               send=Integer.parseInt(sending);
               send-=mon;
               sending=Integer.toString(send);
               try {
                  
                  fw = new FileWriter(_file, false);
                  fw.write(sending);
                  fw.flush();
                  fw.close();
                     
                  
               } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
               
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            br = new BufferedReader(new FileReader(root +receive + "\\잔액.txt"));
            try {
               transmit = br.readLine(); //수취인 잔액 저장
               br.close();
               tran=Integer.parseInt(transmit);
               tran+=mon;
               transmit=Integer.toString(tran);
               try {
                     
                  fw = new FileWriter(_file1, false);
                  fw.write(transmit);
                  fw.flush();
                  fw.close();
         
                     
               } catch (IOException e) {
               // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         
         
         //송금인 이체내역 추가
         File file = new File(path + "\\이체내역.txt");
         try {
            
            fw = new FileWriter(file, true);
            fw.write(rname + "\t-" + money+"\t\t잔액 : "+sending+"\n");
            fw.flush();
            fw.close();
            check=true;
            
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
         check=false;
         //수취인 이체내역 추가
         File file1 = new File(root +receive + "\\이체내역.txt");
         try {
            fw = new FileWriter(file1, true);
            fw.write(gname + "\t+" + money+"\t\t잔액 : "+transmit+"\n"); 
            fw.flush();
            fw.close();
            check=true;
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

         return check;
   }
  
   ///검사보고서 2.18
   public void change() {
      
      signup signup2 = new signup(birth, phoneNum);
         Scanner scan = new Scanner(System.in);
         String checkPW = "";
         System.out.print("정보를 수정하려면 로그인비밀번호를 입력하세요 : ");
         checkPW = scan.nextLine();
                  
         //로그인 확인하는 부분
         String pw = "";
         FileReader fr;
         try {
            fr = new FileReader(path+"\\로그인비밀번호.txt");////파일 경로 추가
            while(true) {
               int temp;
               try {
                  temp = fr.read();
                  if(temp == -1)
                  break;
                  pw += (char)temp;
               } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
               
            }
         } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         
         if(!pw.equals(checkPW)) {
            System.out.println("비밀번호가 올바르지 않습니다.");
            return;
         }
         
         
         //수정
         int ch_index =0;//수정할 항목의 인덱스
         String chInfo = "";
         do {
            System.out.println("1.사용자 이름   2.핸드폰 번호   3.로그인 비밀번호   4.계좌 비밀번호");
            System.out.print("수정할 정보의 인덱스를 입력하세요 : ");
            try {
               ch_index=scan.nextInt();
               scan.nextLine();
            }catch(InputMismatchException e) {//정수 외 문자가 입력되었을 때 예외처리
               scan=new Scanner(System.in);
               System.out.println("숫자 형식이 아닙니다.");
               continue;
            }

         }while(ch_index<1|ch_index>4);
         
         //검사
         if(ch_index == 1) {//이름 수정
            while(true) {
               System.out.print("수정할 정보를 입력하세요 : ");
               chInfo = scan.nextLine();
               if(!signup2.checkName(chInfo)) {
                  continue;
               }
               break;
            }
         }
         else if(ch_index == 2) {//전화번호 수정
            while(true) {
               System.out.print("수정할 정보를 입력하세요 : ");
               chInfo = scan.nextLine();
               if(!signup2.checkPhone(chInfo)) {
                  continue;
               }
               chInfo = chInfo.replaceAll("[ .-]","");
               phoneNum = chInfo;
               break;
                              
            }
         }
         else if(ch_index == 3) {//로그인 비밀번호
            while(true) {
               System.out.print("수정할 정보를 입력하세요 : ");
               chInfo = scan.nextLine();
               if(!signup2.checkLoginPW(chInfo)) {
                  continue;
               }
               break;
            }
         }
         else if(ch_index == 4) {//계좌 비밀번호
            while(true) {
               System.out.print("수정할 정보를 입력하세요 : ");
               chInfo = scan.nextLine();
               if(!signup2.checkAccountPW(chInfo)) {
                  continue;
               }
               break;
            }
         }
         
         //텍스트파일에 수정
         
         if(ch_index == 1) {//이름 수정
            File fileName = new File(path+"//이름.txt");////////파일 추가
            FileWriter fw = null;
            try {
               fw = new FileWriter(fileName,false);
               fw.write(chInfo);
               fw.flush();
               System.out.println("수정되었습니다.");
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }finally {
               if(fw!=null) {
                  try {
                     fw.close();
                  } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
            }
         }
         else if(ch_index == 2) {//전화번호 수정
            File fileName = new File(path+"//전화번호.txt");////////파일 추가
            FileWriter fw = null;
            try {
               fw = new FileWriter(fileName,false);
               fw.write(chInfo);
               fw.flush();
               System.out.println("수정되었습니다.");
               phoneNum = chInfo;
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }finally {
               if(fw!=null) {
                  try {
                     fw.close();
                  } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
            }
         }
         else if(ch_index == 3) {//로그인비밀번호 수정
            File fileName = new File(path+"//로그인비밀번호.txt");////////파일 추가
            FileWriter fw = null;
            try {
               fw = new FileWriter(fileName,false);
               fw.write(chInfo);
               fw.flush();
               System.out.println("수정되었습니다.");
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }finally {
               if(fw!=null) {
                  try {
                     fw.close();
                  } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
            }
         }
         else if(ch_index == 4) {//계좌 비밀번호 수정
            File fileName = new File(path+"//계좌비밀번호.txt");////////파일 추가
            FileWriter fw = null;
            try {
               fw = new FileWriter(fileName,false);
               fw.write(chInfo);
               fw.flush();
               System.out.println("수정되었습니다.");
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }finally {
               if(fw!=null) {
                  try {
                     fw.close();
                  } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
            }
         }
   }
   
   ///검사보고서 2.19
   public void transferAccount() {//계좌변동
      
      double d;
          String accountnum;//계좌번호
          String money;//계좌 잔액
          File ID_folder = new File(path);
          
          while(true) {
          System.out.println("1.신한은행        2.국민은행        3.우리은행        4.하나은행        5.농협은행        6.기업은행        7.카카오뱅크        8.케이뱅크");
          System.out.print("변경하고자 하는 은행을 선택하세요 : ");
          
          if(signup.checkBank()) {
             if(signup.choice!=0) {
                break;
             }
          }
          }
          
          while(true) {//계좌번호 입력
             System.out.print("계좌번호를 입력하세요 : ");
             accountnum = scan.nextLine().trim();
            
             FileReader fileRead;
             try {
                fileRead = new FileReader(path + "\\계좌번호.txt");
                BufferedReader buffer = new BufferedReader(fileRead);
                try {
                   String line = buffer.readLine();
                   fileRead.close();
                   buffer.close();
                   
                   if(accountnum.equals(line)) { //계좌 번호 중복시
                      System.out.println("현재 사용중인 계좌번호입니다.");
                	   continue;
                   }
                   
                } catch (IOException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                }
             } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
             }
             
            if(signup.checkAccount(accountnum, signup.choice)) {
               break;
            }
          }
          while(true) {//계좌 잔액 입력
                System.out.print("해당 계좌의 잔액을 입력하세요 : ");
                money=scan.nextLine().trim();
                
                if(signup.checkMoney(money)) {
                  money = money.replace(",", "");
                   break;
                }
          }
               
            try {
               FileWriter fw = new FileWriter(path+"//은행명.txt",false);
               fw.write(signup.bank);
               fw.close();
               
               FileWriter fw2= new FileWriter(path+"//계좌번호.txt",false);
               fw2.write(accountnum);
               fw2.close();
               
               FileWriter fw3 = new FileWriter(path+"//잔액.txt",false);
               fw3.write(money);
               fw3.close();
               
               FileWriter fw4 = new FileWriter(path+"//이체내역.txt",false);
               fw4.close();
               
               System.out.println("계좌가 변동되었습니다.");
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
   }
 
   ///검사보고서 2.20
   static boolean checkFile(String name) {
      
      File dir = new File(root);
         File[] filelist = dir.listFiles();
         int check = 0;
         exist = 0;
         String s_name = null;//이름 검사
         try {
            for(int i=0;i<filelist.length;i++) {
               try {
               File file=filelist[i];//filelist[i]는 ID 파일
               if(file.isDirectory()) {//각 아이디 파일이 디렉토리인지 확인
                     FileReader read=new FileReader(filelist[i].getPath()+"\\이름.txt");
                     BufferedReader br=new BufferedReader(read);//이름 파일 read 작업
                       try {
                                 String str[] = new String[2];
                                 for(int j=0; ;j++) {
                                    str[0]=br.readLine();
                                    if(str[0]==null) {
                                       s_name=str[1];
                                       break;
                                    }
                                    str[1]=br.readLine();
                                    if(str[1]==null) {
                                       s_name=str[0];
                                       break;
                                    }
                                 }br.close();
                              } catch (IOException e) {
                                 // TODO Auto-generated catch block
                                 e.printStackTrace();
                              }

//                     System.out.println(s_name);
                     if(name.contains(s_name)) {
                        check=1;//이름 존재
                        checkname[exist++]=filelist[i].getPath();//사용자가 찾는 파일 경로 저장하기 위한 작업
                        receiver[exist-1]=filelist[i].getPath();
                     }
                     br.close();
                  
               }
               }catch(FileNotFoundException e) {
                  e.printStackTrace();
               }            
            }
         }catch(IOException e){
            e.printStackTrace();
         }
         if(check==1) {//맞는 이름이 존재한다면
            return true;
         }
         else {
            return false;
         }
   }
   
   ///검사보고서 2.21
   public boolean search(String name) {
      
      if(checkFile(name)) {//검색한 이름이 존재한다면
            
            //"이름 은행명 계좌번호" 출력
            for(int i=0;i<exist;i++) {//이름이 존재해서 id가 저장된 배열 크기만큼 반복
               
               File save=new File(checkname[i]);//해당 id 파일
               if(save.exists()) {
                  BufferedReader br;
                     try {
                        br = new BufferedReader(new FileReader(save.getPath()+"\\이름.txt"));
                        String s_name=null;
                        try {
                                 String str[] = new String[2];
                                 for(int j=0; ;j++) {
                                    str[0]=br.readLine();
                                    if(str[0]==null) {
                                       s_name=str[1];
                                       break;
                                    }
                                    str[1]=br.readLine();
                                    if(str[1]==null) {
                                       s_name=str[0];
                                       break;
                                    }
                                 }br.close();
                              } catch (IOException e) {
                                 // TODO Auto-generated catch block
                                 e.printStackTrace();
                              }

                        System.out.print(s_name+'\t');//이름 출력
                     
                     } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }//이름 파일 read 작업

                  
                     try {
                        br = new BufferedReader(new FileReader(save.getPath()+"\\은행명.txt"));
                        String s_account=br.readLine();
                        System.out.print(s_account+'\t');//은행명 출력
                        br.close();
                     } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }//학번 파일 read 작업
                  
                     try {
                        br = new BufferedReader(new FileReader(save.getPath()+"\\계좌번호.txt"));
                        String s_num=null;
                        try {
                                 String str[] = new String[2];
                                 for(int j=0; ;j++) {
                                    str[0]=br.readLine();
                                    if(str[0]==null) {
                                       s_num=str[1];
                                       break;
                                    }
                                    str[1]=br.readLine();
                                    if(str[1]==null) {
                                       s_num=str[0];
                                       break;
                                    }
                                 }br.close();
                              } catch (IOException e) {
                                 // TODO Auto-generated catch block
                                 e.printStackTrace();
                              }
                        System.out.println(s_num);//계좌번호 출력
                         System.out.println("------------------------------");//사람 구분
                     } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     //학번 파일 read 작업
                  }
               }
            }   
            return true;
         }
         else { //검색한 이름이 존재하지 않으면
            return false;
         }
   }
   
   ///검사보고서 2.22
   public void searchFile() {
      
      if(ID_folder.exists()) {
            File[] filelist = ID_folder.listFiles();
            String line;
            for(int i=0;i<filelist.length;i++) {
               if(filelist[i].getName().equals("이체내역.txt")) {
                  
                  FileReader filereader= null;
                  
                  try {
                     filereader = new FileReader(filelist[i]);
                     BufferedReader buffer=null;
                     try {
                        buffer = new BufferedReader(filereader);
                        line=buffer.readLine();
                        
                        if(line==null) {
                           System.out.println("이체 내역 없음");
                           break;
                        }
                        else {
                           do {
                              System.out.println(line);
                              line=buffer.readLine();
                           }while(line!=null);
                        }
                        
                        
                     }catch (IOException e) {
                        e.printStackTrace();
                     }finally {
                        if(buffer!=null) {
                           try {
                              buffer.close();
                           }catch(IOException e) {
                              e.printStackTrace();
                           }
                        }
                     }
                  } catch (FileNotFoundException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
                  
               }
            }
         }
      }
   
}
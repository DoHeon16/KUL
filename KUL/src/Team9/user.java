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
   
   String userid; //�α����� ����� ���̵�
   String path; //�α����� ����� path
   String rid = null; //������ id

   String birth = "";
   String phoneNum = "";
   File ID_folder;
   signup signup = new signup();
   static String[] receiver = new String[100]; //�������� path �����ϴ� �迭
   static String root = "C://AccountFile//";
   static int exist;
   static String[] checkname = new String[100]; //�̸� Ȯ���ϸ鼭 path ������ �迭
   
   Scanner scan = new Scanner(System.in);
   
   public user(String path, String userid) {
      super();
      this.path = path;
      this.userid = userid;
      ID_folder = new File(path);
      FileReader fileRead;
      try {
         fileRead = new FileReader(path + "\\�������.txt");
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
         fileRead = new FileReader(path + "\\��ȭ��ȣ.txt");
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
   
   ///�˻纸�� 2.15
   public void transferMenu() {
      
      boolean flag = true;
      while(flag) {
         System.out.println("1.������ �˻�    2.������ �Է�");
         System.out.print("�޴��� �Է��ϼ��� : ");
         int menu;
         try {
            menu=scan.nextInt();
               scan.nextLine();    
         }catch(InputMismatchException e) {//���� �� ���ڰ� �ԷµǾ��� �� ����ó��
               scan = new Scanner(System.in);
               System.out.println("���� ������ �ƴմϴ�.");
               continue;
         }
         if(!(menu == 1 || menu == 2)) {
            continue;
         }
         boolean second = true;
         while(second) {
            switch(menu) {
            case 1:
               System.out.print("�˻��� �̸��� �Է��ϼ��� : ");
                   String search = scan.nextLine();
                   if(search(search)) {
                      if(transfer()) return; //�۱� ���� ��, �Լ� ����
                      else { //�۱� ����
                         second = false;
                         break;
                      }
                   }
                   else {
                      System.out.println("�˻��Ͻ� ����� �������� �ʽ��ϴ�.");
                      continue;
                   }   
            case 2:
               if(transfer()) return;
               else second = false;
            }
         }
      }
   }
  
   ///�˻纸�� 2.16
   public boolean transfer() {
      
      boolean flag1 = true, flag2 = true;
      BufferedReader br;
      String name; //������ �̸�
      String bank; //������ ����
      String account; //������ ����
      String money; //��ü�ݾ�
      String pw; //���� ��й�ȣ
      String info; //����� Ȯ�ο� �۱� ����
      int choice; //���༱������
      
      while(true) {
         System.out.print("�������� �̸��� �Է��ϼ��� : ");
         name = scan.nextLine();
         if(checkFile(name)) {
            info = name;
            break;   
         }
         else {
            System.out.println("�������� �ʴ� ������Դϴ�.");
         }
      }
      
      while(flag1) {
         System.out.println("1.��������        2.��������        3.�츮����        4.�ϳ�����        5.��������        6.�������        7.īī����ũ        8.���̹�ũ");
         System.out.print("�������� ���࿡ �ش��ϴ� ��ȣ�� �Է��ϼ��� : ");
         if(!signup.checkBank()) {
            continue;
         }
         else {
            bank = signup.bank;
         }
         for(int i=0; i<exist; i++) {
            File save = new File(receiver[i]);
            try {
               br = new BufferedReader(new FileReader(save.getPath()+"\\�����.txt"));
               String s_bank = null;
               try {
                  s_bank = br.readLine();
                  if(bank.equals(s_bank)) {
                     info += "(" + bank + "  ";
                     flag1 = false; //while�� Ż��
                     break; //���� ���� ����
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
         if(info == name) { //�̸��� ���� ������ ��� �� ������ ������� ��ġ���� ���� ��
            System.out.println("�������� ���� ������ ��ġ���� �ʽ��ϴ�.");
         }
      }
      
      while(flag2) {
         System.out.print("�������� ���¹�ȣ�� �Է��ϼ��� : ");
         account = scan.nextLine().trim();
         
         //���� Ȯ��
         try {
            Long.parseLong(account);
         } catch (Exception e) {
            System.out.println("�ùٸ� ������� �Է��ϼ���.");
            continue;
         }
         
         for(int i=0; i<exist; i++) {
            if(receiver[i] != null) {
               File save = new File(receiver[i]);
               try {
                  br = new BufferedReader(new FileReader(save.getPath()+"\\���¹�ȣ.txt"));
                  String s_account = null;
                  try {
                     s_account = br.readLine();
                     if(account.equals(s_account)) {
                        rid = save.getName(); //������ ���̵� ��������
                        if(rid.equals(userid)) {
                           System.out.println("�ڱ� �ڽſ��� �۱��� �� �����ϴ�.");
                           return false;
                        }
                        else {
                           info += account + ")�Կ��� ";
                           flag2 = false; //while�� Ż��
                           break; //������ ���� ����
                        }
                     }
                     if(i == exist -1) {
                        System.out.println("�������� ���¹�ȣ�� ��ġ���� �ʽ��ϴ�.");
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
            br = new BufferedReader(new FileReader(path+"\\�ܾ�.txt"));
            String s_money = null;
            try {
               s_money = br.readLine();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            
            System.out.print("�۱��� �ݾ��� �Է��ϼ��� : ");
            money = scan.nextLine();
            
            if(!signup.checkMoney(money)) continue;
            else {
               money = money.replace(",", "");
            }
            
            if(Integer.parseInt(money) > Integer.parseInt(s_money)) {
               System.out.println("�ܾ��� �����մϴ�.");
            }
            else if(Integer.parseInt(money) > 5000000) {
               System.out.println("�۱� �ִ� �ݾ��� 500�����Դϴ�.");
            }
            else {
               info += money + "�� �۱�\n";
               break; 
            }
         } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      while(true) {
         try {
            br = new BufferedReader(new FileReader(path+"\\���º�й�ȣ.txt"));
            String s_pw;
            try {
               s_pw = br.readLine();
               System.out.print("���� ��й�ȣ�� �Է��ϼ��� : ");
               pw = scan.nextLine();
               
               //���� Ȯ��
               try {
                  Double.parseDouble(pw);
               } catch (Exception e) {
                  System.out.println("�ùٸ� ������� �Է��ϼ���.");
                  continue;
               }
               if(pw.length() != 4) {
                  System.out.println("���� ��й�ȣ�� 4�ڸ��Դϴ�.");
                  continue;
               }
               
               if(!s_pw.equals(pw)) {
                  System.out.println("���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
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
      System.out.print("�۱������� �ùٸ��� y, �ùٸ��� �ʴٸ� �ƹ�Ű�� �����ּ��� : ");
      String ok = scan.nextLine();
      if(ok.equals("y")) {
         if(update(rid, money)) {// �۱��ξ��̵�, �����ξ��̵�, �ݾ� ���ڷ� ����
            System.out.println("�۱��� �Ϸ�Ǿ����ϴ�.");
               return true;
            }
            else {
               System.out.println("�۱ݿ� �����Ͽ����ϴ�.");
               return false;
            }
      }
      else { //�۱� ����
         System.out.println("�۱ݿ� �����Ͽ����ϴ�.");
         return false;
      }
   }
 
   ///�˻纸�� 2.17
   public boolean update(String receive, String money) {
      
      BufferedReader br;
         FileWriter fw;
         String gname = null, rname = null;
         boolean check=false;
         
         // �۱���, ������ �̸� ��������
         try {
            br = new BufferedReader(new FileReader(path + "\\�̸�.txt"));
            try {
               gname = br.readLine(); //�۱��� �̸� ����
               br.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            br = new BufferedReader(new FileReader(root +receive + "\\�̸�.txt"));
            try {
               rname = br.readLine(); //������ �̸� ����
               br.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         
         //�۱���,������ �ܾ� ����
         String sending="",transmit="";
         int send, tran;
         int mon=Integer.parseInt(money);
         File _file=new File(path+"\\�ܾ�.txt");
         File _file1=new File(root+receive+"\\�ܾ�.txt");
         try {
            br = new BufferedReader(new FileReader(path + "\\�ܾ�.txt"));
            try {
               sending = br.readLine(); //�۱��� �ܾ� ����
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
            br = new BufferedReader(new FileReader(root +receive + "\\�ܾ�.txt"));
            try {
               transmit = br.readLine(); //������ �ܾ� ����
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
         
         
         //�۱��� ��ü���� �߰�
         File file = new File(path + "\\��ü����.txt");
         try {
            
            fw = new FileWriter(file, true);
            fw.write(rname + "\t-" + money+"\t\t�ܾ� : "+sending+"\n");
            fw.flush();
            fw.close();
            check=true;
            
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
         check=false;
         //������ ��ü���� �߰�
         File file1 = new File(root +receive + "\\��ü����.txt");
         try {
            fw = new FileWriter(file1, true);
            fw.write(gname + "\t+" + money+"\t\t�ܾ� : "+transmit+"\n"); 
            fw.flush();
            fw.close();
            check=true;
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

         return check;
   }
  
   ///�˻纸�� 2.18
   public void change() {
      
      signup signup2 = new signup(birth, phoneNum);
         Scanner scan = new Scanner(System.in);
         String checkPW = "";
         System.out.print("������ �����Ϸ��� �α��κ�й�ȣ�� �Է��ϼ��� : ");
         checkPW = scan.nextLine();
                  
         //�α��� Ȯ���ϴ� �κ�
         String pw = "";
         FileReader fr;
         try {
            fr = new FileReader(path+"\\�α��κ�й�ȣ.txt");////���� ��� �߰�
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
            System.out.println("��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
            return;
         }
         
         
         //����
         int ch_index =0;//������ �׸��� �ε���
         String chInfo = "";
         do {
            System.out.println("1.����� �̸�   2.�ڵ��� ��ȣ   3.�α��� ��й�ȣ   4.���� ��й�ȣ");
            System.out.print("������ ������ �ε����� �Է��ϼ��� : ");
            try {
               ch_index=scan.nextInt();
               scan.nextLine();
            }catch(InputMismatchException e) {//���� �� ���ڰ� �ԷµǾ��� �� ����ó��
               scan=new Scanner(System.in);
               System.out.println("���� ������ �ƴմϴ�.");
               continue;
            }

         }while(ch_index<1|ch_index>4);
         
         //�˻�
         if(ch_index == 1) {//�̸� ����
            while(true) {
               System.out.print("������ ������ �Է��ϼ��� : ");
               chInfo = scan.nextLine();
               if(!signup2.checkName(chInfo)) {
                  continue;
               }
               break;
            }
         }
         else if(ch_index == 2) {//��ȭ��ȣ ����
            while(true) {
               System.out.print("������ ������ �Է��ϼ��� : ");
               chInfo = scan.nextLine();
               if(!signup2.checkPhone(chInfo)) {
                  continue;
               }
               chInfo = chInfo.replaceAll("[ .-]","");
               phoneNum = chInfo;
               break;
                              
            }
         }
         else if(ch_index == 3) {//�α��� ��й�ȣ
            while(true) {
               System.out.print("������ ������ �Է��ϼ��� : ");
               chInfo = scan.nextLine();
               if(!signup2.checkLoginPW(chInfo)) {
                  continue;
               }
               break;
            }
         }
         else if(ch_index == 4) {//���� ��й�ȣ
            while(true) {
               System.out.print("������ ������ �Է��ϼ��� : ");
               chInfo = scan.nextLine();
               if(!signup2.checkAccountPW(chInfo)) {
                  continue;
               }
               break;
            }
         }
         
         //�ؽ�Ʈ���Ͽ� ����
         
         if(ch_index == 1) {//�̸� ����
            File fileName = new File(path+"//�̸�.txt");////////���� �߰�
            FileWriter fw = null;
            try {
               fw = new FileWriter(fileName,false);
               fw.write(chInfo);
               fw.flush();
               System.out.println("�����Ǿ����ϴ�.");
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
         else if(ch_index == 2) {//��ȭ��ȣ ����
            File fileName = new File(path+"//��ȭ��ȣ.txt");////////���� �߰�
            FileWriter fw = null;
            try {
               fw = new FileWriter(fileName,false);
               fw.write(chInfo);
               fw.flush();
               System.out.println("�����Ǿ����ϴ�.");
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
         else if(ch_index == 3) {//�α��κ�й�ȣ ����
            File fileName = new File(path+"//�α��κ�й�ȣ.txt");////////���� �߰�
            FileWriter fw = null;
            try {
               fw = new FileWriter(fileName,false);
               fw.write(chInfo);
               fw.flush();
               System.out.println("�����Ǿ����ϴ�.");
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
         else if(ch_index == 4) {//���� ��й�ȣ ����
            File fileName = new File(path+"//���º�й�ȣ.txt");////////���� �߰�
            FileWriter fw = null;
            try {
               fw = new FileWriter(fileName,false);
               fw.write(chInfo);
               fw.flush();
               System.out.println("�����Ǿ����ϴ�.");
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
   
   ///�˻纸�� 2.19
   public void transferAccount() {//���º���
      
      double d;
          String accountnum;//���¹�ȣ
          String money;//���� �ܾ�
          File ID_folder = new File(path);
          
          while(true) {
          System.out.println("1.��������        2.��������        3.�츮����        4.�ϳ�����        5.��������        6.�������        7.īī����ũ        8.���̹�ũ");
          System.out.print("�����ϰ��� �ϴ� ������ �����ϼ��� : ");
          
          if(signup.checkBank()) {
             if(signup.choice!=0) {
                break;
             }
          }
          }
          
          while(true) {//���¹�ȣ �Է�
             System.out.print("���¹�ȣ�� �Է��ϼ��� : ");
             accountnum = scan.nextLine().trim();
            
             FileReader fileRead;
             try {
                fileRead = new FileReader(path + "\\���¹�ȣ.txt");
                BufferedReader buffer = new BufferedReader(fileRead);
                try {
                   String line = buffer.readLine();
                   fileRead.close();
                   buffer.close();
                   
                   if(accountnum.equals(line)) { //���� ��ȣ �ߺ���
                      System.out.println("���� ������� ���¹�ȣ�Դϴ�.");
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
          while(true) {//���� �ܾ� �Է�
                System.out.print("�ش� ������ �ܾ��� �Է��ϼ��� : ");
                money=scan.nextLine().trim();
                
                if(signup.checkMoney(money)) {
                  money = money.replace(",", "");
                   break;
                }
          }
               
            try {
               FileWriter fw = new FileWriter(path+"//�����.txt",false);
               fw.write(signup.bank);
               fw.close();
               
               FileWriter fw2= new FileWriter(path+"//���¹�ȣ.txt",false);
               fw2.write(accountnum);
               fw2.close();
               
               FileWriter fw3 = new FileWriter(path+"//�ܾ�.txt",false);
               fw3.write(money);
               fw3.close();
               
               FileWriter fw4 = new FileWriter(path+"//��ü����.txt",false);
               fw4.close();
               
               System.out.println("���°� �����Ǿ����ϴ�.");
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
   }
 
   ///�˻纸�� 2.20
   static boolean checkFile(String name) {
      
      File dir = new File(root);
         File[] filelist = dir.listFiles();
         int check = 0;
         exist = 0;
         String s_name = null;//�̸� �˻�
         try {
            for(int i=0;i<filelist.length;i++) {
               try {
               File file=filelist[i];//filelist[i]�� ID ����
               if(file.isDirectory()) {//�� ���̵� ������ ���丮���� Ȯ��
                     FileReader read=new FileReader(filelist[i].getPath()+"\\�̸�.txt");
                     BufferedReader br=new BufferedReader(read);//�̸� ���� read �۾�
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
                        check=1;//�̸� ����
                        checkname[exist++]=filelist[i].getPath();//����ڰ� ã�� ���� ��� �����ϱ� ���� �۾�
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
         if(check==1) {//�´� �̸��� �����Ѵٸ�
            return true;
         }
         else {
            return false;
         }
   }
   
   ///�˻纸�� 2.21
   public boolean search(String name) {
      
      if(checkFile(name)) {//�˻��� �̸��� �����Ѵٸ�
            
            //"�̸� ����� ���¹�ȣ" ���
            for(int i=0;i<exist;i++) {//�̸��� �����ؼ� id�� ����� �迭 ũ�⸸ŭ �ݺ�
               
               File save=new File(checkname[i]);//�ش� id ����
               if(save.exists()) {
                  BufferedReader br;
                     try {
                        br = new BufferedReader(new FileReader(save.getPath()+"\\�̸�.txt"));
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

                        System.out.print(s_name+'\t');//�̸� ���
                     
                     } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }//�̸� ���� read �۾�

                  
                     try {
                        br = new BufferedReader(new FileReader(save.getPath()+"\\�����.txt"));
                        String s_account=br.readLine();
                        System.out.print(s_account+'\t');//����� ���
                        br.close();
                     } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }//�й� ���� read �۾�
                  
                     try {
                        br = new BufferedReader(new FileReader(save.getPath()+"\\���¹�ȣ.txt"));
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
                        System.out.println(s_num);//���¹�ȣ ���
                         System.out.println("------------------------------");//��� ����
                     } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     //�й� ���� read �۾�
                  }
               }
            }   
            return true;
         }
         else { //�˻��� �̸��� �������� ������
            return false;
         }
   }
   
   ///�˻纸�� 2.22
   public void searchFile() {
      
      if(ID_folder.exists()) {
            File[] filelist = ID_folder.listFiles();
            String line;
            for(int i=0;i<filelist.length;i++) {
               if(filelist[i].getName().equals("��ü����.txt")) {
                  
                  FileReader filereader= null;
                  
                  try {
                     filereader = new FileReader(filelist[i]);
                     BufferedReader buffer=null;
                     try {
                        buffer = new BufferedReader(filereader);
                        line=buffer.readLine();
                        
                        if(line==null) {
                           System.out.println("��ü ���� ����");
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
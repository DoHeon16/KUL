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
   
   signup s=new signup();//signup ��ü ����->���̵� ��� üũ ����
   Scanner scan=new Scanner(System.in);
   
  
   //�˻纸�� 2.1
   public void login() {
      
      boolean check=true;//���� ��ġ Ȯ�� ����
      File Folder=new File(path);
      
      //���̵� �Է�
      while(check) {
         
         System.out.println("ID�� �Է��ϼ���.");
         ID=scan.nextLine();
               
          if (Folder.exists()) {//accountfile ���ο� ����
               //System.out.println(path);
                  try {
                        
                    File idList[]=Folder.listFiles();//�����ϴ� ���̵� ���� ��� �ޱ�
                    
                    for(int i=0;i<idList.length;i++) {
                        //System.out.println(idList[i].getName());
                       
                       if(idList[i].getName().equals(ID)) {
                          //��ġ�ϴ� ���̵� ����
                          check=false;//��ġ�ϴ� ���̵� �����ϹǷ� �ݺ� ����
               
                          break;
                       }
                    }
                    if(check) {//���̵� �������� ���� ��
                       System.out.println("�ش� ���̵� �������� �ʽ��ϴ�.");
                       continue;
                    }
                    
                }
                  catch (Exception e) {
                     // TODO: handle exception
                  }
            }
         
      }
      
      
      //��й�ȣ �Է�
      check=true;
      path=path+ID;//path�� �ش� ���̵� �ش��ϴ� path�� ����
      File ID_folder=new File(path);
      while(check) {
         System.out.println("��й�ȣ�� �Է��ϼ���.");
         PW=scan.nextLine();
         
          //��й�ȣ ��ġ Ȯ��
         if(ID_folder.exists()) {
            File[] filelist=ID_folder.listFiles();
            String line;
            for(int i=0;i<filelist.length;i++) {
               if(filelist[i].getName().equals("�α��κ�й�ȣ.txt")) {
                  FileReader filereader;
                  
                  try {
                     filereader = new FileReader(filelist[i]);
                      BufferedReader buffer=new BufferedReader(filereader);
                      
                      try {
                        line=buffer.readLine();
                        if(PW.equals(line)) {//��й�ȣ ��ġ
                            System.out.println("�α��ο� �����߽��ϴ�.");
                           check=false;//�ݺ� ����
                         }
                        else {
                           System.out.println("��й�ȣ�� ���� �ʽ��ϴ�.");
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
      user u=new user(path,ID);//user ��ü ����(�޴��� �̾���)
      
      //�α��� ���� ��
      while(true) {
         
         System.out.println("1.������ü          2.������������          3.���º���          4.��ü���� ��ȸ          5.�α׾ƿ�");
         int choice;
         try {
            choice=scan.nextInt();
            scan.nextLine();
         }catch(InputMismatchException e) {//���� �� ���ڰ� �ԷµǾ��� �� ����ó��
            scan=new Scanner(System.in);
            System.out.println("���� ������ �ƴմϴ�.");
            continue;
         }
         //scan.nextLine();//���� ó��
         
         switch(choice) {
         case 1://��ü
            u.transferMenu();
            break;
         case 2://����
            u.change();
            break;
         case 3://����
            u.transferAccount();
            break;
         case 4://��ü ���� ��ȸ
            u.searchFile();
            break;
         default: path="C://AccountFile//"; Folder=null; return;
         }
      }
      
      
      
   }
}
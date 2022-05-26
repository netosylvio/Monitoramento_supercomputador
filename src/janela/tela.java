// Janela grafica para test_case
// Versao 1.0 - 29/05/2012
// Sylvio Villas Boas Neto - Operacao


//////////////////////
package janela;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Calendar;
import java.text.DateFormatSymbols;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;




public class tela extends javax.swing.JFrame {

    public tela() {
        initComponents();
        this.setSize(870, 565);


 String titulo ="<html><pre><font color = \"green\" size =\"6\">Cyborg"
         + "</font>";
 String texto = "<br><br> Versão :  1.0"
                + "<br> Developed by: <font color =\"blue\"> Sylvio Neto</font>"
                + "<br><br> For further informations: <i><u>neto.sylvio@gmail.com"
                + "</u></i>   ";

 String rodape = "<font color = \"red\"><br><br><br>"
                + " Powered by JAVA 6.0"
                + "</font><br>"
                + "</pre></html>";

       final  String messagem = titulo + texto + rodape;

        textArea1.setEditable(false);
        textArea1.setFocusable(false);
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(100);

       
        JMenuItem  menuItem = new JMenuItem();
        menuItem.setText("About");
     /////////////////////////////
   final Icon info = new ImageIcon("C:\\Documents and Settings\\"
           + "sssylvio\\Meus documentos\\Downloads\\terminator3.jpg");

   

     menuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
              //  JOptionPane.showMessageDialog(null, messagem);
                JOptionPane.showMessageDialog(null, messagem, "About Cyborg",0, info);

            }
        });

JMenuItem  menuItem2 = new JMenuItem();
 menuItem2.setText("Close");

 menuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

      jMenu1.add(menuItem);
      jMenu1.addSeparator();
      jMenu1.add(menuItem2);
  

       final Execucao exe = new Execucao();
   //    final long TEMPO = ( 1000 * 60 ); // 1 minuto
    final long TEMPO = (120000 * 60); // 2 horas
    //    final long TEMPO = (90000 * 60); // 1:30 horas
  //  final long TEMPO = (180000 * 60); // 3 horas
       Timer timer = null;
       timer = new Timer();
       TimerTask tarefa = new TimerTask() {
        public void run() {
               try {               
                 exe.executar();
               }
               catch(Exception e){}
            }
         };
         timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);


       iniciaRelogio();
       iniciaCalendario();
       verifica();

    }

 public final void verifica(){
        new Thread(){
        @Override
        public void run() {
 
        int ra = 0;
        while (true) {
          ra = jProgressBar1.getValue();
          //////////////////////////////
       boolean fechado = jButton1.isEnabled();

        
     ///////////////////////
          if (fechado == true && ra == 1 ){
          /*
            String envia = "/stornext/home/operacao/test_case/fonts/envia_email_2.bash";
            Runtime r_envia = Runtime.getRuntime();
            Process pro = null;
            try {
               pro = r_envia.exec(envia);
               pro.waitFor();
            }catch(Exception e)
        {textArea1.append(e+" \n\nErro na execucao de envio de email");}
     */
 final Plotagem pll = new Plotagem();
       pll.plotar();


          }
          try {
          sleep(6000); // 5 segundos
          } catch (Exception e){System.out.println("E = "+e);}
        }
      }
   }.start();
 }


 public void iniciaCalendario(){

   new Thread(){
   @Override
   public void run() {

   while(true){
     Calendar datahora = Calendar.getInstance();
       String diaa, mess, anoo, data_final;
       int dayy= datahora.get(Calendar.DAY_OF_MONTH);
       int ma = datahora.get(Calendar.MONTH);
       ma++;
       int year = datahora.get(Calendar.YEAR);
       int semm = datahora.get(Calendar.DAY_OF_WEEK);
       String[] shortWeekdays = new DateFormatSymbols().getWeekdays();
       String dia_semana = shortWeekdays[semm];
       if (dayy < 10){diaa = "0"+String.valueOf(dayy);}
       else {diaa = String.valueOf(dayy);}
       if (ma < 10){mess = "0"+String.valueOf(ma);}
       else {mess = String.valueOf(ma);}
       if (year < 10){anoo = "0"+String.valueOf(year);}
       else {anoo= String.valueOf(year);}
       data_final = dia_semana+",   "+diaa+"-"+mess+"-"+anoo;
       jLabel23.setText(data_final);



     try{
          sleep(60000 * 60);//faz a thread entrar em estado de espera por 1 hora

       }catch(Exception e){}
    }
  }
 }.start();


   }



public final void iniciaRelogio(){
   new Thread(){
   @Override
   public void run() {

   while(true){//while para fazer o loop infinito

       Calendar test = Calendar.getInstance();
       String data, datah, datam, datas;
       int hora = test.get(Calendar.HOUR_OF_DAY);//pega as horas
       int minuto = test.get(Calendar.MINUTE);//pega os minutos
       int segundo = test.get(Calendar.SECOND);//pega os segundos

       if (hora < 10){datah = "0"+String.valueOf(hora);}
       else {datah = String.valueOf(hora);}
       if (minuto < 10){datam = "0"+String.valueOf(minuto);}
       else {datam = String.valueOf(minuto);}
       if (segundo < 10){datas = "0"+String.valueOf(segundo);}
       else {datas = String.valueOf(segundo);}

       data = datah+":"+datam+":"+datas;
       jLabel22.setText(data);

     try{
          sleep(1000);//faz a thread entrar em estado de espera por 1 segundo
       }catch(Exception e){}
    }
  }
 }.start();//inicia a thread.
}

/////////////////////q

   public class Execucao {
 
       public void executar (){

          final ServerSocket s ;
           try {

               s = new ServerSocket(34567);


          jButton1.setEnabled(false);
          System.gc();
          System.runFinalization();

          jProgressBar1.setIndeterminate(true);
          jProgressBar1.setBackground(Color.BLACK);
          jProgressBar1.setStringPainted(false);
          jProgressBar1.setValue(1);

          Thread t = new Thread() {
          @Override
          public void run()
           {
             try {
                 Thread.sleep(700);

 /////////////////////////////////////////////////////////////////////
                 final Plotagem pl = new Plotagem();
                  pl.control = 0;
//limpa tela
       Color amarelo_fundo = new Color(0,0,0);
      

          jTextField1.setBackground(amarelo_fundo);
          jTextField1.setText(null);
          jTextField2.setBackground(amarelo_fundo);
          jTextField2.setText(null);
          jTextField3.setBackground(amarelo_fundo);
          jTextField3.setText(null);
          jTextField4.setBackground(amarelo_fundo);
          jTextField4.setText(null);
          jTextField5.setBackground(amarelo_fundo);
          jTextField5.setText(null);
          jTextField6.setBackground(amarelo_fundo);
          jTextField6.setText(null);
          jTextField7.setBackground(amarelo_fundo);
          jTextField7.setText(null);
          jTextField8.setBackground(amarelo_fundo);
          jTextField8.setText(null);
          jTextField9.setBackground(amarelo_fundo);
          jTextField9.setText(null);
          jTextField10.setBackground(amarelo_fundo);
          jTextField10.setText(null);
          jTextField11.setBackground(amarelo_fundo);
          jTextField11.setText(null);
          jTextField12.setBackground(amarelo_fundo);
          jTextField12.setText(null);
          jTextField13.setBackground(amarelo_fundo);
          jTextField13.setText(null);
          jTextField14.setBackground(amarelo_fundo);
          jTextField14.setText(null);
          jTextField15.setBackground(amarelo_fundo);
          jTextField15.setText(null);
          jTextField16.setBackground(amarelo_fundo);
          jTextField16.setText(null);
          jTextField17.setBackground(amarelo_fundo);
          jTextField17.setText(null);
          jTextField18.setBackground(amarelo_fundo);
          jTextField18.setText(null);
          jTextField19.setBackground(amarelo_fundo);
          jTextField19.setText(null);
          jTextField20.setBackground(amarelo_fundo);
          jTextField20.setText(null);
          jTextField21.setBackground(amarelo_fundo);
          jTextField21.setText(null);
          jTextField22.setBackground(amarelo_fundo);
          jTextField22.setText(null);
          jTextField23.setBackground(amarelo_fundo);
          jTextField23.setText(null);
          jTextField24.setBackground(amarelo_fundo);
          jTextField24.setText(null);
          jTextField25.setBackground(amarelo_fundo);
          jTextField25.setText(null);
          textArea1.setText(null);
          

//String command = "/stornext/home/operacao/test_case/fonts/roda.bash";
String command = "notepad";
Runtime r = Runtime.getRuntime();
Process p = null;

 try {
        p = r.exec(command);
        textArea1.append("\nExecutando Test_case, por favor aguarde...............");
        p.waitFor();
        jButton1.setEnabled(true);
        s.close();
          }catch(Exception e)
       {textArea1.append(e+" \n\nErro na execucao do Script");}
// Plotar
  
       pl.plotar();


          }catch (Exception ex) {}
        }
      };
     t.start();

    }catch (Exception e) {
    textArea1.append("\n\nTest_case em execucao............\n");}

       }
 }

// a partir daqui outra função.
public class Plotagem {
    //////////////////////////////////////////
    int control;
public final void plotar(){

     Color verde = new Color(0,255,0);
       Color vermelho = new Color (255,0,0);


int [] vetor_geral = new int [22];
          // 0 eh OK
          // 1 eh Erro
          // 2 eh Vazio
          for (int e =0; e < vetor_geral.length; e++){
          vetor_geral[e] = 2;
          }
int verdade = 1;
//File f = new File ("/stornext/home/operacao/test_case/output/out_test_case.txt");
File f = new File ("C:\\output.txt");
long tam = f.length();

 if (f.exists()==true && tam > 1489 ){
//if (f.exists()==true && tam > 900 ) {
textArea1.setText(null);
   try {

        FileReader reader = new FileReader(f);
        BufferedReader input = new BufferedReader(reader);
        String linha;

        int i=0;
        while ((linha = input.readLine()) != null) {
            verdade = 0;
                boolean equals = linha.contains("Ok");
                boolean erro = linha.contains("Erro");
                if (equals == true){
                    vetor_geral[i]=0;
                    i = i+1;
               }else if (erro == true) {
                       vetor_geral[i]=1;
                       i = i+1;
                    }
                linha = linha.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
                textArea1.append(linha);
                textArea1.append("\n");
                 }
                 input.close();
               }catch(IOException ioe) {textArea1.append(ioe+" \n\nErro na execucao do Script");}

     }

if (verdade == 0){
 int j=0;
 int  vet_seis[] = {0,0,0,0,0,0};

 while (j < 22){
    if (j == 14){j=15;}
    if (j == 18){j=19;}

switch (j) {

  case 0:
    if (vetor_geral[j]==0){
        jTextField1.setBackground(verde);
        jTextField1.setForeground(Color.BLACK);
        jTextField1.setText("OK");
    }else {
        jTextField1.setBackground(vermelho);
        jTextField1.setText("Erro");
        jTextField1.setForeground(Color.WHITE);
        vet_seis[0]=1;
    }
break;

  case 1:
    if (vetor_geral[j]==0){
        jTextField2.setBackground(verde);
        jTextField2.setForeground(Color.BLACK);
        jTextField2.setText("OK");
    }else {
        jTextField2.setBackground(vermelho);
        jTextField2.setText("Erro");
        jTextField2.setForeground(Color.WHITE);
        vet_seis[0]=1;
    }
break;
 case 2:
    if (vetor_geral[j]==0){
        jTextField4.setBackground(verde);
        jTextField4.setForeground(Color.BLACK);
        jTextField4.setText("OK");
    }else {
        jTextField4.setBackground(vermelho);
        jTextField4.setText("Erro");
        jTextField4.setForeground(Color.WHITE);
        vet_seis[0]=1;
    }
break;

 case 3:
    if (vetor_geral[j]==0){
        jTextField5.setBackground(verde);
        jTextField5.setForeground(Color.BLACK);
        jTextField5.setText("OK");
    }else {
        jTextField5.setBackground(vermelho);
        jTextField5.setText("Erro");
        jTextField5.setForeground(Color.WHITE);
        vet_seis[1]=1;
    }
break;

 case 4:
    if (vetor_geral[j]==0){
        jTextField6.setBackground(verde);
        jTextField6.setForeground(Color.BLACK);
        jTextField6.setText("OK");
    }else {
        jTextField6.setBackground(vermelho);
        jTextField6.setText("Erro");
        jTextField6.setForeground(Color.WHITE);
        vet_seis[1]=1;
    }
break;

 case 5:
    if (vetor_geral[j]==0){
        jTextField7.setBackground(verde);
        jTextField7.setForeground(Color.BLACK);
        jTextField7.setText("OK");
    }else {
        jTextField7.setBackground(vermelho);
        jTextField7.setText("Erro");
        jTextField7.setForeground(Color.WHITE);
        vet_seis[1]=1;
    }
break;

 case 6:
    if (vetor_geral[j]==0){
        jTextField8.setBackground(verde);
        jTextField8.setForeground(Color.BLACK);
        jTextField8.setText("OK");
    }else {
        jTextField8.setBackground(vermelho);
        jTextField8.setText("Erro");
        jTextField8.setForeground(Color.WHITE);
        vet_seis[1]=1;
    }
break;

 case 7:
    if (vetor_geral[j]==0){
        jTextField10.setBackground(verde);
        jTextField10.setForeground(Color.BLACK);
        jTextField10.setText("OK");
    }else {
        jTextField10.setBackground(vermelho);
        jTextField10.setText("Erro");
        jTextField10.setForeground(Color.WHITE);
        vet_seis[2]=1;
    }
break;

 case 8:
    if (vetor_geral[j]==0){
        jTextField11.setBackground(verde);
        jTextField11.setForeground(Color.BLACK);
        jTextField11.setText("OK");
    }else {
        jTextField11.setBackground(vermelho);
        jTextField11.setText("Erro");
        jTextField11.setForeground(Color.WHITE);
        vet_seis[2]=1;
    }
break;

 case 9:
    if (vetor_geral[j]==0){
        jTextField12.setBackground(verde);
        jTextField12.setForeground(Color.BLACK);
        jTextField12.setText("OK");
    }else {
        jTextField12.setBackground(vermelho);
        jTextField12.setText("Erro");
        jTextField12.setForeground(Color.WHITE);
        vet_seis[2]=1;
    }
break;
 case 10:
    if (vetor_geral[j]==0){
        jTextField25.setBackground(verde);
        jTextField25.setForeground(Color.BLACK);
        jTextField25.setText("OK");
    }else {
        jTextField25.setBackground(vermelho);
        jTextField25.setText("Erro");
        jTextField25.setForeground(Color.WHITE);
        vet_seis[2]=1;
    }
break;


case 11:
    if (vetor_geral[j]==0){
        jTextField14.setBackground(verde);
        jTextField14.setForeground(Color.BLACK);
        jTextField14.setText("OK");
    }else {
        jTextField14.setBackground(vermelho);
        jTextField14.setText("Erro");
        jTextField14.setForeground(Color.WHITE);
        vet_seis[3]=1;
    }
break;

 case 12:
    if (vetor_geral[j]==0){
        jTextField15.setBackground(verde);
        jTextField15.setForeground(Color.BLACK);
        jTextField15.setText("OK");
    }else {
        jTextField15.setBackground(vermelho);
        jTextField15.setText("Erro");
        jTextField15.setForeground(Color.WHITE);
        vet_seis[3]=1;
    }
break;

 case 13:
    if (vetor_geral[j]==0){
        jTextField16.setBackground(verde);
        jTextField16.setForeground(Color.BLACK);
        jTextField16.setText("OK");
    }else {
        jTextField16.setBackground(vermelho);
        jTextField16.setText("Erro");
        jTextField16.setForeground(Color.WHITE);
        vet_seis[3]=1;
    }
break;
//BRAMS
 case 15:
    if (vetor_geral[j]==0){
        jTextField18.setBackground(verde);
        jTextField18.setForeground(Color.BLACK);
        jTextField18.setText("OK");
    } else if (vetor_geral[j]== 1) {
        jTextField18.setBackground(vermelho);
        jTextField18.setText("Erro");
        jTextField18.setForeground(Color.WHITE);
        vet_seis[4]=1;
    } else {
        jTextField18.setBackground(Color.BLACK);
        vet_seis[4]=2;
        }

break;

 case 16:
    if (vetor_geral[j]==0){
        jTextField19.setBackground(verde);
        jTextField19.setForeground(Color.BLACK);
        jTextField19.setText("OK");
    } else if (vetor_geral[j]== 1) {
        jTextField19.setBackground(vermelho);
        jTextField19.setText("Erro");
        jTextField19.setForeground(Color.WHITE);
        vet_seis[4]=1;
    } else {
        jTextField19.setBackground(Color.BLACK);
         vet_seis[4]=2;
       }

break;

 case 17:
    if (vetor_geral[j]==0){
        jTextField20.setBackground(verde);
        jTextField20.setForeground(Color.BLACK);
        jTextField20.setText("OK");
    } else  if (vetor_geral[j]== 1) {
        jTextField20.setBackground(vermelho);
        jTextField20.setText("Erro");
        jTextField20.setForeground(Color.WHITE);
        vet_seis[4]=1;
    } else {
         jTextField20.setBackground(Color.BLACK);
         vet_seis[4]=2;
    }

break;
// ETA
 case 19:
    if (vetor_geral[j]==0){
        jTextField22.setBackground(verde);
        jTextField22.setForeground(Color.BLACK);
        jTextField22.setText("OK");
    }else if (vetor_geral[j]== 1) {
        jTextField22.setBackground(vermelho);
        jTextField22.setText("Erro");
        jTextField22.setForeground(Color.WHITE);
        vet_seis[5]=1;
   } else {
         jTextField22.setBackground(Color.BLACK);
         vet_seis[5]=2;

    }
break;

 case 20:
    if (vetor_geral[j]==0){
        jTextField23.setBackground(verde);
        jTextField23.setForeground(Color.BLACK);
        jTextField23.setText("OK");
    }else if (vetor_geral[j]== 1) {
        jTextField23.setBackground(vermelho);
        jTextField23.setText("Erro");
        jTextField23.setForeground(Color.WHITE);
        vet_seis[5]=1;
  } else {
         jTextField23.setBackground(Color.BLACK);
         vet_seis[5]=2;

    }
break;
default:;}
j++;
 }

//////////////////////////////////////////////////////////////////////////

 int y =0;
 int erro_barra = 0;
 while (y < 6){

 switch (y) {
  case 0:
    if (vet_seis[y] == 0){
        jTextField3.setBackground(verde);
        jTextField3.setForeground(Color.BLACK);
        jTextField3.setText("OK");
    }else {
        jTextField3.setBackground(vermelho);
        jTextField3.setText("Erro");
        jTextField3.setForeground(Color.WHITE);
        erro_barra = 1;
    }
    break;

  case 1:
    if (vet_seis[y] == 0){
        jTextField9.setBackground(verde);
        jTextField9.setForeground(Color.BLACK);
        jTextField9.setText("OK");
    }else {
        jTextField9.setBackground(vermelho);
        jTextField9.setText("Erro");
        jTextField9.setForeground(Color.WHITE);
        erro_barra = 1;
    }
    break;

  case 2:
    if (vet_seis[y] == 0){
        jTextField13.setBackground(verde);
        jTextField13.setForeground(Color.BLACK);
        jTextField13.setText("OK");
    }else {
        jTextField13.setBackground(vermelho);
        jTextField13.setText("Erro");
        jTextField13.setForeground(Color.WHITE);
        erro_barra = 1;
    }
    break;

  case 3:
    if (vet_seis[y] == 0){
        jTextField17.setBackground(verde);
        jTextField17.setForeground(Color.BLACK);
        jTextField17.setText("OK");
    }else {
        jTextField17.setBackground(vermelho);
        jTextField17.setText("Erro");
        jTextField17.setForeground(Color.WHITE);
        erro_barra = 1;
    }
    break;
//BRAMS
  case 4:
    if (vet_seis[y] == 0){
        jTextField21.setBackground(verde);
        jTextField21.setForeground(Color.BLACK);
        jTextField21.setText("OK");
    } else if (vet_seis[y] == 1) {
        jTextField21.setBackground(vermelho);
        jTextField21.setText("Erro");
        jTextField21.setForeground(Color.WHITE);
        erro_barra = 1;
         } else {
        jTextField21.setBackground(Color.BLACK);

    }
    break;


//ETA
   case 5:
    if (vet_seis[y] == 0){
        jTextField24.setBackground(verde);
        jTextField24.setForeground(Color.BLACK);
        jTextField24.setText("OK");
    }else  if (vet_seis[y] == 1) {
        jTextField24.setBackground(vermelho);
        jTextField24.setText("Erro");
        jTextField24.setForeground(Color.WHITE);
        erro_barra = 1;
    } else {
          jTextField24.setBackground(Color.BLACK);

    }
    break;

    default:;}
y++;
 }

 jProgressBar1.setIndeterminate(false);
jProgressBar1.setValue(0);
   if (erro_barra == 1){
       jProgressBar1.setBackground(vermelho);

       
     

  /*     String envia = "/stornext/home/operacao/test_case/fonts/envia_email.bash";
       Runtime r_envia = Runtime.getRuntime();
       Process pro = null;
       try {
         pro = r_envia.exec(envia);
         pro.waitFor();
      }catch(Exception e)
     {textArea1.append(e+" \n\nErro na execucao de envio de email");}
*/

 }  else {
 jProgressBar1.setBackground(verde);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                       // Logger.getLogger(tela.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
   PrintScreen print= new PrintScreen();
       print.takeAPrint();


       if (control == 0){
/*
     String envia = "/stornext/home/operacao/test_case/fonts/envia_email_ok.bash";
       Runtime r_envia = Runtime.getRuntime();
       Process pro = null;
       try {
         pro = r_envia.exec(envia);
         pro.waitFor();
      }catch(Exception e)
     {textArea1.append(e+" \n\nErro na execucao de envio de email");}
 */
      control = 2;
       }
 }

}else {
    jProgressBar1.setIndeterminate(false);
jProgressBar1.setValue(0);
     textArea1.setText(null);
     textArea1.append("\nTest_case nao executado.");
     textArea1.append("\n\nPor favor tente mais tarde.");
}
jProgressBar1.setIndeterminate(false);
jProgressBar1.setValue(0);
 
 }
  }

//
public class PrintScreen {
public void takeAPrint() {

Toolkit toolkit = Toolkit.getDefaultToolkit();
//Definindo a dimensão que quer capturar
//pode ser definido o tamanho que desejar
Dimension screenSize = toolkit.getScreenSize();
Rectangle screenRect = new Rectangle(screenSize);
// criando o print screen
Robot robot=null;
            try {
                robot = new Robot();
            } catch (AWTException ex) {
                Logger.getLogger(tela.class.getName()).log(Level.SEVERE, null, ex);
            }
BufferedImage screenCapturedImage = robot.createScreenCapture(screenRect);
            try {
                //depois disso é só procurar a imagem no local indicado abaixo, no meu caso em:
                // /Users/rodrigogomes/printScreen.png
                //Aqui você pode alterar o formato da imagem para, por exemplo, JPG
                //É só mudar o “png” para “jpg” e pronto
                ImageIO.write(screenCapturedImage, "jpg", new File("C:\\printScreen.jpg"));
                //File(“/Users/rodrigogomes/printScreen.png”));
            } catch (IOException ex) {
                Logger.getLogger(tela.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        textArea1 = new java.awt.TextArea();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cyborg - Operação");
        setResizable(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 73, 870, 10);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Test_case");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(370, 20, 100, 30);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ambiente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("HOME");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SUBMIT_HOME");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("WORK_HOME");

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField2.setBackground(new java.awt.Color(0, 0, 0));
        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField3.setBackground(new java.awt.Color(0, 0, 0));
        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField4.setBackground(new java.awt.Color(0, 0, 0));
        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 130, 130, 160);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SMS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CDP_PING");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CDP_SUITES");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("RPC_SMS");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("RPCBIND -I");

        jTextField5.setBackground(new java.awt.Color(0, 0, 0));
        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField6.setBackground(new java.awt.Color(0, 0, 0));
        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField7.setBackground(new java.awt.Color(0, 0, 0));
        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField7.setForeground(new java.awt.Color(255, 255, 255));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField8.setBackground(new java.awt.Color(0, 0, 0));
        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField8.setForeground(new java.awt.Color(255, 255, 255));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField9.setBackground(new java.awt.Color(0, 0, 0));
        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(150, 130, 130, 160);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aux", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("SUBMISSAO");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ESCRITA_SUBMIT");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ESCRITA_WORK");

        jTextField10.setBackground(new java.awt.Color(0, 0, 0));
        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField11.setBackground(new java.awt.Color(0, 0, 0));
        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField12.setBackground(new java.awt.Color(0, 0, 0));
        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField13.setBackground(new java.awt.Color(0, 0, 0));
        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ESCRITA_ARCHIVE");

        jTextField25.setBackground(new java.awt.Color(0, 0, 0));
        jTextField25.setEditable(false);
        jTextField25.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField25.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(290, 130, 140, 160);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tupa2", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("SUBMISSAO");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ESCRITA_SUBMIT");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ESCRITA_WORK");

        jTextField14.setBackground(new java.awt.Color(0, 0, 0));
        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField15.setBackground(new java.awt.Color(0, 0, 0));
        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField16.setBackground(new java.awt.Color(0, 0, 0));
        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField17.setBackground(new java.awt.Color(0, 0, 0));
        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(440, 130, 130, 160);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Brams", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("MAKESFC");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("MAKEVFILE");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("INITIAL");

        jTextField18.setBackground(new java.awt.Color(0, 0, 0));
        jTextField18.setEditable(false);
        jTextField18.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField19.setBackground(new java.awt.Color(0, 0, 0));
        jTextField19.setEditable(false);
        jTextField19.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField19.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField20.setBackground(new java.awt.Color(0, 0, 0));
        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField20.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField21.setBackground(new java.awt.Color(0, 0, 0));
        jTextField21.setEditable(false);
        jTextField21.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField21.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(580, 130, 130, 160);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Eta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("PRE");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 8));
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("FCT");

        jTextField22.setBackground(new java.awt.Color(0, 0, 0));
        jTextField22.setEditable(false);
        jTextField22.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField23.setBackground(new java.awt.Color(0, 0, 0));
        jTextField23.setEditable(false);
        jTextField23.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField24.setBackground(new java.awt.Color(0, 0, 0));
        jTextField24.setEditable(false);
        jTextField24.setFont(new java.awt.Font("Tahoma", 1, 9));
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField24.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel7);
        jPanel7.setBounds(720, 130, 130, 160);

        textArea1.setBackground(new java.awt.Color(0, 0, 0));
        textArea1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(textArea1);
        textArea1.setBounds(20, 310, 560, 180);

        jProgressBar1.setBackground(new java.awt.Color(0, 0, 0));
        jProgressBar1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jProgressBar1.setForeground(new java.awt.Color(255, 255, 0));
        jProgressBar1.setBorderPainted(false);
        jPanel1.add(jProgressBar1);
        jProgressBar1.setBounds(590, 470, 260, 19);

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setForeground(new java.awt.Color(0, 0, 255));
        jButton1.setText("Submit");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(770, 90, 80, 20);

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setForeground(new java.awt.Color(0, 255, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("jLabel22");
        jLabel22.setOpaque(true);
        jPanel1.add(jLabel22);
        jLabel22.setBounds(780, 50, 70, 20);

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("jLabel23");
        jPanel1.add(jLabel23);
        jLabel23.setBounds(10, 50, 260, 14);

        jScrollPane1.setViewportView(jPanel1);

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
        Execucao exe = new Execucao();
        exe.executar();
     
    

    }//GEN-LAST:event_jButton1ActionPerformed

 
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables

}

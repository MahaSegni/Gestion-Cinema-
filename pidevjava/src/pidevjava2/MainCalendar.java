/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.icalendarfx.properties.calendar.Method;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;
import models.Calendar;
import Service.ServiceCalendar;


/**
 *
 * @author Administrateur
 */
public class MainCalendar extends Application {
   
              TextField clienttf = new TextField();
    //File file = filechooser.showOpenDialog(primaryStage); // permet de choisir le path de ficher
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    //2021-02-24 20:48:12
    String filename = sdf.format(dt);// pour la creation d'un nom unique du ficher
    File file = new File(filename + ".ics");

    @Override
    public void start(Stage primaryStage) {
        VCalendar mainVCalendar = new VCalendar()
                .withProductIdentifier(ICalendarAgenda.DEFAULT_PRODUCT_IDENTIFIER).withVersion();

        BorderPane root = new BorderPane();
        ICalendarAgenda agenda = new ICalendarAgenda(mainVCalendar);
        root.setCenter(agenda);
        Label time = new Label("Time  ");
        Button increaseweek = new Button(" Week >> ");
        Button decreaseweek = new Button(" << Week");
        Button yearplus = new Button(" Year >>");
        Button yearminus = new Button(" << Year ");
        Label importandexportcalender = new Label(" Import / Export calender  ");
        Button importbtn = new Button(" Import Calender ");
       // clienttf.setPromptText("User name Here ...");
        Button Exportbtn = new Button(" Export Calender ");
        HBox buttonHboxTime = new HBox(
                time,
                yearminus,
                yearplus,
                decreaseweek,
                increaseweek,
                importandexportcalender,
                importbtn,
               // clienttf,
                Exportbtn
        );
        //HBox buttonHboxTime = new HBox(yearminus, yearplus, decreaseweek, increaseweek, Exportbtn);
        buttonHboxTime.setSpacing(5);
        root.setTop(buttonHboxTime);

        //weeks
        increaseweek.setOnAction((e)
                -> {
            LocalDateTime newDisplayedLocalDateTime = agenda.getDisplayedLocalDateTime().plus(Period.ofWeeks(1));
            agenda.setDisplayedLocalDateTime(newDisplayedLocalDateTime);
        });
        decreaseweek.setOnAction((e)
                -> {
            LocalDateTime newDisplayedLocalDateTime = agenda.getDisplayedLocalDateTime().minus(Period.ofWeeks(1));
            agenda.setDisplayedLocalDateTime(newDisplayedLocalDateTime);
        });

        //years 
        yearplus.setOnAction((e)
                -> {
            LocalDateTime newDisplayedLocalDateTime = agenda.getDisplayedLocalDateTime().plus(Period.ofYears(1));
            agenda.setDisplayedLocalDateTime(newDisplayedLocalDateTime);
        });
        yearminus.setOnAction((e)
                -> {
            LocalDateTime newDisplayedLocalDateTime = agenda.getDisplayedLocalDateTime().minus(Period.ofYears(1));
            agenda.setDisplayedLocalDateTime(newDisplayedLocalDateTime);
        });
        //import ics file
        FileChooser filechooser1 = new FileChooser();
        filechooser1.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("ics files", "*.ics"));
        importbtn.setOnAction(e
                -> {
            File file1 = filechooser1.showOpenDialog(primaryStage);
            if (file1 != null && file1.toString().lastIndexOf("ics") > 0) {
                try {
                    // process ITIP and log exceptions
                    final List<String> log = new ArrayList<>();
                    VCalendar iTIPMessage = VCalendar.parseICalendarFile(file1.toPath());
                    Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> log.add(exception.getMessage()));
                    List<String> messagelog = mainVCalendar.processITIPMessage(iTIPMessage);
                    log.addAll(messagelog);
                    log.forEach(System.out::println);
                    JOptionPane.showMessageDialog(null, "Your Calender has been successfully Imported! \n Please refrech your calender! \n"
                            + "by Increase and decreasing weeks just one time! ");

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Invalide file :" + file1.getName() + ". Please Select a valide ics file");
                throw new IllegalArgumentException("Invalide file :" + file1 + ". Please Select a valide ics file");
            }

        });

        //creation ics file
        FileChooser filechooser = new FileChooser();
        filechooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("ics files", "*.ics"));
        Exportbtn.setOnAction(e
                -> {
          /* if (clienttf.getText().isEmpty() || clienttf.getText() == null) {
                JOptionPane.showMessageDialog(null, " Please enter Your client User name ! ");

            } else { */

                VCalendar publishMessage = new VCalendar().withMethod(Method.MethodType.PUBLISH);
                mainVCalendar.copyChildrenInto(publishMessage);
                BufferedWriter writer = null;
                //BufferedWriter writer2 = null;
                try {

                    writer = new BufferedWriter(new FileWriter(file));
                    //writer2 = new BufferedWriter(new FileWriter(file));
                    StringBuffer buff = new StringBuffer(publishMessage.toString());
                    //StringBuffer buff2 = new StringBuffer(publishMessage.toString());
                    while (buff.toString().indexOf(';') > 0) {
                        int debut = buff.toString().indexOf(';'); // kanit sans +1 
                        System.out.println(debut);
                        StringBuffer tempbuff = new StringBuffer(buff.toString().substring(debut));
                        int fin = debut + tempbuff.toString().indexOf(':') ; //kanit +3
                        System.out.println(fin);
                        buff.delete(debut, fin);
                    }

                    writer.write(buff.toString());
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Your Calender has been successfully Created! \n "
                            + "Please check your Software Directory! \n File name : "
                            + filename.toUpperCase() + ".ics");
                    JOptionPane.showMessageDialog(null, "Your created events has been successfully uploaded to database!  ");
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                agenda.appointments().addListener(new ListChangeListener< Agenda.Appointment>() {
                    public void onChanged(ListChangeListener.Change<? extends Agenda.Appointment> c) {
                        while (c.next()) {
                            if (c.wasPermutated()) {
                                for (int i = c.getFrom(); i < c.getTo(); ++i) {
                                    //permutate
                                }
                            } else if (c.wasUpdated()) {
                                //update item
                            } else {
                                for (Agenda.Appointment a : c.getRemoved()) {
                                }
                                for (Agenda.Appointment a : c.getAddedSubList()) {
                                    try {
                                        printAppointment(a);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(MainCalendar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    }
                });
//"[Coachini APP][IMPORTANT] Calendrie des seance de coaching" filename + ".ics" sendto@gmail.com from@gmail.com 
// Bonne Journée a vous. /n vous trouvez ci joindre votre calendrie des seances de coaching /n Coordialement. /n Administration Coachini pwd
// "file path / filename if it exist in your project directory"
            // preparation integration
//         try {
//            Connection cnx = ReviewConnection.getInstance().getCnx();
//            String requete = "SELECT * FROM compte_client WHERE user_name='"+clienttf.getText()+"'";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(requete);
//              convert here the rs to string to get email client and put it in  emailClient 
//  // a tester a part           String emailClient = rs.toString(); 
//              while(rs.next()) {}
//              System.out.println("Email recupérée !! ");
//              } catch (SQLException ex) {System.err.println(ex.getMessage());}
        String to = "Maha.segni@esprit.tn";//take user email address from database  ( get emailClient ) 
        final String user = "Maha.segni@esprit.tn";//save it like this   
        final String password = "xvxpjdjklxhwsxpf";//pass of user  

        //1) get the session object     
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        //2) compose message     
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("[Happy PArk][IMPORTANT] Calendrie des rendez-vous"); 

            //3) create MimeBodyPart object and set your message text     
            BodyPart messageBodyPart1 = new MimeBodyPart(); 
            messageBodyPart1.setText("Bonne Journée a vous. \n \n"
                    + "vous trouvez ci joindre votre calendrie des rendez-vous . Vous pouvez le consulter. "
                    + "\n"
                    + "\n"
                    + "\n"
                    + "Coordialement. "
                    );

            //4) create new MimeBodyPart object and set DataHandler object to this object      
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            String filenamemail = filename + ".ics";//change   
            DataSource source = new FileDataSource(filenamemail);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filenamemail);

            //5) create Multipart object and add MimeBodyPart objects to this object      
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);

            //6) set the multiplart object to the message object  
            message.setContent(multipart);

            //7) send message  
            Transport.send(message);

            System.out.println("message sent....");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
            
        });

        Scene scene = new Scene(root, 1366, 786);
        primaryStage.setScene(scene);
        primaryStage.setTitle(" Calender ");
        primaryStage.show();
    }

    private void printAppointment(Agenda.Appointment a) throws ParseException {

        System.out.println(" Summary :" + a.getSummary());
        System.out.println(" Description :" + a.getDescription());
        System.out.println(" Date :" + a.getEndLocalDateTime().format(DateTimeFormatter.ISO_DATE));

       System.out.println(" Starts at :" + a.getStartLocalDateTime().format(DateTimeFormatter.ISO_TIME));
        System.out.println(" Finishs at :" + a.getEndLocalDateTime().format(DateTimeFormatter.ISO_TIME));
    

        java.sql.Date sqlDate1;
        sqlDate1 = java.sql.Date.valueOf(a.getStartLocalDateTime().format(DateTimeFormatter.ISO_DATE));


        java.sql.Date sqlDate2;
        sqlDate2 = java.sql.Date.valueOf(a.getEndLocalDateTime().format(DateTimeFormatter.ISO_TIME));
        ServiceCalendar rc = new ServiceCalendar();
       // Calendar rc = new Calendar();
        rc.ajouter(new Calendar(a.getSummary(),a.getDescription(),sqlDate1,sqlDate2 )); 
        rc.afficher();

        

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date date = formatter.parse(a.getStartTime().toString());
//            String strCorrectDepartureDate = new SimpleDateFormat("ddMMM").format(date);
//            System.out.println(strCorrectDepartureDate);
//        } catch (ParseException ex) {
//            Logger.getLogger(MainPlaningCoach.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(a.getStartTime().toString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}

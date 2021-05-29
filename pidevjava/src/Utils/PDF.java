/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Employe;
import Service.ServiceEmploye;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.management.Notification;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 *
 * @author HP
 */


public class PDF {
    
    /**
     * @param args the command line arguments
     */
    
     private Statement ste;
     private Connection con;
    
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
        //Image img = Image.getInstance("photo.png");
        //Image img2 = Image.getInstance("logo.png");
        ServiceEmploye us=new ServiceEmploye();
        con = MaConnexion.getInstance().getConnection();
                ste = con.createStatement();
       List<Employe> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * from `employe`;");
     while (rs.next()) {                
          /*     int idCommande=rs.getInt(1);
               int idUser=rs.getInt(2);
               float total=rs.getFloat(3);
               String etat=rs.getString(4);*/
          int idemp = rs.getInt(1);
          String nomemp = rs.getString(2);
          String prenomemp = rs.getString(3);
          int numtelemp = rs.getInt(4);
          String adresseemp = rs.getString(5);
          
          Employe e=new Employe(idemp, nomemp,prenomemp, numtelemp,adresseemp);   
               
        arr.add(e);}
     
        for(Employe h:arr)
        {
        document.add(new Paragraph("Reference :"+h.getIdemp()));
        document.add(new Paragraph("Nom :"+ h.getNomemp()));
        document.add(new Paragraph("Prenom:"+h.getPrenomemp()));
        document.add(new Paragraph("numero telephone :"+h.getNumtelemp()));
        document.add(new Paragraph("adresse mail :"+h.getAdresseemp()));
           //document.add(img);
         //document.add(img2);
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        } 
        
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}

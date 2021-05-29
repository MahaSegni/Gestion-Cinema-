/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Conge;
import Entities.Employe;
import Service.ServiceConge;
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
import java.sql.Date;
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


public class PDF1 {
    
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
        ServiceConge us=new ServiceConge();
        con = MaConnexion.getInstance().getConnection();
                ste = con.createStatement();
       List<Conge> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * from `conge`;");
     while (rs.next()) {                
          /*     int idCommande=rs.getInt(1);
               int idUser=rs.getInt(2);
               float total=rs.getFloat(3);
               String etat=rs.getString(4);*/
          int idconge = rs.getInt(1);
          Date dateconge = rs.getDate(2);
          String motifconge = rs.getString(3);
          
          int nbjourconge = rs.getInt(4);
            int employe_id = rs.getInt(5);
          
          Conge e=new Conge(idconge, dateconge,motifconge, nbjourconge,employe_id);   
               
        arr.add(e);}
     
        for(Conge h:arr)
        {
        document.add(new Paragraph("Reference :"+h.getIdconge()));
        document.add(new Paragraph("Date Conge :"+ h.getDateconge()));
        document.add(new Paragraph("Motif :"+h.getMotifconge()));
        document.add(new Paragraph("nombre de jours du conge :"+h.getNbjourconge()));
        document.add(new Paragraph("reference emlpoye correspondante :"+h.getEmploye_id()));
           //document.add(img);
         //document.add(img2);
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        } 
        
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}

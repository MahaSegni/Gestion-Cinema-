/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Cinema;
import Service.ServiceCinema;
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
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mahas
 */
public class PDFCinema {
     private Statement ste;
     private Connection con;
    
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
        //Image img = Image.getInstance("photo.png");
        //Image img2 = Image.getInstance("logo.png");
        ServiceCinema us=new ServiceCinema();
        con = MaConnexion.getInstance().getConnection();
                ste = con.createStatement();
       List<Cinema> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT num, nbr,film,date, heurep   from `cinema`;");
     while (rs.next()) {                
          /*     int idCommande=rs.getInt(1);
               int idUser=rs.getInt(2);
               float total=rs.getFloat(3);
               String etat=rs.getString(4);*/
          int idemp = rs.getInt(1);
          int nomemp = rs.getInt(2);
          int prenomemp = rs.getInt(3);
          java.util.Date adresseemp = rs.getDate(4);
          Time numtelemp = rs.getTime(5);
          
          Cinema e=new Cinema(idemp, nomemp,prenomemp,adresseemp,numtelemp);   
               
        arr.add(e);}
     document.add(new Paragraph("-------------------------Rapport Cinema------------------------------------------------------------- "));
        
        for(Cinema h:arr)
        {
        document.add(new Paragraph("numero salle :"+h.getNum()));
        document.add(new Paragraph("nombre de personne :"+ h.getNbr()));
        document.add(new Paragraph("film :"+h.getFilm()));
        document.add(new Paragraph("date  :"+h.getDate()));
        document.add(new Paragraph("heure :"+h.getHeurep()));
           //document.add(img);
         //document.add(img2);
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                                                                        "));
        
        } 
        
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}

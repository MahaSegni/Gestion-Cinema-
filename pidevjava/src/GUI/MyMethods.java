/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;


import javafx.print.PrinterJob;
import javafx.scene.Node;

/**
 *
 * @author 
 * BOUARRHA Ayoub
 * LAMINE Marouane 
 */
public class MyMethods {
     
    static String before(String value, int a) {
	if (a <0 )//le cas où aucun mot d'avant n'existe
	    return "";
	
        int posPremierSeparateur =0;
        for(int i=a;i>=0;i--)
        {
            if(value.charAt(i)==' '||value.charAt(i)=='\t'||value.charAt(i)=='\n' ||value.charAt(i)=='\'' ||value.charAt(i)=='’')
            {
                posPremierSeparateur=i+1;
                break;
            }
        }
        
        FXMLDocumentController.positionStartWordBefore=posPremierSeparateur;
        FXMLDocumentController.positionEndWordBefore=a+1;
	return value.substring(FXMLDocumentController.positionStartWordBefore, FXMLDocumentController.positionEndWordBefore);
    }        
        
    static String after(String value, int posPremierSeparateur) {
	if (posPremierSeparateur >=value.length())//le cas où aucun mot d'apres n'existe
	    return "";
        
        //Cas separateur avant un autre separateur
        if((value.charAt(posPremierSeparateur-1)==' '
        || value.charAt(posPremierSeparateur-1)=='\'' 
        || value.charAt(posPremierSeparateur-1)=='\n' 
        || value.charAt(posPremierSeparateur-1)=='\t') && (value.charAt(posPremierSeparateur)==' '
                                                        || value.charAt(posPremierSeparateur)=='\n' 
                                                        || value.charAt(posPremierSeparateur)=='\t'))
        {
            return "";
        }
        try
        {            
            //retourne la position du separateur le plus proche
            FXMLDocumentController.positionEndWordAfter=getIndexSeparateurSuivant(value,posPremierSeparateur);    
            FXMLDocumentController.positionStartWordAfter=posPremierSeparateur;
             
            return value.substring(FXMLDocumentController.positionStartWordAfter,  FXMLDocumentController.positionEndWordAfter);
        }
        catch(Exception ex)//cas dernier mot
        {
            FXMLDocumentController.positionStartWordAfter=posPremierSeparateur;
            FXMLDocumentController.positionEndWordAfter=value.length();
            return value.substring(posPremierSeparateur);
        }
        
    }
    
    static String currentWord(String value, int a) {
	//Cas aucun mot saisi( example suppresion de text)
        if (a <0 )
	    return "";
        
        try
        {
        //Cas aucun mot saisi( example suppresion d'un mot)
        if((value.charAt(a)==' '||value.charAt(a)=='\t'||value.charAt(a)=='\n' ||value.charAt(a)=='\'' ||value.charAt(a)=='’')
            &&(value.charAt(a+1)==' '||value.charAt(a+1)=='\t'||value.charAt(a+1)=='\n' ||value.charAt(a+1)=='\'' ||value.charAt(a+1)=='’') )
            return "";
        }
        catch(Exception ex){}
            
        int posPremierSeparateur =0;
        for(int i=a;i>=0;i--)
        {
            if(value.charAt(i)==' '||value.charAt(i)=='\t'||value.charAt(i)=='\n' ||value.charAt(i)=='\'' ||value.charAt(i)=='’')
            {
                posPremierSeparateur=i+1;
                break;
            }
            else if(i==0)//cas premier mot
            {
               break;//sortir avec position 0
            }
        }
        
        
        try
        {
             
            FXMLDocumentController.positionEndWord=getIndexSeparateurSuivant(value,posPremierSeparateur);            
            FXMLDocumentController.positionStartWord=posPremierSeparateur;
            //retourne la position du separateur le plus proche
            
            return value.substring(FXMLDocumentController.positionStartWord, FXMLDocumentController.positionEndWord);
        }
        catch(Exception ex)//cas dernier mot
        {
            FXMLDocumentController.positionStartWord=posPremierSeparateur;
            FXMLDocumentController.positionEndWord=value.length();
            return value.substring(posPremierSeparateur);
        }
        
    }
    
    public static int getIndexSeparateurSuivant(String txt,int indexSeparateurPrecedent) {
           
            int indexEspaceProchain=txt.indexOf(' ',indexSeparateurPrecedent+1);
            int indexRetourLigneProchaine=txt.indexOf('\n',indexSeparateurPrecedent+1);
            int indexTabulationProchaine=txt.indexOf('\t',indexSeparateurPrecedent+1);
            int indexApostropheProchaine=txt.indexOf('\'',indexSeparateurPrecedent+1);
            int indexApostrophe2Prochaine=txt.indexOf('’',indexSeparateurPrecedent+1);
            
            //affecter au nombre négarifs la max valeur integer
            if(indexEspaceProchain==-1)indexEspaceProchain=2147483000;
            if(indexRetourLigneProchaine==-1)indexRetourLigneProchaine=2147483000;
            if(indexTabulationProchaine==-1)indexTabulationProchaine=2147483000;
            if(indexApostropheProchaine==-1)indexApostropheProchaine=2147483000;
            if(indexApostrophe2Prochaine==-1)indexApostrophe2Prochaine=2147483000;
            //retourne la position du separateur le plus proche
            int indexSepProche = Math.min(Math.min(indexEspaceProchain,indexRetourLigneProchaine),Math.min(indexTabulationProchaine,Math.min(indexApostropheProchaine,indexApostrophe2Prochaine)));
            
            if(indexSepProche==2147483000)//Cas mot est à la fin du text
                 return txt.length();
            
            return indexSepProche;
    }
    
    public static boolean isNumericOuCaractereSpe(String str){  
        // voir si c'est un simple caractére
        if(str.length()==1)
            return true;
        
        // voir si c'est une valeur numérique
        try  
        {  
            double d = Double.parseDouble(str);  
        }  
        catch(NumberFormatException nfe)  
        {  
            return false;  
        }  
        return true;  
    }
    
    public static void print(Node node) {
    System.out.println("Creation du printer job.");

    PrinterJob job = PrinterJob.createPrinterJob();
    if (job != null) {
      System.out.println(job.jobStatusProperty().asString());

      boolean printed = job.printPage(node);
      if (printed) {
        job.endJob();
      } else {
        System.out.println("Impression echouee.");
      }
    } else {
      System.out.println("Creation du printer job impossible.");
    }
  }
            
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author 
 * BOUARRHA Ayoub
 * LAMINE Marouane 
 */

public class Dictionnaire {   
    private String nomDico;
    private TreeMap <String,ArrayList<String>> listMotDebPar;
    private TreeMap <String,ArrayList<String>> listMotTerPar;   
    

    
    
     public static String temp="";
     /*cette variable "temp" sert comme intermediaire temporaire entre la classe 
     Dictionnaire et Classe MotCorrect pour l'utilisation du mot érroné      
     courant pour la comparaison*/
     private static Dictionnaire instance = null;
     
    //Constructeur d'instance unique
    public static Dictionnaire getOneDictionnaire(String _nomDico) 
    {
    if(instance == null) {
       instance = new Dictionnaire(_nomDico);
    }
    else
        System.out.println("Impossible d'ajouter un autre Dictionnaire");
    return instance;
   }
      
    private Dictionnaire(String _nomDico) 
    {
        this.nomDico=_nomDico;
        this.listMotDebPar = chargerListMotDebPar();
        this.listMotTerPar = chargerListMotTerPar();       
    }   
    
    public void ChangerDic(String _nomDico) 
    {
        this.nomDico=_nomDico;
        this.listMotDebPar = chargerListMotDebPar();
        this.listMotTerPar = chargerListMotTerPar();       
    } 

    public Boolean AjouterMotAuDic (String _nouvauMot)
    {        
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(nomDico, true)))) 
        {
            out.print("\n"+_nouvauMot);
            out.flush();
            out.close();
            this.listMotDebPar.get(Character.toString(_nouvauMot.charAt(0))).add(_nouvauMot);
            this.listMotTerPar.get(Character.toString(_nouvauMot.charAt(_nouvauMot.length()-1))).add(_nouvauMot);
            return true;
        } 
        catch (IOException e) 
        {
            return false;
        }
    }
    
    private TreeMap <String,ArrayList<String>> chargerListMotDebPar()  
    {
        //TreeMap <lettre,Liste des mots qui débute la lettre selectionner>
        TreeMap <String,ArrayList<String>> listMotDebPar = new TreeMap();
        try 
        {
            BufferedReader br = new BufferedReader(
		new InputStreamReader(
                    new FileInputStream(nomDico), "ISO-8859-1"));
            
            String value;//variable de lecture des lignes du fichier
            char c;//variable de lecture du premier caractére de chaque mot
            while(true)
            {
                try 
                {
                    value=br.readLine(); //lecture de la ligne courante
                    if(value==null)break; //lecture de fichier fini
                    c=value.charAt(0);//lecture du premier caractere
                       if(!(listMotDebPar.containsKey(Character.toString(c))))// voir si il n'y a pas de clé pour le caractére courant
                            //Ajout d'une nouvelle clé au cas d'absence d'une anciene
                            listMotDebPar.put(Character.toString(c),new ArrayList<>(Arrays.asList(value)));
                        else
                            //Ajout d'un nouvelle mot avec les anciens mots déjà éxistant dans la liste
                           listMotDebPar.get(Character.toString(c)).add(value);
                    
                } 
                catch (EOFException ex) {break;} 
                catch (IOException ex) {break;}
            }
        }
        //cas de probléme au niveau de fichier
        catch (FileNotFoundException e) {return null;} catch (UnsupportedEncodingException ex) { } 
        
        return listMotDebPar;           
        
    }    
    
    private TreeMap <String,ArrayList<String>> chargerListMotTerPar()
    {        
        TreeMap <String,ArrayList<String>> listMotTerPar = new TreeMap();
        try 
        {                 
            //BufferedReader br = new BufferedReader(new FileReader(nomDico));
                 BufferedReader br = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(nomDico), "ISO-8859-1"));
            String value;//variable de lecture des lignes du fichier
            char c;//variable de lecture du dernier caractere de chaque mot
            while(true)
            {
                try 
                {
                    value=br.readLine(); //lecture de la ligne courante
                    if(value==null)break; //lecture de fichier fini
                    c=value.charAt(value.length()-1);//lecture du dernier caractere
                        if(!(listMotTerPar.containsKey(Character.toString(c)))) // voir si il n'y a pas de clé pour le caractére courant
                            //Ajout d'une nouvelle clé au cas d'absence d'une anciene
                            listMotTerPar.put(Character.toString(c),new ArrayList<>(Arrays.asList(value)));
                        else
                            //Ajout d'un nouvelle mot avec les anciens mots déja éxistant dans la liste
                            listMotTerPar.get(Character.toString(c)).add(value);  
                    
                } 
                catch (EOFException ex) {break;} 
                catch (IOException ex) {break;}
            }
        } 
        //cas de probléme au niveau de fichier
        catch (FileNotFoundException e) {return null;} catch (UnsupportedEncodingException ex) { } 
        
        return listMotTerPar;           
        
    }
    
    public  Boolean CorrectOuPas(String mot)
    {
        try
        {
            if(MyMethods.isNumericOuCaractereSpe(mot))//voir si le String est un nombre 
                return true;
            return this.listMotDebPar.get(Character.toString(mot.toLowerCase().charAt(0))).contains(mot.toLowerCase());
        }
        catch(Exception ex)// Cas mots qui débute avec des symoble non correct 
        {
            return false;
        }
    }
    
    public ArrayList<MotCorrect> Correction(String _motInc)
    {  
        if(CorrectOuPas(_motInc))
            return null;
        
        ArrayList<MotCorrect> listeRetour= new ArrayList();
        int nbrCorrespandance=0;
        
        //Voir si la lettre du depart du mot incorrect se trouve sur la liste "listMotDebPar"
        if(listMotDebPar.containsKey(Character.toString(_motInc.charAt(0))))
        {            
           for(String motCor : listMotDebPar.get(Character.toString(_motInc.charAt(0))))
           {               
            //voir si le motCor selectionner a la meme taille que _motInc ou ils ont une difference entre de 2 caracter de plus ou de moins 
              if(((_motInc.length()>=motCor.length()-1 && motCor.length()-1 >=1)&&(_motInc.length()<=motCor.length()+1))) 
              {
                for(int i=0; i<=_motInc.length()-1;i++)
                {
                       if(motCor.contains(Character.toString(_motInc.charAt(i))))
                       {
                           nbrCorrespandance++;
                       }
                       
                }                
                // voir si la correspondance  est presente au cas d'une limite d'un seul caractére a ajouté 
                if(motCor.length()-nbrCorrespandance<=1)
                    listeRetour.add(new MotCorrect(motCor));
                nbrCorrespandance=0;
              }
           }  
        }
        
        
        //Voir si la lettre du depart du mot incorrect se trouve sur la liste "listMotTerPar" 
        if(listMotTerPar.containsKey(Character.toString(_motInc.charAt(_motInc.length()-1))))
        {
           for(String motCor : listMotTerPar.get(Character.toString(_motInc.charAt(_motInc.length()-1))))
           {
            //voir si le motCor selectionner a la meme taille que _motInc ou ils ont une difference entre de 2 caracter de plus ou de moins 
              if(((_motInc.length()>=motCor.length()-1 && motCor.length()-1 >=1)&&(_motInc.length()<=motCor.length()+1))) 
              {
                for(int i=0; i<=_motInc.length()-1;i++)
                {
                       if(motCor.contains(Character.toString(_motInc.charAt(i))))
                       {
                           nbrCorrespandance++;
                       }                       
                }                
                // voir si la correspondance  est presente au cas d'une limite d'un seul caractére a ajouté  
                // et voir si le mot à ajouter n'est pas déjà present dans la listeRetour
                MotCorrect mc=new MotCorrect(motCor);
                if(motCor.length()-nbrCorrespandance<=1 && !listeRetour.contains(mc))
                    listeRetour.add(mc);
                nbrCorrespandance=0;
              }
           }  
        }
        
        Dictionnaire.temp=_motInc;
        Collections.sort(listeRetour);
        return listeRetour;
    }  
     

}



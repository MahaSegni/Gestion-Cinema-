/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author 
 */

class MotCorrect implements Comparable<MotCorrect> {
    
    public String mot;
    
     @Override
    public boolean equals(Object o)
    {
        return this.mot.equals(((MotCorrect)o).mot);
    }
    
    public MotCorrect(String mot)
    {
        this.mot=mot;
    }
    
    public String toString()
    {
        return mot;
    }
    
     public int compareTo(MotCorrect o)
    {
        int pntA=0;
        int pntB=0;
        for(int i=0; i<=mot.length()-1;i++)
        {
            for(int j=i+1; j<=mot.length()-1;j++)
            {
                if(Dictionnaire.temp.contains(mot.substring(i,j)))
                    pntA++;
                else
                    break;
            }
        }
         for(int i=0; i<=mot.length()-1;i++)
        {
            for(int j=i+1; j<=o.mot.length()-1;j++)
            {
                if(Dictionnaire.temp.contains(o.mot.substring(i,j)))
                    pntB++;
                else
                    break;
            }           
        }
        
         if(pntA>pntB)return -1;
         if(pntB>pntA)return 1;
         return 0;
         
         
          
    }
}

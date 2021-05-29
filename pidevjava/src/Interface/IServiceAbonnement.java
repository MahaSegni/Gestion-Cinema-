/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Abonnement;
import java.util.List;

/**
 *
 * @author Zeineb
 */
public interface IServiceAbonnement {
    
    public void ajoutAbonnement(Abonnement a);
    public List <Abonnement> MesAbonnements(int id);
    public List<Abonnement> getabo_last2days();
    
    public void delete_abonnement(int id);
    public List<Abonnement> getAboByNom(String nom);
    public void updateState(int id);
    public void ajoutAbonnement_enligne(Abonnement a);
}

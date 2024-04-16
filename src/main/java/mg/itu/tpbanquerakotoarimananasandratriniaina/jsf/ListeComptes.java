/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerakotoarimananasandratriniaina.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanquerakotoarimananasandratriniaina.entity.CompteBancaire;
import mg.itu.tpbanquerakotoarimananasandratriniaina.jsf.util.Util;
import mg.itu.tpbanquerakotoarimananasandratriniaina.service.GestionnaireCompte;

/**
 *
 * @author Sandratriniaina
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

private List<CompteBancaire> allComptes;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        if (allComptes == null) {
            allComptes = gestionnaireCompte.getAllComptes();
        }
        return allComptes;
    }
    
    public String supprimerCompte(CompteBancaire compte) {
        gestionnaireCompte.supprimerCompte(compte);
        Util.addFlashInfoMessage("Compte de " + compte.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
    
}

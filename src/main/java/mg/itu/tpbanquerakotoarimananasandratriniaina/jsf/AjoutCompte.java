/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerakotoarimananasandratriniaina.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanquerakotoarimananasandratriniaina.entity.CompteBancaire;
import mg.itu.tpbanquerakotoarimananasandratriniaina.jsf.util.Util;
import mg.itu.tpbanquerakotoarimananasandratriniaina.service.GestionnaireCompte;

/**
 *
 * @author Sandratriniaina
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {
    
    private String nom;
    @PositiveOrZero
    private int soldeInitial;

    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSoldeInitial() {
        return soldeInitial;
    }

    public void setSoldeInitial(int soldeInitial) {
        this.soldeInitial = soldeInitial;
    }
    
    public String creerCompte(){
        CompteBancaire compte = new CompteBancaire(nom, soldeInitial);
        gestionnaireCompte.creerCompte(compte);
        Util.addFlashInfoMessage("Creation du compte pour " + compte.getNom() + " avec solde initial de " + compte.getSolde() + " effectuée avec succès");
        return "listeComptes?faces-redirect=true";
    }
    
}

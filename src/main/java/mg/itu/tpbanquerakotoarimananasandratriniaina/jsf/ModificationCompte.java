/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerakotoarimananasandratriniaina.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanquerakotoarimananasandratriniaina.entity.CompteBancaire;
import mg.itu.tpbanquerakotoarimananasandratriniaina.jsf.util.Util;
import mg.itu.tpbanquerakotoarimananasandratriniaina.service.GestionnaireCompte;

/**
 *
 * @author Sandratriniaina
 */
@Named(value = "modificationCompte")
@ViewScoped
public class ModificationCompte implements Serializable{

    private Long id;
    private CompteBancaire compte;
    private String nomInitial;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public String getNomInitial() {
        return nomInitial;
    }

    public void setNomInitial(String nomInitial) {
        this.nomInitial = nomInitial;
    }

    
    public void loadCompte() {
        this.compte = gestionnaireCompte.findCompteById(id);
        this.nomInitial = compte.getNom();
    }

    public String enregistrerModification(){
        CompteBancaire compteModifie = gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage("Compte, dont le nom était " + nomInitial + " a été modifié en " + compteModifie.getNom());
        return "listeComptes?faces-redirect=true";
    }
    
}

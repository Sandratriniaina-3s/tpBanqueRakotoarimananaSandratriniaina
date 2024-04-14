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
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {

    private Long idSource;
    private Long idDestination;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public TransfertArgent() {
    }
    
    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String effectuerTransfert() {
        boolean erreur = false;
        CompteBancaire cSource, cDestination;
        cSource = gestionnaireCompte.findCompteById(idSource);

        if (cSource == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (cSource.getSolde() < montant) {
                Util.messageErreur("Solde insuffisant !", "Solde insuffisant !", "form:montant");
                erreur = true;
            }
        }

        cDestination = gestionnaireCompte.findCompteById(idDestination);
        if (cDestination == null) {
            // Message d'erreur associé au composant destination ; form:destination est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la destination est "destination"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        }

        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }

        gestionnaireCompte.transferer(cSource, cDestination, montant);
        // Message de succès ; addFlash à cause de la redirection.
        Util.addFlashInfoMessage("Transfert du montant " + montant + " de " + cSource.getNom() + " vers " + cDestination.getNom() + " effectué avec succès");
        return "listeComptes?faces-redirect=true";
    }
    
}

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
        CompteBancaire cSource, cDestination;
        cSource = gestionnaireCompte.findCompteById(idSource);
        cDestination = gestionnaireCompte.findCompteById(idDestination);
        gestionnaireCompte.transferer(cSource, cDestination, montant);
        return "listeComptes?faces-redirect=true";
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerakotoarimananasandratriniaina.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import mg.itu.tpbanquerakotoarimananasandratriniaina.entity.CompteBancaire;
import mg.itu.tpbanquerakotoarimananasandratriniaina.service.GestionnaireCompte;

/**
 *
 * @author Sandratriniaina
 */
@ApplicationScoped
public class Init {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    @Transactional
    public void Init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        long nbComptes = gestionnaireCompte.nbComptes();
        if (nbComptes == 0) {
            gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }
    }

}

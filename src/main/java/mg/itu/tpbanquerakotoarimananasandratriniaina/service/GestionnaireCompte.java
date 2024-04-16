/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerakotoarimananasandratriniaina.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.tpbanquerakotoarimananasandratriniaina.entity.CompteBancaire;

/**
 *
 * @author Sandratriniaina
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "user1",
        password = "User1-Password",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> typedQuery = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return typedQuery.getResultList();
    }

    public long nbComptes() {
        TypedQuery<Long> typedQuery = em.createNamedQuery("CompteBancaire.count", Long.class);
        return typedQuery.getSingleResult();
    }
    
    public CompteBancaire findCompteById(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    @Transactional
    public void transferer(CompteBancaire cSource, CompteBancaire cDestination, int montant) {
        retirer(cSource, montant);
        deposer(cDestination, montant);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire c) {
        return em.merge(c);
    }

    /**
     * Dépôt d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void deposer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.deposer(montant);
        update(compteBancaire);
    }

    /**
     * Retrait d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void retirer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.retirer(montant);
        update(compteBancaire);
    }

    @Transactional
    public void supprimerCompte(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }

}

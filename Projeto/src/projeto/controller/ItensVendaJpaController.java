/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import projeto.controller.exceptions.NonexistentEntityException;
import projeto.entities.ItensVenda;
import projeto.entities.Produto;
import projeto.entities.Venda;

/**
 *
 * @author b1400209
 */
public class ItensVendaJpaController implements Serializable {

    public ItensVendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItensVenda itensVenda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto IDProduto = itensVenda.getIDProduto();
            if (IDProduto != null) {
                IDProduto = em.getReference(IDProduto.getClass(), IDProduto.getIDproduto());
                itensVenda.setIDProduto(IDProduto);
            }
            Venda IDVenda = itensVenda.getIDVenda();
            if (IDVenda != null) {
                IDVenda = em.getReference(IDVenda.getClass(), IDVenda.getIDvenda());
                itensVenda.setIDVenda(IDVenda);
            }
            em.persist(itensVenda);
            if (IDProduto != null) {
                IDProduto.getItensVendaList().add(itensVenda);
                IDProduto = em.merge(IDProduto);
            }
            if (IDVenda != null) {
                IDVenda.getItensVendaList().add(itensVenda);
                IDVenda = em.merge(IDVenda);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItensVenda itensVenda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensVenda persistentItensVenda = em.find(ItensVenda.class, itensVenda.getIDprodutosVenda());
            Produto IDProdutoOld = persistentItensVenda.getIDProduto();
            Produto IDProdutoNew = itensVenda.getIDProduto();
            Venda IDVendaOld = persistentItensVenda.getIDVenda();
            Venda IDVendaNew = itensVenda.getIDVenda();
            if (IDProdutoNew != null) {
                IDProdutoNew = em.getReference(IDProdutoNew.getClass(), IDProdutoNew.getIDproduto());
                itensVenda.setIDProduto(IDProdutoNew);
            }
            if (IDVendaNew != null) {
                IDVendaNew = em.getReference(IDVendaNew.getClass(), IDVendaNew.getIDvenda());
                itensVenda.setIDVenda(IDVendaNew);
            }
            itensVenda = em.merge(itensVenda);
            if (IDProdutoOld != null && !IDProdutoOld.equals(IDProdutoNew)) {
                IDProdutoOld.getItensVendaList().remove(itensVenda);
                IDProdutoOld = em.merge(IDProdutoOld);
            }
            if (IDProdutoNew != null && !IDProdutoNew.equals(IDProdutoOld)) {
                IDProdutoNew.getItensVendaList().add(itensVenda);
                IDProdutoNew = em.merge(IDProdutoNew);
            }
            if (IDVendaOld != null && !IDVendaOld.equals(IDVendaNew)) {
                IDVendaOld.getItensVendaList().remove(itensVenda);
                IDVendaOld = em.merge(IDVendaOld);
            }
            if (IDVendaNew != null && !IDVendaNew.equals(IDVendaOld)) {
                IDVendaNew.getItensVendaList().add(itensVenda);
                IDVendaNew = em.merge(IDVendaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itensVenda.getIDprodutosVenda();
                if (findItensVenda(id) == null) {
                    throw new NonexistentEntityException("The itensVenda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensVenda itensVenda;
            try {
                itensVenda = em.getReference(ItensVenda.class, id);
                itensVenda.getIDprodutosVenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itensVenda with id " + id + " no longer exists.", enfe);
            }
            Produto IDProduto = itensVenda.getIDProduto();
            if (IDProduto != null) {
                IDProduto.getItensVendaList().remove(itensVenda);
                IDProduto = em.merge(IDProduto);
            }
            Venda IDVenda = itensVenda.getIDVenda();
            if (IDVenda != null) {
                IDVenda.getItensVendaList().remove(itensVenda);
                IDVenda = em.merge(IDVenda);
            }
            em.remove(itensVenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItensVenda> findItensVendaEntities() {
        return findItensVendaEntities(true, -1, -1);
    }

    public List<ItensVenda> findItensVendaEntities(int maxResults, int firstResult) {
        return findItensVendaEntities(false, maxResults, firstResult);
    }

    private List<ItensVenda> findItensVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItensVenda.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ItensVenda findItensVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItensVenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getItensVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItensVenda> rt = cq.from(ItensVenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

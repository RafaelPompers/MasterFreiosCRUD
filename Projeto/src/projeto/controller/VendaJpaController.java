/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import projeto.entities.Cliente;
import projeto.entities.ItensVenda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import projeto.controller.exceptions.NonexistentEntityException;
import projeto.entities.Venda;

/**
 *
 * @author b1400209
 */
public class VendaJpaController implements Serializable {

    public VendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venda venda) {
        if (venda.getItensVendaList() == null) {
            venda.setItensVendaList(new ArrayList<ItensVenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente IDcliente = venda.getIDcliente();
            if (IDcliente != null) {
                IDcliente = em.getReference(IDcliente.getClass(), IDcliente.getIDcliente());
                venda.setIDcliente(IDcliente);
            }
            List<ItensVenda> attachedItensVendaList = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListItensVendaToAttach : venda.getItensVendaList()) {
                itensVendaListItensVendaToAttach = em.getReference(itensVendaListItensVendaToAttach.getClass(), itensVendaListItensVendaToAttach.getIDprodutosVenda());
                attachedItensVendaList.add(itensVendaListItensVendaToAttach);
            }
            venda.setItensVendaList(attachedItensVendaList);
            em.persist(venda);
            if (IDcliente != null) {
                IDcliente.getVendaList().add(venda);
                IDcliente = em.merge(IDcliente);
            }
            for (ItensVenda itensVendaListItensVenda : venda.getItensVendaList()) {
                Venda oldIDVendaOfItensVendaListItensVenda = itensVendaListItensVenda.getIDVenda();
                itensVendaListItensVenda.setIDVenda(venda);
                itensVendaListItensVenda = em.merge(itensVendaListItensVenda);
                if (oldIDVendaOfItensVendaListItensVenda != null) {
                    oldIDVendaOfItensVendaListItensVenda.getItensVendaList().remove(itensVendaListItensVenda);
                    oldIDVendaOfItensVendaListItensVenda = em.merge(oldIDVendaOfItensVendaListItensVenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venda venda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda persistentVenda = em.find(Venda.class, venda.getIDvenda());
            Cliente IDclienteOld = persistentVenda.getIDcliente();
            Cliente IDclienteNew = venda.getIDcliente();
            List<ItensVenda> itensVendaListOld = persistentVenda.getItensVendaList();
            List<ItensVenda> itensVendaListNew = venda.getItensVendaList();
            if (IDclienteNew != null) {
                IDclienteNew = em.getReference(IDclienteNew.getClass(), IDclienteNew.getIDcliente());
                venda.setIDcliente(IDclienteNew);
            }
            List<ItensVenda> attachedItensVendaListNew = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListNewItensVendaToAttach : itensVendaListNew) {
                itensVendaListNewItensVendaToAttach = em.getReference(itensVendaListNewItensVendaToAttach.getClass(), itensVendaListNewItensVendaToAttach.getIDprodutosVenda());
                attachedItensVendaListNew.add(itensVendaListNewItensVendaToAttach);
            }
            itensVendaListNew = attachedItensVendaListNew;
            venda.setItensVendaList(itensVendaListNew);
            venda = em.merge(venda);
            if (IDclienteOld != null && !IDclienteOld.equals(IDclienteNew)) {
                IDclienteOld.getVendaList().remove(venda);
                IDclienteOld = em.merge(IDclienteOld);
            }
            if (IDclienteNew != null && !IDclienteNew.equals(IDclienteOld)) {
                IDclienteNew.getVendaList().add(venda);
                IDclienteNew = em.merge(IDclienteNew);
            }
            for (ItensVenda itensVendaListOldItensVenda : itensVendaListOld) {
                if (!itensVendaListNew.contains(itensVendaListOldItensVenda)) {
                    itensVendaListOldItensVenda.setIDVenda(null);
                    itensVendaListOldItensVenda = em.merge(itensVendaListOldItensVenda);
                }
            }
            for (ItensVenda itensVendaListNewItensVenda : itensVendaListNew) {
                if (!itensVendaListOld.contains(itensVendaListNewItensVenda)) {
                    Venda oldIDVendaOfItensVendaListNewItensVenda = itensVendaListNewItensVenda.getIDVenda();
                    itensVendaListNewItensVenda.setIDVenda(venda);
                    itensVendaListNewItensVenda = em.merge(itensVendaListNewItensVenda);
                    if (oldIDVendaOfItensVendaListNewItensVenda != null && !oldIDVendaOfItensVendaListNewItensVenda.equals(venda)) {
                        oldIDVendaOfItensVendaListNewItensVenda.getItensVendaList().remove(itensVendaListNewItensVenda);
                        oldIDVendaOfItensVendaListNewItensVenda = em.merge(oldIDVendaOfItensVendaListNewItensVenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venda.getIDvenda();
                if (findVenda(id) == null) {
                    throw new NonexistentEntityException("The venda with id " + id + " no longer exists.");
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
            Venda venda;
            try {
                venda = em.getReference(Venda.class, id);
                venda.getIDvenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venda with id " + id + " no longer exists.", enfe);
            }
            Cliente IDcliente = venda.getIDcliente();
            if (IDcliente != null) {
                IDcliente.getVendaList().remove(venda);
                IDcliente = em.merge(IDcliente);
            }
            List<ItensVenda> itensVendaList = venda.getItensVendaList();
            for (ItensVenda itensVendaListItensVenda : itensVendaList) {
                itensVendaListItensVenda.setIDVenda(null);
                itensVendaListItensVenda = em.merge(itensVendaListItensVenda);
            }
            em.remove(venda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venda> findVendaEntities() {
        return findVendaEntities(true, -1, -1);
    }

    public List<Venda> findVendaEntities(int maxResults, int firstResult) {
        return findVendaEntities(false, maxResults, firstResult);
    }

    private List<Venda> findVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venda.class));
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

    public Venda findVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venda> rt = cq.from(Venda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

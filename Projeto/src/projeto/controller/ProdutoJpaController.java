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
import projeto.entities.Compra;
import projeto.entities.ItensVenda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import projeto.controller.exceptions.NonexistentEntityException;
import projeto.entities.Produto;

/**
 *
 * @author b1400209
 */
public class ProdutoJpaController implements Serializable {

    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produto produto) {
        if (produto.getItensVendaList() == null) {
            produto.setItensVendaList(new ArrayList<ItensVenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra IDcompra = produto.getIDcompra();
            if (IDcompra != null) {
                IDcompra = em.getReference(IDcompra.getClass(), IDcompra.getIDcompra());
                produto.setIDcompra(IDcompra);
            }
            List<ItensVenda> attachedItensVendaList = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListItensVendaToAttach : produto.getItensVendaList()) {
                itensVendaListItensVendaToAttach = em.getReference(itensVendaListItensVendaToAttach.getClass(), itensVendaListItensVendaToAttach.getIDprodutosVenda());
                attachedItensVendaList.add(itensVendaListItensVendaToAttach);
            }
            produto.setItensVendaList(attachedItensVendaList);
            em.persist(produto);
            if (IDcompra != null) {
                IDcompra.getProdutoList().add(produto);
                IDcompra = em.merge(IDcompra);
            }
            for (ItensVenda itensVendaListItensVenda : produto.getItensVendaList()) {
                Produto oldIDProdutoOfItensVendaListItensVenda = itensVendaListItensVenda.getIDProduto();
                itensVendaListItensVenda.setIDProduto(produto);
                itensVendaListItensVenda = em.merge(itensVendaListItensVenda);
                if (oldIDProdutoOfItensVendaListItensVenda != null) {
                    oldIDProdutoOfItensVendaListItensVenda.getItensVendaList().remove(itensVendaListItensVenda);
                    oldIDProdutoOfItensVendaListItensVenda = em.merge(oldIDProdutoOfItensVendaListItensVenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produto produto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto persistentProduto = em.find(Produto.class, produto.getIDproduto());
            Compra IDcompraOld = persistentProduto.getIDcompra();
            Compra IDcompraNew = produto.getIDcompra();
            List<ItensVenda> itensVendaListOld = persistentProduto.getItensVendaList();
            List<ItensVenda> itensVendaListNew = produto.getItensVendaList();
            if (IDcompraNew != null) {
                IDcompraNew = em.getReference(IDcompraNew.getClass(), IDcompraNew.getIDcompra());
                produto.setIDcompra(IDcompraNew);
            }
            List<ItensVenda> attachedItensVendaListNew = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListNewItensVendaToAttach : itensVendaListNew) {
                itensVendaListNewItensVendaToAttach = em.getReference(itensVendaListNewItensVendaToAttach.getClass(), itensVendaListNewItensVendaToAttach.getIDprodutosVenda());
                attachedItensVendaListNew.add(itensVendaListNewItensVendaToAttach);
            }
            itensVendaListNew = attachedItensVendaListNew;
            produto.setItensVendaList(itensVendaListNew);
            produto = em.merge(produto);
            if (IDcompraOld != null && !IDcompraOld.equals(IDcompraNew)) {
                IDcompraOld.getProdutoList().remove(produto);
                IDcompraOld = em.merge(IDcompraOld);
            }
            if (IDcompraNew != null && !IDcompraNew.equals(IDcompraOld)) {
                IDcompraNew.getProdutoList().add(produto);
                IDcompraNew = em.merge(IDcompraNew);
            }
            for (ItensVenda itensVendaListOldItensVenda : itensVendaListOld) {
                if (!itensVendaListNew.contains(itensVendaListOldItensVenda)) {
                    itensVendaListOldItensVenda.setIDProduto(null);
                    itensVendaListOldItensVenda = em.merge(itensVendaListOldItensVenda);
                }
            }
            for (ItensVenda itensVendaListNewItensVenda : itensVendaListNew) {
                if (!itensVendaListOld.contains(itensVendaListNewItensVenda)) {
                    Produto oldIDProdutoOfItensVendaListNewItensVenda = itensVendaListNewItensVenda.getIDProduto();
                    itensVendaListNewItensVenda.setIDProduto(produto);
                    itensVendaListNewItensVenda = em.merge(itensVendaListNewItensVenda);
                    if (oldIDProdutoOfItensVendaListNewItensVenda != null && !oldIDProdutoOfItensVendaListNewItensVenda.equals(produto)) {
                        oldIDProdutoOfItensVendaListNewItensVenda.getItensVendaList().remove(itensVendaListNewItensVenda);
                        oldIDProdutoOfItensVendaListNewItensVenda = em.merge(oldIDProdutoOfItensVendaListNewItensVenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produto.getIDproduto();
                if (findProduto(id) == null) {
                    throw new NonexistentEntityException("The produto with id " + id + " no longer exists.");
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
            Produto produto;
            try {
                produto = em.getReference(Produto.class, id);
                produto.getIDproduto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produto with id " + id + " no longer exists.", enfe);
            }
            Compra IDcompra = produto.getIDcompra();
            if (IDcompra != null) {
                IDcompra.getProdutoList().remove(produto);
                IDcompra = em.merge(IDcompra);
            }
            List<ItensVenda> itensVendaList = produto.getItensVendaList();
            for (ItensVenda itensVendaListItensVenda : itensVendaList) {
                itensVendaListItensVenda.setIDProduto(null);
                itensVendaListItensVenda = em.merge(itensVendaListItensVenda);
            }
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produto.class));
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

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produto> rt = cq.from(Produto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

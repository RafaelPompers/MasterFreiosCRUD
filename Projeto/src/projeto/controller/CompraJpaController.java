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
import projeto.entities.Fornecedor;
import projeto.entities.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import projeto.controller.exceptions.NonexistentEntityException;
import projeto.entities.Compra;

/**
 *
 * @author b1400209
 */
public class CompraJpaController implements Serializable {

    public CompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) {
        if (compra.getProdutoList() == null) {
            compra.setProdutoList(new ArrayList<Produto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor IDfornecedor = compra.getIDfornecedor();
            if (IDfornecedor != null) {
                IDfornecedor = em.getReference(IDfornecedor.getClass(), IDfornecedor.getIDfornecedor());
                compra.setIDfornecedor(IDfornecedor);
            }
            List<Produto> attachedProdutoList = new ArrayList<Produto>();
            for (Produto produtoListProdutoToAttach : compra.getProdutoList()) {
                produtoListProdutoToAttach = em.getReference(produtoListProdutoToAttach.getClass(), produtoListProdutoToAttach.getIDproduto());
                attachedProdutoList.add(produtoListProdutoToAttach);
            }
            compra.setProdutoList(attachedProdutoList);
            em.persist(compra);
            if (IDfornecedor != null) {
                IDfornecedor.getCompraList().add(compra);
                IDfornecedor = em.merge(IDfornecedor);
            }
            for (Produto produtoListProduto : compra.getProdutoList()) {
                Compra oldIDcompraOfProdutoListProduto = produtoListProduto.getIDcompra();
                produtoListProduto.setIDcompra(compra);
                produtoListProduto = em.merge(produtoListProduto);
                if (oldIDcompraOfProdutoListProduto != null) {
                    oldIDcompraOfProdutoListProduto.getProdutoList().remove(produtoListProduto);
                    oldIDcompraOfProdutoListProduto = em.merge(oldIDcompraOfProdutoListProduto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getIDcompra());
            Fornecedor IDfornecedorOld = persistentCompra.getIDfornecedor();
            Fornecedor IDfornecedorNew = compra.getIDfornecedor();
            List<Produto> produtoListOld = persistentCompra.getProdutoList();
            List<Produto> produtoListNew = compra.getProdutoList();
            if (IDfornecedorNew != null) {
                IDfornecedorNew = em.getReference(IDfornecedorNew.getClass(), IDfornecedorNew.getIDfornecedor());
                compra.setIDfornecedor(IDfornecedorNew);
            }
            List<Produto> attachedProdutoListNew = new ArrayList<Produto>();
            for (Produto produtoListNewProdutoToAttach : produtoListNew) {
                produtoListNewProdutoToAttach = em.getReference(produtoListNewProdutoToAttach.getClass(), produtoListNewProdutoToAttach.getIDproduto());
                attachedProdutoListNew.add(produtoListNewProdutoToAttach);
            }
            produtoListNew = attachedProdutoListNew;
            compra.setProdutoList(produtoListNew);
            compra = em.merge(compra);
            if (IDfornecedorOld != null && !IDfornecedorOld.equals(IDfornecedorNew)) {
                IDfornecedorOld.getCompraList().remove(compra);
                IDfornecedorOld = em.merge(IDfornecedorOld);
            }
            if (IDfornecedorNew != null && !IDfornecedorNew.equals(IDfornecedorOld)) {
                IDfornecedorNew.getCompraList().add(compra);
                IDfornecedorNew = em.merge(IDfornecedorNew);
            }
            for (Produto produtoListOldProduto : produtoListOld) {
                if (!produtoListNew.contains(produtoListOldProduto)) {
                    produtoListOldProduto.setIDcompra(null);
                    produtoListOldProduto = em.merge(produtoListOldProduto);
                }
            }
            for (Produto produtoListNewProduto : produtoListNew) {
                if (!produtoListOld.contains(produtoListNewProduto)) {
                    Compra oldIDcompraOfProdutoListNewProduto = produtoListNewProduto.getIDcompra();
                    produtoListNewProduto.setIDcompra(compra);
                    produtoListNewProduto = em.merge(produtoListNewProduto);
                    if (oldIDcompraOfProdutoListNewProduto != null && !oldIDcompraOfProdutoListNewProduto.equals(compra)) {
                        oldIDcompraOfProdutoListNewProduto.getProdutoList().remove(produtoListNewProduto);
                        oldIDcompraOfProdutoListNewProduto = em.merge(oldIDcompraOfProdutoListNewProduto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compra.getIDcompra();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getIDcompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            Fornecedor IDfornecedor = compra.getIDfornecedor();
            if (IDfornecedor != null) {
                IDfornecedor.getCompraList().remove(compra);
                IDfornecedor = em.merge(IDfornecedor);
            }
            List<Produto> produtoList = compra.getProdutoList();
            for (Produto produtoListProduto : produtoList) {
                produtoListProduto.setIDcompra(null);
                produtoListProduto = em.merge(produtoListProduto);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

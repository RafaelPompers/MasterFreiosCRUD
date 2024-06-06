/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author b1400209
 */
@Entity
@Table(name = "itens_venda")
@NamedQueries({
    @NamedQuery(name = "ItensVenda.findAll", query = "SELECT i FROM ItensVenda i"),
    @NamedQuery(name = "ItensVenda.findByIDprodutosVenda", query = "SELECT i FROM ItensVenda i WHERE i.iDprodutosVenda = :iDprodutosVenda"),
    @NamedQuery(name = "ItensVenda.findByQuantidade", query = "SELECT i FROM ItensVenda i WHERE i.quantidade = :quantidade")})
public class ItensVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDprodutosVenda")
    private Integer iDprodutosVenda;
    @Column(name = "Quantidade")
    private Integer quantidade;
    @JoinColumn(name = "IDProduto", referencedColumnName = "IDproduto")
    @ManyToOne
    private Produto iDProduto;
    @JoinColumn(name = "IDVenda", referencedColumnName = "IDvenda")
    @ManyToOne
    private Venda iDVenda;

    public ItensVenda() {
    }

    public ItensVenda(Integer iDprodutosVenda, Integer quantidade, Produto iDProduto, Venda iDVenda) {
        this.iDprodutosVenda = iDprodutosVenda;
        this.quantidade = quantidade;
        this.iDProduto = iDProduto;
        this.iDVenda = iDVenda;
    }

    public ItensVenda(Integer quantidade, Produto iDProduto, Venda iDVenda) {
        this.quantidade = quantidade;
        this.iDProduto = iDProduto;
        this.iDVenda = iDVenda;
    }
    
    public ItensVenda(Integer iDprodutosVenda) {
        this.iDprodutosVenda = iDprodutosVenda;
    }

    public Integer getIDprodutosVenda() {
        return iDprodutosVenda;
    }

    public void setIDprodutosVenda(Integer iDprodutosVenda) {
        this.iDprodutosVenda = iDprodutosVenda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getIDProduto() {
        return iDProduto;
    }

    public void setIDProduto(Produto iDProduto) {
        this.iDProduto = iDProduto;
    }

    public Venda getIDVenda() {
        return iDVenda;
    }

    public void setIDVenda(Venda iDVenda) {
        this.iDVenda = iDVenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDprodutosVenda != null ? iDprodutosVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensVenda)) {
            return false;
        }
        ItensVenda other = (ItensVenda) object;
        if ((this.iDprodutosVenda == null && other.iDprodutosVenda != null) || (this.iDprodutosVenda != null && !this.iDprodutosVenda.equals(other.iDprodutosVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "projeto.entities.ItensVenda[ iDprodutosVenda=" + iDprodutosVenda + " ]";
    }
    
}

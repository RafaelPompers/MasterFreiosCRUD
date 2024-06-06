/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author b1400209
 */
@Entity
@Table(name = "compra")
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIDcompra", query = "SELECT c FROM Compra c WHERE c.iDcompra = :iDcompra"),
    @NamedQuery(name = "Compra.findByValorTotal", query = "SELECT c FROM Compra c WHERE c.valorTotal = :valorTotal"),
    @NamedQuery(name = "Compra.findByDataDaCompra", query = "SELECT c FROM Compra c WHERE c.dataDaCompra = :dataDaCompra"),
    @NamedQuery(name = "Compra.findByTipoDePagamento", query = "SELECT c FROM Compra c WHERE c.tipoDePagamento = :tipoDePagamento")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDcompra")
    private Integer iDcompra;
    @Basic(optional = false)
    @Column(name = "ValorTotal")
    private float valorTotal;
    @Basic(optional = false)
    @Column(name = "DataDaCompra")
    @Temporal(TemporalType.DATE)
    private Date dataDaCompra;
    @Basic(optional = false)
    @Column(name = "TipoDePagamento")
    private String tipoDePagamento;
    @JoinColumn(name = "IDfornecedor", referencedColumnName = "IDfornecedor")
    @ManyToOne
    private Fornecedor iDfornecedor;
    @OneToMany(mappedBy = "iDcompra")
    private List<Produto> produtoList;

    public Compra() {
    }

    public Compra(Integer iDcompra) {
        this.iDcompra = iDcompra;
    }

    public Compra(Integer iDcompra, float valorTotal, Date dataDaCompra, String tipoDePagamento) {
        this.iDcompra = iDcompra;
        this.valorTotal = valorTotal;
        this.dataDaCompra = dataDaCompra;
        this.tipoDePagamento = tipoDePagamento;
    }

    public Integer getIDcompra() {
        return iDcompra;
    }

    public void setIDcompra(Integer iDcompra) {
        this.iDcompra = iDcompra;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(Date dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public String getTipoDePagamento() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(String tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }

    public Fornecedor getIDfornecedor() {
        return iDfornecedor;
    }

    public void setIDfornecedor(Fornecedor iDfornecedor) {
        this.iDfornecedor = iDfornecedor;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDcompra != null ? iDcompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.iDcompra == null && other.iDcompra != null) || (this.iDcompra != null && !this.iDcompra.equals(other.iDcompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "projeto.entities.Compra[ iDcompra=" + iDcompra + " ]";
    }
    
}

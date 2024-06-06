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
@Table(name = "venda")
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByIDvenda", query = "SELECT v FROM Venda v WHERE v.iDvenda = :iDvenda"),
    @NamedQuery(name = "Venda.findByCodVenda", query = "SELECT v FROM Venda v WHERE v.codVenda = :codVenda"),
    @NamedQuery(name = "Venda.findByValorTotal", query = "SELECT v FROM Venda v WHERE v.valorTotal = :valorTotal"),
    @NamedQuery(name = "Venda.findByDataDaVenda", query = "SELECT v FROM Venda v WHERE v.dataDaVenda = :dataDaVenda"),
    @NamedQuery(name = "Venda.findByTipoPagamento", query = "SELECT v FROM Venda v WHERE v.tipoPagamento = :tipoPagamento"),
    @NamedQuery(name = "Venda.findByEstadoVenda", query = "SELECT v FROM Venda v WHERE v.estadoVenda = :estadoVenda")})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDvenda")
    private Integer iDvenda;
    @Basic(optional = false)
    @Column(name = "CodVenda")
    private String codVenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ValorTotal")
    private Float valorTotal;
    @Column(name = "DataDaVenda")
    @Temporal(TemporalType.DATE)
    private Date dataDaVenda;
    @Column(name = "TipoPagamento")
    private String tipoPagamento;
    @Column(name = "EstadoVenda")
    private String estadoVenda;
    @OneToMany(mappedBy = "iDVenda")
    private List<ItensVenda> itensVendaList;
    @JoinColumn(name = "IDcliente", referencedColumnName = "IDcliente")
    @ManyToOne
    private Cliente iDcliente;

    public Venda() {
    }

  
    public Venda(Integer iDvenda, String codVenda, String tipoPagamento, String estadoVenda, Cliente iDcliente) {
        this.iDvenda = iDvenda;
        this.codVenda = codVenda;
        this.tipoPagamento = tipoPagamento;
        this.estadoVenda = estadoVenda;
        this.iDcliente = iDcliente;
    }
    

    public Venda(String codVenda, Float valorTotal, Date dataDaVenda, String tipoPagamento, String estadoVenda, List<ItensVenda> itensVendaList, Cliente iDcliente) {
        this.codVenda = codVenda;
        this.valorTotal = valorTotal;
        this.dataDaVenda = dataDaVenda;
        this.tipoPagamento = tipoPagamento;
        this.estadoVenda = estadoVenda;
        this.itensVendaList = itensVendaList;
        this.iDcliente = iDcliente;
    }
    
    public Venda(Integer iDvenda) {
        this.iDvenda = iDvenda;
    }

    public Venda(Integer iDvenda, String codVenda) {
        this.iDvenda = iDvenda;
        this.codVenda = codVenda;
    }

    public Integer getIDvenda() {
        return iDvenda;
    }

    public void setIDvenda(Integer iDvenda) {
        this.iDvenda = iDvenda;
    }

    public String getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(String codVenda) {
        this.codVenda = codVenda;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataDaVenda() {
        return dataDaVenda;
    }

    public void setDataDaVenda(Date dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getEstadoVenda() {
        return estadoVenda;
    }

    public void setEstadoVenda(String estadoVenda) {
        this.estadoVenda = estadoVenda;
    }

    public List<ItensVenda> getItensVendaList() {
        return itensVendaList;
    }

    public void setItensVendaList(List<ItensVenda> itensVendaList) {
        this.itensVendaList = itensVendaList;
    }

    public Cliente getIDcliente() {
        return iDcliente;
    }

    public void setIDcliente(Cliente iDcliente) {
        this.iDcliente = iDcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDvenda != null ? iDvenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.iDvenda == null && other.iDvenda != null) || (this.iDvenda != null && !this.iDvenda.equals(other.iDvenda))) {
            return false;
        }
        return true;
    }

    
    
}

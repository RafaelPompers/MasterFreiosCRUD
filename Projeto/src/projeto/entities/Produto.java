/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.entities;

import java.io.Serializable;
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

/**
 *
 * @author b1400209
 */
@Entity
@Table(name = "produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByIDproduto", query = "SELECT p FROM Produto p WHERE p.iDproduto = :iDproduto"),
    @NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p WHERE p.nome = :nome"),
    @NamedQuery(name = "Produto.findByCategoria", query = "SELECT p FROM Produto p WHERE p.categoria = :categoria"),
    @NamedQuery(name = "Produto.findByValorPago", query = "SELECT p FROM Produto p WHERE p.valorPago = :valorPago"),
    @NamedQuery(name = "Produto.findByValorVenda", query = "SELECT p FROM Produto p WHERE p.valorVenda = :valorVenda"),
    @NamedQuery(name = "Produto.findByQuantidadeComprada", query = "SELECT p FROM Produto p WHERE p.quantidadeComprada = :quantidadeComprada"),
    @NamedQuery(name = "Produto.findByQuantidadeVendida", query = "SELECT p FROM Produto p WHERE p.quantidadeVendida = :quantidadeVendida")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDproduto")
    private Integer iDproduto;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Categoria")
    private String categoria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ValorPago")
    private Float valorPago;
    @Column(name = "ValorVenda")
    private Float valorVenda;
    @Basic(optional = false)
    @Column(name = "QuantidadeComprada")
    private int quantidadeComprada;
    @Column(name = "QuantidadeVendida")
    private Integer quantidadeVendida;
    @OneToMany(mappedBy = "iDProduto")
    private List<ItensVenda> itensVendaList;
    @JoinColumn(name = "IDcompra", referencedColumnName = "IDcompra")
    @ManyToOne
    private Compra iDcompra;

    public Produto() {
    }

    public Produto(String nome, String categoria, Float valorPago, Float valorVenda, int quantidadeComprada) {
        this.nome = nome;
        this.categoria = categoria;
        this.valorPago = valorPago;
        this.valorVenda = valorVenda;
        this.quantidadeComprada = quantidadeComprada;
    }
    
    public Produto(Integer iDproduto) {
        this.iDproduto = iDproduto;
    }

    public Produto(Integer iDproduto, int quantidadeComprada) {
        this.iDproduto = iDproduto;
        this.quantidadeComprada = quantidadeComprada;
    }

    public Integer getIDproduto() {
        return iDproduto;
    }

    public void setIDproduto(Integer iDproduto) {
        this.iDproduto = iDproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getValorPago() {
        return valorPago;
    }

    public void setValorPago(Float valorPago) {
        this.valorPago = valorPago;
    }

    public Float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public Integer getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(Integer quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public List<ItensVenda> getItensVendaList() {
        return itensVendaList;
    }

    public void setItensVendaList(List<ItensVenda> itensVendaList) {
        this.itensVendaList = itensVendaList;
    }

    public Compra getIDcompra() {
        return iDcompra;
    }

    public void setIDcompra(Compra iDcompra) {
        this.iDcompra = iDcompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDproduto != null ? iDproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.iDproduto == null && other.iDproduto != null) || (this.iDproduto != null && !this.iDproduto.equals(other.iDproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "projeto.entities.Produto[ iDproduto=" + iDproduto + " ]";
    }
    
}

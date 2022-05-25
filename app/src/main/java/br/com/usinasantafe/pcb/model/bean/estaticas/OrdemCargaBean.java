package br.com.usinasantafe.pcb.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbordemcarregest")
public class OrdemCargaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idOrdemCarga;
    @DatabaseField
    private Long destExpOrdemCarga;
    @DatabaseField
    private String ticketOrdemCarga;
    @DatabaseField
    private String produtoOrdemCarga;
    @DatabaseField
    private String classifOrdemCarga;
    @DatabaseField
    private Long qtdeEmbProdOrdemCarga;
    @DatabaseField
    private Long idEmprUsuOrdemCarga;
    @DatabaseField
    private Long idPeriodProdOrdemCarga;
    @DatabaseField
    private Long idProdOrdemCarga;

    public OrdemCargaBean() {
    }

    public Long getIdOrdemCarga() {
        return idOrdemCarga;
    }

    public void setIdOrdemCarga(Long idOrdemCarga) {
        this.idOrdemCarga = idOrdemCarga;
    }

    public Long getDestExpOrdemCarga() {
        return destExpOrdemCarga;
    }

    public void setDestExpOrdemCarga(Long destExpOrdemCarga) {
        this.destExpOrdemCarga = destExpOrdemCarga;
    }

    public String getTicketOrdemCarga() {
        return ticketOrdemCarga;
    }

    public void setTicketOrdemCarga(String ticketOrdemCarga) {
        this.ticketOrdemCarga = ticketOrdemCarga;
    }

    public String getProdutoOrdemCarga() {
        return produtoOrdemCarga;
    }

    public void setProdutoOrdemCarga(String produtoOrdemCarga) {
        this.produtoOrdemCarga = produtoOrdemCarga;
    }

    public String getClassifOrdemCarga() {
        return classifOrdemCarga;
    }

    public void setClassifOrdemCarga(String classifOrdemCarga) {
        this.classifOrdemCarga = classifOrdemCarga;
    }

    public Long getQtdeEmbProdOrdemCarga() {
        return qtdeEmbProdOrdemCarga;
    }

    public void setQtdeEmbProdOrdemCarga(Long qtdeEmbProdOrdemCarga) {
        this.qtdeEmbProdOrdemCarga = qtdeEmbProdOrdemCarga;
    }

    public Long getIdEmprUsuOrdemCarga() {
        return idEmprUsuOrdemCarga;
    }

    public void setIdEmprUsuOrdemCarga(Long idEmprUsuOrdemCarga) {
        this.idEmprUsuOrdemCarga = idEmprUsuOrdemCarga;
    }

    public Long getIdPeriodProdOrdemCarga() {
        return idPeriodProdOrdemCarga;
    }

    public void setIdPeriodProdOrdemCarga(Long idPeriodProdOrdemCarga) {
        this.idPeriodProdOrdemCarga = idPeriodProdOrdemCarga;
    }

    public Long getIdProdOrdemCarga() {
        return idProdOrdemCarga;
    }

    public void setIdProdOrdemCarga(Long idProdOrdemCarga) {
        this.idProdOrdemCarga = idProdOrdemCarga;
    }
}

package br.com.usinasantafe.pcb.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbordemcarregest")
public class OrdemCarregBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idOrdemCarreg;
    @DatabaseField
    private Long destExpOrdemCarreg;
    @DatabaseField
    private String ticketOrdemCarreg;
    @DatabaseField
    private String produtoOrdemCarreg;
    @DatabaseField
    private String classifOrdemCarreg;
    @DatabaseField
    private Long qtdeEmbProdOrdemCarreg;
    @DatabaseField
    private Long idEmprUsuOrdemCarreg;
    @DatabaseField
    private Long idPeriodProdOrdemCarreg;
    @DatabaseField
    private Long idProdOrdemCarreg;

    public OrdemCarregBean() {
    }

    public Long getIdOrdemCarreg() {
        return idOrdemCarreg;
    }

    public void setIdOrdemCarreg(Long idOrdemCarreg) {
        this.idOrdemCarreg = idOrdemCarreg;
    }

    public Long getDestExpOrdemCarreg() {
        return destExpOrdemCarreg;
    }

    public void setDestExpOrdemCarreg(Long destExpOrdemCarreg) {
        this.destExpOrdemCarreg = destExpOrdemCarreg;
    }

    public String getTicketOrdemCarreg() {
        return ticketOrdemCarreg;
    }

    public void setTicketOrdemCarreg(String ticketOrdemCarreg) {
        this.ticketOrdemCarreg = ticketOrdemCarreg;
    }

    public String getProdutoOrdemCarreg() {
        return produtoOrdemCarreg;
    }

    public void setProdutoOrdemCarreg(String produtoOrdemCarreg) {
        this.produtoOrdemCarreg = produtoOrdemCarreg;
    }

    public String getClassifOrdemCarreg() {
        return classifOrdemCarreg;
    }

    public void setClassifOrdemCarreg(String classifOrdemCarreg) {
        this.classifOrdemCarreg = classifOrdemCarreg;
    }

    public Long getQtdeEmbProdOrdemCarreg() {
        return qtdeEmbProdOrdemCarreg;
    }

    public void setQtdeEmbProdOrdemCarreg(Long qtdeEmbProdOrdemCarreg) {
        this.qtdeEmbProdOrdemCarreg = qtdeEmbProdOrdemCarreg;
    }

    public Long getIdEmprUsuOrdemCarreg() {
        return idEmprUsuOrdemCarreg;
    }

    public void setIdEmprUsuOrdemCarreg(Long idEmprUsuOrdemCarreg) {
        this.idEmprUsuOrdemCarreg = idEmprUsuOrdemCarreg;
    }

    public Long getIdPeriodProdOrdemCarreg() {
        return idPeriodProdOrdemCarreg;
    }

    public void setIdPeriodProdOrdemCarreg(Long idPeriodProdOrdemCarreg) {
        this.idPeriodProdOrdemCarreg = idPeriodProdOrdemCarreg;
    }

    public Long getIdProdOrdemCarreg() {
        return idProdOrdemCarreg;
    }

    public void setIdProdOrdemCarreg(Long idProdOrdemCarreg) {
        this.idProdOrdemCarreg = idProdOrdemCarreg;
    }
}

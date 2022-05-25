package br.com.usinasantafe.pcb.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbbagcarregest")
public class BagBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idRegMedPesBag;
    @DatabaseField
    private Long nroBag;
    @DatabaseField
    private Long codBarraBag;
    @DatabaseField
    private Long idEmprUsuBag;
    @DatabaseField
    private Long idPeriodProdBag;
    @DatabaseField
    private Long idProdBag;

    public BagBean() {
    }

    public Long getIdRegMedPesBag() {
        return idRegMedPesBag;
    }

    public void setIdRegMedPesBag(Long idRegMedPesBag) {
        this.idRegMedPesBag = idRegMedPesBag;
    }

    public Long getNroBag() {
        return nroBag;
    }

    public void setNroBag(Long nroBag) {
        this.nroBag = nroBag;
    }

    public Long getCodBarraBag() {
        return codBarraBag;
    }

    public void setCodBarraBag(Long codBarraBag) {
        this.codBarraBag = codBarraBag;
    }

    public Long getIdEmprUsuBag() {
        return idEmprUsuBag;
    }

    public void setIdEmprUsuBag(Long idEmprUsuBag) {
        this.idEmprUsuBag = idEmprUsuBag;
    }

    public Long getIdPeriodProdBag() {
        return idPeriodProdBag;
    }

    public void setIdPeriodProdBag(Long idPeriodProdBag) {
        this.idPeriodProdBag = idPeriodProdBag;
    }

    public Long getIdProdBag() {
        return idProdBag;
    }

    public void setIdProdBag(Long idProdBag) {
        this.idProdBag = idProdBag;
    }
}

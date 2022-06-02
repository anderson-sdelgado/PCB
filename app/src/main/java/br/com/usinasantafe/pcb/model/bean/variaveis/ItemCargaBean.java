package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbitemcargavar")
public class ItemCargaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemCarga;
    @DatabaseField
    private Long idCabecCarga;
    @DatabaseField
    private Long idRegMedPesBagCarga;
    @DatabaseField
    private Long nroBag;
    @DatabaseField
    private Long codBarraBag;

    public ItemCargaBean() {
    }

    public Long getIdItemCarga() {
        return idItemCarga;
    }

    public void setIdItemCarga(Long idItemCarga) {
        this.idItemCarga = idItemCarga;
    }

    public Long getIdCabecCarga() {
        return idCabecCarga;
    }

    public void setIdCabecCarga(Long idCabecCarga) {
        this.idCabecCarga = idCabecCarga;
    }

    public Long getIdRegMedPesBagCarga() {
        return idRegMedPesBagCarga;
    }

    public void setIdRegMedPesBagCarga(Long idRegMedPesBagCarga) {
        this.idRegMedPesBagCarga = idRegMedPesBagCarga;
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
}

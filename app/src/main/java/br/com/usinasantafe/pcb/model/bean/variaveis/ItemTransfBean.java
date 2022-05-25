package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbitemtransfvar")
public class ItemTransfBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemTransf;
    @DatabaseField
    private Long idCabecTransf;
    @DatabaseField
    private Long idRegMedPesBagTransf;
    @DatabaseField
    private Long nroBag;
    @DatabaseField
    private Long codBarraBag;

    public ItemTransfBean() {
    }

    public Long getIdItemTransf() {
        return idItemTransf;
    }

    public void setIdItemTransf(Long idItemTransf) {
        this.idItemTransf = idItemTransf;
    }

    public Long getIdCabecTransf() {
        return idCabecTransf;
    }

    public void setIdCabecTransf(Long idCabecTransf) {
        this.idCabecTransf = idCabecTransf;
    }

    public Long getIdRegMedPesBagTransf() {
        return idRegMedPesBagTransf;
    }

    public void setIdRegMedPesBagTransf(Long idRegMedPesBagTransf) {
        this.idRegMedPesBagTransf = idRegMedPesBagTransf;
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

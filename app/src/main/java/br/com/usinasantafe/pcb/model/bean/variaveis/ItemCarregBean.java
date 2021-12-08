package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbitemvar")
public class ItemCarregBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemCarreg;
    @DatabaseField
    private Long idCabecItemCarreg;
    @DatabaseField
    private Long idRegMedPesBagCarreg;
    @DatabaseField
    private Long statusItemCarreg; // 1 - Não Enviado; 2 - Enviado

    public ItemCarregBean() {
    }

    public Long getIdItemCarreg() {
        return idItemCarreg;
    }

    public void setIdItemCarreg(Long idItemCarreg) {
        this.idItemCarreg = idItemCarreg;
    }

    public Long getIdCabecItemCarreg() {
        return idCabecItemCarreg;
    }

    public void setIdCabecItemCarreg(Long idCabecItemCarreg) {
        this.idCabecItemCarreg = idCabecItemCarreg;
    }

    public Long getIdRegMedPesBagCarreg() {
        return idRegMedPesBagCarreg;
    }

    public void setIdRegMedPesBagCarreg(Long idRegMedPesBagCarreg) {
        this.idRegMedPesBagCarreg = idRegMedPesBagCarreg;
    }

    public Long getStatusItemCarreg() {
        return statusItemCarreg;
    }

    public void setStatusItemCarreg(Long statusItemCarreg) {
        this.statusItemCarreg = statusItemCarreg;
    }
}

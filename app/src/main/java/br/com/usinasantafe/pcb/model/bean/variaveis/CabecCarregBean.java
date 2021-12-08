package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbcabeccarregvar")
public class CabecCarregBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecCarreg;
    @DatabaseField
    private Long idFuncCabecCarreg;
    @DatabaseField
    private Long idOrdemCabecCarreg;
    @DatabaseField
    private String dthrCabecCarreg;
    @DatabaseField
    private Long statusCabecCarreg; // 1 - Aberto; 2 - Fechado

    public CabecCarregBean() {
    }

    public Long getIdCabecCarreg() {
        return idCabecCarreg;
    }

    public void setIdCabecCarreg(Long idCabecCarreg) {
        this.idCabecCarreg = idCabecCarreg;
    }

    public Long getIdFuncCabecCarreg() {
        return idFuncCabecCarreg;
    }

    public void setIdFuncCabecCarreg(Long idFuncCabecCarreg) {
        this.idFuncCabecCarreg = idFuncCabecCarreg;
    }

    public Long getIdOrdemCabecCarreg() {
        return idOrdemCabecCarreg;
    }

    public void setIdOrdemCabecCarreg(Long idOrdemCabecCarreg) {
        this.idOrdemCabecCarreg = idOrdemCabecCarreg;
    }

    public String getDthrCabecCarreg() {
        return dthrCabecCarreg;
    }

    public void setDthrCabecCarreg(String dthrCabecCarreg) {
        this.dthrCabecCarreg = dthrCabecCarreg;
    }

    public Long getStatusCabecCarreg() {
        return statusCabecCarreg;
    }

    public void setStatusCabecCarreg(Long statusCabecCarreg) {
        this.statusCabecCarreg = statusCabecCarreg;
    }
}

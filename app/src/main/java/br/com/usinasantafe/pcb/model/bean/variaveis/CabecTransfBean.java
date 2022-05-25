package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbcabectransfvar")
public class CabecTransfBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecTransf;
    @DatabaseField
    private Long idFuncCabecTransf;
    @DatabaseField
    private String dthrCabecTransf;
    @DatabaseField
    private Long dthrLongCabecTransf;
    @DatabaseField
    private Long statusCabecTransf; // 1 - Aberto; 2 - Fechado

    public CabecTransfBean() {
    }

    public Long getIdCabecTransf() {
        return idCabecTransf;
    }

    public void setIdCabecTransf(Long idCabecTransf) {
        this.idCabecTransf = idCabecTransf;
    }

    public Long getIdFuncCabecTransf() {
        return idFuncCabecTransf;
    }

    public void setIdFuncCabecTransf(Long idFuncCabecTransf) {
        this.idFuncCabecTransf = idFuncCabecTransf;
    }

    public String getDthrCabecTransf() {
        return dthrCabecTransf;
    }

    public void setDthrCabecTransf(String dthrCabecTransf) {
        this.dthrCabecTransf = dthrCabecTransf;
    }

    public Long getDthrLongCabecTransf() {
        return dthrLongCabecTransf;
    }

    public void setDthrLongCabecTransf(Long dthrLongCabecTransf) {
        this.dthrLongCabecTransf = dthrLongCabecTransf;
    }

    public Long getStatusCabecTransf() {
        return statusCabecTransf;
    }

    public void setStatusCabecTransf(Long statusCabecTransf) {
        this.statusCabecTransf = statusCabecTransf;
    }
}
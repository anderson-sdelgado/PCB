package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbcabeccargavar")
public class CabecCargaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecCarga;
    @DatabaseField
    private Long idFuncCabecCarga;
    @DatabaseField
    private Long idOrdemCabecCarga;
    @DatabaseField
    private String dthrCabecCarga;
    @DatabaseField
    private Long dthrLongCabecCarga;
    @DatabaseField
    private Long statusCabecCarga; // 1 - Aberto; 2 - Fechado

    public CabecCargaBean() {
    }

    public Long getIdCabecCarga() {
        return idCabecCarga;
    }

    public void setIdCabecCarga(Long idCabecCarga) {
        this.idCabecCarga = idCabecCarga;
    }

    public Long getIdFuncCabecCarga() {
        return idFuncCabecCarga;
    }

    public void setIdFuncCabecCarga(Long idFuncCabecCarga) {
        this.idFuncCabecCarga = idFuncCabecCarga;
    }

    public Long getIdOrdemCabecCarga() {
        return idOrdemCabecCarga;
    }

    public void setIdOrdemCabecCarga(Long idOrdemCabecCarga) {
        this.idOrdemCabecCarga = idOrdemCabecCarga;
    }

    public String getDthrCabecCarga() {
        return dthrCabecCarga;
    }

    public void setDthrCabecCarga(String dthrCabecCarga) {
        this.dthrCabecCarga = dthrCabecCarga;
    }

    public Long getStatusCabecCarga() {
        return statusCabecCarga;
    }

    public void setStatusCabecCarga(Long statusCabecCarga) {
        this.statusCabecCarga = statusCabecCarga;
    }

    public Long getDthrLongCabecCarga() {
        return dthrLongCabecCarga;
    }

    public void setDthrLongCabecCarga(Long dthrLongCabecCarga) {
        this.dthrLongCabecCarga = dthrLongCabecCarga;
    }
}

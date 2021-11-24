package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;

import br.com.usinasantafe.pcb.model.pst.Entidade;

public class CabecCargaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    public Long idCabecCarga;
    @DatabaseField
    public Long matricFuncCabecCarga;
    @DatabaseField
    public Long idOrdemCargaCabecCarga;
    @DatabaseField
    public String dthrInicialCabecCarga;
    @DatabaseField
    public String dthrFinalCabecCarga;
    @DatabaseField
    public Long statusCargaCabecCarga;

    public CabecCargaBean() {
    }

    public Long getIdCabecCarga() {
        return idCabecCarga;
    }

    public void setIdCabecCarga(Long idCabecCarga) {
        this.idCabecCarga = idCabecCarga;
    }

    public Long getMatricFuncCabecCarga() {
        return matricFuncCabecCarga;
    }

    public void setMatricFuncCabecCarga(Long matricFuncCabecCarga) {
        this.matricFuncCabecCarga = matricFuncCabecCarga;
    }

    public Long getIdOrdemCargaCabecCarga() {
        return idOrdemCargaCabecCarga;
    }

    public void setIdOrdemCargaCabecCarga(Long idOrdemCargaCabecCarga) {
        this.idOrdemCargaCabecCarga = idOrdemCargaCabecCarga;
    }

    public String getDthrInicialCabecCarga() {
        return dthrInicialCabecCarga;
    }

    public void setDthrInicialCabecCarga(String dthrInicialCabecCarga) {
        this.dthrInicialCabecCarga = dthrInicialCabecCarga;
    }

    public String getDthrFinalCabecCarga() {
        return dthrFinalCabecCarga;
    }

    public void setDthrFinalCabecCarga(String dthrFinalCabecCarga) {
        this.dthrFinalCabecCarga = dthrFinalCabecCarga;
    }

    public Long getStatusCargaCabecCarga() {
        return statusCargaCabecCarga;
    }

    public void setStatusCargaCabecCarga(Long statusCargaCabecCarga) {
        this.statusCargaCabecCarga = statusCargaCabecCarga;
    }
}

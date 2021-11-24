package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;

import br.com.usinasantafe.pcb.model.pst.Entidade;

public class ItemCargaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    public Long idItemCarga;
    @DatabaseField
    public Long idCabecItemCarga;
    @DatabaseField
    public Long nroBagItemCarga;
    @DatabaseField
    public String dthrItemCarga;

    public ItemCargaBean() {
    }

    public Long getIdItemCarga() {
        return idItemCarga;
    }

    public void setIdItemCarga(Long idItemCarga) {
        this.idItemCarga = idItemCarga;
    }

    public Long getIdCabecItemCarga() {
        return idCabecItemCarga;
    }

    public void setIdCabecItemCarga(Long idCabecItemCarga) {
        this.idCabecItemCarga = idCabecItemCarga;
    }

    public Long getNroBagItemCarga() {
        return nroBagItemCarga;
    }

    public void setNroBagItemCarga(Long nroBagItemCarga) {
        this.nroBagItemCarga = nroBagItemCarga;
    }

    public String getDthrItemCarga() {
        return dthrItemCarga;
    }

    public void setDthrItemCarga(String dthrItemCarga) {
        this.dthrItemCarga = dthrItemCarga;
    }
}

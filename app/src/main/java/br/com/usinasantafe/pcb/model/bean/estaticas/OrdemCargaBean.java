package br.com.usinasantafe.pcb.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbordemcargaest")
public class OrdemCargaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idOrdemCarga;
    @DatabaseField
    private String nroOrdemCarga;

    public OrdemCargaBean() {
    }

    public Long getIdOrdemCarga() {
        return idOrdemCarga;
    }

    public void setIdOrdemCarga(Long idOrdemCarga) {
        this.idOrdemCarga = idOrdemCarga;
    }

    public String getNroOrdemCarga() {
        return nroOrdemCarga;
    }

    public void setNroOrdemCarga(String nroOrdemCarga) {
        this.nroOrdemCarga = nroOrdemCarga;
    }
}

package br.com.usinasantafe.pcb.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbsafraest")
public class SafraBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idSafra;
    @DatabaseField
    private String descrSafra;
    @DatabaseField
    private Long atualSafra;

    public SafraBean() {
    }

    public Long getIdSafra() {
        return idSafra;
    }

    public void setIdSafra(Long idSafra) {
        this.idSafra = idSafra;
    }

    public String getDescrSafra() {
        return descrSafra;
    }

    public void setDescrSafra(String descrSafra) {
        this.descrSafra = descrSafra;
    }

    public Long getAtualSafra() {
        return atualSafra;
    }

    public void setAtualSafra(Long atualSafra) {
        this.atualSafra = atualSafra;
    }
}

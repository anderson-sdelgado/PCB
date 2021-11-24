package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tblogerrovar")
public class LogErroBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idLogErro;
    @DatabaseField
    private Long nroAparelho;
    @DatabaseField
    private String exception;
    @DatabaseField
    private String dthr;
    @DatabaseField
    private Long dthrLong;
    @DatabaseField
    private Long status;

    public LogErroBean() {
    }

    public Long getIdLogErro() {
        return idLogErro;
    }

    public void setIdLogErro(Long idLogErro) {
        this.idLogErro = idLogErro;
    }

    public Long getNroAparelho() {
        return nroAparelho;
    }

    public void setNroAparelho(Long nroAparelho) {
        this.nroAparelho = nroAparelho;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDthr() {
        return dthr;
    }

    public void setDthr(String dthr) {
        this.dthr = dthr;
    }

    public Long getDthrLong() {
        return dthrLong;
    }

    public void setDthrLong(Long dthrLong) {
        this.dthrLong = dthrLong;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}

package br.com.usinasantafe.pcb.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pcb.model.pst.Entidade;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idConfig;
    @DatabaseField
    private Long nroAparelhoConfig;
    @DatabaseField
    private String senhaConfig;
    @DatabaseField
    private String dthrServConfig;
    @DatabaseField
    private Long idSafra;
    @DatabaseField
    private Long statusRetVerif; // 0 - Não Verificando; 1 - Verificando
    @DatabaseField
    private Long tipoApont; // 1 - Ordem Carga; 2 - Transferencia
    @DatabaseField
    private Long posicaoTela;
    // 1 - Inicio do Boletim;
    // 2 - Configuracao;
    // 3 - Log Menu Inicial;
    // 4 - Log Lista de Bag;
    // 5 - Log Lista de Transf;

    public ConfigBean() {
    }

    public Long getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Long idConfig) {
        this.idConfig = idConfig;
    }

    public Long getNroAparelhoConfig() {
        return nroAparelhoConfig;
    }

    public void setNroAparelhoConfig(Long nroAparelhoConfig) {
        this.nroAparelhoConfig = nroAparelhoConfig;
    }

    public String getSenhaConfig() {
        return senhaConfig;
    }

    public void setSenhaConfig(String senhaConfig) {
        this.senhaConfig = senhaConfig;
    }

    public String getDthrServConfig() {
        return dthrServConfig;
    }

    public void setDthrServConfig(String dthrServConfig) {
        this.dthrServConfig = dthrServConfig;
    }

    public Long getStatusRetVerif() {
        return statusRetVerif;
    }

    public void setStatusRetVerif(Long statusRetVerif) {
        this.statusRetVerif = statusRetVerif;
    }

    public Long getTipoApont() {
        return tipoApont;
    }

    public void setTipoApont(Long tipoApont) {
        this.tipoApont = tipoApont;
    }

    public Long getPosicaoTela() {
        return posicaoTela;
    }

    public void setPosicaoTela(Long posicaoTela) {
        this.posicaoTela = posicaoTela;
    }

    public Long getIdSafra() {
        return idSafra;
    }

    public void setIdSafra(Long idSafra) {
        this.idSafra = idSafra;
    }
}

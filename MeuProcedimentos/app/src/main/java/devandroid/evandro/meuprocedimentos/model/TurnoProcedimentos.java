package devandroid.evandro.meuprocedimentos.model;

public class TurnoProcedimentos {

    private String data;
    private String turno;
    private String cpf;
    private String data_nascimento;
    private String local;
    private String procedimentos;

    public TurnoProcedimentos() {
    }

    public TurnoProcedimentos(String data, String turno, String cpf, String local, String procedimentos) {
        this.data = data;
        this.turno = turno;
        this.cpf = cpf;
        this.local = local;
        this.procedimentos = procedimentos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(String procedimentos) {
        this.procedimentos = procedimentos;
    }
}

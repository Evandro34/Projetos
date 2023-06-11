package devandroid.evandro.esusprocedimentosesf.model;

public class Consulta extends Pessoa {


    private int fkidPessoaConsulta;
    private String data;
    private String turno;
    private String local;
    private String procedimentos;

    public int getFkidPessoaConsulta() {
        return fkidPessoaConsulta;
    }

    public void setFkidPessoaConsulta(int fkidPessoaConsulta) {
        this.fkidPessoaConsulta = fkidPessoaConsulta;
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

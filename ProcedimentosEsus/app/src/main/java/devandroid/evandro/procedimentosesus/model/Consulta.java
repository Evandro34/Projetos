package devandroid.evandro.procedimentosesus.model;

public class Consulta  extends Paciente{
    private int IdConsulta;
    private String cnsPaciente;
    private String data;

    private String data_nascimento;

    private String sexo;

    @Override
    public String getData_nascimento() {
        return data_nascimento;
    }

    @Override
    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    @Override
    public String getSexo() {
        return sexo;
    }

    @Override
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    private String turno;
    private String local;
    private String procedimentos;


    public int getIdConsulta() {
        return IdConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        IdConsulta = idConsulta;
    }

    public String getCnsPaciente() {
        return cnsPaciente;
    }

    public void setCnsPaciente(String cnsPaciente) {
        this.cnsPaciente = cnsPaciente;
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

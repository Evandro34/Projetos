package devandroid.evandro.esusprocedimentosesf.model;

public class Pessoa {

    private int idPessoa;
    private String cpf;
    private String nome;
    private String data_nascimento;
    private String sexo;
    private String cor;

    private Endereco enderecoPaciente;
    private Consulta consultaPaciente;

    public Endereco getEnderecoPaciente() {
        return enderecoPaciente;
    }

    public void setEnderecoPaciente(Endereco enderecoPaciente) {
        this.enderecoPaciente = enderecoPaciente;
    }

    public Consulta getConsultaPaciente() {
        return consultaPaciente;
    }

    public void setConsultaPaciente(Consulta consultaPaciente) {
        this.consultaPaciente = consultaPaciente;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}

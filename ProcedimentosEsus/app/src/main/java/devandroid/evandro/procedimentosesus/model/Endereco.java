package devandroid.evandro.procedimentosesus.model;

public class Endereco extends Paciente{

     private String fkCpfEndereco;
     private String logradouro;
     private String endereco;
     private String numero;
     private String bairro;
     private String cidade;
     private String estado;
     private String cep;

    public String getFkCpfEndereco() {
        return fkCpfEndereco;
    }

    public void setFkCpfEndereco(String fkCpfEndereco) {
        this.fkCpfEndereco = fkCpfEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

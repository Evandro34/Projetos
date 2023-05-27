package devandroid.evandro.esusprocedimetnos.dataModel;

import android.app.DownloadManager;

public class EnderecoDataModel {

        /*
     private String fkCpfEndereco;
     private String logradouro;
     private String endereco;
     private String numero;
     private String bairro;
     private String cidade;
     private String estado;
     private String cep;
     */

    public static  final String TABELA ="endereco";

    public static  final String FKCPFENDERECO ="fkCpfPaciente";
    public static  final String LOGRADOURO ="logradouro";
    public static  final String ENDERECO ="endereco";
    public static  final String NUMERO="numero";
    public static  final String BAIRRO ="bairro";
    public static  final String CIDADE ="cidade";
    public static  final String ESTADO ="estado";
    public static  final String CEP ="cep";


    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE IF NOT EXISTS "+TABELA+" ( ";
        query += FKCPFENDERECO+" TEXT, ";
        query += LOGRADOURO+" TEXT, ";
        query += ENDERECO+" TEXT, ";
        query += NUMERO+" TEXT, ";
        query += BAIRRO+" TEXT, ";
        query += CIDADE+" TEXT, ";
        query += ESTADO+" TEXT, ";
        query += CEP+" TEXT, ";
        query += "FOREIGN KEY("+FKCPFENDERECO+") REFERENCES cliente(id) ";

        // datetime default current_timestamp,

        query += ")";

        return query;
    }

}

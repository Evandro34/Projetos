package devandroid.evandro.esusprocedimentosesf.dataModel;

public class EnderecoDM {

    public static final String TABELA = "endereco";

    public static final String IDENDERECO = "idEndereco";
    public static final String FKIDPESSOAENDERECO = "fkidpessoaendereco";
    public static final String LOGRADOURO = "logradouro";
    public static final String ENDERECO = "endereco";
    public static final String NUMERO = "numero";
    public static final String BAIRRO = "bairro";
    public static final String CIDADE = "cidade";
    public static final String ESTADO = "estado";
    public static final String CEP = "cep";

    private static String query;


    public static String gerarTabela() {

        query = "CREATE TABLE IF NOT EXISTS " + TABELA + " ( ";
        query += IDENDERECO + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FKIDPESSOAENDERECO + " INTEGER,";
        query += LOGRADOURO + " TEXT,";
        query += ENDERECO + " TEXT, ";
        query += NUMERO + " TEXT, ";
        query += BAIRRO + " TEXT, ";
        query += CIDADE + " TEXT, ";
        query += ESTADO + " TEXT, ";
        query += CEP + " TEXT, ";
        query += "FOREIGN KEY(" + FKIDPESSOAENDERECO + ") REFERENCES " + PessoaDM.TABELA + "(" + PessoaDM.IDPESSOA + ") ";
        query += ")";

        return query;
    }
}

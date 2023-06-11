package devandroid.evandro.esusprocedimentosesf.dataModel;

public class PessoaDM {


    public static final String TABELA = "pessoa";

    public static final String IDPESSOA = "idPessoa";
    public static final String CPF = "cpf";
    public static final String NOME = "nome";
    public static final String DATA_NASCIMENTO = "datanascimento";
    public static final String SEXO = "sexo";
    public static final String COR = "cor";

    private static String query;

    public static String gerarTabela() {

        query = "CREATE TABLE IF NOT EXISTS " + TABELA + " ( ";
        query += IDPESSOA + " INTEGER PRIMARY KEY AUTOINCREMENT , ";
        query += CPF + " TEXT , ";
        query += NOME + " TEXT, ";
        query += DATA_NASCIMENTO + " TEXT, ";
        query += SEXO + " TEXT, ";
        query += COR + " TEXT ";
        query += ")";

        return query;
    }

}

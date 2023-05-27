package devandroid.evandro.procedimentosesus.dataModel;

public class PacienteDM {

    /*
    private String cpf;
    private String nome;
    private String data_nascimento;
    private String sexo;
    private String cor;

     */
    public static final String TABELA = "paciente";

    public static final String CPF = "cpf";
    public static final String NOME = "nome";
    public static final String DATA_NASCIMENTO = "datanascimento";
    public static final String SEXO = "sexo";
    public static final String COR = "cor";

    private static String query;

    public static String gerarTabela() {

        query = "CREATE TABLE IF NOT EXISTS " + TABELA + " ( ";
        query += CPF + " INTEGER PRIMARY KEY , ";
        query += NOME + " TEXT, ";
        query += DATA_NASCIMENTO + " TEXT, ";
        query += SEXO + " TEXT, ";
        query += COR + " TEXT ";

        query += ")";

        return query;
    }

}

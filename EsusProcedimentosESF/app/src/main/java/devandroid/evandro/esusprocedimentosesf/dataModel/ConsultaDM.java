package devandroid.evandro.esusprocedimentosesf.dataModel;

public class ConsultaDM {

    public static final String TABELA = "consulta";

    public static final String IDCONSULTA = "idConsulta";
    public static final String FKIDPESSOACONSULTA = "fkidpessoaconsulta";
    public static final String DATA = "data";
    public static final String TURNO = "turno";
    public static final String LOCAL = "local";
    public static final String PROCEDIMENTO = "procedimentos";

    private static String query;

    public static String gerarTabela() {

        query = "CREATE TABLE IF NOT EXISTS  " + TABELA + " ( ";
        query += IDCONSULTA + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FKIDPESSOACONSULTA + " INTEGER, ";
        query += DATA + " TEXT, ";
        query += TURNO + " TEXT, ";
        query += LOCAL + " TEXT, ";
        query += PROCEDIMENTO + " TEXT, ";
        query += "FOREIGN KEY(" + FKIDPESSOACONSULTA + ") REFERENCES " + PessoaDM.TABELA + "(" + PessoaDM.IDPESSOA + ")";
        query += ")";

        return query;
    }
}

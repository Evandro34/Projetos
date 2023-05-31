package devandroid.evandro.procedimentosesus.dataModel;

public class ConsultaDM {
/*
    private int IdConsulta;
    private String cnsPaciente;
    private String data;
    private String turno;
    private String local;
    private String procedimentos;
*/


    public static final String TABELA = "consulta";

    public static final String ID = "id";
    public static final String FKCPF = "fkcpfPaciente";
    public static final String DATA = "data";

    public static final String TURNO = "turno";
    public static final String LOCAL = "local";
    public static final String PROCEDIMENTO= "procedimentos";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE IF NOT EXISTS  "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FKCPF+" TEXT,";
        query += DATA+" TEXT, ";
        query += TURNO+" TEXT, ";
        query += LOCAL+" TEXT, ";
        query += PROCEDIMENTO+" TEXT, ";
        query += "FOREIGN KEY("+FKCPF+") REFERENCES "+ PacienteDM.TABELA+"("+PacienteDM.CPF+") ";
        query += ")";

        return query;
    }
}

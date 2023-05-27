package devandroid.evandro.esusprocedimetnos.dataModel;

public class ConsultaDataModel {

    /*
    private int IdConsulta;
    private String fkCpfPaciente;
    private String data;
    private String turno;
    private String local;
    private String procedimentos;
     */

    public static  final String TABELA ="consulta";

    public static final String ID = "id";

    public static  final String FKCPFPACIENTE ="fkCpfPaciente";
    public static  final String DATA ="data";
    public static  final String TURNO ="turno";
    public static  final String LOCAL ="local";
    public static  final String PROCEDIMENTOS ="procedimentos";


    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE IF NOT EXISTS "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FKCPFPACIENTE+" TEXT, ";
        query += DATA+" TEXT, ";
        query += TURNO+" TEXT, ";
        query += LOCAL+" TEXT, ";
        query += PROCEDIMENTOS+" INTEGER, ";
        query += "FOREIGN KEY("+FKCPFPACIENTE+") REFERENCES cliente(id) ";

        // datetime default current_timestamp,

        query += ")";

        return query;
    }
}

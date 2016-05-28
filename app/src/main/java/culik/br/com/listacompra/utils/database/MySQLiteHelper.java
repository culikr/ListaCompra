package culik.br.com.listacompra.utils.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by LUIZ on 02/05/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_PRODUTO = "produto";
    public static final String TABLE_LISTACOMPRA = "listacompra";
    public static final String TABLE_LISTA = "lista";
    //campos comuns
    public static final String COLUMN_SNOME="nome";
    public static final String COLUMN_IDPRODUTO="idproduto";
    public static final String COLUMN_IDITEM="iditem";
    public static final String COLUMN_IDLISTA="idlista";
    //tabela listacompra
    public static final String COLUMN_DDATACAD="ddatacad";
    public static final String COLUMN_TELEFONE="telefone";
    public static final String COLUMN_MENSAGEM="mensagem";
    public static final String COLUMN_EMAIL="email";
    //tabela listaproduto
    public static final String COLUMN_DQUANT="dquant";
    public static final String COLUMN_LOCAL="local";
    public static final String COLUMN_PRECO="preco";



    private static final String DATABASE_NAME = "listaprod.db";

    private static final int DATABASE_VERSION = 6;

    // Database creation sql statement
    private static final String DATABASE_CREATE_PRODUTO = "create table "
            + TABLE_PRODUTO + "(" + COLUMN_IDPRODUTO
            + " integer primary key autoincrement, " + COLUMN_LOCAL
            + " text,  " + COLUMN_PRECO
            + " double, " + COLUMN_SNOME
            + " text not null);";

    private static final String DATABASE_CREATE_LISTA_COMPRA = "create table "
            + TABLE_LISTACOMPRA + "(" + COLUMN_IDLISTA
            + " integer primary key autoincrement, " + COLUMN_SNOME
            + " text not null, "+ COLUMN_TELEFONE
            + " text , "+ COLUMN_EMAIL
            + " text , "+ COLUMN_DDATACAD
            + " datetime ," +  COLUMN_MENSAGEM
            + " text );";

    private static final String DATABASE_CREATE_LISTA = "create table "
            + TABLE_LISTA + "(" + COLUMN_DQUANT
            + " double, " +  COLUMN_IDPRODUTO
            + " integer not null, "+ COLUMN_IDLISTA
            + " integer not null, " + COLUMN_IDITEM
            + " integer primary key autoincrement, FOREIGN KEY(" + COLUMN_IDLISTA + ") REFERENCES "+ TABLE_LISTACOMPRA + "( " + COLUMN_IDLISTA +")"
            + ",FOREIGN KEY(" + COLUMN_IDPRODUTO + ") REFERENCES "+ TABLE_PRODUTO + "( " + COLUMN_IDPRODUTO +") );";




    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_PRODUTO);
        database.execSQL(DATABASE_CREATE_LISTA_COMPRA);
        database.execSQL(DATABASE_CREATE_LISTA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTACOMPRA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTA);
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_PONTOS);
        onCreate(db);

//        db.execSQL("insert into produto(nome,valor) values('Feijao',4.00)");
        //db.execSQL("insert into produto(nome,valor) values('Arroz',3.69)");
        //db.execSQL("insert into produto(nome,valor) values('Batata',3.00)");
        //db.execSQL("insert into produto(nome,valor) values('cebola',4.00)");
        //db.close();

    }
}


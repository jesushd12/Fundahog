package com.example.jesus.fundahog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jesus on 12/3/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "fundahog.sqlite"; // Nombre del archivo de la Base de datos
    private static final int DB_SCHEME_NAME = 1; // Version del esquema de la Base de datos

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.TABLA_USUARIO);
        db.execSQL(DataBaseManager.TABLA_MEDICO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.myapp.snackgame;

import android.app.Application;

public class SubApp extends Application {

    public  String DB_PATH= "/data/data/com.myapp.snackgame/databases/";
    public  String DATABASE_NAME = "db_thien_chua.db";

    public TruyenChuaGS getTruyenThienChua(){
        return TruyenChuaGS.getInstance(this,DB_PATH,DATABASE_NAME);
    }
}

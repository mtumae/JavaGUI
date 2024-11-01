package com.mutere.sufeeds.sufeedsproject;

import java.sql.Connection;

public class BackendAdmin {
    public static void main(String[] args){
        Dbfunctions db=new Dbfunctions();
        Connection conn=db.connect_to_db("db_Mtume_Mutere_188916","postgres","");
    }
}

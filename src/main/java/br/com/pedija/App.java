package br.com.pedija;


import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.view.EscolherAppview;

public class App {
    public static void main(String[] args) {
        DatabaseConnection.initDatabase();
        EscolherAppview escolherAppview = new EscolherAppview();



        escolherAppview.EscolherAppview();

    }
}
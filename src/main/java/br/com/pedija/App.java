package br.com.pedija;


import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.view.EscolherAppview;

public class App {
    public static void main(String[] args) {

        EscolherAppview escolherAppview = new EscolherAppview();

        DatabaseConnection.initDatabase();

        escolherAppview.EscolherAppview();

    }
}
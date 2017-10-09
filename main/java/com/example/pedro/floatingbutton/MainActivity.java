package com.example.pedro.floatingbutton;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener/*Interface para o controle dos menus da gaveta*/ {


    ActionBarDrawerToggle toggle;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListasMainContent listasMainContent = new ListasMainContent();

        fragmentTransaction.replace(R.id.frame_activity_main,listasMainContent);

        fragmentTransaction.commit();








        /*
        *Lógica do Drawer
        */

        //Parâmetros para inicilizar o suporte
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.description_open_drawer, R.string.description_close_drawer);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        //Seta o listener da gaveta do drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.gaveta);
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;

    }

    //Método para ações da action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Condicional p/ que o botão hamburguer abra o drawer
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        int itemSelecionado = item.getItemId();

        if (itemSelecionado == R.id.opcao_config) {
            Intent it = new Intent(this, SettingsActivity.class);
            startActivity(it);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //Método p/ fechar o drawer quando for apertado o botão voltar e este estiver ativo
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // O drawer está aberto??
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            //Fecha o drawer
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //Não está aberto, logo segue para a lógica padrão do botão voltar
            super.onBackPressed();
        }


    }

    //Método para ações da navigation view do drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Para poder fechar o drawer apos a seleção
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int itemSelecionado = item.getItemId();

        if (itemSelecionado == R.id.item_menu_drawer_lista) {

            FragmentTransaction transaction= fragmentManager.beginTransaction();
            ListasMainContent ltmc = new ListasMainContent();
            transaction.replace(R.id.frame_activity_main,ltmc);
            transaction.commit();
            drawer.closeDrawer(GravityCompat.START);

            return true;



        } else if (itemSelecionado == R.id.item_menu_drawer_produto) {
            FragmentTransaction transaction= fragmentManager.beginTransaction();
            ProdutosMainContent pdmc = new ProdutosMainContent();
            transaction.replace(R.id.frame_activity_main,pdmc);
            transaction.commit();
            drawer.closeDrawer(GravityCompat.START);

            return true;
        }
        return false;
    }
}

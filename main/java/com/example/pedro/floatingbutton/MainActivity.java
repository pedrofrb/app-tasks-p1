package com.example.pedro.floatingbutton;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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

    //Faz parte da lógica do Drawer
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Atributos
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);


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


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "\uD83D\uDE2C", Toast.LENGTH_SHORT).show();
                // TODO: 07/10/17 Ação do Click do Floating Button

            }
        });

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
            Toast.makeText(this, "Deveria ter algo aqui \uD83E\uDD14", Toast.LENGTH_SHORT).show();
            // TODO: 07/10/17 Botão da action bar para o SharedPreferences
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
        int itemSelecionado = item.getItemId();

        if (itemSelecionado == R.id.item_menu_drawer_lista) {
            Toast.makeText(this, "Deveria aparecer uma lista aqui \uD83E\uDD14", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemSelecionado == R.id.item_menu_drawer_produto) {
            Intent it = new Intent(this,ProdutosMainContent.class);
            startActivity(it);
            return true;
        }
        return false;
    }
}

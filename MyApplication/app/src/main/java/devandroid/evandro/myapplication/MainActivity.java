package devandroid.evandro.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterProduto.OnClick {

    private AdapterProduto adapterProduto;
    private List<Produto>produtoList = new ArrayList<>();
    private SwipeableRecyclerView rvProdutos;

    private ImageButton ibAdd;
    private ImageButton ibVerMais;

    private TextView text_info;

    private ProdutoDAO produtoDAO;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produtoDAO = new ProdutoDAO(this);

        produtoList = produtoDAO.getListProdutos();

        ibAdd = findViewById(R.id.ib_add);
        ibVerMais = findViewById(R.id.ib_ver_mais);

        rvProdutos = findViewById(R.id.rvProdutos);
        text_info = findViewById(R.id.text_info);



        //carregaLista();

        configRecyclerView();
        ouvinteCliques();

    }

    private void configRecyclerView(){

        produtoList.clear();
        produtoList = produtoDAO.getListProdutos();

        verificaQTDLista();

        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setHasFixedSize(true);

        adapterProduto = new AdapterProduto(produtoList,this);
        rvProdutos.setAdapter(adapterProduto);

        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                Produto produto =produtoList.get(position);
                Intent intent = new Intent(getBaseContext(),FormProdutoActivity.class);
                intent.putExtra("produto",produto);
                startActivity(intent);

            }

            @Override
            public void onSwipedRight(int position) {

                Produto produto =produtoList.get(position);
                //excluir linha do adpater
                produtoDAO.deleteProduto(produto);

                produtoList.remove(produto);


                //informar que linha foi excluida
                adapterProduto.notifyItemRemoved(position);

                verificaQTDLista();


            }
        });



    }

    private void verificaQTDLista(){
        if(produtoList.size()==0){

            text_info.setVisibility(View.VISIBLE);

        }
        else{
            text_info.setVisibility(View.INVISIBLE);
        }
    }

    private void carregaLista(){

        Produto produto1 = new Produto();
        produto1.setNome("Monitor 34 LG");
        produto1.setEstoque(45);
        produto1.setValor(1349.99);

        Produto produto2 = new Produto();
        produto2.setNome("Caixa de Som C3 Tech");
        produto2.setEstoque(15);
        produto2.setValor(149.99);

        Produto produto3 = new Produto();
        produto3.setNome("Microfone Blue yeti");
        produto3.setEstoque(38);
        produto3.setValor(1699.99);

        Produto produto4 = new Produto();
        produto4.setNome("Gabinete NZXT H440");
        produto4.setEstoque(15);
        produto4.setValor(979.99);

        Produto produto5 = new Produto();
        produto5.setNome("Placa Mãe Asus");
        produto5.setEstoque(60);
        produto5.setValor(1199.99);

        Produto produto6 = new Produto();
        produto6.setNome("Memória Corsair 16GB");
        produto6.setEstoque(44);
        produto6.setValor(599.99);

        produtoList.add(produto1);
        produtoList.add(produto2);
        produtoList.add(produto3);
        produtoList.add(produto4);
        produtoList.add(produto5);
        produtoList.add(produto6);

        produtoList.add(produto1);
        produtoList.add(produto2);
        produtoList.add(produto3);
        produtoList.add(produto4);
        produtoList.add(produto5);
        produtoList.add(produto6);

    }


    @Override
    public void onClickListener(Produto produto) {

    }

    private void ouvinteCliques(){
        ibAdd.setOnClickListener(view -> {
            //Toast.makeText(this,"Add",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,FormProdutoActivity.class);
            startActivity(intent);
        });
         ibVerMais.setOnClickListener(view -> {
             PopupMenu popupMenu = new PopupMenu(this,ibVerMais);
             popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar,popupMenu.getMenu());

             popupMenu.setOnMenuItemClickListener(menuItem -> {

                 if(menuItem.getItemId() ==R.id.menu_sobre){
                     Toast.makeText(this,"Sobre",Toast.LENGTH_LONG).show();
                 }
                 return true;
             });

             popupMenu.show();

        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        configRecyclerView();
    }
}
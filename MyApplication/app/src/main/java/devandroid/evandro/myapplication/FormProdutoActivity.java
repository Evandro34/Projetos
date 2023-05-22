package devandroid.evandro.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class FormProdutoActivity extends AppCompatActivity {

    private EditText edProduto, edEstoque, edValor;
    private ImageButton ibVoltar;

    private ProdutoDAO produtoDAO;
    private Produto produto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);

        produtoDAO = new ProdutoDAO(this);
        edProduto = findViewById(R.id.ed_produtos);
        edEstoque = findViewById(R.id.ed_estoque);
        edValor = findViewById(R.id.ed_valor);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            produto = (Produto) bundle.getSerializable("produto");
            editProduto();

        }


    }


    private void editProduto() {
        edProduto.setText(produto.getNome());
        edEstoque.setText(String.valueOf(produto.getEstoque()));
        edValor.setText(String.valueOf(produto.getValor()));
    }


    public void salvarProduto(View view) {

        String nome = edProduto.getText().toString();
        String quantidade = edEstoque.getText().toString();
        String valor = edValor.getText().toString();


        if (!nome.isEmpty()) {


            if (!quantidade.isEmpty()) {
                int qtd = Integer.parseInt(quantidade);
                if (qtd >= 1) {
                    if (!valor.isEmpty()) {

                        double valorproduto = Double.parseDouble(valor);

                        if (valorproduto > 0) {
                            // Toast.makeText(this, "Tudo Certo", Toast.LENGTH_LONG).show();
                            if (produto == null) produto = new Produto();
                            produto.setNome(nome);
                            produto.setEstoque(qtd);
                            produto.setValor(valorproduto);

                            if (produto.getId() != 0) {
                                produtoDAO.atualizaProduto(produto);
                            } else {
                                produtoDAO.salvarProduto(produto);
                            }

                            finish();

                        } else {
                            edValor.requestFocus();
                            edValor.setError("Informe um valor maior que zero");
                        }

                    } else {
                        edValor.requestFocus();
                        edValor.setError("Informe um valor do produto");
                    }

                } else {
                    edEstoque.requestFocus();
                    edEstoque.setError("Informe um valor maior que zero.");
                }

            } else {
                edEstoque.requestFocus();
                edEstoque.setError("Informe o valor valido.");
            }

        } else {
            edProduto.requestFocus();
            edProduto.setError("Informe o nome do produto.");

        }
    }
}
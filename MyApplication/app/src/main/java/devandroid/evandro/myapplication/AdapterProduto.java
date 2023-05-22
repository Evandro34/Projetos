package devandroid.evandro.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewholder>{


    private List<Produto>produtoList ;

    private OnClick onClick;


    public AdapterProduto(List<Produto> produtoList, OnClick onClick) {
        this.produtoList = produtoList;
        this.onClick = onClick;
    }


    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto,parent,false);
        return new MyViewholder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        Produto produto = produtoList.get(position);


        holder.txt_produto.setText(produto.getNome());
        holder.txt_estoque.setText("Estoque: "+ produto.getEstoque());
        holder.txt_valor.setText("R$ "+produto.getValor());

        holder.itemView.setOnClickListener(view -> {
            onClick.onClickListener(produto);
        });


    }

    @Override
    public int getItemCount() {

        return produtoList.size();
    }


public interface OnClick{
        void onClickListener(Produto produto);
}


    class MyViewholder extends RecyclerView.ViewHolder{

        TextView txt_produto,txt_estoque,txt_valor;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            txt_produto = itemView.findViewById(R.id.text_produto);
            txt_estoque = itemView.findViewById(R.id.text_estoque);
            txt_valor = itemView.findViewById(R.id.text_valor);

        }
    }
}

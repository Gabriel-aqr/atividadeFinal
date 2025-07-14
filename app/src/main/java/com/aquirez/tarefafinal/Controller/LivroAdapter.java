package com.aquirez.tarefafinal.Controller;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.aquirez.tarefafinal.R;
import com.aquirez.tarefafinal.entidade.livro;
import java.util.List;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.VH> {
    public interface OnItemClickListener { void onClick(livro l); }
    private List<livro> livros;
    private OnItemClickListener listener;

    public LivroAdapter(List<livro> livros) { this.livros = livros; }

     public VH onCreateViewHolder(ViewGroup p, int v) {
        return new VH(LayoutInflater.from(p.getContext()).inflate(R.layout.item_livro, p, false));
    }

     public void onBindViewHolder(VH h, int p) {
        livro l = livros.get(p);
        h.titulo.setText(l.getTitulo());
        h.autor.setText(l.getAutor());
        h.descricao.setText(l.getDescricao());
        if (l.getCapaUri() != null) h.capa.setImageURI(Uri.parse(l.getCapaUri()));
        h.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onClick(l);
        });
    }

    public int getItemCount() { return livros.size(); }

    public void setLivros(List<livro> newList) {
        livros = newList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener l) { listener = l; }

    static class VH extends RecyclerView.ViewHolder {
        TextView titulo, autor, descricao;
        ImageView capa;
        VH(View v) {
            super(v);
            titulo = v.findViewById(R.id.itemTitulo);
            autor = v.findViewById(R.id.itemAutor);
            descricao = v.findViewById(R.id.itemDescricao);
            capa = v.findViewById(R.id.itemCapa);
        }
    }
}
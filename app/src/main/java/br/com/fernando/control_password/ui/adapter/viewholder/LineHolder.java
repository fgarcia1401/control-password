package br.com.fernando.control_password.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.model.SiteRegister;
import br.com.fernando.control_password.ui.adapter.LineAdapter;



public class LineHolder extends RecyclerView.ViewHolder {

    public TextView site;
    public TextView email;
    public ImageView imageView;

    public LineHolder(View itemView) {
        super(itemView);
        site = itemView.findViewById(R.id.main_line_site);
        email = itemView.findViewById(R.id.main_line_email);
        imageView = itemView.findViewById(R.id.main_line_image);
    }

    public void bind(final SiteRegister item, final LineAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

}

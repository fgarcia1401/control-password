package br.com.fernando.control_password.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.fernando.control_password.BuildConfig;
import br.com.fernando.control_password.R;
import br.com.fernando.control_password.api.ControlPasswordService;
import br.com.fernando.control_password.data.DataStorage;
import br.com.fernando.control_password.model.SiteRegister;
import br.com.fernando.control_password.ui.adapter.viewholder.LineHolder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private final List<SiteRegister> mSiteRegisters;
    private final OnItemClickListener listener;
    private ControlPasswordService controlPasswordService;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(SiteRegister item);
    }

    public LineAdapter(List<SiteRegister> mSiteRegisters, ControlPasswordService controlPasswordService, Context context, OnItemClickListener listener) {
        this.mSiteRegisters = mSiteRegisters;
        this.listener = listener;
        this.controlPasswordService = controlPasswordService;
        this.context = context;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_line_view, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position) {
        SiteRegister siteRegister = mSiteRegisters.get(position);
        holder.site.setText(siteRegister.getSite());
        holder.email.setText(siteRegister.getEmail());
        holder.bind(mSiteRegisters.get(position), listener);

        String token = DataStorage.getInstance(context).getToken();

        Call<ResponseBody> call = this.controlPasswordService.getImage(token, BuildConfig.API_CONTROL_PASSWORD_URL + "logo/" + siteRegister.getSite());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                        holder.imageView.setImageBitmap(bm);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSiteRegisters != null ? mSiteRegisters.size() : 0;
    }
}

package ru.geekbrains.androidadmin.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.geekbrains.androidadmin.R;
import ru.geekbrains.androidadmin.activities.BaseActivity;
import ru.geekbrains.androidadmin.activities.EditPersonKeywordsActivity;
import ru.geekbrains.androidadmin.model.Keyword;
import ru.geekbrains.androidadmin.model.Person;
import ru.geekbrains.androidadmin.model.Site;
import ru.geekbrains.androidadmin.model.User;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    public static final String EXTRA_PERSON_ID = "extraPersonID";
    public static final String EXTRA_PERSON_NAME = "extraPersonName";
    private List<?> list;
    private Context context;

    public MyRecyclerViewAdapter(List<?> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.card, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Object o = list.get(position);
        final BaseActivity activity = (BaseActivity) context;
        String name = null;
        if (o instanceof User){
            final User user = (User) o;
            name = user.getLogin();
        }
        if (o instanceof Site){
            final Site site = (Site) o;
            name = site.getName();
        }
        if (o instanceof Person){
            final Person person = (Person) o;
            name = person.getName();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditPersonKeywordsActivity.class);
                    intent.putExtra(EXTRA_PERSON_ID, person.getId());
                    intent.putExtra(EXTRA_PERSON_NAME, person.getName());
                    context.startActivity(intent);
                }
            });
        }
        if (o instanceof Keyword){
            Keyword keyword = (Keyword) o;
            name = keyword.getName();
        }
        holder.tvName.setText(name);
        holder.tvData.setText(null);
        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.showPopupMenu(holder.ivMenu, o);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvData;
        private ImageView ivMenu;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvData = itemView.findViewById(R.id.tv_data);
            ivMenu = itemView.findViewById(R.id.iv_menu);

        }
    }
}

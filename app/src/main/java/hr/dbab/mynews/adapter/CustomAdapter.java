package hr.dbab.mynews.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hr.dbab.mynews.CustomItemClickListener;
import hr.dbab.mynews.R;
import hr.dbab.mynews.model.Article;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    //creating member variables
    private List<Article> articleList;
    private CustomItemClickListener customItemClickListener;

    //creating constructor for our adapter class
    public CustomAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //we need to return our ViewHolder
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_article, viewGroup, false);
        return new CustomAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.CustomViewHolder customViewHolder, int position) {
        //getting the current article from list of articles and storing that into a variable
        Article currentArticle = articleList.get(position);

        //getting the values from the current article
        String articleTitle = currentArticle.getTitle();
        String articleDescription = currentArticle.getDescription();

        //setting the views of the cardView to those values
        customViewHolder.tvTitle.setText(articleTitle);
        customViewHolder.tvDescription.setText(articleDescription);
        customViewHolder.cardLayout.setTag(currentArticle);
    }

    @Override
    public int getItemCount() {
        //we get as many items as there are in our articleList
        return articleList.size();
    }

    //creating a ViewHolder class
    class CustomViewHolder extends RecyclerView.ViewHolder {
        //creating variables for our views in card_article layout
        TextView tvTitle;
        TextView tvDescription;
        LinearLayout cardLayout;

        //this constructor passes the View (itemView) which we can use to get references
        // to our views on our card_article layout
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            cardLayout = itemView.findViewById(R.id.card_lin_layout);

            //calling the setOnClickListener method
            cardLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //checking if the customItemClickListener is null
                    if (customItemClickListener != null) {
                        customItemClickListener.customOnClick(view, getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setCustomItemClickListener(CustomItemClickListener customItemClickListener) {
        this.customItemClickListener = customItemClickListener;
    }
}

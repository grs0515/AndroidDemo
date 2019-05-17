package com.grs.demo.mvvm.sample.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.grs.demo.R;
import com.grs.demo.databinding.MovieItemBinding;
import com.grs.demo.mvvm.sample.model.entity.Movie;
import com.grs.demo.mvvm.sample.viewModel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaohaoChang on 2017/2/11.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BindingHolder> {
    private List<Movie> movies;

    public MovieAdapter() {
        movies = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new BindingHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        MovieViewModel movieViewModel = new MovieViewModel(movies.get(position));
        holder.itemBinding.setViewModel(movieViewModel);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addItem(Movie movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);
    }

    public void clearItems() {
        movies.clear();
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding itemBinding;

        public BindingHolder(MovieItemBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }
    }
}

package com.example.architectureexample.scences.remoteApi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.architectureexample.R;
import com.example.architectureexample.api.post.Post;

public class PostAdaptor extends ListAdapter<Post, PostAdaptor.PostHolder> {
    private PostAdaptor.OnItemClickListener listener;

    public PostAdaptor() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Post> DIFF_CALLBACK = new DiffUtil.ItemCallback<Post>() {
        @Override
        public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getText().equals(newItem.getText()) &&
                    oldItem.getUserId() == newItem.getUserId();

        }
    };

    @NonNull
    @Override
    public PostAdaptor.PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new PostAdaptor.PostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdaptor.PostHolder holder, int position) {
        Post currentPost = getItem(position);
        holder.textViewTitle.setText(currentPost.getTitle());
        holder.textViewDescription.setText(currentPost.getText());
        holder.textViewPriority.setText(String.valueOf(currentPost.getUserId()));
    }

    public Post getPostAt(int position) {
        return getItem(position);
    }

    class PostHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public void setOnItemClickListener(PostAdaptor.OnItemClickListener listener) {
        this.listener = listener;
    }
}

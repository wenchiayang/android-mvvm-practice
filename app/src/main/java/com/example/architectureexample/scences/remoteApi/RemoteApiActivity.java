package com.example.architectureexample.scences.remoteApi;

import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.architectureexample.R;
import com.example.architectureexample.api.post.PostAPIInterface;
import com.example.architectureexample.api.post.Post;
import com.example.architectureexample.scences.main.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class RemoteApiActivity extends AppCompatActivity {
    private PostViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_api);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_remote_api);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final PostAdaptor adaptor = new PostAdaptor();
        recyclerView.setAdapter(adaptor);

        // TODO: - if you want to share data at different fragments
        // try this: https://www.youtube.com/watch?v=ACK67xU1Y3s
        viewModel = new ViewModelProvider(this).get(PostViewModel.class);
//        viewModel.getAllPosts().observe(this, new Observer<List<Post>>() {
//            @Override
//            public void onChanged(List<Post> posts) {
//                adaptor.submitList(posts);
//            }
//        });

        viewModel.setPostRepositoryListener(new PostRepository.PostRepositoryListener() {
            @Override
            public void didFetchAllPosts(List<Post> posts) {
                String content = "";

                for (Post post : posts) {
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    Log.d(RemoteApiActivity.class.getCanonicalName(), content);
                }

                Toast.makeText(RemoteApiActivity.this, content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void didFetchError(String message) {
                Toast.makeText(RemoteApiActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

//        viewModel.setPostViewModelViewListener(new PostViewModel.PostViewModelViewListener() {
//            @Override
//            public void didFetchAllPosts(List<Post> posts) {
//                String content = "";
//
//                for (Post post : posts) {
//                    content += "ID: " + post.getId() + "\n";
//                    content += "User ID: " + post.getUserId() + "\n";
//                    content += "Title: " + post.getTitle() + "\n";
//                    content += "Text: " + post.getText() + "\n\n";
//                    Log.d(RemoteApiActivity.class.getCanonicalName(), content);
//                }
//
//                Toast.makeText(RemoteApiActivity.this, content, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void didFetchError(String message) {
//                Toast.makeText(RemoteApiActivity.this, message, Toast.LENGTH_SHORT).show();
//            }
//        });
        viewModel.fetchAllPosts();
    }
}

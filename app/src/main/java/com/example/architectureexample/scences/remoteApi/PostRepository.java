package com.example.architectureexample.scences.remoteApi;

import android.app.Application;
import com.example.architectureexample.api.APIClient;
import com.example.architectureexample.api.post.PostAPIInterface;
import com.example.architectureexample.api.post.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class PostRepository {
    private PostAPIInterface postApiInterface;
    private PostRepositoryListener listener;

    public PostRepository(Application application) {
        // TODO: - Instantiated JsonPlaceholderApi interface
        Retrofit retrofit = APIClient.getInstance().getRetrofit();
        postApiInterface = retrofit.create(PostAPIInterface.class);
    }

    public void fetchAllPosts() {
        Call<List<Post>> call = postApiInterface.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    listener.didFetchError("Response Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                listener.didFetchAllPosts(posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                listener.didFetchError(t.getMessage());
            }
        });
    }

    public interface PostRepositoryListener {
        void didFetchAllPosts(List<Post> posts);
        void didFetchError(String message);
    }

    public void setPostRepositoryListener(PostRepositoryListener listener) {
        this.listener = listener;
    }
}

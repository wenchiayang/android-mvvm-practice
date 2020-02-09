package com.example.architectureexample.scences.remoteApi;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.architectureexample.api.post.Post;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostRepository repository;
//    private PostViewModelViewListener listener;
    private PostRepository.PostRepositoryListener listener;

    public PostViewModel(@NonNull Application application) {
        super(application);
        repository = new PostRepository(application);
        repository.setPostRepositoryListener(listener);
//        repository.setPostRepositoryListener(new PostRepository.PostRepositoryListener() {
//            @Override
//            public void didFetchAllPosts(List<Post> posts) {
//                listener.didFetchAllPosts(posts);
//            }
//
//            @Override
//            public void didFetchError(String message) {
//                listener.didFetchError(message);
//            }
//        });
    }

    public void fetchAllPosts() {
        repository.fetchAllPosts();
    }

    public interface PostViewModelViewListener {
        void didFetchAllPosts(List<Post> posts);
        void didFetchError(String message);
    }

    public void setPostRepositoryListener(PostRepository.PostRepositoryListener listener) {
        this.listener = listener;
        repository.setPostRepositoryListener(listener);
    }
//    public void setPostViewModelViewListener(PostViewModelViewListener listener) {
//        this.listener = listener;
//    }
}

package me.code.uppgift3projekt.service;

import me.code.uppgift3projekt.data.Post;
import me.code.uppgift3projekt.data.User;
import me.code.uppgift3projekt.exception.NotOwnerException;
import me.code.uppgift3projekt.exception.PostAlreadyExistsException;
import me.code.uppgift3projekt.exception.PostDoesNotExistException;
import me.code.uppgift3projekt.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository repository;
    private final UserService userService;

    @Autowired
    public PostService(PostRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Post create(User user, String title, String content)
            throws PostAlreadyExistsException
    {
        var existing = repository.getByTitle(title);
        if (existing.isPresent())
            throw new PostAlreadyExistsException();

        var post = new Post(title, content, user);
        repository.save(post);

        return post;
    }

    public Post delete(User user, String title)
            throws PostDoesNotExistException, NotOwnerException
    {
        var post = repository
                .getByTitle(title)
                .orElseThrow(PostDoesNotExistException::new);

        if (!post.getCreator().equals(user))
            throw new NotOwnerException();

        repository.delete(post);

        return post;
    }

    public Post edit(User user, String title, String updatedContent)
            throws PostDoesNotExistException, NotOwnerException
    {
        var post = repository
                .getByTitle(title)
                .orElseThrow(PostDoesNotExistException::new);

        if (!post.getCreator().equals(user))
            throw new NotOwnerException();

        post.setContent(updatedContent);
        repository.save(post);

        return post;
    }

    public Collection<Post> getAll() {
        return repository.getAll();
    }

    public Collection<Post> getAllUserPosts(String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(repository::getAllByUser).orElse(null);
    }
}

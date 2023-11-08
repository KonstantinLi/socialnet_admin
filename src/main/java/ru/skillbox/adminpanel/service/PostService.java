package ru.skillbox.adminpanel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.skillbox.adminpanel.dto.request.FindPostRq;
import ru.skillbox.adminpanel.dto.response.PostRs;
import ru.skillbox.adminpanel.entity.Post;
import ru.skillbox.adminpanel.exception.PostNotFoundException;
import ru.skillbox.adminpanel.exception.SearchPeriodParsingException;
import ru.skillbox.adminpanel.mapper.PostMapper;
import ru.skillbox.adminpanel.repository.PostsRepository;
import ru.skillbox.adminpanel.util.PostSearchExecutor;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final PostSearchExecutor postSearchExecutor;
    private final PostsRepository postsRepository;

    public String buildPostsPage(Model model, FindPostRq findPostRq) throws SearchPeriodParsingException {
        model.addAttribute("posts", "router-link-active");
        model.addAttribute("postsList", findPosts(findPostRq));
        return "posts";
    }

    public void blockUnblockPost(Long id) {
        Post post = postsRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Can not find post id: " + id));
        post.setIsBlocked(post.getIsBlocked() == null || !post.getIsBlocked());
        postsRepository.save(post);
    }

    private List<PostRs> findPosts(FindPostRq findPostRq) throws SearchPeriodParsingException {
        return postListToPostRsList(postSearchExecutor.findPosts(findPostRq)).stream()
                .sorted(Comparator.comparing(PostRs::getTime).reversed())
                .toList();
    }

    private List<PostRs> postListToPostRsList(List<Post> posts) {
        return posts.stream().map(postMapper::postToPostRs).toList();
    }
}

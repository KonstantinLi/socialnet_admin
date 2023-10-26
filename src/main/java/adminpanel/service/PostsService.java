package adminpanel.service;

import adminpanel.api.request.FindPostRq;
import adminpanel.api.response.PostRs;
import adminpanel.errors.TimeException;
import adminpanel.mappers.PostMapper;
import adminpanel.model.PostEntity;
import adminpanel.repositories.PostsRepository;
import adminpanel.service.search.SearchPosts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostMapper postMapper;
    private final SearchPosts searchPosts;
    private final PostsRepository postsRepository;

    public String buildPostsPage(Model model, FindPostRq findPostRq) throws TimeException {
        model.addAttribute("posts", "router-link-active");
        model.addAttribute("postsList", findPosts(findPostRq));
        return "posts";
    }

    public void blockUnblockPost(Long id) {
        PostEntity post = postsRepository.findById(id).orElseThrow();
        post.setIsBlocked(!post.getIsBlocked());
        postsRepository.save(post);
    }

    private List<PostRs> findPosts(FindPostRq findPostRq) throws TimeException {
        return postToPostRs(searchPosts.findPosts(findPostRq)).stream()
                .sorted(Comparator.comparing(PostRs::getId))
                .collect(Collectors.toList());
    }

    private List<PostRs> postToPostRs(List<PostEntity> posts) {
        return posts.stream().map(postMapper::toPostRs).collect(Collectors.toList());
    }
}

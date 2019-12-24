package com.follow.book.springboot.service.posts;

import com.follow.book.springboot.domain.posts.Posts;
import com.follow.book.springboot.domain.posts.PostsRepository;
import com.follow.book.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public PostsSaveResponseDto save(PostsSaveRequestDto requestDto) {
        Posts post = postsRepository.save(requestDto.toEntity());
        return PostsSaveResponseDto.of(post.getId());
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PostsResponseDto(posts);
    }
}

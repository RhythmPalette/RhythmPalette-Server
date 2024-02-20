package com.umc.Palette.domain.post.service;


import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.repository.CommentRepository;
import com.umc.Palette.domain.emotion.domain.Emotion;
import com.umc.Palette.domain.emotion.repository.EmotionRepository;
import com.umc.Palette.domain.follow.domain.Follow;
import com.umc.Palette.domain.music.repository.MusicRepository;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.post.dto.PostRequest;
import com.umc.Palette.domain.post.dto.PostResponse;
import com.umc.Palette.domain.post.dto.SliceResponse;

import com.umc.Palette.domain.post.repository.PostRepository;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.post.repository.PostLikeRepository;
import com.umc.Palette.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final EmotionRepository emotionRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    public PostResponse.postDetail addPost(PostRequest.AddDTO addDTO){
        Emotion emotion = emotionRepository.findById(addDTO.getEmotionId()).orElseThrow();
        User user = userRepository.findById(addDTO.getUserId()).orElseThrow();
        musicRepository.save(addDTO.getMusicRequest().toEntity());
        Post post = postRepository.save(addDTO.toEntity(user, emotion));
        return PostResponse.postDetail.of(post, user);
    }

    //게시글 상세 조회
    public PostResponse.postDetail getPostDetail(Long postId , Long userId){
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        return PostResponse.postDetail.of(post, user);
    }

    //비로그인 사용자의 모든 게시글 조회
    public SliceResponse getPostList(Long userId, Pageable pageable){

        Slice<Post> postList = postRepository.findAllBy(pageable);
        User user = userRepository.findById(userId).orElseThrow();
        Slice<PostResponse.postDetail> postDtoList = postList.map(post -> PostResponse.postDetail.of(post, user));
        return new SliceResponse<>(postDtoList);
    }

    //로그인 사용자의 팔로우한 사용자 게시글 조회
    public SliceResponse getPostListWithFollow(Long userId, Pageable pageable){
        User user = userRepository.findById(userId).orElseThrow();
        Slice<Post> postList = postRepository.findAllPostByFollow(userId, pageable);
        Slice<PostResponse.postDetail> postDtoList = postList.map(post -> PostResponse.postDetail.of(post, user));
        return new SliceResponse<>(postDtoList);
    }

    //장르로 모든 게시글 조회
    public SliceResponse getPostListWithGenre(String genre, Long userId, Pageable pageable){
        User user = userRepository.findById(userId).orElseThrow();
        Slice<Post> postList = postRepository.findAllPostByGenre(genre, pageable);
        Slice<PostResponse.postDetail> postDtoList = postList.map(post -> PostResponse.postDetail.of(post, user));
        return new SliceResponse<>(postDtoList);
    }

    //감정으로 모든 게시글 조회
    public SliceResponse getPostListWithEmotion(Long emotionId, Long userId, Pageable pageable){
        User user = userRepository.findById(userId).orElseThrow();
        Slice<Post> postList = postRepository.findAllPostByEmotion(emotionId, pageable);
        Slice<PostResponse.postDetail> postDtoList = postList.map(post -> PostResponse.postDetail.of(post, user));
        return new SliceResponse<>(postDtoList);
    }



    //월을 입력받고 해당 월에 본인이 작성한 게시글 조회
    public List<PostResponse.postDetail> getPostCalendar(int month, Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        List<Post> postListByMonth = postRepository.findAllByMonth(month);
        return PostResponse.postDetail.of(postListByMonth,user);
    }

    //본인이 작성한 게시글 최신순 조회
    public SliceResponse getPostMyPage(Long userId, Pageable pageable){
        User user = userRepository.findById(userId).orElseThrow();
        Slice<Post> postListMyPage = postRepository.findPostByUserOrderByCreatedAtDesc(userId, pageable);
        Slice<PostResponse.postDetail> postDtoList = postListMyPage.map(post -> PostResponse.postDetail.of(post, user));
        return new SliceResponse<>(postDtoList);
    }

    public void updatePost(PostRequest.UpdateDTO updateDTO, Long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        post.updatePost(updateDTO.getContent(),
                updateDTO.getSituation1(),
                updateDTO.getSituation2(),
                updateDTO.getSituation3()
        );
        Emotion emotion = emotionRepository.findById(updateDTO.getEmotionId()).orElseThrow();
        post.updateEmotion(emotion);
        postRepository.save(post);

    }
    public String deletePost(Long postId){
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        for(Comment comment : comments){
            commentRepository.delete(comment);
        }
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.deleteById(postId);
        return post.getPostImg();
    }

    public List<PostResponse.postDetail> getAllPostWhitUserId(Long userId){
        List<Post> postList = postRepository.findAllByUserId(userId);
        User user = userRepository.findById(userId).orElseThrow();
        return PostResponse.postDetail.of(postList,user);
    }


}

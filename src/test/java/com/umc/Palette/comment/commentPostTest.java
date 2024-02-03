package com.umc.Palette.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

public class commentPostTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    public void testPostComment() throws Exception {
//        // Create a CommentRequestDTO
//        CommentRequestDTO.CreateDTO requestDTO = new CommentRequestDTO.CreateDTO();
//        requestDTO.setUserId(1L);  // 사용자 ID
//        requestDTO.setPostId(2L);  // 게시물 ID
//        requestDTO.setComment("This is a comment");
//        requestDTO.setCreatedAt(LocalDateTime.now());
//        requestDTO.setUpdatedAt(LocalDateTime.now());

//
//
//        // Perform a POST request to /posts/{postId}/comments with the created CommentRequestDTO
//        mockMvc.perform(MockMvcRequestBuilders.post("/posts/{postId}/comments", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDTO)))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//    }
}

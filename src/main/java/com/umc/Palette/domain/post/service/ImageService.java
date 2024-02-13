package com.umc.Palette.domain.post.service;

import com.umc.Palette.domain.post.domain.Image;
import com.umc.Palette.domain.post.dto.PostRequestDTO;
import com.umc.Palette.domain.post.dto.PostResponseDTO;
import com.umc.Palette.domain.post.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    @Value("${kakao-api-key}")
    private String kakaoApiKey;

    public PostResponseDTO.ImageDTO createImage(PostRequestDTO.ImageDTO imageDTO){
        String kakaoApiUrl = "https://api.kakaobrain.com/v2/inference/karlo/t2i";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        PostResponseDTO.ImageDTO response = restTemplate.exchange(
                kakaoApiUrl,
                HttpMethod.POST,
                new HttpEntity<>(imageDTO, headers),
                PostResponseDTO.ImageDTO.class
        ).getBody();

        for (Image image : response.getImages()){
            Image image1 = new Image(image.getId(), image.getSeed(), image.getImage());
            imageRepository.save(image1);
        }
        return response;
    }
}

package com.umc.Palette.domain.post.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.umc.Palette.domain.music.dto.request.MusicRequest;
import com.umc.Palette.domain.post.domain.Image;
import com.umc.Palette.domain.post.dto.PostRequest;
import com.umc.Palette.domain.post.dto.PostResponse;
import com.umc.Palette.domain.post.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    @Value("${kakao-api-key}")
    private String kakaoApiKey;

    private final AmazonS3 amazonS3;

    @Value("${app.s3.bucket}")
    private String bucketName;

    public PostResponse.ImageDTO createImage(PostRequest.ImageDTO imageDTO){
        String kakaoApiUrl = "https://api.kakaobrain.com/v2/inference/karlo/t2i";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        imageDTO.setSamples(6);

        PostResponse.ImageDTO response = restTemplate.exchange(
                kakaoApiUrl,
                HttpMethod.POST,
                new HttpEntity<>(imageDTO, headers),
                PostResponse.ImageDTO.class
        ).getBody();

        for (Image image : response.getImages()){
            Image image1 = new Image(image.getId(), image.getSeed(), image.getImage());
            imageRepository.save(image1);
        }
        return response;
    }

    public String uploadImageFromUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        String fileName = extractFileName(url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();
        S3Object s3Object = new S3Object();
        s3Object.setObjectContent(inputStream);
        // ObjectKey는 S3 내에서의 이미지 파일 경로와 이름을 말합니다. (예: "images/myimage.jpg")
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, s3Object.getObjectContent(), new ObjectMetadata()));
        inputStream.close();
        connection.disconnect();

        return amazonS3.getUrl(bucketName,fileName).toString();
    }

    private String extractFileName(URL url) {
        String filePath = url.getPath();
        int lastSlashIndex = filePath.lastIndexOf('/');
        int lastDotIndex = filePath.lastIndexOf('.');
        return filePath.substring(lastSlashIndex + 1, lastDotIndex);
    }
}

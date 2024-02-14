INSERT INTO emotion (emotion_id, name) VALUES
                                           (1, '기쁨'),
                                           (2, '슬픔'),
                                           (3, '화남'),
                                           (4, '무감정'),
                                           (5, '사랑'),
                                           (6, '즐거움'),
                                           (7, '불안함');
--서비스 실행 시 감정표현을 생성해주는 쿼리문
--properties파일에 spring.jpa.hibernate.ddl-auto=none 필요
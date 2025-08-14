//package com.dabom.videocomment.model.dto;
//
//import com.dabom.videocomment.model.entity.VideoComment;
//import lombok.Getter;
//
//@Getter
//public class VideoCommentRegisterDto {
//    private String content;
//    private String  username;
//    private Integer videoIdx;
//    private Integer userIdx;
//
//    public VideoComment toEntity(){
//        Video video = Video.builder()
//                .idx(videoIdx)
//                .build();
//
//        User user = User.builder()
//                .idx(userIdx)
//                .build();
//
//        VideoComment entity = VideoComment.builder()
//                .content(content)
//                .video(video)
//                .user(user)
//                .isDeleted(false)
//                .build();
//        return entity;
//    }
//}

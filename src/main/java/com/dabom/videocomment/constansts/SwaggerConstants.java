package com.dabom.videocomment.constansts;

public final class SwaggerConstants {
    // 영상 댓글 요청
    public static final String VIDEO_COMMENT_CREATED_REQUEST = """
            {
                 "content": "정말 유익한영상입니다!"
            }
            """;

    // 영상 댓글 응답
    public static final String VIDEO_COMMENT_CREATED_RESPONSE = """
            {
                "data": 1,
                "code": 200,
                "message": "댓글이 성공적으로 등록되었습니다."
            }    
            """;
    public static final String VIDEO_COMMENT_DELETED = """
            
            """;

    // 영상 댓글 페이징(무한 스크롤)
    public static final String VIDEO_COMMENT_PAGING = """
                {
                    "data": {
                        "content": [
                            {
                                "idx": 34,
                                "content": "ㅁㅈㅇ",
                                "createdAt": "2025-08-17 22:16:02",
                                "updatedAt": "2025-08-17 22:16:02",
                                "isModified": false
                            },
                            {
                                "idx": 35,
                                "content": "ㅁㄴㅇ",
                                "createdAt": "2025-08-17 22:16:05",
                                "updatedAt": "2025-08-17 22:16:05",
                                "isModified": false
                            }
                        ],
                        "hasNext": true,
                        "totalCount": 21
                    },
                    "code": 200,
                    "message": "댓글 조회 성공"
                }
            """;

    // 영상 댓글 수정 요청
    public static final String VIDEO_COMMENT_UPDATE_REQUEST = """
                {
                    "comment": "수정할 댓글 내용"
                }
            """;

    // 영상댓글 수정 응답
    public static final String VIDEO_COMMENT_UPDATE_RESPONSE = """
            {
                "data": {
                    "idx": 25,
                    "content": "수정된댓글2",
                    "createdAt": "2025-08-17 13:05:22",
                    "updatedAt": "2025-08-18 11:36:43",
                    "isModified": true
                },
                "code": 200,
                "message": "댓글 수정 완료"
            }  
            """;

    //영상 댓글 삭제 응답
    public static final String VIDEO_COMMENT_DELETE_RESPONSE = """
            {
                "data": null,
                "code": 200,
                "message": "댓글 삭제 성공"
            }
            """;

    // 영상댓글 리스트 기능(응답)
    public static final String VIDEO_COMMENT_LIST_RESPONSE = """
            {
                "data": [
                    {
                        "idx": 24,
                        "content": null,
                        "createdAt": "2025-08-17 13:05:07",
                        "updatedAt": "2025-08-17 13:05:07",
                        "isModified": false
                    },
                    {
                        "idx": 23,
                        "content": "수정된 댓글",
                        "createdAt": "2025-08-17 13:04:55",
                        "updatedAt": "2025-08-17 21:58:22",
                        "isModified": true
                    }
                ],
                "code": 200,
                "message": "댓글 조회 성공"
            }
            """;
}

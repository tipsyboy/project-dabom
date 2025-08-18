package com.dabom.boardcomment.constansts;

import org.springframework.format.number.PercentStyleFormatter;

public final class SwaggerConstants {
    public static final String BOARD_COMMENT_CREATED_REQUEST = """
            {
                 "content": "좋은 게시글이네요!"
            }
            """;
    public static final String BOARD_COMMENT_CREATED_RESPONSE = """
            {
                "data": 1,
                "code": 200,
                "message": "댓글이 성공적으로 등록되었습니다."
            }    
            """;
    public static final String BOARD_COMMENT_DELETED = """
            
            """;

    public static final String BOARD_COMMENT_PAGING = """
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

    public static final String BOARD_COMMENT_UPDATE_REQUEST = """
                {
                    "comment": "수정할 댓글 내용"
                }
            """;

    public static final String BOARD_COMMENT_UPDATE_RESPONSE = """
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

    public static final String BOARD_COMMENT_DELETE_RESPONSE = """
            {
                "data": null,
                "code": 200,
                "message": "댓글 삭제 성공"
            }
            """;
    public static final String BOARD_COMMENT_LIST_RESPONSE = """
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

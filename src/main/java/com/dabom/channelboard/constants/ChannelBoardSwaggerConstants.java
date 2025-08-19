package com.dabom.channelboard.constants;

public final class ChannelBoardSwaggerConstants {
    public static final String CHANNEL_BOARD_REGISTER_REQUEST = """
            {
                "title": "첫 번째 공지사항",
                "contents": "안녕하세요. 채널에 오신 것을 환영합니다!"
            }
            """;

    public static final String CHANNEL_BOARD_REGISTER_RESPONSE = """
            {
                "data": 1,
                "code": 200,
                "message": "게시글 등록 성공"
            }
            """;

    public static final String CHANNEL_BOARD_LIST_RESPONSE = """
            {
                "data": {
                    "content": [
                        {
                            "idx": 1,
                            "title": "첫 번째 공지사항",
                            "contents": "안녕하세요. 채널에 오신 것을 환영합니다!",
                            "createAt": "2025-08-18 15:30:00",
                            "commentCount": 5
                        },
                        {
                            "idx": 2,
                            "title": "두 번째 공지사항",
                            "contents": "이벤트 안내드립니다.",
                            "createAt": "2025-08-18 16:00:00",
                            "commentCount": 3
                        }
                    ],
                    "hasNext": true,
                    "totalCount": 10
                },
                "code": 200,
                "message": "게시글 목록 조회 성공"
            }
            """;

    public static final String CHANNEL_BOARD_READ_RESPONSE = """
            {
                "data": {
                    "idx": 1,
                    "title": "첫 번째 공지사항",
                    "contents": "안녕하세요. 채널에 오신 것을 환영합니다!",
                    "createAt": "2025-08-18 15:30:00",
                    "commentCount": 5
                },
                "code": 200,
            }
            """;

    public static final String CHANNEL_BOARD_UPDATE_REQUEST = """
            {
                "boardIdx": 1,
                "title": "수정된 제목",
                "contents": "수정된 내용"
            }
            """;

    public static final String CHANNEL_BOARD_UPDATE_RESPONSE = """
            {
                "data": 1,
                "code": 200,
                "message": "게시글 수정 성공"
            }
            """;

    public static final String CHANNEL_BOARD_DELETE_RESPONSE = """
            {
                "data": null,
                "code": 200,
                "message": "게시글 삭제 성공"
            }
            """;
}
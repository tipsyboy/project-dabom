package com.dabom.member.contants;

public abstract class MemberSwaggerConstants {
    public static final String SIGNUP_MEMBER_REQUEST = """
            {
                "email": "test@test.com",
                "channelName": "testChannel",
                "password": "qwer!234",
                "memberRole": "USER"
            }
            """;

    public static final String SIGNUP_MEMBER_RESPONSE = """
            {
                "회원 가입 성공"
            }
            """;

    public static final String LOGIN_MEMBER_REQUEST = """
            {
                "email": "test@test.com",
                "password": "qwer!234"
            }
            """;

    public static final String LOGIN_MEMBER_RESPONSE = """
            {
                "로그인 성공했습니다."
            }
            """;

    public static final String LOGOUT_MEMBER_RESPONSE = """
            {
                "로그아웃 성공했습니다."
            }
            """;

    public static final String CHECK_EMAIL_MEMBER_REQUEST = """
            {
                "email": "test@test.com"
            }
            """;

    public static final String CHECK_EMAIL_MEMBER_RESPONSE = """
            {
                "isDuplicate": "false"
            }
            """;

    public static final String CHECK_CHANNEL_NAME_MEMBER_REQUEST = """
            {
                "channelName": "test"
            }
            """;

    public static final String CHECK_CHANNEL_NAME_MEMBER_RESPONSE = """
            {
                "isDuplicate": "false"
            }
            """;

    public static final String UPDATE_MEMBER_INFO_REQUEST = """
            {
                "id": "test",
                "name": "testUpdate",
                "content": "테스트 계정입니다."
            }
            """;

    public static final String UPDATE_MEMBER_INFO_RESPONSE = """
            {
                "채널 정보 수정이 완료되었습니다."
            }
            """;

    public static final String READ_MEMBER_INFO_RESPONSE = """
            {
                "id": "test",
                "name": "testUpdate",
                "content": "테스트 계정입니다.",
                "email": "test@test.com"
            }
            """;

    public static final String DELETE_MEMBER_RESPONSE = """
            {
                "삭제 완료 되었습니다. 실제 데이터 삭제까지는 하루정도 소요됩니다."
            }
            """;
}

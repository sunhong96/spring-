package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class memberServiceTest {

    MemberService memberService;

    @BeforeEach // 각테스트 실행전 무조건 실행
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    } // 실행하기전에 appConfig 를 만들고 할당해줌

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA" , Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}

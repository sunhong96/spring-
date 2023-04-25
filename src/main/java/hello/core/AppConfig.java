package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixdiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //생성자를 통해서 구현체에 뭐가들어갈지 주입해줌
    }

    @Bean // (name = "mmm") mmm 으로 이름을 바꿔줄수있음
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //생성자를 통해서 구현체에 뭐가들어갈지 주입해줌
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixdiscountPolicy(); //할인율을 바꾸려면 이코드만 건들면 됨
        return new RateDiscountPolicy();
    }
}

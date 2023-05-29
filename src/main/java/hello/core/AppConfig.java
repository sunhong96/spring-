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

    //singleton
    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //memberService 빈을 만드는 코드를 보면 memberRepository() 를 호출한다.
    //이 메서드를 호출하면 new MemoryMemberRepository() 를 호출한다.
    //orderService 빈을 만드는 코드도 동일하게 memberRepository() 를 호출한다.
    //이 메서드를 호출하면 new MemoryMemberRepository() 를 호출한다.
    //결과적으로 각각 다른 2개의 MemoryMemberRepository 가 생성되면서 싱글톤이 깨지는 것 처럼 보인다

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository
    //결과적으로 3번호출하는 것


    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //ConfigurationSingletonTest 여기서 실행해보면 이렇게 3번만 호출함
    //자바 코드를 보면 memberRepository 이 3번 호출이 돼야 되는게 맞음
    //하지만 스프링이 싱글톤을 보장해서 1번만 호출하게해줌

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //생성자를 통해서 구현체에 뭐가들어갈지 주입해줌.
    }

    @Bean // (name = "mmm") mmm 으로 이름을 바꿔줄수있음
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //생성자를 통해서 구현체에 뭐가들어갈지 주입해줌
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixdiscountPolicy(); //할인율을 바꾸려면 이코드만 건들면 됨
        return new RateDiscountPolicy();
    }
}

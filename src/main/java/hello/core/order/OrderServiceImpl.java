package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixdiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{


    private final MemberRepository memberRepository; //= new MemoryMemberRepository();
////    private final DiscountPolicy discountPolicy = new FixdiscountPolicy(); //고정할인금액정책

//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    // 고정할인금액정책을 새로운 10퍼 할인금액정책으로 변경해줌
//    // 어쨋든 코드를 변경해줘야 되기 떄문에 OCP 위반임
//
//    //OCP 원칙은 소프트웨어 요소(클래스, 모듈, 함수 등)는 확장에는 열려 있어야 하고
//    // 변경에는 닫혀 있어야 한다는 것을 말합니다
//    // 즉, 기존의 코드를 수정하지 않고도 새로운 기능을 추가할 수 있어야 한다는 것입니다
    private final DiscountPolicy discountPolicy;
    // 인터페이스에만 의존하도록 설계된 것 이렇게만 해주면 NPE가 발생함
    // 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를
    // 대신 생성하고 주입해주어야 한다.

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    } setter 라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법이다
    //@Autowired 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 주입할 대상이 없어도 동작하게
    //하려면 @Autowired(required = false) 로 지정하면 된다.


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } // 생성자로 생성자로 주입시켜줌 AppConfig 에서

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    } //단일체계 원칙을 잘지켜 설계댐

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

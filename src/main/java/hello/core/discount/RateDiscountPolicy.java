package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary 이걸 넣으면 여러개 빈이 선택이 되어도 최선 우선순위가되서 의존관계주입이 됨.
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGarde() == Grade.VIP){
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }

}

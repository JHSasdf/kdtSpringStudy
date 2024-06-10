package hello.core.order;

import hello.core.discount.FixedDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(3L, "ji", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixedDiscountPolicy());
        Order order = orderService.createOrder(3L, "Itema", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
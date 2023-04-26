package hello.core.member;

public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository; //  = = new MemoryMemberRepository();
    // 이걸 없애줌으로써 오롯이 추상화에만 의존함 dip 를 치킴

    // 추상화에 의존해야 하며, 구체화에 의존해서는 안된다는 원칙입니다.
    // 즉, 추상화된 인터페이스나 추상 클래스에 의존하도록 설계해야 하며,
    // 구체적인 구현 클래스에 의존해서는 안된다는 것입니다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 생성자로 주입시켜줌 AppConfig 에서
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

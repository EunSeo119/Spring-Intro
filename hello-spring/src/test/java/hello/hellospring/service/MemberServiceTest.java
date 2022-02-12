package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join()  {   //회원가입(Test는 이름 한글로 써도 됨! 근데 한글로 하면 에러뜨네..ㅋ)
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void except_duplicate() {    //중복_회원_예외
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));     //IllegalStateException 말고 NullPointerException 넣으면 테스트 실패! 에러!

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");     //메시지 검증은 이거 추가!


/*  //try, catch문으로 하는 방법

        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");     //("이미 존재하는 뷁뷁.") 하면 에러!
        }

*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
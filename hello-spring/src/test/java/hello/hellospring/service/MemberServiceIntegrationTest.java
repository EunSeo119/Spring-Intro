package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
//test 해보고 그 매서드 마다! 데이터 지움(걍 db 에 반영안함 -> 그래서 다음 테스트 지우는 코드 없어도 또 반복 실행 가능!):testcase에서만 롤백!
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join()  {   //회원가입(Test는 이름 한글로 써도 됨! 근데 한글로 하면 에러뜨네..ㅋ)
        //given
        Member member = new Member();
        member.setName("spring");

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

    }

}
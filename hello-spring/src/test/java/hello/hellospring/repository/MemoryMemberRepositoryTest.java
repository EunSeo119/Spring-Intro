package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //매서드 들이 실행 될 때마다 동작되는 것!
    // test들은 순차적으로 실행 x => 그래서 테스트 끝낼때마다 clear 해줘야함!(안그러면 충돌..)
    // 이걸로 테스트 실행되고 끝날때 마다 respository 저장소 지워줌!!!
    public void afterEach() {
        repository.clearStore();
    }

    @Test   //밑에꺼를 실행시킬 수 있다
    public void save() {

        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //반환 타입이 optional 이기 때문에 .get()으로 꺼내 줌
        assertThat(member).isEqualTo(result);       //member랑 result랑 비교해서 같나 틀리나? 틀리면 에러
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);  //.isEqualTo(member2); 하면 에러!
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);     //.isEqualTo(3); 하면 에러!
    }
}

package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearstore();
    }/** 매테스트마다 저장소를 지워주기 위함. 저장소를 안지우면 테스트시 순서가 랜덤이기때문에
    오류가 안걸림**/


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //반환타입이 optional이라 get()으로 꺼냄

        Assertions.assertEquals(member, result);// 테스트해서 결과값이 같은지 검사해보는 것
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        /**동규한테 물어볼것 멤버1객체를 생성후 setName메서드를 실행하면 name변수에 spring이 저장
        근데 이 객체를 인자로 받아서 레포지터리의 세이브메서드를 실행하면 어떻게 되는건지
        1, id=0,name=spring1로 저장되는게 맞나 ? store.put(member.getId(), member)은
        그럼 무슨 뜻인가 해시맵에 id0과 member1객체를 저장한다는건데 여기서 맴버1객체가 나타내는 것
        자체가 무엇인가, 내가 이해하기론 id=0 spring1을 저장한다는거같은데 member객체자체가 반환하는것이
        Id와 Name인데 그럼 해쉬맵에 멤버에서 겟한 아이디를 키로 멤버1의 아이디와 이름을 밸루로 저장하는게 맞나
        그리고 왜 member1과 member2는 왜 다른가? 단순히 다른 객체라서? 그럼 id는 0이 맞나?
        member.setId(++sequence);이 부분의 이해가 내가 한것이 맞나
        가장짚어야할것!! 인자에 인스턴스를 넣는다는 것은 그것의 리턴값을 넣는거와 같은것인가?
        맞다면 리턴값이 여러개일경우에는?**/
         repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }
    @Test
    public void findAll() {
        Member member1 = new Member();// 각 메소드마다 객체를 생성했는데 중복안되게 한번에
        //편리하게 되는 방법은 없을까?
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member1);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        /**모든 테스트는 한번에 실행시킬수 있지만 순서가 랜덤이다.그래서 findAll이 먼저 실행되면
        findByName은 오류가 난다** 해결법은 맨위 메서드와 메모리멤버레포지토리에 클리어메소드로
         해결한다**/
    }
}


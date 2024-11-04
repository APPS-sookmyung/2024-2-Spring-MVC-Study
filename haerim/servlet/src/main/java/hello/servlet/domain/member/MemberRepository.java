package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // 싱글톤이여서 static을 굳이 안 써도 되긴함.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 아이디 값이 하나씩 올라가는

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    // 싱글톤 만들 때는 private로 막아줘야 함.
    private MemberRepository() {

    }

    //save
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // 스토어에 있는 값들을 다 꺼내서 새로운 ArrayList에 담아 넘겨줌
    }

    // 실무에선 안 쓰고 test용
    public void clearStore(){
        store.clear();
    }
}


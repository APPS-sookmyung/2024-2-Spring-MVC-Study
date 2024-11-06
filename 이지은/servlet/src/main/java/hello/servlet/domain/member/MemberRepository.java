package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static이므로 memberrepository가 아무리 많아져도 하나만 생성됨
    private static long sequence = 0L;// 사실 싱글톤 덕분에 static 없어도 하나만 생성됨이 보장됨

    // 싱글톤
    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){ // private으로 아무나 생성하지 못하도록 함

    }

    // save
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values()); //store 내의 모든 원소를 꺼내서 새로 arraylist에 담아줌
    }

    // test에서만 사용
    public void clearStore(){
        store.clear();
    }


}

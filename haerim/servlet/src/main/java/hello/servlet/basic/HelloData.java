package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

// JSON 객체 변환
@Getter @Setter
public class HelloData {

    private String username;
    private int age;

//    //==== lombok 생성 코드 ====//
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public int getAge() {
//        return age;
//    }
//    public void setAge(int age) {
//        this.age = age;
//    }

}

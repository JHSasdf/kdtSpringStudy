package hello.core.singleton;

public class SingletonService {
    // static 영역에 객체 instance를 만들었기 때문에 모든 클래스가 하나만 가질 수 있고
    private static final SingletonService instance = new SingletonService();
    public static SingletonService getInstance() {
        return instance;
    }
    // 생성자도 private이라 외부에서 생성 불가
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 호출");
    }

}

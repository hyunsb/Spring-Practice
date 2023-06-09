package shop.mtcoding.springdemo;

public class User {

    private Long id;
    private String userName;
    private String email;

    public User() {
        System.out.println("User 디폴트 생성자 호출됨");
    }

    public User(Long id, String userName, String email) {
        System.out.println("User AllArguments 생성자 호출됨");
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public Long getId() {
        System.out.println("getId() : json으로 변환되기 위해 실행됨");
        return id;
    }

    public String getUserName() {
        System.out.println("getUsername() : json으로 변환되기 위해 실행됨");
        return userName;
    }

    public String getEmail() {
        System.out.println("getEmail() : json으로 변환되기 위해 실행됨");
        return email;
    }
}

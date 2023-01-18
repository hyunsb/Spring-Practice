package hyunsb.hellospring.domain;

public class Member {
    private Long id;        // 시스템이 저장하는 ID, 데이터 식별자
    private String name;    // 회원 이름

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

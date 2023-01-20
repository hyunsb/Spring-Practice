package hyunsb.hellospring.domain;

import javax.persistence.*;


@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // 시스템이 저장하는 ID, 데이터 식별자

//    @Column(name = "name")
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

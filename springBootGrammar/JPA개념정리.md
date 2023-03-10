# JPA 개념정리

## JPA란?

---

### ■ JPA는 Java Persistence API 이다.

- `persistece`(영속성) - 데이터를 생성한 프로그램의 실행이 종료되더라도 사라지지 않는 데이터의 특성을 말한다.
- `JAVA`환경에서 데이터를 `DBMS`를 통하여 `HDD`에 영구히 저장 및 관리할 수 있는 `API`(Application Programming Interface)
- JAVA 개발환경에서 데이터를 영구히 보관하기 위한 인터페이스

### ■ JPA는 `ORM`(Object Relational Mapping) 기술이다.

- JAVA에서 DB Table로 데이터를 `input`(DML) 하거나 `output`(Select) 할 때 서로의 데이터 타입을 맞추기 위해 데이터베이스에 있는 테이블을 JAVA에서 모델링한다.

    ```java
    @Entity
    @Getter
    @Setter
    @Table(name = "member")
    public class MemberEntity {
    
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
    
        @Column(name = "email")
        private String email;
    
        @Column(name = "password")
        private String password;
    
        @Column(name = "name")
        private String name;
    
    }
    ```

- 데이터베이스의 테이블을 자동으로 생성되게 하는 기술

### ■ JPA는 반복적인 `CRUD`작업을 생략하게 해준다.

- Select All (전체), Select, Delete, Update, Insert (1건)에 대한 간단한 처리
- 자바 → DB 커넥션 요청 시, DB → 자바 신분 확인 후 세션을 오픈, 자바는 커넥션을 가짐
- 자바 → DB 쿼리 전송, DB → 자바 데이터 전송
- DB의 데이터 타입을 JAVA의 데이터 타입으로 변환하는 단순 반복 로직을 처리해준다. 함수 하나로!

### ■ JPA는 `영속성 컨텍스트`(Context)를 가지고 있다.

- `컨텍스트` ? 어떤 대상의 모든 요소 ? 정보
- JAVA - [영속성 컨텍스트] - DB (영속성 컨텍스트는 DB와 동기화)
- `자바`에서 들어온 데이터 요청을 영속성 컨텍스트가 `DB`에게 전달
- DB에서 돌아온 응답을 자바 오브젝트로 변경한 뒤 자바에게 전달
- 자바가 가지고 있는 정보와 DB의 정보가 다를 때, 자바에서 Insert 작업이 요청됐다면 DB는 자동으로 Update 작업 실행 - 영속성 컨텍스트에 의해!
- 이 일련의 모든 작업을 영속성 컨텍스트를 통해 확인할 수 있다.
  (모든 메타데이터 정보를 가지고 있다.)

### ■ JPA는 DB와 OOP의 불일치성을 해결하기 위한 방법론을 제공한다. (DB는 객체저장 불가능)

- JAVA에서 Player 클래스가 Team 객체를 포함하고 있는 경우 DB는 이를 인식하지 못한다.
  하지만 이러한 문제를 JPA가 ORM을 통하여 자동으로 외래키 처리 해준다.

    ```java
    class Team{
    	int id;
    	String name;
    	String year;
    }
    ```

    ```java
    class Player{
    	int id;
    	String name;
    	// DB의 저장 타입과 일치하지 않지만 이를 JPA가 ORM를 통해 자동으로 외래키 처리해준다!
    	Team team;
    }
    ```


### ■ JPA는 OOP의 관점에서 모델링을 할 수 있게 해준다. (상속, 컴포지션, 연관관계)

- Car 클래스가 Engine 객체를 포함하고 있는 경우, 두 클래스에 동일한 TimeStamp 요소를 포함시킨다고 가정한다.

    ```java
    class Car{
    	String name;
    	String color;
    	Engine engine;
    
    	**TimeStamp createTime;
    	TimeStamp updateTime;**
    }
    ```

    ```java
    class Engine{
    	int id;
    	int power;
    
    	**TimeStamp createTime;
    	TimeStamp updateTime;**
    }
    ```

- 이러한 경우 동일 요소를 한데 묶어 클래스화 시키고, 이를 상속받는 형식으로 변경이 가능하다.

    ```java
    // 두 클래스의 동일 변수인 createTime, updateTime 을 묶어 EntityDate 클래스 생성
    
    class EntityDate {
    
    	TimeStamp createDate;
    	TimeStamp updateDate;
    
    }
    ```

    ```java
    // EntityDate 클래스를 상속받는 형식으로 OOP관점에서의 모델링 가능
    class Car extends EntityDate {
    
    	String name;
    	String color;
    	Engine engine;
    
    }
    ```

    ```java
    class Engine extends EntityDate {
    
    	int id;
    	int power;
    
    }
    ```

- DB에는 아래와 같은 형식으로 저장된다.

  Car Table

  | id | name | color | engineId | createDate | updateDate |
      | --- | --- | --- | --- | --- | --- |
  | 1 | BMW | white | 1 |  |  |
  | 2 | sonata | black | 2 |  |  |

### ■ 방언(Dialect) 처리가 용이하여 Migration, 유지보수에 좋다.

- `ANSI SQL`: 모든 DBMS에서 공통으로 사용 가능한 핵심 표준 SQL
- 각 DBMS Vnedor(공급업체)에서 자신만의 독자적인 기능을 추가한 다양한 SQL이 존재한다.

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e946a5a0-815f-45a6-9619-a9ba13caa682/Untitled.png)

- JPA에서는 설정을 통해 원하는 Dialect만 설정해주면 그에 알맞은 쿼리를 생성해준다.

---

### 프로토콜(Protocol) vs 인터페이스(Interface)

프로토콜: 권리가 동일한 약속

인터페이스: 상하관계가 존재하는 약속

- A 의 데이터를 B가 사용하길 원한다고 가정할 때
- B는 A의 인터페이스를 지키며 데이터를 사용해야한다.
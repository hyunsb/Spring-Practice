# REST API

---

`REST`(Representational State Transfer): 웹의 장점을 최대한 활용할 수 있는 아키텍처(Architecture)

### REST의 구성

자원(resource) - URI

행위(verb) - HTTP METHOD

표현(Representations)

---

## ✅ REST의 특징

### **Uniform (유니폼 인터페이스)**

- 일관된 인터페이스를 의미, 동일한 리소스 요청에 동일한 URL로 처리한다.
- `URI`는 리소스 식별자, `URL`은 로컬 위치를 가리킨다.

### **Stateless (무상태성)**

- 서버에 작업을 위한 상태정보(세션, 쿠키)를 따로 저장·관리하지 않는다.

  (대체제로 Web Token, localStorage 등의 사용 권장)

- 들어오는 요청만을 처리하면 되어 서비스의 자유도가 높아지며, 불필요한 정보의 배제로 인해 구현이 단순해진다.

### **Cacheable (캐시 가능)**

- 자주 변경되지 않는 사항들에 대해 HTTP가 가지는 캐싱 기능을 적용할 수 있다. (HTTP 프로토콜 표준에서 사용하는 Modified 태그, E-tag를 사용하여 캐싱 구현)

### **Self-descriptiveness (자체 표현 구조)**

- REST API 메시지만 보고도 이를 쉽게 이해할 수 있는 자체 표현 구조로 되어있다.

### **Client - Server 구조**

- REST 서버는 API제공, 클라이언트는 사용자 인증이나 컨텍스트(세션, 로그인정보)등을 직접 관리하는 구조로, 각각의 역할이 확실하게 분리된다.
- 클라이언트와 서버에서 개발해야 할 내용이 명확해지고 서로간 의존성이 줄어들게 된다.

### **Layerd System (계층형 구조)**

- 서버는 네트워크 상의 여러 계층으로 구성될 수 있지만 클라이언트는 서버의 복잡도와 상관 없이 End Point 만 알고 있으면 된다.

---

## ✅ REST API 디자인 가이드

- URI는 정보의 자원을 표현해야 한다.
- 자원에 대한 행위는 HTTP Method (GET, POST, PUT, DELETE)로 표현한다.
- URI의 마지막에 `/` 를 포함하지 않고 `_` 대신 `-` 사용을 권장한다.
- URI 경로에는 소문자가 적합하다.
- 파일 확장자는 URI에 포함시키지 않는다.

URI는 정보의 자원을 표현해야 한다.

```java
GET      /members/delete/1 (X)
DELETE   /members/1        (O)
```

자원에 대한 행위는 HTTP Method (GET, POST, PUT, DELETE)로 표현한다.

```java
GET      /members/1      /* GET을 통해 URI 요청 시 리소스를 조회 */
POST     /members/1      /* POST를 통해 URI 요청 시 리소스를 생성 */
PUT      /members/1      /* PUT을 통해 URI 요청 시 리소스를 수정 */
DELETE   /members/1      /* DELETE를 통해 URI 요청 시 리소스를 삭제 */

URI는 자원을 표현하는 데 집중하고 행위에 대한 정의는 HTTP Method를 통해 구분한다.
```

URI 경로에는 소문자가 적합하다.

```java
대소문자에 따라 다른 리소스로 인식하게 되기 때문에 대문자 사용을 피한다.
RFC 3986(URI 문법 형식)은 URI 스키마와 호스트를 제외하고는 대소문자를 구별하도록 규정

RFC 3986 is the URI (Unified Resource Identifier) Syntax document
```
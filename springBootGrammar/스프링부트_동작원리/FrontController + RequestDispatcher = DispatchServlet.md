## ■  FrontController 패턴

- 최초 앞단에서 request를 받아 필요한 클래스에 넘겨준다.
  web.xml에 모든 request에 해당하는 response값을 정의하기 힘들기 때문
- 클라이언트 → 톰캣 → 자원 의 순서로 request, response 할 때,
  클라이언트 → 톰캣 에서 request, response 객체가 생성되는데 해당 정보와
  톰캣 → 자원 에서 새로운 요청으로 생겨난 request, response 객체의 정보가 다른 상황이 발생한다.
- 혹은 클라이언트가 서버에게 A.jsp 를 요청하여 A.html 이라는 응답을 받은 후 어떠한 정보를 가지고 b.jsp를 요청했을 때, 새로운 request 객체가 생성되면서 기존의 request 객체에 저장된 정보를 잃어버리는 상황이 발생한다.
- 즉, 요청과 응답 사이에 클라이언트의 정보를 잃어버리게 되는데 이를 보완하고자 클라이언트 → 톰캣 에서 생성된 request, response 객체의 정보를 그대로 유지시켜주는 기법인 `RequestDispatcher` 가 생겨났다.

---

## ■  RequestDispatcher

- 필요한 클래스에 대한 요청이 도달했을 때 `FrontController`에 도착한 `request`와 `response`정보를 그대로 유지시켜준다.
- `RequestDispatcher`를 사용하면 페이지 간 데이터 이동이 가능!
    - 클라이언트가 서버에게 A.jsp 를 요청하여 A.html 이라는 응답을 받은 후 어떠한 정보를 가지고 b.jsp를 요청했을 때, 새로운 request 객체가 생성되면서 기존의 request 객체에 저장된 정보를 잃어버리는 상황이 발생했을 때,

      RequestDispatcher를 사용하면 A.html의 정보를 B.html에서도 사용할 수 있게 된다.
      B.jsp를 요청했을 때 의 request 객체는 새로운 객체를 생성하는 것이 아닌 기존의 객체를 재사용 하기 때문이다.


---

## ■  DispatchServlet

- Spring에서 제공하는 `FrontController 패턴` + `RequestDispatcher`의 기능을 수행하는 기술
- DispatchServlet이 자동생성되어 질 때 수 많은 객체가 생성(IoC) 된다.(Controller, Repository, Bean, … ) 이는 보통 필터들이고 해당 필터들은 기본적으로 필요한 자동으로 등록되고, 개발자가 추가적인 필터를 직접 등록할 수도 있다.
- 컴포넌트 스캔 - src 루트에 있는 모든 객체를 스캔하여 Heap 영역에 등재 (IoC)
  어노테이션을 통하여 메모리에 등재할 것을 구분한다.
- 요청에 대한 응답(주소 분배) - 컴포넌트 스캔 이후 요청에 대한 응답 처리
- DispatchServlet에서 생성되는 수많은 객체들은 어디서 관리될까 - `스프링 컨테이너`
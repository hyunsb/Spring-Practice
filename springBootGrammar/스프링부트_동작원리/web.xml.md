## ■  Web.xml (웹 배포 서술자, Deployment Descriptor)

- `**ServletContext`의 초기 파라미터 설정**
    - 애플리케이션내 모든 컴포넌트가 사용할 수 있는 초기 파라미터이다.
    - `ServetConfig`의 단점을 보완하여 개발된 파라미터이다.

      [서블릿 초기화 파라미터 (ServletConfig)](https://m.blog.naver.com/crazybnn/30099209553)


- `**Session`의 유효기간 설정**

- **Servlet/JSP에 대한 정의 및 매핑**
    - 클라이언트가 요청한 URI에 대하여 실제 자원의 위치를 정의에 따라 매핑
    - 매핑 시 (web.xml에 직접 매핑, @WebServlet 어노테이션 사용) 모든 클래스에 매핑을 적용시키기에는 코드가 너무 복잡해지기 때문에 `FrontController` 패턴을 이용한다.

- **`Mime Type` 매핑**
    - Mime Type(Multipurpose Internet Extensions): 바이너리 데이터인 첨부파일을 아스키코드로 인코딩하기 위해 사용되는 기법

      [MIME 타입 - HTTP | MDN](https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types)

      웹을 통해 HTTP통신에서 전달되는 다양한 형태의 데이터를 표현하기 위해 사용된다.

        <aside>
        ✨ Mime타입, 데이터없이 접근하는 클라이언트는 HTTP 통신의 GET 방식을 사용

        </aside>

- **Welcome File list**
- **Error Pages 처리**
    - 유효하지 않은 요청을 가지고 서버에 접근했을 때의 처리방법
- **리스너/필터 설정**
    - 필터: 클라이언트의 요청에서 특정 데이터를 걸러내어 제거하는 방법
    - 리스너

      [[서블릿/JSP] 리스너(Listner)란? 이벤트 리스너의 개념 설명. 구현 및 등록하기](https://dololak.tistory.com/616)

- **보안**
## ■  스프링 컨테이너 (Spring Container)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e6c2d019-198f-42c3-9172-916bcbc530f7/Untitled.png)

- **ContextLoaderListener**

  생성되는 모든 서블릿 (클라이언트의 모든 요청)이 공통적으로 필요한 기능 (DB Connect, etc ... )은 클라이언트의 요청으로 인하여 DispatchServlet이 스레드를 생성하기 전에 ContextLoaderListener을 통해 메모리에 등재된다.

  스레드마다 공통적으로 사용되는 기능을 메모리에 등재 - IoC Container 에서 관리

  root_ApplicationContext파일을 읽어서 동작한다.

- **ApplicationContext**

  `DispatcherServlet`에 의해 컴포넌트 스캐닝된 수 많은 객체들이 `ApplicationContext`에 등록되고 이것을 `IoC`(Inversion of Control) 라고 한다.

  개발자가 직접 new 를 통하여 객체를 생성하게 된다면 해당 객체를 가르키는 레퍼런스(참조) 변수를 관리하기 어렵기 때문에 스프링이 이를 관리한다.

  스프링이 직접 객체를 관리하기 때문에 개발자는 객체의 주소를 몰라도 되며, 개발자는 객체가 필요할 때 DI(Dependency Injection; 의존성 주입)를 통하여 객체를 가져와서 사용할 수 있다.

  ApplicationContext는 싱글톤으로 관리되기 때문에 어디에서 접근하든 동일한 객체라는 것을 보장한다.
    
  ---

  ApplicationContext의 종류: `root-applicationContext`, `servlet-applicationContext`

    - **servlet-applicationContext**
        - DispatcherServlet에 의해 실행된다.

          ViewResolver, Interceptor, MultipartResolver, HandlerMapping 등의 객체를 생성하고 웹과 관련된 어노테이션 @Controller, @RestController를 스캔한다.

    - **root-applicationContext**
        - ContextLoaderListener에 의해 실행된다.

          DB관련 객체(@Service, @Repository)를 스캔(메모리에 로딩)한다.

          ContextLoaderListener는 web.xml에 의해 실행되기 때문에 servlet-applicationContext보다 먼저 로드된다. 즉, servlet-applicationContext에서는 root-applicationContext에서 로드한 객체를 참조할 수 있지만 그 반대는 생성 시점이 다르기 때문에 불가능하다.


- Bean Factory
    - 필요한 객체를 Bean Factory에 등록할 수도 있다.

      여기에 객체를 등록하면 초기에 메모리에 로드되지 않고 필요할 때 getBean()(최근엔 어노테이션 @Bean을 사용하여 호출) 메서드를 사용하여 객체를 호출하고 메모리에 로드할 수 있다.

      이것 또한 IoC이며 필요할 때 DI하여 사용할 수 있다.

      ApplicationContext와 다른점은 Bean Factory에 로드되는 객체들은 미리 로드되지 않고 필요할 때 호출되서 로드되기 때문에 lazy-loading이 된다는 점이다.
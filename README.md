# 스프링에서 스프링 부트로

>## 5.1 스프링 부트 소개

스프링 부트는 스프링에서 파생된 여러 서브 프로젝트에서 시작해 이제는 완전한 메인 프로젝트 가되어버린 특이한 케이스다.<br>

스프링 부트는 엄밀히 말하면 '스프링 프레임 워크 개발 도구'라고 볼 수 있다. 스프링 부트는 엔터프라이즈급 앱을 개발하기 위해 필요한 기능을 제공하는 개발 도구다.

스프링 부트의 중요 특징으로 Auto Configuration(자동 설정)이 있다. 예를들어 스프링 부트는 데이터베이스와 관련 모듈을 추가하면 자동으로 관련 설정을 찾아 실행한다.

스프링만 이용하는 경우와 비교했을 때 **추가한 모듈에 대한 설정이 전혀 필요하지 않다**는 것이다.

또 다른 특징으로는 '내장 톰캣'과 단독 실행 가능한 도구라는 것이다. 별도의 서버 설정 없이 개발 및 실행 가능하다. 이를 이용해 스프링 부트 프로젝트를 jar 파일로 만들고 다른 OS에서 실행 하는 등의 작업이 가능하다.

- ### 기존 개발에서 달라지는 점

설정과 관련해 필요한 라이브러리를 기존 build.gradle파일에 추가하는 설정이 자동으로 처리되고, 내장 톰캣으로 WAS의 추가 설정이 필요하지 않다.

빈 설정은 XML대신, 자바 설정을 이용한다.

JSP를 이용했지만 스프링 부트는 Thymeleaf라는 템플릿 엔진을 이용한다. 최근 스프링 부트는 화면을 궝하지 않고 데이터만을 제공하는 API 서버라는 형태를 이용하기도 한다.

#### _스프링 부트의 프로젝트 생성 방식_

1. Spring Initializr를 이용한 자동 생성 // 대부분 사용
2. Maven이나 Gradle을 이용한 직접 생성.

#### _생성시 추가한 dependencies_

 - Spring Boot DevTools
 - Lombok
 - Spring Web
 - Thymeleaf
 - Spring Data JPA
 - MariaDB Driver

- ### 스프링 부트에서 웹 개발

스프링 부트에서 웹 개발은 컨트롤러나 화면 개발은 유사하지만, web.xml이나 servelt-context.xml과 같은 웹 관련 설정파일이 없다.

이러한 설정을 대신하는 클래스를 작성한다.

> ## 5.2 Thymeleaf

스프링과 마찬가지로 스프링 부트도 다양한 View 관련 기술을 적용할 수 있다. 이때 스프링 부트는 Thymeleaf라는 템플릿 엔진을 이용한다.

Thymeleaf는 '템플릿'이기 때문에 JSP처럼 직접 데이터를 생성하지 않고 만들어진 결과에 데이터를 맞춰 보여주는 방식이다.
JSP와 마찬가지로 서버에서 동작하기는 하지만, HTML을 기반으로 화면을 구성하기에 HTML에 좀 더 가까운 방식으로 작성된다.

- ### Thymeleaf 기초문법

Thymeleaf를 이요하기 위해 가장 중요한 설정은 '네임스페이스(xmlns)'에 Thymeleaf를 지정하는 것이다. 네임스페이스를 지정하면 `th:`를 통해 모든 기능을 사용할 수 있다.

#### _Thymeleaf출력_

Model로 전달된 데이터를 출력하기 위해 HTML 태그 내에 `th:...`로 시작하는 속성을 이용하거나 inlining을 이용한다.

#### _Thymeleaf 주석 처리_

에러가 난 부분을 찾기 위해 주석처리 할 때는 `<!--/* ... */-->`를 이용한다.<br>
주석을 사용하면 Thymeleaf가 파싱 처리할 때 삭제되어 처리되기에 잘못된 문법에 대한 체크도 건너뛸 수 있고, 삭제된 상태에서 처리되어 브라우저는 해당 부분은 결과 자체가 없다.

#### _th:with를 이용한 변수 선언_

임시로 변수를 선언해야 하는 상황에서는 `th:with`를 이용해 간단히 처리할 수 있다.<br>
`th:with = "name = value"` 형태로 ','을 이용해 여러 개 선언할 수 있다.

#### _반복문 제어문 처리_

반복문 처리는 크게 2가지 방법이 있다.
- 반복문이 필요한 테그에 `th:each`를 적용한다.
  * 기존의 HTML을 그대로 둔 상테에서 반복 처리를 할 수 있다.
  * 다만 JSTL과 조금 이질적인 형태이다.
- `<th:block>`이라는 별도의 태그를 이용한다.
  * 추가로 태그가 들어간다는 단점이 있다.

#### _반복문의 status 변수_

`th:each`를 처리할 때 현재 반복문의 내부 상태에 변수를 추가해 사용할 수 있다. 이를 status 변수라고 한다.
주로 index/count/size/first/last/odd/even 등을 이용해 자주 사용하는 값을 출력할 수 있다.

```html
<ul>
    <li th:each="str,status: ${list}">
<!--        각 인덱스 번호와 해당 str 값 출력-->
        [[${status.index}]] -- [[${str}]]
        
    </li>
</ul>
```

#### _th:if/unless/switch_

제어문의 형태로 위와 같은 코드를 이용할 수 있다.

`th:if`와 `th:unless`는 별도의 속성으로 사용할 수 있으므로 if else와는 조금 다르게 사용된다.
또한 '?'를 삼항 연산자로 그대로 사용할 수 있다.
```html
<ul>
    <li th:each="str,status: ${list}">
        <span th:if="${status.odd}">ODD -- [[{${str}]]</span>
        <span th:unless="${status.odd}">EVEN -- [[{${str}]]</span>
    </li>
</ul>
```
```html
<ul>
    <li th:each="str,status: ${list}">
        <span th:text="${status.odd} ? 'ODD --- ' + ${str} : 'EVEN ---'+ ${str} "></span>
    </li>
</ul>
```

`th:switch`는 `th:case`와 같이 사용하여 Switch 문을 처리할 때 사용할 수 있다.
```html
<ul>
    <li th:each="str,status: ${list}">
        <th:block th:switch="${status.index %3}">
            <span th:case="0">0</span>
            <span th:case="1">1</span>
            <span th:case="2">2</span>
        </th:block>
    </li>
</ul>
```

#### _Thymeleaf 링크 처리_

JSP를 이용할 때 '/'로 시작하는 것과 특정 프로젝트의 경로부터 시작하는 것을 모두 고려한느 건 번거롭지만, Thymeleaf는 '@'로 링크를 작성하기만 하면 된다.
```html
<a th:herf="@{/hello}">go to /hello</a>
```

#### _링크 쿼리 스트링 처리_
쿼리 스트링은 'key=value'의 형태로 파라미터를 처리할 때 사용되며 '()'를 이용해 파라미터의 이름과 값을 지정한다.
```html
<a th:herf="@{/hello(name='AAA', age=16}">go to /hello</a>
<!--/hello?name=AAA$age16-->
```
한글의 경우 자동으로 URL 인코딩 처리가 이루어진다.<br>
배열의 경우 또한 같은 이름의 파라미터를 자동으로 처리한다.

- ### Thymeleaf의 특별한 기능들


#### _인라인 처리_

상황에 따라 동일한 데이터를 다르게 출력해주는 인라인 기능을 자바스크립트를 사용할 때 편리하다.


#### _레이아웃 기능_

`<th:block>`을 이용하면 레이아웃을 만들고 특정한 페이지에서는 필요한 부분만을 작성하는 방식으로 개발이 가능하다.
레이아웃 기능을 위해 별도의 라이브러리가 필요하므로 build.gradle을 수정해야한다.

```build.gradle
depednecies{
// https://mvnrepository.com/artifact/nz.net.ultraq.thymeleaf/thymeleaf-layout-dialect
    implementation group: 'nz.net.ultraq.thymeleaf', name: 'thymeleaf-layout-dialect', version: '3.0.0'
}
```

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div><h3>Sample Layout Header</h3></div>

<div layout:fragment="content">
    <p>Page content goes here</p>
</div>

<div>
    <h3>Sample Layout Footer</h3>
</div>
<th:block layout:fragment="scrip"></th:block>
</body>
</html>
```
네임스페이스에 레이아웃을 적용시킨다.<br>
`<layout:fragment>`속성을 이용하면 해당 영역은 나중에 다른 파일에서 해당 부분만을 개발할 수 있다.<br>
위 코드는 'content'와 'script' 부분을 fragment로 지정했다. 새로운 화면을 작성할 때 코드를 그대로 활용하면서 'content/script' 중 원하는 영역만을 작성할 수 있다.
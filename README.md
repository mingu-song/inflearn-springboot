### 자바 스프링부트 활용 웹개발 실무용
* https://www.inflearn.com/course/backend-송자바/
* https://github.com/stylehosting/example-spring

### 왜하나
* 공짜기도 하고 기본에 충실하기 위해 진행해봄
* gradle 을 사용하며, 조금씩 수정한 부분 있음
* java 11, spring boot 2.5.2
* 스프링부트라고 제목이 되어 있지만, 대부분 스프링의 기술을 사용하는 듯.. (기분탓인가)

### E07
* allowMultiQueries=true
* commons-lang3 :: RandomStringUtils.randomAlphabetic()
* List 정보를 Java에서 하나씩 save 하거나, Mybatis에서 foreach 로 처리 성능 차이
    * 1만건 insert = 23.121s : 0.871s => db connection 생성 회수에 따른 차이
    * 100건 이하는 비슷함
    
### E08
* Enum 처리를 위해 아래 빈 생성 :: 시리얼라이즈에 정의된 맵형태로 조회됨
  * BaseCodeLabelEnumJsonSerializer
  * WebConfig.objectMapper
  * WebConfig.mappingJackson2JsonView

### E09
* Mybatis 조건에 일반 클래스 사용하기
  * test="@org.apache.commons.lang3.StringUtils@isNotEmpty(keyword)"

### E10
* foreach 조회조건
  ```xml
  AND B.BOARD_TYPE IN (
    <foreach collection="boardTypes" item="value" separator=",">
    #{value}
    </foreach>
    )
  ```

### E11
* addArgumentResolvers 를 등록하여 처리
* Mybatis에서 페이징은 이 방법뿐인가??

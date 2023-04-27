package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 스프링 컨텍스트는 해당 어노테이션이 붙은 클래스의 패키지를 스캔하여,
// @Component 어노테이션이 붙은 클래스를 찾아 자동으로 빈으로 등록합니다.

//        basePackages = "hello.core.member", // 등록 범위를 지정해줄수있음
//
//        basePackageClasses = AutoAppConfig.class, // 등록 범위를 지정해줄수있음

        //지정하지않으면 package hello.core 에 @ComponentScan 이 붙은 클래스의 패키지를 다뒤짐

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)// 그중에서 뺄거를 정해줌
        //컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에,
        //AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되어 버린다. 그래서
        //excludeFilters 를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외했다. 보통 설정 정보를 컴포넌트
        //스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택했다.
)
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    //같은 이름의 빈이 있을때
    //이 경우 수동 빈 등록이 우선권을 가진다.
    //수동 빈이 자동 빈을 오버라이딩 해버린다.
    //스프링 부트에서는 오류가 남
}

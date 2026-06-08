package com.co2ma.dazimcapsule.ai;

import com.co2ma.dazimcapsule.capsule.CapsuleEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CapsuleSummaryServiceTest {
    @Autowired
    private CapsuleSummaryService capsuleSummaryService;

    @Test
    void analyze() {
        List<CapsuleEntry> entries = List.of(
                CapsuleEntry.builder()
                        .name("김다짐")
                        .email("kim@test.com")
                        .body("""
                    올해는 매일 30분씩 독서를 하겠다고 다짐했어. 1년 뒤의 나는 책을 얼마나 읽었을까?
                    이수진아, 네가 항상 웃는 모습이 나한테 큰 힘이 돼. 1년 뒤에도 그 웃음 잃지 마.
                    민준아, 너랑 같이 운동하기로 한 거 꼭 지키자. 우리 둘 다 건강해지자!
                    """)
                        .build(),
                CapsuleEntry.builder()
                        .name("이수진")
                        .email("lee@test.com")
                        .body("""
                    나는 올해 새로운 취미를 찾고 싶어. 그림을 배워보고 싶은데 용기가 안 났거든.
                    다짐아, 네가 독서 열심히 한다고 했는데 나도 자극받아서 같이 해보고 싶어졌어.
                    민준아, 너는 항상 긍정적이잖아. 그 에너지가 부러워. 나도 좀 나눠줘!
                    """)
                        .build(),
                CapsuleEntry.builder()
                        .name("박민준")
                        .email("park@test.com")
                        .body("""
                    운동도 열심히 하고 싶고, 올해는 꼭 여행도 한 번 가고 싶어. 혼자라도 도전해볼 거야.
                    다짐아, 네가 책 읽는 거 보면 나도 뭔가 해야겠다는 생각이 들어. 항상 고마워.
                    수진아, 그림 배우고 싶다고 했잖아. 망설이지 말고 당장 등록해. 후회 없을 거야!
                    """)
                        .build()
        );

        CapsuleSummaryDTO result = capsuleSummaryService.analyze(entries);
        System.out.println("결과: " + result.getTitle() + " " + result.getBody());
    }
}

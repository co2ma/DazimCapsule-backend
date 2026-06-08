package com.co2ma.dazimcapsule.ai;

import com.co2ma.dazimcapsule.capsule.CapsuleEntry;
import lombok.RequiredArgsConstructor;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CapsuleSummaryService {

    private final ChatClient.Builder chatClientBuilder;

    public CapsuleSummaryDTO analyze(List<CapsuleEntry> entries) {
        ChatClient chatClient = chatClientBuilder.build();

        String combined = entries.stream()
                .map(e -> e.getName() + ": " + e.getBody())
                .collect(Collectors.joining("\n"));

        String json = chatClient.prompt()
                .system("""
                        너는 주어진 리스트에서 사람들의 다짐을 분석하는 전문가야
                        서로에게 어떤 이야기를 해주고 있는지, 어떤 다짐을 했는지 너가 요약 및 분석 해서 
                        감성적인 워딩으로 답변해
                        다음의 JSON 형식으로 답을 해
                        { 
                            title: (다짐들을 관통하는 한 줄 소제목),
                            body: (어떠한 내용의 이야기를 주고 받으려 했는지 감성적으로 풀어서 장문으로 작성)
                        }    
                        분석 시 주의사항:
                            - 긍정적이고 따뜻한 톤을 유지하세요
                            - JSON 형식으로 답을 해
                            - 한국어로 응답하세요 
                            - 이야기를 해주듯이 대화하는 느낌으로 부드럽게 title과 body 내용을 채울 것.
                        """)
                .user(combined)
                .call()
                .content();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, CapsuleSummaryDTO.class);
        } catch (Exception e) {
            return new CapsuleSummaryDTO("우리의 다짐", "요약을 불러오지 못했습니다.");
        }
    }
}

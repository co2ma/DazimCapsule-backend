package com.co2ma.dazimcapsule.scheduler;

import com.co2ma.dazimcapsule.ai.CapsuleSummaryDTO;
import com.co2ma.dazimcapsule.ai.CapsuleSummaryService;
import com.co2ma.dazimcapsule.capsule.CapsuleEntry;
import com.co2ma.dazimcapsule.capsule.CapsuleEntryRepository;
import com.co2ma.dazimcapsule.capsule.CapsuleEntryService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final JavaMailSender mailSender;
    private final CapsuleEntryService capsuleEntryService;
    private final CapsuleSummaryService capsuleSummaryService;

    @Value("${spring.mail.username}")
    private String from;

    public void emailProcess(List<CapsuleEntry> capsuleEntryList) throws MessagingException {
        String body = buildBody(capsuleEntryList);
        CapsuleSummaryDTO res = capsuleSummaryService.analyze(capsuleEntryList);
        for(CapsuleEntry capsuleEntry : capsuleEntryList){
            sendEmail(capsuleEntry.getEmail(), capsuleEntry.getName(), body, res.getTitle(), res.getBody());
            capsuleEntry.markAsProcessed();
            capsuleEntryService.saveTrue(capsuleEntry);
        }
    }

    private void sendEmail(String to, String name, String body, String resTitle, String resBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("[다짐 캡슐] 미래의 당신에게 편지가 도착했습니다");
        helper.setText(buildHtml(name, body, resTitle, resBody), true);

        mailSender.send(message);
    }

    // 1. 본문 미리 만들기 (리스트 처리, 한 번만 호출)
    private String buildBody(List<CapsuleEntry> entries) {
        StringBuilder entriesHtml = new StringBuilder();
        for (CapsuleEntry entry : entries) {
            entriesHtml.append("""
            <div style="margin-bottom: 16px; background: rgba(255,255,255,0.04); border: 1px solid rgba(255,255,255,0.08); border-radius: 12px; padding: 16px 18px;">
              <p style="font-size: 12px; color: rgba(200,180,255,0.7); margin: 0 0 8px; font-weight: 500;">%s 님</p>
              <p style="font-size: 13px; color: rgba(255,255,255,0.8); line-height: 1.8; margin: 0;">%s</p>
            </div>
        """.formatted(entry.getName(), entry.getBody()));
        }
        return entriesHtml.toString();
    }

    // 2. 개별 유저에게 보낼 최종 HTML (유저마다 호출)
    private String buildHtml(String userName, String body, String resTitle, String resBody) {
        return """
    <div style="background: linear-gradient(160deg, #1a1535, #2d1f4e, #4a2060, #7a3070); padding: 40px 20px; font-family: Arial, sans-serif;">
      <div style="max-width: 560px; margin: auto;">
        <h1 style="color: white; font-weight: 300; text-align: center; margin-bottom: 4px;">다짐 캡슐이 열렸어요</h1>
        <p style="color: rgba(255,255,255,0.4); text-align: center; font-size: 13px;">%s 님께 전하는 이야기</p>

        <div style="background: rgba(255,255,255,0.05); border: 1px solid rgba(255,255,255,0.1); border-radius: 16px; padding: 24px 28px; margin-top: 24px;">
          <p style="font-size: 15px; color: rgba(255,255,255,0.9); line-height: 2; margin: 0 0 12px; font-weight: 300;">
            긴 시간을 건너, 드디어 당신에게 캡슐이 도착했습니다. 🌙
          </p>
          <p style="font-size: 13px; color: rgba(255,255,255,0.55); line-height: 2; margin: 0 0 12px;">
            그 시절의 추억, 간절했던 소망, 두근거리던 설렘, 그리고 다짐들이 오랜 잠에서 깨어났어요.
            캡슐을 닫던 그 순간, 당신은 어떤 마음이었을까요?
          </p>
          <p style="font-size: 13px; color: rgba(255,255,255,0.55); line-height: 2; margin: 0;">
            당신의 소중한 친구, 사랑하는 사람, 함께했던 이들이 그날 어떤 이야기를 남겼는지,
            지금부터 함께 열어볼까요 ✉️
          </p>
        </div>

        <div style="background: rgba(255,255,255,0.07); border: 1px solid rgba(255,255,255,0.12); border-radius: 20px; padding: 28px; margin-top: 16px;">
          <p style="font-size: 11px; color: rgba(255,255,255,0.35); letter-spacing: 0.08em; margin: 0 0 14px;">모두의 다짐</p>
          %s
          <div style="margin-top: 24px; padding-top: 20px; border-top: 1px solid rgba(255,255,255,0.08);">
            <p style="font-size: 11px; color: rgba(255,255,255,0.35); margin: 0 0 10px;">%s</p>
            <div style="background: rgba(140,100,200,0.15); border: 1px solid rgba(140,100,200,0.25); border-radius: 12px; padding: 16px 18px;">
              <p style="font-size: 13px; color: rgba(200,180,255,0.8); line-height: 1.8; margin: 0;">%s</p>
            </div>
          </div>
        </div>

        <p style="color: rgba(255,255,255,0.2); text-align: center; font-size: 11px; margin-top: 20px;">다짐캡슐 · 미래의 나에게 보내는 편지</p>
      </div>
    </div>
""".formatted(userName, body, resTitle, resBody);
    }
}

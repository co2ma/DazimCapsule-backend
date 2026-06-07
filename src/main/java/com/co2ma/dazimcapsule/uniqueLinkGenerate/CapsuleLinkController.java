package com.co2ma.dazimcapsule.uniqueLinkGenerate;

import com.co2ma.dazimcapsule.capsule.CapsuleEntry;
import com.co2ma.dazimcapsule.capsule.CapsuleEntryService;
import com.co2ma.dazimcapsule.scheduler.EmailSendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name="Link_Generator", description = "고유 링크를 생성합니다.")
@RestController
@RequestMapping("/makeForm")
@RequiredArgsConstructor
public class CapsuleLinkController {

    private final CapsuleLinkService capsuleLinkService;

    @PostMapping
    @Operation(summary="Create_Form", description = "캡슐을 만들 수 있는 고유한 링크를 생성합니다.")
    ResponseEntity<?> makeForm(@RequestBody CapsuleLinkRequestDTO capsuleLinkRequestDTO) {
        try{
            //System.out.println(capsuleLinkRequestDTO.getUniqueLink() + " " + capsuleLinkRequestDTO.getExpirationDate());
            capsuleLinkService.createUniqueLink(capsuleLinkRequestDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("캡슐 저장 중 오류가 발생했습니다.");
        }
    }
}

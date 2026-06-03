package com.co2ma.dazimcapsule.capsule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="캡슐", description = "캡슐 전송 데이터")
@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class CapsuleEntryController {

    private final CapsuleEntryService capsuleEntryService;

    @Operation(summary = "링크 valid 검사", description = "링크가 유효한지 아닌지 반환 해줍니다.")
    @GetMapping("/{link}")
    public ResponseEntity<CapsuleEntry> getCapsule(@PathVariable("link") String link) {
        // 아직 미완성 get 명령 확인만 완료
        System.out.println("Get 명령 확인: " + link);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "캡슐 전송하기", description = "캡슐을 DB로 전송 합니다.")
    @PostMapping("/{link}")
    public ResponseEntity<String> uploadCapsule(@PathVariable("link") String link,
                                                @Valid @RequestBody CapsuleEntryRequestDTO capsuleEntryRequestDTO) {
        System.out.println("===== Controller 진입 =====");
        System.out.println("link: " + link);
        try{
            capsuleEntryRequestDTO.setUniqueLink(link);
            capsuleEntryService.createCapsule(capsuleEntryRequestDTO);
            return ResponseEntity.ok().body("캡슐 등록 완료");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("캡슐 저장 중 오류가 발생했습니다.");
        }

    }
}

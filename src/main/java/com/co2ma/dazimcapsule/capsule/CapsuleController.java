package com.co2ma.dazimcapsule.capsule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name="캡슐", description = "캡슐 전송 데이터")
@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class CapsuleController {

    private final CapsuleService capsuleService;

    @Operation(summary = "캡슐 전송하기", description = "캡슐을 DB로 전송 합니다.")
    @PostMapping("/{link}")
    public ResponseEntity<CapsuleRequestDTO> uploadCapsule(@PathVariable("link") String link,
                                                            @RequestParam("date") LocalDate date,
                                                            @RequestBody CapsuleRequestDTO capsuleRequestDTO) {
        capsuleRequestDTO.setUniqueLink(link);
        capsuleRequestDTO.setExpirationDate(date);
        capsuleService.createCapsule(capsuleRequestDTO);
        return ResponseEntity.ok(capsuleRequestDTO);
    }
}

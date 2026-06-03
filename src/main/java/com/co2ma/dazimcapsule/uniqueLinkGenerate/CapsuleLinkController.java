package com.co2ma.dazimcapsule.uniqueLinkGenerate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/makeForm")
@RequiredArgsConstructor
public class CapsuleLinkController {

    private final CapsuleLinkService capsuleLinkService;

    @PostMapping
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

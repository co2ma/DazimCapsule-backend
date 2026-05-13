package com.co2ma.dazimcapsule.capsule;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/success")
public class CapsuleController {
    private final CapsuleService capsuleService;

    @PostMapping
    public ResponseEntity<CapsuleResponseDTO>  create(@RequestBody CapsuleRequestDTO request){
        CapsuleResponseDTO response = capsuleService.createCapsule(request);
        return ResponseEntity.ok(response);
    }

}

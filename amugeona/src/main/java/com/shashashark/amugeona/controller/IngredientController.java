package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.IngredientDto;
import com.shashashark.amugeona.model.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@CrossOrigin
public class IngredientController {
    private static final String SUCCESS = "success";
    private final IngredientService ingredientService;

    //재료 칸에는 수정, 삭제가 필요 없을 거 같다는 생각이 듬
    //혹시 모르니까 Service 단에 수정 삭제는 주석처리
    @GetMapping("/list")
    public ResponseEntity<List<IngredientDto>> list() {
        return new ResponseEntity<>(ingredientService.selectAll(), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<IngredientDto> detail(String name) {
        return new ResponseEntity<>(ingredientService.selectOne(name).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<String> write(IngredientDto ingredientDto) {
        ingredientService.writeIngredient(ingredientDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

}

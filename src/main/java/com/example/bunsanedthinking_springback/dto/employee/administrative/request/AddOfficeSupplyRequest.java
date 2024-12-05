package com.example.bunsanedthinking_springback.dto.employee.administrative.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddOfficeSupplyRequest {

    @NotBlank(message = "비품 이름은 필수 입력 항목입니다.")
    @Size(max=20, message = "비품 이름이 20글자를 초과하였습니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "비품 이름은 한글 또는 영문만 입력 가능합니다.")
    private String name;

    @NotBlank(message = "설명은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9.,!?\\s]+$", message = "설명에는 한글, 영문, 숫자, 공백, 일부 특수문자(.,!?)만 입력 가능합니다.")
    private String description;

    @NotNull(message = "현재 재고는 필수 입력 항목입니다.")
    @Positive(message = "현재 재고는 최소 1이어야 합니다.")
    private Integer inventory;

    @NotNull(message = "총 재고는 필수 입력 항목입니다.")
    @Positive(message = "현재 재고는 최소 1이어야 합니다.")
    private Integer total_inventory;

    @NotNull(message = "부서 ID는 필수 입력 항목입니다.")
    @Min(value = 9100, message = "부서 ID는 9100 이상의 숫자여야 합니다.")
    private Integer department_id;
}

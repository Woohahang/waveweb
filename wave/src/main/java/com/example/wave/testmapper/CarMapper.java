package com.example.wave.testmapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper { // 인터페이스로 정의해야 합니다
    CarDTO toDTO(Car car); // 메서드 시그니처만 포함
    Car toEntity(CarDTO carDTO); // 추가적인 매핑 메서드
}
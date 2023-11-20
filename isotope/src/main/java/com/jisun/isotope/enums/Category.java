package com.jisun.isotope.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum Category {
    PAINT("paint","페인트"),
    PHOTO("photo","포토"),
    SKETCH("sketch","스케치");

    private final String label;
    private final String kor;

}

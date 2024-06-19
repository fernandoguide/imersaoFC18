package br.com.fernandoguide.imersaofc18.domain;

import lombok.Getter;

@Getter
public enum Rating {
    LIVRE("L"),
    L10("L10"),
    L12("L12"),
    L14("L14"),
    L16("L16"),
    L18("L18");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

}

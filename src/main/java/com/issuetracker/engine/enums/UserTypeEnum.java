package com.issuetracker.engine.enums;

import lombok.Getter;

@Getter

public enum UserTypeEnum {

    PROFESSOR(1L),
    ADMINISTRADOR(2L),
    ALUNO(3L);

    private Long id;

    UserTypeEnum(long id){
        this.id = id;
    }

}

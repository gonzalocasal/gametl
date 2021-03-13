package com.gametl.common.model;

public interface Game<T extends Play> {
    void addPlay(T play);
}

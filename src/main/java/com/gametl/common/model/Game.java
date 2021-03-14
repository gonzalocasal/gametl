package com.gametl.common.model;

/**
 * For any Game.
 */
public interface Game<T extends Play> {
    void addPlay(T play);
}

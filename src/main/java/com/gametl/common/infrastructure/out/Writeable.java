package com.gametl.common.infrastructure.out;

import java.util.List;

/**
 * For any flat files lines generator.
 */
public interface Writeable<T> {

    List<String> getObjectLines(T object);

}

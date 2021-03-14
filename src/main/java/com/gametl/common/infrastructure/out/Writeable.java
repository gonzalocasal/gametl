package com.gametl.common.infrastructure.out;

import java.util.List;

public interface Writeable<T> {

    List<String> getObjectLines(T object);

}

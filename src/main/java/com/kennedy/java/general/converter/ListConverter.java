package com.kennedy.java.general.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListConverter {
    public static <$SOURCE, $TARGET> List<$TARGET> convert(List<$SOURCE> source, Function<$SOURCE, $TARGET> convert) {
    	return source.stream().filter(src -> src != null).map(convert).collect(Collectors.toList());
    }
}

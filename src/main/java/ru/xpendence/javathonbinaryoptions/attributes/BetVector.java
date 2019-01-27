package ru.xpendence.javathonbinaryoptions.attributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:22
 * e-mail: 2262288@gmail.com
 */
public enum BetVector {
    UP(0),
    DOWN(1),
    DRAW(2);

    private Integer id;
    private static final Random RANDOM = new Random();
    private static final List<BetVector> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    BetVector(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Integer randomVector() {
        return VALUES.get(RANDOM.nextInt(SIZE)).id;
    }
}

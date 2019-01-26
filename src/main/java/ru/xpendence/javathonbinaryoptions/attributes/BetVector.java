package ru.xpendence.javathonbinaryoptions.attributes;

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

    BetVector(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

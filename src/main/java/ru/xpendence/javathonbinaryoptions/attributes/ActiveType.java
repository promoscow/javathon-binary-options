package ru.xpendence.javathonbinaryoptions.attributes;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:39
 * e-mail: 2262288@gmail.com
 */
public enum ActiveType {
    DISABLED(0),
    ENABLED(1);

    private Integer id;

    ActiveType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

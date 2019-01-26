package ru.xpendence.javathonbinaryoptions.entity;

import ru.xpendence.javathonbinaryoptions.attributes.ActiveType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:37
 * e-mail: 2262288@gmail.com
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private ActiveType active = ActiveType.ENABLED;

    @Id
    public Long getId() {
        return id;
    }

    @Column(name = "created", updatable = false)
    private LocalDateTime getCreated() {
        return created;
    }

    @Column(name = "updated", insertable = false)
    public LocalDateTime getUpdated() {
        return updated;
    }

    @PrePersist
    void onCreate() {
        if (Objects.isNull(this.getCreated())) {
            this.setCreated(LocalDateTime.now());
        }
    }

    @PreUpdate
    void onUpdate() {
        this.setUpdated(LocalDateTime.now());
    }

    @Column(name = "active")
    public ActiveType getActive() {
        return active;
    }

    public void setActive(ActiveType active) {
        this.active = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private void setCreated(LocalDateTime created) {
        this.created = created;
    }

    private void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}

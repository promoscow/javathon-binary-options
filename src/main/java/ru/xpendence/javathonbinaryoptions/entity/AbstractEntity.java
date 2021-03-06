package ru.xpendence.javathonbinaryoptions.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private ActiveType active = ActiveType.ENABLED;

    public AbstractEntity(Long id, LocalDateTime created, LocalDateTime updated, ActiveType active) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.active = active;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "created", updatable = false)
    public LocalDateTime getCreated() {
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
}

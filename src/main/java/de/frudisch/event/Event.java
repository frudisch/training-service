package de.frudisch.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Serdeable
@Introspected
@MappedEntity
public record Event(
        @Id @Nullable @AutoPopulated UUID id,
        @JsonFormat(pattern="yyyy-MM-dd") LocalDate date,
        @JsonFormat(pattern="HH:mm") LocalTime start,
        @JsonFormat(pattern="HH:mm") LocalTime end,
        @Nullable String enemy) {
}

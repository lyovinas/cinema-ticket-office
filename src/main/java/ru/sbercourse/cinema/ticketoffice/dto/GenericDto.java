package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class GenericDto {

  @Schema(description = "Идентификатор записи", accessMode = Schema.AccessMode.READ_ONLY)
  protected Long id;

  @Schema(description = "Дата/время создания записи", accessMode = Schema.AccessMode.READ_ONLY)
  protected LocalDateTime createdWhen;

  @Schema(description = "Пользователь, создавший запись", example = "DEFAULT_USER",
          accessMode = Schema.AccessMode.READ_ONLY)
  protected String createdBy;
}

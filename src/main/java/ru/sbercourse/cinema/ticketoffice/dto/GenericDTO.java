package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class GenericDTO {

  @Schema(description = "Идентификатор записи", accessMode = Schema.AccessMode.READ_ONLY)
  protected Long id;

  @Schema(description = "Дата/время создания записи", accessMode = Schema.AccessMode.READ_ONLY)
  protected LocalDateTime createdWhen;

  @Schema(description = "Пользователь, создавший запись", example = "DEFAULT_USER",
          accessMode = Schema.AccessMode.READ_ONLY)
  protected String createdBy;

  @Schema(description = "Флаг софт удаления записи",
          accessMode = Schema.AccessMode.READ_ONLY)
  protected boolean isDeleted = false;

  @Schema(description = "Дата/время софт удаления записи",
          accessMode = Schema.AccessMode.READ_ONLY)
  protected LocalDateTime deletedWhen;

  @Schema(description = "Пользователь, пометивший запись на удаление", example = "DEFAULT_USER",
          accessMode = Schema.AccessMode.READ_ONLY)
  protected String deletedBy;

  @Schema(description = "Дата/время изменения записи",
          accessMode = Schema.AccessMode.READ_ONLY)
  protected LocalDateTime updatedWhen;

  @Schema(description = "Пользователь, изменивший запись", example = "DEFAULT_USER",
          accessMode = Schema.AccessMode.READ_ONLY)
  protected String updatedBy;
}

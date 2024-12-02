package pro.nazirka.selmag.manager.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewProductPayload(
        @NotNull(message = "{catalog.products.create.errors.title_is_null}")
        @Size(min = 3, max = 30, message = "{catalog.products.create.errors.title_size_is_invalid}")
        String title,

        @Size(max = 1024, message = "{catalog.products.create.errors.details_size_is_null}")
        String details) {
}

package pro.nazirka.selmag.catalog.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductPayload(
        @NotNull(message = "{catalog.products.update.errors.title_is_null}")
        @Size(min = 3, max = 30, message = "{catalog.products.update.errors.title_size_is_invalid}")
        String title,

        @Size(max = 1024, message = "{catalog.products.update.errors.details_size_is_null}")
        String details) {
}

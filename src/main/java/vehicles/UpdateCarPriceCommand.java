package vehicles;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarPriceCommand {

    @Schema(description = "Update price tag for a new one", example = "1900000")
    private int price;
}

package ru.practicum.explorewithme.model.category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    @Min(0)
    private long id;
    @NotNull
    @Length(min = 5, max = 120)
    private String name;
}

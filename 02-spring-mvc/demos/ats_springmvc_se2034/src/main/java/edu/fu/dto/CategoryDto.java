package edu.fu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String categoryName;
    private List<SkillDto> skills;

    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }
}

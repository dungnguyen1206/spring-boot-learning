package edu.fu.dao;

import edu.fu.dto.CategoryDto;
import edu.fu.dto.SkillDto;

import java.util.List;
import java.util.Set;

public interface JobSkillRepository {

    List<SkillDto> getAllSkillsByCategoryName(String categoryName);

    List<String> getAllCategoryNames();

}

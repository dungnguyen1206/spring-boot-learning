package edu.fu.service;

import edu.fu.dao.JobSkillRepository;
import edu.fu.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {
    private final JobSkillRepository jobSkillRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
       List<String> categories = jobSkillRepository.getAllCategoryNames();
       return categories.stream().map(name -> {
           CategoryDto dto = new CategoryDto(name);
           dto.setSkills(jobSkillRepository.getAllSkillsByCategoryName(name));
            return dto;
       }).collect(Collectors.toList());
    }
}

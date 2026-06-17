package edu.fu.dao;

import edu.fu.dto.CategoryDto;
import edu.fu.dto.SkillDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class JobSkillRepositoryImpl implements JobSkillRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<SkillDto> getAllSkillsByCategoryName(String categoryName) {
        Session session = sessionFactory.openSession();
        return session.createQuery("Select new edu.fu.dto.SkillDto(s.id, s.skillName) From Skills s where s.category = :categoryName",SkillDto.class).setParameter("categoryName", categoryName).getResultList();
    }

    @Override
    public List<String> getAllCategoryNames() {
        Session session = sessionFactory.openSession();
        return session.createQuery("Select distinct s.category From Skills s", String.class).getResultList();
    }
}

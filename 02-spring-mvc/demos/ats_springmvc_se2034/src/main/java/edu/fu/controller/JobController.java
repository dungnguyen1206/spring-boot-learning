package edu.fu.controller;

import edu.fu.dto.CategoryDto;
import edu.fu.dto.JobRequest;
import edu.fu.entities.Departments;
import edu.fu.entities.Jobs;
import edu.fu.service.DepartmentService;
import edu.fu.service.JobService;
import edu.fu.service.JobSkillService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller // --> Spring Bean
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private final DepartmentService departmentService;
    private final JobSkillService jobSkillService;

    @GetMapping
    public ModelAndView getAllJobs() {
        //Call JobService
        List<Jobs>  jobs = jobService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jobs/job_management");
        modelAndView.addObject("jobs", jobs);
        return modelAndView;
    }

    @GetMapping("/detail")
    public String jobDetail (Model model) {
        List<Departments> departments = departmentService.findAllDepartments();
        List<CategoryDto> categories = jobSkillService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("jobRequest", new JobRequest());
        model.addAttribute("departments", departments);
        return "jobs/job_detail";
    }

    @PostMapping
    public String saveJob (@ModelAttribute JobRequest jobRequest , RedirectAttributes redirectAttributes) {
            try {
                Jobs job = jobService.save(jobRequest);
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", e.getMessage());
                return  "redirect:/jobs/detail";
            }
            redirectAttributes.addFlashAttribute("message", "Job saved successfully");
        return  "redirect:/jobs";
    }
}

package edu.fu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {
    private String title;
    private String description;
    private String location;
    private String status;
    private double minSalary;
    private double maxSalary;
    private String utmSource;
    private String utmMedia;
    private LocalDateTime deadline;
    private Long departmentId;
    private List<Long> skillIds;
}

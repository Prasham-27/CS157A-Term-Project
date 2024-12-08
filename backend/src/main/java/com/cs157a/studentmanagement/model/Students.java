package com.cs157a.studentmanagement.model;


import com.cs157a.studentmanagement.model.id_class.EnrollmentsId;
import com.cs157a.studentmanagement.utils.enums.AcademicYear;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class Students {

   @OneToOne // CONSTRAINT FOREIGN KEY
   @JoinColumn(name = "user_id", nullable = false, unique = true)
   Users studentId;

   @ManyToOne  // CONSTRAINT FOREIGN KEY
   @JoinColumn(name = "major_id", nullable = false, unique = true)
   Integer majorId;

   @Column(name = "gpa", nullable = false, columnDefinition = "REAL")
   private double gpa;

   @Column(name = "academic_year", nullable = false)
   @Enumerated(EnumType.STRING)
   private AcademicYear academicYear;

   @OneToMany(mappedBy = "enrollment")
   private List<EnrollmentsId> enrollmentsIds;

}

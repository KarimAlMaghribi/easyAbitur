package de.backend.spring.easyAbitur.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "physikLK")
public class PhysikLK extends SchoolSubject{
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
  List<PhysikLK_Exam> physikLK_Exams;
}

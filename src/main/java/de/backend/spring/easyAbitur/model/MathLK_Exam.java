package de.backend.spring.easyAbitur.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "mathLK_Exam")
public class MathLK_Exam extends Exam {
}

package com.prog4.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String number;
}

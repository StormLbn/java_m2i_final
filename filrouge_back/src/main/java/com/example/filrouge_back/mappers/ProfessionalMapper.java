package com.example.filrouge_back.mappers;

import com.example.filrouge_back.entities.Professional;
import com.example.filrouge_back.models.entitydtos.ProfessionalInfoDTO;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

@Mapper
public interface ProfessionalMapper {

    Professional professionalInfoDtoToProfessional(ProfessionalInfoDTO dto);

    ProfessionalInfoDTO professionalToProfessionalInfoDto(Professional professional);
}

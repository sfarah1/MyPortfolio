package com.baeldung.crud.repositories;

import com.baeldung.crud.DTO.ParticipationDTO;

public interface ParticipationRepository {
    public Object findAllParticipation();
    int addParticipation(ParticipationDTO participationDTO);
   boolean deleteParticipation(int participation_id);

}

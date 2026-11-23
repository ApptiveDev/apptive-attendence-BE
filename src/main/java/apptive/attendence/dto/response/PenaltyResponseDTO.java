package apptive.attendence.dto.response;

import java.time.LocalDate;
import java.util.List;

public record PenaltyResponseDTO(
    LocalDate penaltyDate,
    String name,
    List<PenaltyDTO> penalties,
    Integer total
) {}
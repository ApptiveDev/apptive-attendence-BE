package apptive.attendence.dto.response;

import java.util.Date;
import java.util.List;

public record PenaltyResponseDTO(
    Date penaltyDate,
    String name,
    List<PenaltyDTO> penalties,
    Integer total
) {}
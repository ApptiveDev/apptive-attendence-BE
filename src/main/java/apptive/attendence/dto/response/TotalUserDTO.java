package apptive.attendence.dto.response;

import java.util.List;

public record TotalUserDTO (
    List<SimpleUserDTO> attendees,
    List<SimpleUserDTO> nonAttendees
    ) {}

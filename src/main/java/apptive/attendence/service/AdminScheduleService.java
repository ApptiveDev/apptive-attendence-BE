package apptive.attendence.service;

import apptive.attendence.dto.request.ScheduleRequestDTO;
import apptive.attendence.dto.response.ScheduleResponseDTO;
import apptive.attendence.dto.response.SimpleUserDTO;
import apptive.attendence.dto.response.TotalUserDTO;
import apptive.attendence.entity.Attendence;
import apptive.attendence.entity.Schedule;
import apptive.attendence.entity.Users;
import apptive.attendence.repository.AttendenceRepository;
import apptive.attendence.repository.ScheduleRepository;
import apptive.attendence.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminScheduleService {

    private final UsersRepository usersRepository;
    private final ScheduleRepository scheduleRepository;
    private final AttendenceRepository attendenceRepository;

    public ScheduleResponseDTO save(ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = Schedule.builder()
                        .title(scheduleRequestDTO.title())
                        .scheduleDate(scheduleRequestDTO.scheduleDate())
                        .startTime(scheduleRequestDTO.startTime())
                        .endTime(scheduleRequestDTO.endTime())
                        .permissionPenalty(scheduleRequestDTO.permissionPenalty())
                        .nonPermissionPenalty(scheduleRequestDTO.nonPermissionPenalty())
                        .build();
        scheduleRepository.save(schedule);
        return ScheduleResponseDTO.from(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public TotalUserDTO getTotalAttendence(Long scheduleId) {
        List<Users> users = usersRepository.findAll();

        List<SimpleUserDTO> attendees = new ArrayList<>();
        List<SimpleUserDTO> nonAttendees = new ArrayList<>();

        for (Users user : users) {
            Long userId = user.getId();

            SimpleUserDTO simpleUserDTO = new SimpleUserDTO(
                    userId, user.getName()
            );

            Attendence current = attendenceRepository.findByUsersIdAndScheduleId(userId, scheduleId);
            if (attendenceRepository.countByUsersId(userId) > 0 && current.getAttendence()) {
                attendees.add(simpleUserDTO);
            } else {
                nonAttendees.add(simpleUserDTO);
            }
        }

        TotalUserDTO totalUserDTO = new TotalUserDTO(
                attendees, nonAttendees
        );
        return totalUserDTO;
    }
}

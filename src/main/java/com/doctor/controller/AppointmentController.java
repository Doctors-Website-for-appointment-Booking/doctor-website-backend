package com.doctor.controller;

import com.doctor.dto.AppointmentDto;
import com.doctor.entity.Appointment;
import com.doctor.service.AppointmentService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> bookAppointment(
            @RequestBody AppointmentDto appointmentDto
    ) throws MessagingException {
        Appointment appointment = appointmentService.createAppointment(appointmentDto);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED); // 201 Created
    }
}

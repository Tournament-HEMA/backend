package controller;

import model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import service.ParticipantService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ParticipantController {
    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public String getString()
    {
        return "First try";
    }

    @GetMapping
    public List<Participant> getParticipants()
    {
        List<Participant> result = new ArrayList<Participant>();
        result.add(new Participant(1,"name"));
        return result;
    }
}

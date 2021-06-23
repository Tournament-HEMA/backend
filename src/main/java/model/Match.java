package model;
import lombok.Data;
import lombok.Value;

@Data
@Value
public class Match {
    int id;
    int participantId;
    int score;
}

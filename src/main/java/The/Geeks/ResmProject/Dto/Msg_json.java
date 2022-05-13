package The.Geeks.ResmProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@With

public class Msg_json {

    public Msg_json(String string) {
    }
    AuthorizationInfo authorizationInfo;
    Integer socketId;
    Data data;

    
}

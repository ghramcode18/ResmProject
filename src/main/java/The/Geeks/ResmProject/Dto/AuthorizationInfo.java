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

public class AuthorizationInfo {
    private String username;
    private String permission;
}
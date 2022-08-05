package The.Geeks.ResmProject.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import The.Geeks.ResmProject.message.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Builder
public class ViewProfile {
    boolean successful;
    String error;
    ProfileInfo profileInfo = new ProfileInfo();
}

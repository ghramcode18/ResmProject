package The.Geeks.ResmProject.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import The.Geeks.ResmProject.message.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Setter
@Getter
public class SearchResponce {
    boolean successful;
    String error;
   public ResponseInfo responseInfo = new ResponseInfo();

}

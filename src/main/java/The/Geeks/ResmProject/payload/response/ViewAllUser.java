package The.Geeks.ResmProject.payload.response;

import java.util.ArrayList;
import java.util.List;

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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ViewAllUser {
    ResponseMessage responseMessage = new ResponseMessage();
    List<AllUserInfo> allUserInfo = new ArrayList<AllUserInfo>();
}

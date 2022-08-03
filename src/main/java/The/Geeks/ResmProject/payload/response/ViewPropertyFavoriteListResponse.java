package The.Geeks.ResmProject.payload.response;

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
// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class ViewPropertyFavoriteListResponse {
    ResponseMessage responseMessage = new ResponseMessage();
    ResponseInfo responseInfo = new ResponseInfo();
}


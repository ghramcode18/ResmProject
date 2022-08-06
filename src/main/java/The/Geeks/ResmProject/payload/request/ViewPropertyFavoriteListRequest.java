package The.Geeks.ResmProject.payload.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.payload.request.Pagintion;
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
public class ViewPropertyFavoriteListRequest {
    String token;
    Pagintion pagintion;
}

package The.Geeks.ResmProject.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "chatsMessage") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long chatMessageId;
    String message;
    Date dateSent;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "userID" , nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "chatID" , nullable = false)
    private Chat chat;
}

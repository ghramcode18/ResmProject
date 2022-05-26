package The.Geeks.ResmProject.domain;

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

@Entity @Table(name = "tagsComment") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class TagComment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long tagComment;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "tagID" , nullable = false)
    private Tag tag;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "commentID" , nullable = false)
    private Comment comment;
}

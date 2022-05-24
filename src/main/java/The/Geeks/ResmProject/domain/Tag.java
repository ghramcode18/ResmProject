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

@Entity @Table(name = "tags") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long tagId;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "taggerUserinfoID" , nullable = false)
    private User taggerUserinfo;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "taggedUserinfoID" , nullable = false)
    private User taggedUserinfo;
}

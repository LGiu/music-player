package br.com.listenermusic.model;

import br.com.global.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document()
public class Music implements Model<Music> {

    @Id
    private String id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private Binary file;

    @NotNull
    @NotEmpty
    private Long size;

    @NotNull
    @NotEmpty
    private String idCustomer;

}
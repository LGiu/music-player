package br.com.musicupload.api.DTO;

import br.com.global.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicDTO implements Model<MusicDTO> {

    private String id;

    private String name;

    private MultipartFile file;

    private Long size;

    @NotNull
    @NotEmpty
    private String idCustomer;

}
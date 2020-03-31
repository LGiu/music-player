package br.com.musicupload.api.controller;


import br.com.global.Util.Return;
import br.com.musicupload.api.DTO.MusicDTO;
import br.com.musicupload.api.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MusicController {

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.topic.requestreply-topic}")
    String requestReplyTopic;

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping(value = "/music-upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Return> uplodaMusic(@RequestParam("file") MultipartFile file,
                                               @RequestParam("idCustomer") String idCustomer) {
        MusicDTO musicDTO = new MusicDTO();
        if(file != null){
            musicDTO.setName(file.getName());
            musicDTO.setFile(file);
            musicDTO.setSize(file.getSize());
            musicDTO.setIdCustomer(idCustomer);
        }

        return new ResponseEntity<>(musicService.execute(musicDTO, requestTopic, requestReplyTopic), HttpStatus.OK);

    }

}
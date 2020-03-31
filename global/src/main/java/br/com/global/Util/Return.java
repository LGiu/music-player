package br.com.global.Util;



import br.com.global.model.Model;
import lombok.Data;

import java.util.List;

@Data
public class Return<U extends Model> {


    public Return() {
        this.error = false;
    }

    public Return(String mensagem) {
        this.message = mensagem;
        this.error = true;
    }

    public Return(List<String> mensagens) {
        this.message = String.join(";", mensagens);
        this.error = true;
    }


    public Return(U clazz) {
        this.idSave = clazz.getId();
        this.error = false;
    }

    public Return(String id, boolean error) {
        this.idDelete = id;
        this.error = false;
    }

    private String idSave;

    private String idDelete;

    private String message;

    private boolean error;


}

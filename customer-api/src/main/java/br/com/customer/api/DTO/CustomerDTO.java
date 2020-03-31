package br.com.customer.api.DTO;

import br.com.global.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Model<CustomerDTO> {

    private String id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String country;

    @NotNull
    @NotEmpty
    private String musicStyle;

}
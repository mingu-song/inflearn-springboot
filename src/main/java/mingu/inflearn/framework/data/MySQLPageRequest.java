package mingu.inflearn.framework.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MySQLPageRequest {

    private int page;
    private int size;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private int limit;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private int offset;
}

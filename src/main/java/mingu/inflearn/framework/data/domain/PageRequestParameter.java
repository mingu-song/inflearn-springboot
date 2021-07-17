package mingu.inflearn.framework.data.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageRequestParameter<T> {

    private MySQLPageRequest pageRequest;
    private T parameter;
}

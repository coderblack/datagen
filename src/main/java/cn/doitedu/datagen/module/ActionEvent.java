package cn.doitedu.datagen.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionEvent {

    private String eventId;
    private List<Map<String,String>> properties;

}

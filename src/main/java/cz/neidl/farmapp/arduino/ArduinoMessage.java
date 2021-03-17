package cz.neidl.farmapp.arduino;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArduinoMessage {
    public String type;
}

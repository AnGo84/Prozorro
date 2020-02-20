package ua.prozorro.source.nburate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.prozorro.source.ContentData;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NBURatesPageDTO {
    private ContentData data;
    private List<NBURateDTO> nbuRates;
}

package be.kdg.programming3.spaceMissions.presentation.converter;

import be.kdg.programming3.spaceMissions.domain.MissionType;
import org.springframework.core.convert.converter.Converter;

public class StringToMissionTypeConverter implements Converter<String, MissionType> {

    @Override
    public MissionType convert(String source) {
        return MissionType.valueOf(source.toUpperCase());
    }

}
